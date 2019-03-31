import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

/*
 * Task:
 * The goal of this problem is to represent a given positive integer n as a sum of as
 * many pairwise distinct positive integers as possible. That is, to find the maximum
 * k such that n can be written as a1+a2+···+ak where a1,...,ak are positive integers
 * and ai != aj for all 1 <= i < j <= k.
 * 
 * Input: 		The input consists of a single integer n.
 * Constraints:	1 <= n <= 10^9;
 * Output: 		In the first line, output the maximum number k such that n can be
 * 				represented as a sum of k pairwise distinct positive integers. In the
 * 				second line, output k pairwise distinct positive integers that sum up
 * 				to n (if there are many such representations, output any of them).
 * 
 * Sample 1
 * 	Input:		6 
 *  Output:		3
 *  				1 2 3
 *    
 * Sample 2
 * 	Input:		8
 * 	Output:		3
 * 				1 2 5
 * 
 * Sample 3
 * 	Input:		2
 * 	Output:		1
 * 				2
 */
public class DifferentSummands {
    private static List<Integer> optimalSummands(int n) {
        List<Integer> summands = new ArrayList<Integer>();
        int value;
        while ((value = summands.size()) < n) {
        		value++;
        		summands.add(value);
        		n -= value; 
        }
        int lastIndex = summands.size() - 1;
        summands.set(lastIndex, summands.get(lastIndex) + n);
        
        
//        while (n > summands.size()) {
//        		summands = summands.stream().map(i -> i + 1).collect(Collectors.toList());
//        		summands.add(1);
//        		n = n - summands.size();
//        		System.out.println(summands.toString());
//        }
//        summands.set(0, summands.get(0) + n);

        return summands;
    }
    
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        int n = scanner.nextInt();
        List<Integer> summands = optimalSummands(n);
        System.out.println(summands.size());
        for (Integer summand : summands) {
            System.out.print(summand + " ");
        }
        scanner.close();
    }
}

