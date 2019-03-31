import java.util.*;

/*
 * Task:
 * Compose the largest number out of a set of integers.
 * 'Compose' means putting one number next to the other, like so, for 3 and 5, it
 * would be 53. For 28 and 13 it would be 2813. 
 * 
 * Input: 		The first line of the input contains an integer n. The second line
 * 				contains integers a1,a2,...,an.
 * Constraints:	1 <= n <= 100;
 * 				1 <= ai <= 10^3 for all 1 <= 1 <= n.
 * Output: 		Output the largest number that can be composed out of a1, a2, ..., an.
 * 
 * Sample 1
 * 	Input:		2
 * 				21 2 
 *  Output:		221
 *    
 * Sample 2
 * 	Input:		5
 * 				9 4 6 1 9
 * 	Output:		99641
 * 
 * Sample 3
 * 	Input:		3
 * 				23 39 92
 * 	Output:		923923
 */
public class LargestNumber {
	private static boolean isGreaterOrEqualTo(String newValue, String oldValue) {
		int minSize = Math.min(newValue.length(), oldValue.length());
		for (int i = 0; i < minSize; i++) {
			if (newValue.charAt(i) > oldValue.charAt(i)){
				return true;
			}
			if (newValue.charAt(i) < oldValue.charAt(i)){
				return false;
			}
		}
		return newValue.length() < oldValue.length();
	}
    private static String largestNumber(String[] a) {
        //write your code here
    		List<String> numbers = new ArrayList<>(Arrays.asList(a));
    		String current;
    		String result = "";
    		while (numbers.size() > 0) {
    			current = "0";
	    		for (String str : numbers) {
	    			if (isGreaterOrEqualTo(str, current)) {
	    				current = str;
	    			}
	    		}
	    		result += current;
	    		numbers.remove(current);
    		}
        return result;
    }

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        int n = scanner.nextInt();
        String[] a = new String[n];
        for (int i = 0; i < n; i++) {
            a[i] = scanner.next();
        }
        System.out.println(largestNumber(a));
        scanner.close();
    }
}

