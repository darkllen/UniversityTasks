import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(System.in));
        int n = Integer.parseInt(bufferedReader.readLine());
        byte[][] g = new byte[n][n];
        for(int i = 0; i< n; i++){
            String[] s = bufferedReader.readLine().split(" ");
            for(int j = 0; j< n; j++){
                g[i][j]= Byte.parseByte(s[j]);
            }
        }

        for(int i = 0; i<n; i++){
            for( int j = i; j<n; j++){
                if(g[i][j]!=g[j][i]) {
                    System.out.println("NO");
                    return;
                }
            }
        }
        for(int i = 0; i< n; i++){
            if(g[i][i]==1){
                System.out.println("NO");
                return;
            }
        }
        System.out.println("YES");
    }
}
