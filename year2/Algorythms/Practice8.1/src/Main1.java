import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;

public class Main1 {
    public static int sumK(Mission[] arr){
        int u = 0;
        for (int i = 0; i < arr.length; i++) {
            u+= arr[i].time;
        }
        return u;
    }
    public static void main(String[] args) throws IOException {
        BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(System.in));
        String[] s = bufferedReader.readLine().split(" ");
        int n = Integer.parseInt(s[0]);
        int k = Integer.parseInt(s[1]);



        Mission[] arr = new Mission[n];
        for(int i = 0; i< n; i++){
            s = bufferedReader.readLine().split(" ");
            arr[i] = new Mission(Integer.parseInt(s[0]), Integer.parseInt(s[1]));
        }
        int u = sumK(arr);
        long[][] T = new long[n+1][u];
        for(int c = 0; c<u; c++){
            T[0][c] = 0;
        }
        for(int i = 1; i<=n;i++){
            for(int c = 0; c<u; c++){
                if(c>=arr[i-1].time){
                    T[i][c] = Math.max(T[i-1][c],T[i-1][c-arr[i-1].time]+arr[i-1].score);
                } else{
                    T[i][c] = T[i-1][c];
                }
            }
        }
        int min = u+1;
        for(int i = 1; i<T.length; i++){
            for(int j = 0; j< T[i].length; j++){
                if(T[i][j]>=k && min>j) min = j;
            }
        }
        if(min!=u+1) System.out.println(min);
        else System.out.println(-1);
    }


    static class Mission{
        int score;
        int time;
        public Mission(int score, int time) {
            this.score = score;
            this.time = time;
        }

    }
}
