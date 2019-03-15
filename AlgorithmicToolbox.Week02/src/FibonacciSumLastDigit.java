import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

/*
 * Task:
 * Given an integer n, find the last digit of the sum F(0) + F(1) +···+ F(n)
 * 
 * Input: 		The input consists of a single integer n.
 * Constraints:	0 <= n <= 10^18.
 * Output: 		Output the last digit of F(0) + F(1) + · · · + F(n).
 * 
 * Sample 1
 * 	Input	: 3
 * 	Output	: 4
 *   F(0) + F(1) + F(2) + F(3) = 0 + 1 + 1 + 2 = 4.
 *    
 * Sample 2
 * 	Input	: 100
 * 	Output	: 5
 *    The sum is equal to 927 372 692 193 078 999 175, the last digit is 5.
 */
public class FibonacciSumLastDigit {
	// In the way the exercise is structured, n is the last index number,
	// and not the list size.
	private static int getFibonacciLastDigitEff(long n) {
		if (n <= 1)
			return (int)n;
		
		List<Integer> remainderList = findPeriod(n, 10);

		long nListSize = n + 1;
		// If the list has the same size as n, return the last digit of the reduced list.
		if (nListSize == remainderList.size()) {
			return reduceList(remainderList);
		}
		remainderList = remainderList.subList(0, remainderList.size() / 2 );
//		System.out.println(remainderList);
		long multiple = nListSize / (long)remainderList.size();
//		System.out.println("multiple: " + multiple);
		long remainder = nListSize % (long)remainderList.size();
//		System.out.println("remainder: " + remainder);
		int listTotal = reduceList(remainderList);
//		System.out.println("listTotal: " + listTotal);
//		System.out.println("List size: " + remainderList.size());
		int sum =  (int)(multiple * listTotal) % 10;
		if (remainder > 0) {
			for (int i = 0; i < remainder; i++) {
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
		long n = scanner.nextLong();
		int c = getFibonacciLastDigitEff(n);
		System.out.println(c);
		scanner.close();
	}
	
//	private static long getFibonacciSumNaive(long n) {
//        if (n <= 1)
//            return n;
//
//        long previous = 0;
//        long current  = 1;
//        long sum      = 1;
//
//        for (long i = 0; i < n - 1; ++i) {
//            long tmp_previous = previous;
//            previous = current;
//            current = tmp_previous + current;
//            sum += current;
//        }
//
//        return sum % 10;
//    }
}

