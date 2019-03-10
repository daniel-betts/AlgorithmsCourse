import java.util.Scanner;

/*
 * Task:
 * Given an integer n, find the nth Fibonacci number F(n).
 * 
 * Input: 		The input consists of a single integer n.
 * Constraints:	0 <= n <= 45.
 * Output: 		Output F(n).
 * 
 * Sample 1
 * 	Input	: 10
 * 	Output	: 55 
 *    F(10) = 55
 */

public class Fibonacci {
	/* 
	 * This function creates an array of size n + 1
	 * and computes all Fibonnaci numbers until the 
	 * nth + 1 number.
	 * Note: the first number is n=0
	 */
	private static long calc_fib_eff(int n) {
		if (n <= 1) {
			return n;
		}
		long[] arr = new long[n + 1];
		arr[0] = 0l;
		arr[1] = 1l;
		for (int i = 2; i <= n; i++){
			arr[i] = arr[i-2] + arr[i-1];
		}
		return arr[n];
	}

	public static void main(String args[]) {
		Scanner in = new Scanner(System.in);
		int n = in.nextInt();

		System.out.println(calc_fib_eff(n));
		in.close();
	}

	/* Recursive implementation */
	//private static long calc_fib(int n) {
	//if (n <= 1)
	//  return n;
	//
	//return calc_fib(n - 1) + calc_fib(n - 2);
	//}
}
