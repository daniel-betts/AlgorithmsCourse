import java.util.Scanner;

/*
 * Task:
 * Assuming that the distance between 2 cities is d miles, a car can travel at
 * most m miles on a full tank, and there are gas stations at distances stop1,
 * stop2, ..., stopn along the way, output the minimum number of refills needed.
 * Assume that the car starts with a full tank. If it is not possible to reach
 * the destination, output −1.
 * 
 * Input: 		The first line contains an integer d. The second line contains
 * 				an integer m. The third line specifies an integer n. Finally,
 * 				the last line contains integers stop1, stop2, ... , stopn.
 * Constraints:	1 <= d <= 10^5
 * 				1 <= m <= 400
 * 				1 <= n <= 300
 * 				0 < stop1 < stop2 < ··· < stopn < d.
 * Output: 		Number of refills or -1.
 * 
 * Sample 1
 * 	Input	: 950
 *			  400
 *			  4
 *			  200 375 550 750
 * 	Output	: 2 
 *    The distance between the cities is 950, the car can travel at most 400 miles
 *    on a full tank. It suffices to make two refills: at points 375 and 750. This
 *    is the minimum number of refills as with a single refill one would only be
 *    able to travel at most 800 miles.
 *    
 * Sample 2
 * 	Input	: 10
 *			  3
 *			  4
 *			  1 2 5 9
 * 	Output	: -1
 *    One cannot reach the gas station at point 9 as the previous gas station is too
 *    far away.
 *    
 * Sample 3
 * 	Input	: 200
 *			  250
 *			  2
 *			  100 150
 * 	Output	: 0
 *    There is no need to re ll the tank as the car starts with a full tank and can 
 *    travel for 250 miles whereas the distance to the destination point is 200 miles.
 */
public class CarFueling {
	static int computeMinRefills(int dist, int tank, int[] stops) {
		int refills = 0;
		if(dist <= tank) {
			return refills;
		}
		int lastRefill = 0;
		int lastGasStation = 0;
		int nDistance;
		for (int n = 0; n < stops.length; n++) {
			nDistance = stops[n];
			// Assert if current gas station is too far.
			if (nDistance > lastGasStation + tank) {
				return -1;
			}
			// Assert if we need to refill to reach this gas station.
			if (nDistance > lastRefill + tank) {
				refills++;
				lastRefill = lastGasStation;
			}
			// Assert if tank is enough to get to dist.
			if (lastRefill + tank >= dist) {
				return refills;
			}
			lastGasStation = nDistance;
		}
		if (lastGasStation + tank >= dist) {
			return ++refills;
		}
		return -1;
	}

	public static void main(String[] args) {
		Scanner scanner = new Scanner(System.in);
		int dist = scanner.nextInt();
		int tank = scanner.nextInt();
		int n = scanner.nextInt();
		int stops[] = new int[n];
		for (int i = 0; i < n; i++) {
			stops[i] = scanner.nextInt();
		}

		System.out.println(computeMinRefills(dist, tank, stops));
		scanner.close();
	}
}
