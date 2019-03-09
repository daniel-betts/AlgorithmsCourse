import java.util.*;

public class LCM {
//	private static long lcm_naive(int a, int b) {
//	    for (long l = 1; l <= (long) a * b; ++l)
//	      if (l % a == 0 && l % b == 0)
//	        return l;
//	
//	    return (long) a * b;
//	  }
  private static long lcm_eff(int a, int b) {
    int gcd = gcd_eff(a, b);
    return (long) a * (long) b / (long) gcd;
  }
  
  private static int gcd_eff(int a, int b) {
	  if (a == b) {
		  return a;
	  }
	  int temp, rest;
	  if (a < b) {
		  temp = a;
		  a = b;
		  b = temp;
	  }
	  rest = a % b;
	  if (rest == 0) {
		  return b;
	  }
	  return gcd_eff(b, rest);
  }
  
  public static void main(String args[]) {
    Scanner scanner = new Scanner(System.in);
    int a = scanner.nextInt();
    int b = scanner.nextInt();

    System.out.println(lcm_eff(a, b));
    scanner.close();
  }
}
