import java.util.*;

/*
 * Task:
 * Given an integer n, find the last digit of the nth Fibonacci 
 * number F(n) (that is, F(n) mod 10).
 * 
 * Input: 		The input consists of a single integer n.
 * Constraints:	0 <= n <= 10^7.
 * Output: 		Output the last digit of F(n).
 * 
 * Sample 1
 * 	Input	: 3
 * 	Output	: 2 
 *    F(3) = 2
 *    
 * Sample 2
 * 	Input	: 331
 * 	Output	: 9
 *    F(331) = 668996615388005031531000081241745415306766517246774551964595292186469
 *    
 * Sample 3
 * 	Input	: 327305
 * 	Output	: 5 
 *    F(327305) = 5
 */
public class FibonacciLastDigit {
	private static int getFibonacciLastDigitEff(int n) {
		if (n <= 1)
			return n;

		int previous = 0;
		int current  = 1;
		for (int i = 0; i < n - 1; ++i) {
			int tmp_previous = previous;
			previous = current;
			current = (tmp_previous + current) % 10;
		}

		return current;
	}

	public static void main(String[] args) {
		Scanner scanner = new Scanner(System.in);
		int n = scanner.nextInt();
		int c = getFibonacciLastDigitEff(n);
		System.out.println(c);
		scanner.close();
	}
	
//	private static int getFibonacciLastDigitNaive(int n) {
//        if (n <= 1)
//            return n;
//
//        int previous = 0;
//        int current  = 1;
//
//        for (int i = 0; i < n - 1; ++i) {
//            int tmp_previous = previous;
//            previous = current;
//            current = tmp_previous + current;
//        }
//
//        return current % 10;
//    }
}

