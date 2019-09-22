import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        int n = scanner.nextInt();
        int arr[] = new int[9];
        for(int i = 0; i<n; i++){
            arr[scanner.nextInt()-1]++;
        }
        int max = 0;
        for (int y :arr) {
            if(max<y)max = y;
        }
        System.out.println(n - max);
    }
}
