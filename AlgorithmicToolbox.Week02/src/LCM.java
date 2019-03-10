import java.util.*;

/*
 * Least Common Multiple
 */

/*
 * Task:
 * Given two integers a and b, find their least common multiple.
 * 
 * Input: 		The two integers a, b are given in the same line separated by space.
 * Constraints:	1 <= a,b <= 2*10^9.
 * Output: 		Output the least common multiple of a and b.
 * 
 * Sample 1
 * 	Input	: 6 8
 * 	Output	: 24
 *    
 * Sample 2
 * 	Input	: 28851538 1183019
 * 	Output	: 1933053046
 */
public class LCM {

	private static long lcm_eff(int a, int b) {
		// The least common multiple of 2 integers
		// is the product of both integers divided
		// by the GCD of those 2 integers.
		int gcd = gcd_eff(a, b);
		return (long) a * (long) b / (long) gcd;
	}

	// Recursive function to calculate the GCD
	private static int gcd_eff(int a, int b) {
		// If both numbers are the same, they are their
		// greatest common divisor
		if (a == b) {
			return a;
		}

		int reremainder = a % b;
		// If there is no remainder than b is the GCD.		
		if (reremainder == 0) {
			return b;
		}

		return gcd_eff(b, reremainder);
	}

	public static void main(String args[]) {
		Scanner scanner = new Scanner(System.in);
		int a = scanner.nextInt();
		int b = scanner.nextInt();

		// Make sure a is always greater than b,
		// if it is not we switch the numbers around.
		int temp;
		if (a < b) {
			temp = a;
			a = b;
			b = temp;
		}

		System.out.println(lcm_eff(a, b));
		scanner.close();
	}

	//	private static long lcm_naive(int a, int b) {
	//  for (long l = 1; l <= (long) a * b; ++l)
	//    if (l % a == 0 && l % b == 0)
	//      return l;
	//
	//  return (long) a * b;
	//}
}
