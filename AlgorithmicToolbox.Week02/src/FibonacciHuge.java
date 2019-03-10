import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

/*
 * Task:
 * Given two integers n and m, output F(n) mod m
 * (that is, the remainder of F(n) when divided by m)
 * 
 * Input: 		The two integers a, b are given in the same line separated by space.
 * Constraints:	1 <= n <= 1018, 2 <= m <= 103.
 * Output: 		Output the least common multiple of a and b.
 * 
 * Sample 1
 * 	Input	: 239 1000
 * 	Output	: 161
 *    F(239) mod 1000 = 39679027332006820581608740953902289877834488152161 (mod 1000) = 161
 *    
 * Sample 2
 * 	Input	: 2816213588 239
 * 	Output	: 151
 */
public class FibonacciHuge {
	private static long getFibonacciHugeEff(long n, long m) {
		if (n <= 1)
			return n;

		// The remainderList can have 2 possible 'configurations'
		// a) If n was too small to the m, it was not possible to
		//    to find a period of the remainders, so size == n,
		//    and it will have the remainders of all Fibonacci 
		//    numbers until n.
		// b) n and m were in the correct proportion and a period
		//    was found. So the list will have double the size of
		//    the period.
		List<Integer> remainderList = findPeriod(n, (int)m);

		// If the list has the same size as n, return the last remainder.
		if (n + 1 == remainderList.size()) {
			return remainderList.get((int)n);
		}

		// Otherwise get the period size
		int periodSize = (remainderList.size() / 2);
		// n mod periodSize will give which index number has the
		// remainder of F(n) mod m
		long remainderIndex = n % (long)periodSize;
		return remainderList.get((int)remainderIndex);
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
		long m = scanner.nextLong();
		System.out.println(getFibonacciHugeEff(n, m));
		scanner.close();
	}

	//    private static long getFibonacciHugeNaive(long n, long m) {
	//        if (n <= 1)
	//            return n;
	//
	//        long previous = 0;
	//        long current  = 1;
	//
	//        for (long i = 0; i < n - 1; ++i) {
	//            long tmp_previous = previous;
	//            previous = current;
	//            current = tmp_previous + current;
	//        }
	//
	//        return current % m;
	//    }
}
