import java.util.*;

/*
 * Task:
 * The goal in this problem is to count the number of inversions of a given sequence.
 * An inversion of a sequence a0,a1,...,an 1 is a pair of indices 0 <= i < j < n such that ai > aj.
 * 
 * Input: 		The first line contains an integer n. The next one contains a sequence of n integers
 * 				a0, a1, ... , an-1.
 * Constraints:	1 <= n <= 10^5;
 * 				1 <= ai <= 10^9 for all 0 <= i < n.
 * Output: 		Output the number of inversions in the sequence.
 * 
 * What to do:  This problem can be solved by modifying the merge sort algorithm.
 * 
 * Sample 1
 * 	Input:	    5 
 * 			    2 3 9 2 9
 * 	Output:     2
 * 		        The two inversions here are (1,3) (a1 = 3 > 2 = a3) and (2,3) (a2 = 9 > 2 = a3).
 */
public class Inversions {
	private static int[] a, b;
	private static long getNumberOfInversions(int left, int right) {
		long numberOfInversions = 0;
		if (right <= left + 1) {
			b[left] = a[left];
			return numberOfInversions;
		}
		int ave = (left + right) / 2;
		numberOfInversions += getNumberOfInversions(left, ave);
		numberOfInversions += getNumberOfInversions(ave, right);
		//write your code here
		int leftIndex = left;
		int rightIndex = ave;
		int bIndex = left;
		while (bIndex < right) {
			if (leftIndex < ave && (rightIndex >= right || a[leftIndex] <= a[rightIndex])) {
				b[bIndex] = a[leftIndex];
				leftIndex++;
			} else {
				b[bIndex] = a[rightIndex];
				rightIndex++;
				numberOfInversions += (ave - leftIndex);
			}
			bIndex++;
		}
		
		for (int i = left; i < right; i++) {
			a[i] = b[i];
		}

		return numberOfInversions;
	}

	public static void main(String[] args) {
		Scanner scanner = new Scanner(System.in);
		int n = scanner.nextInt();
		a = new int[n];
		for (int i = 0; i < n; i++) {
			a[i] = scanner.nextInt();
		}
		b = new int[n];
		System.out.println(getNumberOfInversions(0, a.length));
		scanner.close();
	}
}

