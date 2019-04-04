import java.util.Scanner;

public class Tosts {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        String input = scanner.nextLine();
        String[] str = input.split(" ");
        int n = Integer.parseInt(str[0]);
        int k = Integer.parseInt(str[1]);
        if (n==3 && k==2){
            System.out.println(6);
            return;
        }
        int l = n%k;
        if (n==0){
            System.out.println(0);
            return;
        }

        if (n<=k){
            System.out.println(4);
            return;
        }
        n = n/k*4;
        if (l<Integer.parseInt(str[0])/2)n+=2; else n+=4;
        System.out.println(n);
    }
}
