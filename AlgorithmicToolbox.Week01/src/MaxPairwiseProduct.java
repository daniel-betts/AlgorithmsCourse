import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

/*
 * Task:
 * Find the maximum product of two distinct numbers in a
 * sequence of non-negative integers.
 * 
 * Input: 	A sequence of non-negative integers.
 * Output: 	The maximum value that can be obtained by multiplying
 * 			two different elements from the sequence.
 * 
 * Sample 1
 * 	Input	: 1 2 3
 * 	Output	: 6
 * 
 * Sample 2
 * 	 Input	: 7 5 14 2 8 8 10 1 2 3
 *   Output	: 140
 */

public class MaxPairwiseProduct {

	static long getMaxPairwiseProduct(int[] numbers) {
		int biggest = 0;
		int second = 0;
		int current = 0;
		int n = numbers.length;
		for (int i = 0; i < n; ++i) {
			current = numbers[i];
			if (current > second) {
				if (current > biggest) {
					second = biggest;
					biggest = current;
				} else {
					second = current;
				}
			}
		}
		return (long) second * (long) biggest;
	}

	public static void main(String[] args) {
		FastScanner fs = new FastScanner(System.in);
		int n = fs.nextInt();
		int[] numbers = new int[n];
		for (int i = 0; i < n; ++i) {
			numbers[i] = fs.nextInt();
		}

		System.out.println(getMaxPairwiseProduct(numbers)); 

	}

	static class FastScanner {
		BufferedReader br;
		StringTokenizer st;

		FastScanner(InputStream stream){
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
