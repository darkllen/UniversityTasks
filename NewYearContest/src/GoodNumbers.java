import java.util.Scanner;

public class GoodNumbers {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        int n = scanner.nextInt();
        long tenNums = 1;
        for (int i=0; i<n; i++){
            tenNums *=5;
        }
        System.out.println(tenNums);
    }
}
