import java.io.*;
import java.util.Arrays;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter writer = new BufferedWriter(new OutputStreamWriter(System.out));
        int t = Integer.parseInt(bufferedReader.readLine());
        for (int i = 0; i< t; i++){
            int n = Integer.parseInt(bufferedReader.readLine());
            String arr[] = new String[n];
            for(int j = 0; j< n; j++){
                arr[j] = bufferedReader.readLine();
            }
            Arrays.sort(arr);
            String res = "YES";
            boolean y = false;
            for(int k = 0; k<n-1 ; k++){
                for(int l = k+1; l<n; l++){
                    if(arr[k].length()<=arr[l].length()){
                        if(arr[k].equals(arr[l].substring(0,arr[k].length()))){
                            res = "NO";
                            y = true;
                            break;
                        }
                    }
                    if(y)break;
                }
            }
            writer.write(res);
            writer.newLine();
        }
        writer.flush();
    }
}