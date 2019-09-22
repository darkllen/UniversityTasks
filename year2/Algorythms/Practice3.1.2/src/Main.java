
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        int n = scanner.nextInt();
        int a = scanner.nextInt();
        int b = scanner.nextInt();
        int c = scanner.nextInt();
        long x0 = scanner.nextInt();
        long arr[] = new long[n];
        int curr = 0;
        int countMin = 0;
        long sum = 0;
        long min = Long.MAX_VALUE;
        for (int i =0; i<n;i++){
            x0 = ((a*x0*x0+b*x0+c)/100)%1000000;
            if (x0%5<2){
                if (curr==0) continue;
                curr--;
                if (curr==countMin) min = Long.MAX_VALUE;
                arr[curr] = 0;
                if (curr==0) continue;
                if (countMin>=curr){
                    min = Integer.MAX_VALUE;
                    for(int y = 0;y<curr;y++){
                        if (arr[y]<min){
                            min = arr[y];
                            countMin = y;
                        }
                    }
                }
            }else {
                arr[curr] = x0;
                curr++;
                if (x0<min) {
                    min = x0;
                    countMin = curr-1;
                }
            }
            sum+=min;
        }
        System.out.println(sum);
    }
}
