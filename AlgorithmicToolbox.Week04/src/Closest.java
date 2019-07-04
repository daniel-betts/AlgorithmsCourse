import java.io.*;
import java.util.*;
import java.util.stream.Collectors;

import static java.lang.Math.*;

/*
 * Task:
 * Given n points on a plane, find the smallest distance between a pair of two (different)
 * points. Recall that the distance between points (x1, y1) and (x2, y2) is equal to
 * sqrt((x1 - x2)^2 + (y1 - y2)^2)
 * 
 * Input: 		The first line contains the number n of points. Each of the following n
 * 				lines defines a point (xi, yi).
 * Constraints:	2 <= n <= 10^5;
 * 				-10^9 < xi, yi <= 10^9 are integers.
 * Output: 		Output the minimum distance. The absolute value of the difference between
 * 				the answer of your program and the optimal value should be at most 10 3. To
 * 				ensure this, output your answer with at least four digits after the decimal
 * 				point (otherwise your answer, while being computed correctly, can turn out
 * 				to be wrong because of rounding issues).
 * 
 * Sample 1
 * 	Input:	    2
 * 				0 0
 * 				3 4
 * 	Output:     5.0
 *				There are only two points here. The distance between them is 5.
 *
 * Sample 2
 * 	Input:	    4
 * 				7 7
 * 				1 100
 * 				4 8
 * 				7 7
 * 	Output:     0.0
 *				There are two coinciding points among the four given points. Thus, the minimum
 *				distance is zero.
 *
 * Sample 3
 * 	Input:	    11 
 * 				 4  4 
 * 				-2 -2
 * 				-3 -4 
 * 				-1  3 
 * 				 2  3 
 * 				-4  0 
 * 				 1  1 
 * 				-1 -1 
 * 				 3 -1 
 * 				-4  2 
 * 				-2  4
 * 	Output:     1.414213
 *				The smallest distance is sqrt(2). There are two pairs of points at this distance:
 *				(1, 1) and (2, 2); (2, 4) and (1, 3).
 */
public class Closest {

    static class Point implements Comparable<Point> {
        int id;
    		long x, y;
        
        public Point(int id, long x, long y) {
            this.id = id;
        		this.x = x;
            this.y = y;
        }

        @Override
        public int compareTo(Point o) {
            return o.y == y ? Long.signum(x - o.x) : Long.signum(y - o.y);
        }
        
        @Override
        public boolean equals(Object obj) {
        		if (!(obj instanceof Closest.Point)) {
        			return false;
        		}
        		Point p = (Point) obj;
        		return p.x == this.x  && p.y == p.y;
        }
        
        @Override
        public String toString() {
        	return "id: " + this.id + "| (" + this.x + ","  + this.y+ ")";
        }
    }
    
    private static List<Point> xSorted;
	private static List<Point> ySorted;
    
    static void setup(int[] x, int y[]) {
    		int n = x.length;
    		xSorted = new ArrayList<>();
        ySorted = new ArrayList<>();
        
        for (int i = 0; i < n; i++) {
        		// Create a new point (with an id)
        		Point point = new Point(n, x[i], y[i]);
        		// Add the point to both of our Point arrays.
        		xSorted.add(point);
        		ySorted.add(point);
        }
        // Sort the arrays respectively by x and y coordinates.
        xSorted.sort((p1, p2) -> Long.signum(p1.x - p2.x));
        ySorted.sort((p1, p2) -> Long.signum(p1.y - p2.y));
    }
    
    // Check if we have a duplicated point.
    static boolean hasDuplicate() {
    		// Since we are guaranteed a minimum of 2 points, set the first
    		// point and start the loop on index 1;
    		Point prev = xSorted.get(0);
    		Point curr;
    		for (int i = 1; i < xSorted.size(); i++) {
    			curr = xSorted.get(i);
    			if (curr.equals(prev)) {
    				return true;
    			}
    		}
    		return false;
    }
    
    static double calcDistance(Point p1, Point p2) {
    		return Math.sqrt(p1.x * p2.x + p1.y * p2.y);
    }
    
    private static double getMinDistance(List<Point> points) {
    		return 0.0;
    }
    
    static double getDistance(List<Point> points, boolean isX) {
    		if (points.size() == 2) {
    			System.out.println(points.get(0));
    			return calcDistance(points.get(0), points.get(1));
    		}
    		int median = points.size() / 2;
    		long line = points.get(median).x;
    		double leftDistance = getMinDistance(points.subList(0, median));
    		double rightDistance = getMinDistance(points.subList(median, points.size()));
    		double min = Math.min(leftDistance, rightDistance);
    		
    		
    		
    		
    		return 0d;
    }
    
    static double minimalDistance(int[] x, int y[]) {
    		// If there is a duplicated point the minimum distance is 0;
    		if (hasDuplicate()) {
    			return 0d;
    		}
    		
    		getDistance(xSorted, true);
    		
        double ans = Double.POSITIVE_INFINITY;
        //write your code here
        return ans;
    }

    public static void main(String[] args) throws Exception {
        reader = new BufferedReader(new InputStreamReader(System.in));
        writer = new PrintWriter(System.out);
        int n = nextInt();
        int[] x = new int[n];
        int[] y = new int[n];
        for (int i = 0; i < n; i++) {
            x[i] = nextInt();
            y[i] = nextInt();
        }
        setup(x, y);
        System.out.println(minimalDistance(x, y));
        writer.close();
    }

    static BufferedReader reader;
    static PrintWriter writer;
    static StringTokenizer tok = new StringTokenizer("");


    static String next() {
        while (!tok.hasMoreTokens()) {
            String w = null;
            try {
                w = reader.readLine();
            } catch (Exception e) {
                e.printStackTrace();
            }
            if (w == null)
                return null;
            tok = new StringTokenizer(w);
        }
        return tok.nextToken();
    }

    static int nextInt() {
        return Integer.parseInt(next());
    }
}
