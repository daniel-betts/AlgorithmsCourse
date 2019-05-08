import java.util.Arrays;
import java.util.Scanner;

/*
 * Task:
 * You are given a set of points on a line and a set of segments on a line. The goal is to compute,
 * for each point, the number of segments that contain this point.
 * 
 * Input: 		The first line contains two non-negative integers s and p defining the number of
 * 				segments and the number of points on a line, respectively. The next s lines contain
 * 				two integers ai,bi defining the i-th segment [ai, bi]. The next line contains p
 * 				integers defining points x1, x2, ..., xp.
 * Constraints:	1 <= s, p <= 50000;
 * 				-10^8 <= ai <= bi <= 10^8 for all 0 <= i < s;
 * 				-10^8 <= xj <= 10^8 for all 0 <= j < p.
 * Output: 		Output p non-negative integers k0, k1, ..., kp 1 where ki is the number of segments
 * 				which contain xi. More formally, ki =|{j:aj <= xi <= bj}|
 * 
 * Sample 1
 * 	Input:	    2 3 
 * 				0 5 
 * 				7 10 
 * 				1 6 11
 * 	Output:     1 0 0 
 *				Here, we have two segments and three points. The first point lies only 
 *				in the first segment while the remaining two points are outside of all
 *				the given segments.
 *
 * Sample 2
 * 	Input:	    1 3
 * 				-10 10
 * 				-100 100 0
 *  Output:		0 0 1
 *  
 * Sample 3
 * 	Input:	    3 2
 * 				0 5
 * 				-3 2
 * 				7 10 
 * 				1 6
 *  Output:		2 0
 *  
 *  Sample 4
 * 	Input:	    4 1
 * 				3 5
 * 				2 5
 * 				5 5 
 * 				4 5
 * 				5
 *  Output:		4
 */
public class PointsAndSegments {
	private static int[] sortedStart;
	private static int[] sortedEnd;
	private static int[] points;
	private static int lowestStart;
	private static int highestEnd;

	private static void setup(int[] starts, int[] ends, int[] currPoints) {
		sortedStart = starts;
		Arrays.sort(sortedStart);
		lowestStart = sortedStart[0];
		sortedEnd = ends;
		Arrays.sort(sortedEnd);
		highestEnd = sortedEnd[sortedEnd.length - 1];
		points = currPoints;
	}

	private static int[] fastCountSegments() {
		int[] cnt = new int[points.length];
		int startI, totalStarts, endI, totalEnds;
		for (int i = 0; i < points.length; i++) {
			// If the point is smaller than the lowest starting point
			// or bigger than the highest end point, we know that this
			// point will not be in any range.
			if (points[i] < lowestStart || points[i] > highestEnd) {
				cnt[i] = 0;
				continue;
			}
			
			// Find an index that contains the point through binary search.
			startI = loopSearch(0, sortedStart.length, points[i], true);

			// Find the index of the first number that is greater than the point.
			// Give the function the index we found on the previous line, to speed
			// up the process.
			// The index returned here is equal to the number of segments that start
			// before or at the point.
			totalStarts = firstIndexOfGreaterThan(points[i], startI, true);
			
			// Find an index that contains the highest lowest number before the point.
			// i.e.: if our point is 5, find the index of the highest number that is
			// less than five.
			endI = loopSearch(0, sortedEnd.length, (points[i] - 1), false);
			// If the index is -1 it means that all ranges have an end value higher or equal
			// to our point, so we don't need to subtract ranges that ended before the point.
			if( endI == -1) {
				cnt[i] = totalStarts;
				continue;
			}
			// Find the index of the first number that is equal to or greater than the point.
			// Give the function the index we found on the previous line to speed
			// up the process.
			// The index return is the number of ranges that end before the point.
			totalEnds = firstIndexOfGreaterThan(points[i] - 1, endI++, false);
			
			// Total amount of ranges that contain the point is difference between the
			// amount of ranges that start before or at the point, and the amount of
			// ranges that end before the point.
			cnt[i] = totalStarts - totalEnds;
//			System.out.println("startI: " + startI + " | totalStarts: " + totalStarts + " | endI: " + endI + " | totalEnds: " + totalEnds);
		}
		return cnt;
	}

	private static int firstIndexOfGreaterThan(int x, int startingI, boolean start) {
//		System.out.println("firstIndexOfGreaterThan x: " + x);
//		System.out.println("x: " + x + " | startingI: " + startingI);
		int[] arr = start ? sortedStart : sortedEnd;
		while(startingI < arr.length && arr[startingI] <= x) {
			startingI++;
		}
//		System.out.println("First i bigger than x: " + startingI);
		return startingI;

	}

	static int loopSearch(int left, int right, int x, boolean start) {
//		System.out.println("loopSearch x: " + x);
		int value, median, nearestLowerI = -1;
		int[] arr = start ? sortedStart : sortedEnd;
//		System.out.println("left: " + left + " | right: " + right + " | x: " + x);
		while (right - left > 0) {
			median = left + (right - left) / 2;
			value = arr[median];
			if (value > x) {
//				System.out.println("Value: " + value + " bigger than x: " + x);
				right = median;
			} else if (value < x) {
//				System.out.println("Value: " + value + " smaller than x: " + x);
				left = median + 1;
				nearestLowerI = median;
			} else {
//				System.out.println("Value: " + value + " equal to x: " + x);
				return median;
			}
		}
		return nearestLowerI;
	}
//	private static int[] naiveCountSegments(int[] starts, int[] ends, int[] points) {
//		int[] cnt = new int[points.length];
//		for (int i = 0; i < points.length; i++) {
//			for (int j = 0; j < starts.length; j++) {
//				if (starts[j] <= points[i] && points[i] <= ends[j]) {
//					cnt[i]++;
//				}
//			}
//		}
//		return cnt;
//	}

	public static void main(String[] args) {
		Scanner scanner = new Scanner(System.in);
		int n, m;
		n = scanner.nextInt();
		m = scanner.nextInt();
		int[] starts = new int[n];
		int[] ends = new int[n];
		int[] points = new int[m];
		for (int i = 0; i < n; i++) {
			starts[i] = scanner.nextInt();
			ends[i] = scanner.nextInt();
		}
		for (int i = 0; i < m; i++) {
			points[i] = scanner.nextInt();
		}
		//use fastCountSegments
		setup(starts, ends, points);
		int[] cnt = fastCountSegments();
		for (int x : cnt) {
			System.out.print(x + " ");
		}
		scanner.close();
	}
}
