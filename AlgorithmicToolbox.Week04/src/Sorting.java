import java.io.*;
import java.util.*;

/*
 * Task:
 * To force the given implementation of the quick sort algorithm to efficiently process sequences with
 * few unique elements, your goal is replace a 2-way partition with a 3-way partition. That is, your
 * new partition procedure should partition the array into three parts: < x part, = x part, and > x part.
 * 
 * Input: 		The first line contains an integer n. The next one contains a sequence of n integers
 * 				a0, a1, ... , an-1.
 * Constraints:	1 <= n <= 10^5;
 * 				1 <= ai <= 10^9 for all 0 <= i < n.
 * Output: 		Output this sequence sorted in non-decreasing order.
 * 
 * Sample 1
 * 	Input:	    5 
 * 			    2 3 9 2 2
 * 	Output:     2 2 2 3 9
 */
public class Sorting {
	private static Random random = new Random();
	private static int[] a;

	private static void swap(int indexA, int indexB) {
		int temp = a[indexA];
		a[indexA] = a[indexB];
		a[indexB] = temp;
	}
	private static int[] partition3(int left, int right) {
		int swapLessIndex = left;
		int swapSameIndex = left + 1;
		int value = a[swapLessIndex];
		for (int i = left + 1; i < right; i++) {
			if (a[i] < value) {
				swap(i, swapSameIndex);
				swap(swapSameIndex, swapLessIndex);
				swapSameIndex++;
				swapLessIndex++;
			} else if (a[i] == value) {
				swap(i, swapSameIndex);
				swapSameIndex++;
			}
		}

		int m1 = swapLessIndex;
		int m2 = swapSameIndex;
		int[] m = {m1, m2};
		return m;
	}

	private static void randomizedQuickSort(int l, int r) {
		if (l >= r) {
			return;
		}
		int k = random.nextInt(r - l) + l;
		swap(l,k);
		int[] m = partition3(l, r);
		randomizedQuickSort(l, m[0]);
		randomizedQuickSort(m[1], r);
	}

	public static void main(String[] args) {
		FastScanner scanner = new FastScanner(System.in);
		int n = scanner.nextInt();
		a = new int[n];
		for (int i = 0; i < n; i++) {
			a[i] = scanner.nextInt();
		}

		randomizedQuickSort(0, n);
		for (int i = 0; i < n; i++) {
			System.out.print(a[i] + " ");
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

