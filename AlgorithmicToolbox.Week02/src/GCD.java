import java.util.*;

/*
 * Greatest Common Divisor
 */

/*
 * Task:
 * Given two integers a and b, find their greatest common divisor.
 * 
 * Input: 		The two integers a, b are given in the same line separated by space.
 * Constraints:	1 <= a,b <= 2*10^9.
 * Output: 		Output GCD(a, b).
 * 
 * Sample 1
 * 	Input	: 18 35
 * 	Output	: 1
 *    
 * Sample 2
 * 	Input	: 28851538 1183019
 * 	Output	: 17657
 */
public class GCD {
	
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
		System.out.println(gcd_eff(a, b));
		scanner.close();
	}

	//private static int gcd_naive(int a, int b) {
	//int current_gcd = 1;
	//for(int d = 2; d <= a && d <= b; ++d) {
	//  if (a % d == 0 && b % d == 0) {
	//    if (d > current_gcd) {
	//      current_gcd = d;
	//    }
	//  }
	//}
	//
	//return current_gcd;
	//}

}
