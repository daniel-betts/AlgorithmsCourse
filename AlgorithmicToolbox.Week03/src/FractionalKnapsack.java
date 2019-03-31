import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Scanner;

/*
 * Task:
 * The goal of this code problem is to implement an algorithm for
 * the fractional knapsack problem.
 *  
 * Input:		The first line of the input contains the number n
 * 				of items and the capacity W of a knapsack. The next
 * 				n lines define the values and weights of the items.
 * 				The i-th line contains integers vi and wi - the value
 * 				and the weight of i-th item, respectively.
 * Constraints:	1 <= n <= 10^3,0 <= W <= 2*10^6; 0 <= vi,wi <= 2*10^6
 * 				for all 1 <= i <= n. All the numbers are integers.
 * Output:		Output the minimum number of coins with denominations 1, 5, 10 that changes m.
 * 
 * Sample 1
 *  Input	: 3 50
 *  			  60 20
 *  			  100 50
 *  			  120 30
 * 	Output	: 180.0000
 * 	  To achieve the value 180, we take the first item and the third item into the bag.
 * 
 * Sample 2
 *  Input	: 1 10
 *  			  500 30
 * 	Output	: 166.6667
 * 	  Here, we just take one third of the only available item.
 */
public class FractionalKnapsack {
    private static double getOptimalValue(int capacity, int[] values, int[] weights) {
        double value = 0;
        //write your code here
        List<Double> unitValues = new ArrayList<>();
        Map<Double, Integer> unitAmounts = new HashMap<>();
        for (int i = 0; i < values.length; i++) {
        		double unitValue = (double) values[i] / (double) weights[i];
        		unitValues.add(unitValue);
        		unitAmounts.put(unitValue, weights[i]);
        }
        Collections.sort(unitValues);
//        Collections.reverse(unitValues);
        int counter = unitValues.size();
        while (capacity > 0 && counter > 0) {
        		counter--;
        		Double highestValue = unitValues.get(counter);
        		int amountAvailable = unitAmounts.get(highestValue);
        		int amountUsed = Math.min(amountAvailable, capacity);
        		value += highestValue * (double) amountUsed;
        		capacity -= amountUsed;
        }
        return value;
    }

    public static void main(String args[]) {
        Scanner scanner = new Scanner(System.in);
        int n = scanner.nextInt();
        int capacity = scanner.nextInt();
        int[] values = new int[n];
        int[] weights = new int[n];
        for (int i = 0; i < n; i++) {
            values[i] = scanner.nextInt();
            weights[i] = scanner.nextInt();
        }
        System.out.println(getOptimalValue(capacity, values, weights));
        scanner.close();
    }
} 
