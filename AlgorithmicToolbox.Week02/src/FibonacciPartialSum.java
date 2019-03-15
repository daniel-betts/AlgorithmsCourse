import java.util.*;
/*
 * Task:
 * Given two non-negative integers m and n, where m <= n,
 * find the last digit of the sum F(m) + F(m+1) + ···+ F(n).
 * 
 * Input: 		The input consists of a single integer n.
 * Constraints:	0 <= m <= n <= 10^18.
 * Output: 		Output the last digit of F(m) + F(m+1) + · · · + F(n)
 * 
 * Sample 1
 * 	Input	: 3 7
 * 	Output	: 1
 *   F(3) + F(4) + F(5) + F(6) + F(7) = 2 + 3 + 5 + 8 + 13 = 31.
 *    
 * Sample 2
 * 	Input	: 10 10
 * 	Output	: 5
 *    F(10) = 55
 *    
 * Sample 3
 * 	Input	: 10 200
 * 	Output	: 2
 *    F(10) + F(11) +···+ F(200) = 734544867157818093234908902110449296423262
 */
public class FibonacciPartialSum {
	// In the way the exercise is structured, from and to are indexes,
	// and not the list size.
	private static int getFibonacciPartialSumEff(long from, long to) {
		List<Integer> remainderList = findPeriod(to, 10);
		
		if (from == to)
			return remainderList.get((int)(from % (long)remainderList.size()));
		
		long toListSize = to + 1;
		// If the list has the same size as n, return the last digit of the reduced list.
		if (toListSize == remainderList.size()) {
			int sum = 0;
			for(int i = (int) to; i >= (int)from; i--) {
				sum += remainderList.get(i);
			}
			return sum % 10;
		}
		
		remainderList = remainderList.subList(0, remainderList.size() / 2 );
//		System.out.println(remainderList);
		long tailRemainder = toListSize % (long)remainderList.size();
//		System.out.println("tailRemainder: " + tailRemainder);
		long headRemainder =from % (long)remainderList.size();
//		System.out.println("headRemainder: " + headRemainder);
		long body = toListSize / (long)remainderList.size() - from / (long)remainderList.size();
		if (headRemainder > 0)
			body--;
//		System.out.println("body: " + body);
		int listTotal = reduceList(remainderList);
//		System.out.println("listTotal: " + listTotal);
//		System.out.println("List size: " + remainderList.size());
		int sum =  (int)(body * listTotal) % 10;
		if (tailRemainder > 0) {
			for (int i = 0; i < tailRemainder; i++) {
				sum = (sum + remainderList.get(i)) % 10;
			}
		}
		if (headRemainder > 0) {
			for (int i = (int)headRemainder; i < remainderList.size(); i++) {
				sum = (sum + remainderList.get(i)) % 10;
			}
		}
		return sum;
	}
	
	private static int reduceList(List<Integer> list) {
		return list.stream().reduce(0, (v, a) -> v + a) % 10;
	}
	
	private static List<Integer> findPeriod(long n, int m) {
		// The list will contain the sequence of remainders.
		List<Integer> list = new ArrayList<>();
		int previous = 0;
		int current  = 1;
		list.add(0, previous);
		list.add(1, current);

		// This counter is used to keep track of the index of the list.
		// This counter is used to compare the value in a given index
		// with the current remainder that is calculated on the loop.
		// Every time the numbers are different the counter is zeroed
		// out. If the list size is ever double the counter value, it
		// means that we found the period and we can stop computing new
		// Fibonacci numbers and remainders.
		int counter = 0;

		int tmp_previous = 0;
		for (long i = 2; i <= n; ++i) {
			tmp_previous = previous;
			previous = current;
			// Compute the next Fibonacci number.
			current = (tmp_previous + current) % m;
			list.add(current);
			
			// Compare the current Fibonacci number with
			// the number in the counter index.
			// If they are the same it could be we are on
			// the trail of finding the period.
			if (list.get(counter) == current) {
				// Aha! The list size is double the counter size.
				// We found the period!
				if (list.size() == (counter + 1) * 2) {
					break;
				}
				counter++;
			} else {
				counter = 0;
			}
		}

		return list;
	}   
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        long from = scanner.nextLong();
        long to = scanner.nextLong();
        System.out.println(getFibonacciPartialSumEff(from, to));
        scanner.close();
    }
    
//    private static long getFibonacciPartialSumNaive(long from, long to) {
//        long sum = 0;
//
//        long current = 0;
//        long next  = 1;
//
//        for (long i = 0; i <= to; ++i) {
//            if (i >= from) {
//                sum += current;
//            }
//
//            long new_current = next;
//            next = next + current;
//            current = new_current;
//        }
//
//        return sum % 10;
//    }
}

