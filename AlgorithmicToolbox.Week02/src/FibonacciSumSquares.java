import java.util.*;
/*
 * Task:
 * Compute the last digit of F(0)^2 + F(1)^2 +···+ F(n)^2
 * 
 * Input: 		Integer n.
 * Constraints:	0 <= n <= 10^18.
 * Output: 		The last digit of F(0)^2 + F(1)^2 + ··· + F(n)^2
 * 
 * Sample 1
 * 	Input	: 7
 * 	Output	: 3
 *   F(0)^2 + F(1)^2 +···+ F(7)^2 = 0 + 1 + 1 + 4 + 9 + 25 + 64 + 169 = 273.
 *    
 * Sample 2
 * 	Input	: 73
 * 	Output	: 1
 *    F(0)^2 +···+ F(73)^2 = 1052478208141359608061842155201
 *    
 * Sample 3
 * 	Input	: 1234567890
 * 	Output	: 0
 */
public class FibonacciSumSquares {
	private static long getFibonacciSumSquaresEff(long n) {
        if (n <= 1)
            return n;
        
        List<Integer> remainderList = findPeriod(n, 10);

        int previousValue, remainderValue;
		long nListSize = n + 1;
//		 If the list has the same size as n, return the last digit of the reduced list.
		if (nListSize == remainderList.size()) {
			remainderValue = remainderList.get((int)n);
			previousValue = remainderList.get((int)n -1);
		} else {
			remainderList = remainderList.subList(0, remainderList.size() / 2 );
//			System.out.println(remainderList);
			int remainderIndex = (int)(n % (long)remainderList.size());
//			System.out.println("remainderIndex: " + remainderIndex);
			if (remainderIndex == 0) {
//				System.out.println("is zero ");
				previousValue = remainderList.get(remainderList.size() - 1);
			} else {
				previousValue = remainderList.get(remainderIndex - 1);
			}
			remainderValue = remainderList.get(remainderIndex);
		}
		return remainderValue * (remainderValue + previousValue) % 10;
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
        long s = getFibonacciSumSquaresEff(n);
        System.out.println(s);
        scanner.close();
    }
    
//    private static long getFibonacciSumSquaresNaive(long n) {
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
//            sum += current * current;
//        }
//
//        return sum % 10;
//    }
}

