import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class FibonacciHuge {
    private static long getFibonacciHugeNaive(long n, long m) {
        if (n <= 1)
            return n;
        
        List<Integer> remainderList = findPeriod(n, (int)m);
        if (n + 1 == remainderList.size()) {
        		return remainderList.get((int)n);
        }
        int periodSize = (remainderList.size() / 2);
        long modulo = n % (long)periodSize;
        return remainderList.get((int)modulo);
    }
    
    private static List<Integer> findPeriod(long n, int m) {
    		List<Integer> list = new ArrayList<>();
    		int previous = 0;
        int current  = 1;
        list.add(0, previous);
        list.add(1, current);
        
        int counter = 0;
        
        for (long i = 2; i <= n; ++i) {
            int tmp_previous = previous;
            previous = current;
            current = (tmp_previous + current) % m;
            list.add(current);
            if (list.get(counter) == current) {
            		if (list.size() == (counter + 1) * 2) {
            			break;
            		}
            		counter++;
            } else {
            		counter = 0;
            }
        }
        
    		return list;
    }
    
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        long n = scanner.nextLong();
        long m = scanner.nextLong();
        System.out.println(getFibonacciHugeNaive(n, m));
        scanner.close();
    }
}
