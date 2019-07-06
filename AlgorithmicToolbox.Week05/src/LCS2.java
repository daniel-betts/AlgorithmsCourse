import java.util.*;

/*
 * Task:
 * Given two sequences A = (a1,a2,...,an) and B = (b1,b2,...,bm), and the length of their longest common
 * subsequence, i.e., the largest non-negative integer p such that there exist indices 1 <= i1 < i2 <···<ip <= n
 * and 1 <= j1 < j2 <···< jp <= m, such that ai1 = bj1,..., aip =bjp.
 * 
 * Input: 		First line: n. Second line: a1, a2, ..., an. Third line: m. Fourth line: b1, b2, ..., bm.
 * Constraints:	1 <= n, m <= 100; −10^9 < ai, bi < 10^9.
 * Output: 		Output p.
 * 
 * Sample 1
 * 	Input:	    3
 * 				2 7 5
 * 				2
 * 				2 5
 * 	Output:     2
 * 				A common subsequence of length 2 is (2, 5).
 *
 * Sample 2
 * 	Input:	    1
 * 				7
 * 				4
 * 				1 2 3 4
 * 	Output:     0
 *              The two sequences do not share elements.
 *              
 * Sample 3
 * 	Input:	    4
 * 				2 7 8 3
 * 				4
 * 				5 2 8 7
 * 	Output:     2
 *			    One common subsequence is (2, 7). Another one is (2, 8).
 */
public class LCS2 {

	private static int lcs2(int[] a, int[] b) {
		int[][] matrix = new int[a.length + 1][b.length + 1];
		int insertion, deletion;
		// Add initial values on 0 indices.
		for (int bi = 1; bi <= b.length; bi++) {
			matrix[0][bi] = bi;
		}
		for (int ai = 1; ai <= a.length; ai++) {
			matrix[ai][0] = ai;
		}
		for (int i = 1; i < matrix.length; i++) {
//			System.out.println("");
			for (int j = 1; j < matrix[i].length; j++) {
				// Do stuff to compute values
				if (a[i - 1] == b[j - 1]) {
//				we only allow diagonals when the values match.
					matrix[i][j] = matrix[i - 1][j - 1];
				} else {
//					diagonal = matrix[i - 1][j - 1] + 1;
//				}
				insertion = matrix[i][j - 1] + 1;
				deletion = matrix[i - 1][j] + 1;
//				matrix[i][j] = Math.min(insertion, Math.min(deletion, diagonal));
				matrix[i][j] = Math.min(insertion, deletion);
				}
//				System.out.print(matrix[i][j] + " ");
			}
		}
//		System.out.println("");
		// Do trace back and count movements.
		int tracebackCount = 0;
		int ai = a.length, bi = b.length;
		int currMin, newAi, newBi;
		while (ai > 0 && bi > 0) {
//			System.out.println("(" + ai + "," + bi + ")");
			currMin = matrix[ai][bi];
			newAi = ai;
			newBi = bi;
			if (matrix[ai][bi - 1] <= currMin) {
				newAi = ai;
				newBi = bi - 1;
			}
			if (matrix[ai - 1][bi] <= currMin) {
				newAi = ai - 1;
				newBi = bi;
			}
			// we only allow a diagonal backtrace if it
			// meant that the numbers had matched.
			if (matrix[ai - 1][bi - 1] == matrix[ai][bi]) {
				newAi = ai - 1;
				newBi = bi - 1;
			}
			tracebackCount++;
			ai = newAi;
			bi = newBi;
		}
		tracebackCount += ai + bi;
//		System.out.println("tracebackCount: " + tracebackCount);
		return tracebackCount - matrix[a.length][b.length];
	}

	public static void main(String[] args) {
		Scanner scanner = new Scanner(System.in);
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

		System.out.println(lcs2(a, b));
		scanner.close();
	}
}
