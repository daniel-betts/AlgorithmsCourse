import java.util.*;
/*
 * Task:
 * Given an integer n, compute the minimum number of operations needed to obtain
 * the number n starting from the number 1.
 * 
 * Input: 		Single integer.
 * Constraints:	1 <= money <= 10^6.
 * Output: 		In the first line, output the minimum number k of operations needed
 * 				to get n from 1. In the second line output a sequence of intermediate
 * 				numbers. That is, the second line should contain positive integers
 *              a0, a2, ..., ak−1 such that a0 = 1, ak−1 = n and for all 0 <= i < k−1,
 *              ai+1 is equal to either ai + 1, 2ai, or 3ai. If there are many such
 *              sequences, output any one of them.
 * 
 * Sample 1
 * 	Input:	    1
 * 	Output:     0
 *				1
 * Sample 2
 * 	Input:	    5
 * 	Output:     3
 *			    1 2 4 5
 *              Here, we first multiply 1 by 2 two times and then add 1. Another possibility
 *              is to first multiply by 3 and then add 1 two times. Hence “1 3 4 5” is also a
 *              valid output in this case.
 * Sample 3
 * 	Input:	    96234
 * 	Output:     14
 *			    1 3 9 10 11 22 66 198 594 1782 5346 16038 16039 32078 96234
 *              Again, another valid output in this case is 
 *              “1 3 9 10 11 33 99 297 891 2673 8019 16038 16039 48117 96234”.
 */
public class PrimitiveCalculator {
//    private static List<Integer> incorrect_optimal_sequence(int n) {
//        List<Integer> sequence = new ArrayList<Integer>();
//        while (n >= 1) {
//            sequence.add(n);
//            if (n % 3 == 0) {
//                n /= 3;
//            } else if (n % 2 == 0) {
//                n /= 2;
//            } else {
//                n -= 1;
//            }
//        }
//        Collections.reverse(sequence);
//        return sequence;
//    }
    
    private static List<Integer> optimal_sequence(int n) {
    		List<Integer> sequence = new ArrayList<Integer>();
    		sequence.add(1);
    		if (n == 1) return sequence;
    		
        int[] operations = new int[n+1];
        int[] traceback = new int[n+1];
        operations[1] = 0;
        traceback[1] = 1;
        int count = 1;
        int tracebackValue = 1;
        for (int i = 2; i <= n; i++) {
//        		Accounts for adding 1.
        		count = operations[i - 1];
        		tracebackValue = i - 1;
        		int local;
        		if (i % 2 == 0) {
        			local = operations[i / 2];
        			if (local < count) {
        				count = local;
        				tracebackValue = i / 2;
        			}
        		}
        		if(i % 3 == 0) {
        			local = operations[i / 3];
        			if (local < count) {
        				count = local;
        				tracebackValue = i / 3;
        			}
        		}
        		count++;
        		operations[i] = count;
        		traceback[i] = tracebackValue;
        }
//        for (int j=1;j<=n;j++) {
//			System.out.print(operations[j] + " ");
//		}
//        System.out.println("");
//        for (int j=1;j<=n;j++) {
//			System.out.print(traceback[j] + " ");
//		}
//        System.out.println("");
        Integer[] array = new Integer[count + 1];
        while (n > 1) {
//        		System.out.println(n + " = " + traceback[n] + " | count = " + count );
        		array[count] = n;
        		n = traceback[n];
        		count--;
        }
        array[0] = 1;
        return Arrays.asList(array);
    }

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        int n = scanner.nextInt();
        List<Integer> sequence = optimal_sequence(n);
        System.out.println(sequence.size() - 1);
        for (Integer x : sequence) {
            System.out.print(x + " ");
        }
        System.out.println("");
        scanner.close();
    }
}
