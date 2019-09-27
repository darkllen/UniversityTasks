import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        String s[] = br.readLine().split(" ");
        int n = Integer.parseInt(s[0]);
        int arr[] = new int[2000000];
        for (int i = 0; i<n; i++){
            int curr = Integer.parseInt(s[i+1]);
            arr[curr-1]+=curr;
        }
        int min = 2000001;
        boolean wrong = false;
        for(int i = 0; i<arr.length; i++){
            if (arr[i]!=i+1){
                wrong = true;
            }
            if(arr[i]==0){
                if (i+1<min && i+1<n+1)min = i+1;
            }
        }
         if (min!=2000001){
            System.out.println(min);
        }else {
            System.out.println(0);
        }

    }
}
