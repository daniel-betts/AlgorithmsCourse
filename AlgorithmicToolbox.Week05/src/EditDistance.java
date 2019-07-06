import java.util.*;

/*
 * Task:
 * The goal of this problem is to implement the algorithm for computing the edit
 * distance between two strings.
 * 
 * Input: 		Each of the two lines of the input contains a string consisting of lower case latin letters.
 * Constraints:	The length of both strings is at least 1 and at most 100.
 * Output: 		Output the edit distance between the given two strings.
 * 
 * Sample 1
 * 	Input:	    ab
 * 				ab
 * 	Output:     0

 * Sample 2
 * 	Input:	    short
 * 				ports
 * 	Output:     3
 *              short-
 *              -ports
 *              
 * Sample 3
 * 	Input:	    editing
 * 				distance
 * 	Output:     5
 *			    edi-ting-
 *				-distance
 */
class EditDistance {
  public static int editDistance(String s, String t) {
	  int[][] matrix = new int[s.length() + 1][t.length() + 1];
	  int insertion, deletion, diagonal;
	  // Add initial values on 0 indices.
	  for(int a = 1; a <= t.length(); a++) {
		  matrix[0][a] = a;
	  }
	  for(int b = 1; b <= s.length(); b++) {
		  matrix[b][0] = b;
	  }
	  for(int i = 1; i < matrix.length; i ++) {
//		  System.out.println("");
		  for (int j = 1; j < matrix[i].length; j++) {
//			  if (i == 0) {
//				  matrix[0][j] = j;
//			  } else if (j == 0) {
//				  matrix[i][0] = i;
//			  } else {
				  //Do stuff to compute values
				  insertion = matrix[i][j - 1] + 1;
				  deletion = matrix[i - 1][j] + 1;
				  if (s.charAt(i - 1) == t.charAt(j - 1)) {
					  diagonal = matrix[i - 1][j - 1];
				  } else {
					  diagonal = matrix[i - 1][j - 1] + 1;
				  }
				  matrix[i][j] = Math.min(insertion, Math.min(deletion, diagonal));
//			  }
//			  System.out.print(matrix[i][j] + " ");
		  }
	  }
//	  System.out.println("");
    return matrix[s.length()][t.length()];
  }
  public static void main(String args[]) {
    Scanner scan = new Scanner(System.in);

    String s = scan.next();
    String t = scan.next();

    System.out.println(editDistance(s, t));
    scan.close();
  }

}
