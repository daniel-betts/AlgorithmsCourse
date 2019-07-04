import java.util.Scanner;

/*
 * Task:
 * Your goal is to apply dynamic programming for solving the Money Change Problem 
 * for denominations 1, 3, and 4.
 * 
 * Input: 		Integer money.
 * Constraints:	1 <= money <= 10^3.
 * Output: 		The minimum number of coins with denominations 1, 3, 4 that changes money.
 * 
 * Sample 1
 * 	Input:	    2
 * 	Output:     2
 *				1 + 1
 * Sample 2
 * 	Input:	    34
 * 	Output:     9
 *				34 = 3 + 3 + 4 + 4 + 4 + 4 + 4 + 4 + 4.
 */
public class ChangeDP {
	private static int[] coins = new int[]{1,3,4};
	
    private static int getChange(int m) {
        int[] memory = new int[m + 1];
        memory[0] = 0;
         for (int i = 1; i <= m; i++) {
        	 	int count = m;
        		for (int ci = 0; ci < coins.length; ci++) {
        			int coin = coins[ci];
        			if (coin <= i) {
        				int local = memory[i - coin] + 1;
        				if(local < count) {
        					count = local;
        				}
        			}
        		}
        		memory[i] = count;
        }
        return memory[m];
    }

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        int m = scanner.nextInt();
        System.out.println(getChange(m));
        scanner.close();
    }
}

