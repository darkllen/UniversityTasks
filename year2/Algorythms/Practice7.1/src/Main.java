import java.io.*;
import java.util.Arrays;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bufferedWriter = new BufferedWriter(new OutputStreamWriter(System.out));
        String s[] = bufferedReader.readLine().split(" ");
        int n =  Integer.parseInt(s[0]);
        int k = Integer.parseInt(s[1]);
        if(k>n)k=n;
        String[] time = bufferedReader.readLine().split(" ");

        int[] arr = new int[n];
        for(int i = 0; i< n; i++){
            arr[i] = Integer.parseInt(time[i]);
        }

        long[] kass = new long[k];
        for (int i = 0; i < kass.length; i++) {
            kass[i] = arr[i];
        }
        Arrays.sort(kass);
        for(int i = k; i< n; i++){
            kass[0]+=arr[i];
            for(int j = 1; j<k;j++){
                if(kass[j-1]<=kass[j])break;
                long temp = kass[j-1];
                kass[j-1] = kass[j];
                kass[j] = temp;
            }

        }
        bufferedWriter.write(String.valueOf(kass[k-1]));
        bufferedWriter.flush();
       // System.out.println(kass[k-1]);
    }

}
