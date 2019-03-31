import java.util.ArrayList;
import java.util.Arrays;
import java.util.Comparator;
import java.util.List;
import java.util.Scanner;

/*
 * Task:
 * Given a set of n segments {[a0,b0],[a1,b1],...,[an−1,bn−1]} with integer coordinates
 * on a line, find the minimum number m of points such that each segment contains at
 * least one point. That is, find a set of integers X of the minimum size such that
 * for any segment [ai,bi] there is a point x that belongs to X such that ai <= x <= bi.
 * 
 * Input: 		The first line of the input contains the number n of segments. Each of
 * 				the following n lines contains two integers ai and bi (separated by a
 *              space) defining the coordinates of endpoints of the i-th segment.
 * Constraints:	1 <= n <= 100;
 * 				0 <= ai <= bi < 10^9 for all 0 <= i <= n.
 * Output: 		Output the minimum number m of points on the first line and the integer
 * 				coordinates of m points (separated by spaces) on the second line. You
 * 				can output the points in any order. If there are many such sets of points,
 * 				you can output any set. (It is not difficult to see that there always exists
 * 				a set of points of the minimum size such that all the coordinates of the
 * 				points are integers.)
 * 
 * Sample 1
 * 	Input:		3 
 * 				1 3
 * 				2 5
 * 				3 6
 *  Output:		1
 *  				3
 *    In this sample, we have three segments: [1,3],[2,5],[3,6] (of length 2,3,3 respectively).
 *    All of them contain the point with coordinate 3: 1 ≤ 3 ≤ 3, 2 ≤ 3 ≤ 5, 3 ≤ 3 ≤ 6. 
 *    
 * Sample 2
 * 	Input:		4
 * 				4 7
 * 				1 3
 * 				2 5
 * 				5 6
 * 	Output:		2
 * 				3 6
 *    The second and the third segments contain the point with coordinate 3 while the first and
 *    the fourth segments contain the point with coordinate 6. All the four segments cannot be
 *    covered by a single point, since the segments [1, 3] and [5, 6] are disjoint.
 */
public class CoveringSegments {

	private static List<Integer> optimalPoints(Segment[] segments) {
		//write your code here
		Arrays.sort(segments, comp);

		List<Integer> points = new ArrayList<>();
		int limit = -1;
		Segment s;
		for (int i = 0; i < segments.length; i++) {
			s = segments[i];
			if (s.start > limit) {
				limit = s.end;
				points.add(limit);
			}
		}
		return points;
	}

	private static Comparator<Segment> comp = new Comparator<CoveringSegments.Segment>() {

		@Override
		public int compare(Segment o1, Segment o2) {
			if (o1.end < o2.end) {
				return -1;
			}
			if (o1.end == o2.end) {
				return 0;
			}
			return 1;
		}
	}; 

	private static class Segment {
		int start, end;

		Segment(int start, int end) {
			this.start = start;
			this.end = end;
		}
	}
	public static void main(String[] args) {
		Scanner scanner = new Scanner(System.in);
		int n = scanner.nextInt();
		Segment[] segments = new Segment[n];
		for (int i = 0; i < n; i++) {
			int start, end;
			start = scanner.nextInt();
			end = scanner.nextInt();
			segments[i] = new Segment(start, end);
		}
		List<Integer> points = optimalPoints(segments);
		System.out.println(points.size());
		for (int point : points) {
			System.out.print(point + " ");
		}
		scanner.close();
	}
}

