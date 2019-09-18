import java.util.Scanner;

public class Graph {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        int n = scanner.nextInt();
        int count = 0;
        String[] str = new String[n+1];
        for (int i = 0;i<=n;i++){
            str[i] = scanner.nextLine();
            while (str[i].indexOf('1')!=-1){
                count++;
                str[i] = str[i].replaceFirst("1", "");
            }
        }
        System.out.println(count);
    }
}
