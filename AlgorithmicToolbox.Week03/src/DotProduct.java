import java.util.*;

/*
 * Task:
 * Given two sequences a1,a2,...,an (ai is the profit per click of the i-th ad)
 * and b1,b2,...,bn (bi is the average number of clicks per day of the i-th slot),
 * we need to partition them into n pairs (ai,bj) such that the sum of their
 * products is maximized.
 * 
 * Input: 		The first line contains an integer n, the second one contains a
 * 				sequence of integers a1,a2,...,an, the third one contains a sequence
 * 				of integers b1,b2,...,bn.
 * Constraints:	1 <= n <= 10^3;
 * 				−10^5 <= ai,bi <= 10^5 for all 1 <= i <=n
 * Output: 		Output the maximum value of the sum of ai*ci for all i, where c1, c2, ..., cn
 * 				is a permutation of b1,b2,...,bn.
 * 
 * Sample 1
 * 	Input	: 1 
 * 			  23 
 * 			  39
 * 	Output	: 897 
 *    897 = 23 * 39
 *    
 * Sample 2
 * 	Input	: 3
 * 			  1 3 -5
 * 			  -2 4 1
 * 	Output	: 23
 *    23 = 3 * 4 + 1 * 1 + (−5) * (−2).
 */
public class DotProduct {
    private static long maxDotProduct(int[] a, int[] b) {
    		Arrays.sort(a);
    		Arrays.sort(b);
        long result = 0;
        for (int i = 0; i < a.length; i++) {
            result += ((long) a[i]) * ((long) b[i]);
        }
        return result;
    }

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        int n = scanner.nextInt();
        int[] a = new int[n];
        for (int i = 0; i < n; i++) {
            a[i] = scanner.nextInt();
        }
        int[] b = new int[n];
        for (int i = 0; i < n; i++) {
            b[i] = scanner.nextInt();
        }
        System.out.println(maxDotProduct(a, b));
        scanner.close();
    }
}

