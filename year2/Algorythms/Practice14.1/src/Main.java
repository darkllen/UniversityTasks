import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;

public class Main {
    public static final int  MAX = 50;

    static int n;
    static int cap[][] = new int[MAX][MAX];
    static int res[][] = new int[MAX][MAX];
    static int used[] = new int[MAX];
    static char s[] = new char[MAX];

    public static void main(String[] args) throws IOException {
        BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(System.in));
            while (bufferedReader.ready()){
                n = Integer.parseInt(bufferedReader.readLine());
                {
                    for (int i = 0; i < n; i++)
                    {
                        String s = bufferedReader.readLine();

                        for (int j = 0; j < n; j++){
                            int c = s.charAt(j) - '0';
                            cap[i][j] = c;
                        }
                    }
                    System.out.println(requiredCost());
                }
            }


    }
    static int aug(int x, int t, int CurFlow)
    {
        if (x == t) return CurFlow;
        if (used[x]++ !=0) return 0;
        for (int Flow, y = 0; y < n; y++)
        {
            if (res[x][y] > 0 && (Flow = aug(y, t, Math.min(CurFlow, res[x][y])))!=0)
            {
                res[x][y] -= Flow; res[y][x] += Flow;
                return Flow;
            }
        }
        return 0;
    }
    static int mincut(int s, int t)
    {
        for(int i = 0; i< MAX; i++){
            for(int j = 0; j< MAX; j++){
                res[i][j] = cap[i][j];
            }
        }
        int x, flow = 0;

        do{
            Arrays.fill(used, 0);
        }while((x = aug(s, t, Integer.MAX_VALUE))!=0 && (flow += x)!=0);
        return flow;
    }
  static int requiredCost()
    {
        int best = Integer.MAX_VALUE;
        for (int s = 1; s < n; s++)
            best = Math.min(best, mincut(0, s));
        return best;
    }
}
