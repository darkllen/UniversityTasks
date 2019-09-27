import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;


public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        while (br.ready()) {
            int arr[] = new int[100];
            int n = Integer.parseInt(br.readLine());
            String s[] = br.readLine().split(" ");
            for (int i = 0; i < n; i++) {
                int r = Integer.parseInt(s[i]);
                arr[r-150]++;
            }
            int sum = 0;
            s = br.readLine().split(" ");
            int low = Integer.parseInt(s[0]);
            int high = Integer.parseInt(s[1]);
            for (int i = low; i<=high;i++){
                sum+=arr[i-150];
            }
            System.out.println(sum);
        }
    }
}