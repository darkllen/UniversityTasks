import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;

public class Main1 {
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
        Arrays.sort(arr);
        int t = 0;
        int i = 0;
        for(i = 0; i<n; i++){
            t+=arr[i].time;
            k-=arr[i].score;
            if(k<=0) break;
        }
        if(k>0) {
            System.out.println(-1);
            return;
        }
        System.out.println(t);
    }


    static class Mission implements Comparable{
        int score;
        int time;
        double av;
        public Mission(int score, int time) {
            this.score = score;
            this.time = time;
            av = (double) time/score;
        }

        @Override
        public int compareTo(Object o) {
            int i = Double.compare(this.av, ((Mission)o).av);
            if(i == 0){
                return Integer.compare(this.time, ((Mission)o).time);
            }
            return i;
        }
    }
}
