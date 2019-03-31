import java.util.Scanner;

/*
 * Task:
 * The goal in this problem is to find the minimum number of coins needed
 * to change the input value (an integer) into coins with denominations 1, 5, and 10.
 *  
 * Input:		The input consists of a single integer m.
 * Constraints:	1 <= m <= 10^3.
 * Output:		Output the minimum number of coins with denominations 1, 5, 10 that changes m.
 * 
 * Sample 1
 *  Input	: 2
 * 	Output	: 2
 * 	  1 + 1 = 2
 * 
 * Sample 2
 *  Input	: 28
 * 	Output	: 6
 * 	  28 = 10 + 10 + 5 + 1 + 1 + 1.
 */
public class Change {
	private static int[] DENOMINATIONS = {10, 5, 1}; 
	
    private static int getChange(int m) {
        //write your code here
    		int i = 0;
    		int coins = 0;
    		while (m > 0 && i < DENOMINATIONS.length) {
    			coins += m / DENOMINATIONS[i];
    			m = m % DENOMINATIONS[i];
    			i++;
    		}
        return coins;
    }

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        int m = scanner.nextInt();
        System.out.println(getChange(m));
        scanner.close();
    }
}

