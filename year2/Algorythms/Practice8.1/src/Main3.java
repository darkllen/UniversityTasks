import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
public class Main3 {

    public static void main(String[] args) throws IOException {
        int MAX = 100001;
        int INF = 1000000000;
        int Left;
        int Right;
        int[] m = new int[MAX];
        BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(System.in));
        String[] s = bufferedReader.readLine().split(" ");
        int n = Integer.parseInt(s[0]);
        int k = Integer.parseInt(s[1]);
        s = bufferedReader.readLine().split(" ");
        for (int i = 0; i < n; i++) {
            m[i] = Integer.parseInt(s[i]);
        }

        Left = 0; Right = INF;
        while (Left <= Right)
        {
            int Middle = (Left + Right) / 2;
            if (Check(Middle, n, k, m)) Left = Middle + 1;
            else Right = Middle - 1;
        }
        System.out.println(Left-1);
    }

    static boolean Check(int Value, int n, int k, int[] m)
    {
        int i, stall = 1, len = 0;
        for (i = 1; i < n; i++)
        {
            len += m[i] - m[i - 1];
            if (len >= Value) {
                len = 0;
                stall++;
            }
        }
        return stall >= k;
    }
}