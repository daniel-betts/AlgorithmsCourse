import java.util.*;
import java.io.*;

/*
 * Task:
 * The goal in this code problem is to check whether an input sequence contains a majority element.
 * 
 * Input: 		The first line contains an integer n, the next one contains a sequence of n non-negative
				integers a0, a1, . . . , an-1.
 * Constraints:	1 <= n <= 10^5;
 * 				0 <= ai <= 10^9 for all 0 <= i < n.
 * Output: 		Output 1 if the sequence contains an element that appears strictly more than n/2 times,
 * 				and 0 otherwise.
 * 
 * Sample 1
 * 	Input:	    5 
 * 			    2 3 9 2 2
 * 	Output:     1
 *				2 is the majority element.
 * Sample 2
 * 	Input:	    4 
 * 			    1 2 3 4
 * 	Output:     0
 *				There is no majority element in this sequence.
 * Sample 3
 * 	Input:	    4 
 * 			    1 2 3 1
 * 	Output:     0
 *				This sequence also does not have a majority element (note that the element 1 appears 
 *				twice and hence is not a majority element).
 */
public class MajorityElement {
	private static int majority;
	private static int[] a;
	
	private static void swap(int indexA, int indexB) {
		int temp = a[indexA];
		a[indexA] = a[indexB];
		a[indexB] = temp;
	}
	private static int split(int left, int right) {
		int swapLessIndex = left;
		int swapSameIndex = left + 1;
		int value = a[swapLessIndex];
		for (int i = left + 1; i < right; i++) {
			if (a[i] < value) {
				swap(i, swapSameIndex);
				swap(swapSameIndex, swapLessIndex);
				swapSameIndex++;
				swapLessIndex++;
			} else if (a[i] == value) {
				swap(i, swapSameIndex);
				swapSameIndex++;
			}
		}
		if (swapSameIndex - swapLessIndex >= majority) {
			return 1; // Uhul!
		}
		
		if (swapLessIndex - left >= majority) {
			return split(left, swapLessIndex);
		} else if (right - swapSameIndex >= majority) {
			return split(swapSameIndex, right);
		}
		
		return -1;
	}
	
    private static int getMajorityElement(int[] array, int left, int right) {
        if (left == right) {
            return -1;
        }
        if (left + 1 == right) {
            return a[left];
        }
        //write your code here
        a = array;
        majority = a.length / 2 + 1;
        
        return split(left, right);
    }

    public static void main(String[] args) {
        FastScanner scanner = new FastScanner(System.in);
        int n = scanner.nextInt();
        int[] a = new int[n];
        for (int i = 0; i < n; i++) {
            a[i] = scanner.nextInt();
        }
        if (getMajorityElement(a, 0, a.length) != -1) {
            System.out.println(1);
        } else {
            System.out.println(0);
        }
    }
    static class FastScanner {
        BufferedReader br;
        StringTokenizer st;

        FastScanner(InputStream stream) {
            try {
                br = new BufferedReader(new InputStreamReader(stream));
            } catch (Exception e) {
                e.printStackTrace();
            }
        }

        String next() {
            while (st == null || !st.hasMoreTokens()) {
                try {
                    st = new StringTokenizer(br.readLine());
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
            return st.nextToken();
        }

        int nextInt() {
            return Integer.parseInt(next());
        }
    }
}

