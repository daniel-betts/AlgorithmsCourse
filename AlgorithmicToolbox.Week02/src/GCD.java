import java.util.*;

public class GCD {
//  private static int gcd_naive(int a, int b) {
//    int current_gcd = 1;
//    for(int d = 2; d <= a && d <= b; ++d) {
//      if (a % d == 0 && b % d == 0) {
//        if (d > current_gcd) {
//          current_gcd = d;
//        }
//      }
//    }
//
//    return current_gcd;
//  }
  
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

    System.out.println(gcd_eff(a, b));
    scanner.close();
  }
}
