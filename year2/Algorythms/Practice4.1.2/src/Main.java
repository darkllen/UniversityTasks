import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        String s[] = br.readLine().split(" ");
        double n = Integer.parseInt(s[0]);
        long sum = (long) (((n+1)/2)*n);
        for (int i = 1; i<n;i++){
            sum -= Long.parseLong(s[i]);
        }
        System.out.println(sum);
    }
}
