import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

/*
 * Task:
 * The goal in this code problem is to implement the binary search algorithm.
 * 
 * Input: 		The first line of the input contains an integer n and a sequence
 * 				a0 < a1 < ... < an−1 of n pairwise distinct positive integers in
 * 				increasing order. The next line contains an integer k and k positive
 * 				integers b0, b1, ..., bk-1.
 * Constraints:	1 <= n, k <= 10^4;
 * 				1 <= ai <= 10^9 for all 0 <= i < n;
 * 				1 <= bj <= 10^9 for all 0 <= j < k;
 * Output: 		For all i from 0 to k−1, output an index 0 <= j <= n-1 such that
 * 				aj = bi or -1 if there is no such index.
 * 
 * Sample 1
 * 	Input:	    5 1 5  8 12 13 
 * 			    5 8 1 23  1 11
 * 	Output:       2 0 -1  0 -1
 *				In this sample, we are given an increasing sequence a0 = 1, a1 = 5,
 *				a2 = 8, a3 = 12, a4 = 13 of length five and five keys to search:
 *				8,1,23,1,11. We see that a2 = 8 and a0 = 1, but the keys 23 and 11
 *				do not appear in the sequence a. For this reason, we output a sequence
 *				2, 0, −1, 0, −1.
 */
public class BinarySearch {
	static int median;
	static int recursiveSearch(int[] a, int x, int left, int right) {
		if(right <= left) {
			return -1;
		}
		median = left + (right - left) / 2;
		int value = a[median];
		if (value > x) {
			return recursiveSearch(a, x, left, median);
		} 
		if (value < x) {
			return recursiveSearch(a, x, median + 1, right);
		}
		return median;
	}
	
    static int binarySearch(int[] a, int x) {
        int left = 0, right = a.length;
        return recursiveSearch(a, x, left, right);
    }

    static int linearSearch(int[] a, int x) {
        for (int i = 0; i < a.length; i++) {
            if (a[i] == x) return i;
        }
        return -1;
    }

    public static void main(String[] args) {
        FastScanner scanner = new FastScanner(System.in);
        int n = scanner.nextInt();
        int[] a = new int[n];
        for (int i = 0; i < n; i++) {
            a[i] = scanner.nextInt();
        }
        int m = scanner.nextInt();
        int[] b = new int[m];
        for (int i = 0; i < m; i++) {
          b[i] = scanner.nextInt();
        }
        for (int i = 0; i < m; i++) {
            //replace with the call to binarySearch when implemented
            System.out.print(binarySearch(a, b[i]) + " ");
        }
    }
    static class FastScanner {
        BufferedReader br;
        StringTokenizer st;

        FastScanner(InputStream stream) {
            try {
                br = new BufferedReader(new InputStreamReader(stream));
            } catch (Exception e) {
                e.printStackTrace();
            }
        }

        String next() {
            while (st == null || !st.hasMoreTokens()) {
                try {
                    st = new StringTokenizer(br.readLine());
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
            return st.nextToken();
        }

        int nextInt() {
            return Integer.parseInt(next());
        }
    }
}
