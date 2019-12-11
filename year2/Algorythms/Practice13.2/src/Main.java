import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public  class Main{

private static final int MAX = 5001;

    static int i, j, n, v, to;
    static int x[] =new int [MAX];
    static int y[] =new int [MAX];
    static int used[] =new int [MAX];
    static int min_e[] =new int [MAX];
    static int end_e[] =new int [MAX];



    public static void main(String[] args) throws IOException {
        //Читаем координаты городов.
        BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(System.in));
        int n  = Integer.parseInt(bufferedReader.readLine());
        for(int i = 0; i< n; i++){
            String s[] = bufferedReader.readLine().split(" ");
            x[i] = Integer.parseInt(s[0]);
            y[i] = Integer.parseInt(s[1]);
        }
        for (int i = 0; i< MAX; ++i){
            min_e[i] = Integer.MAX_VALUE;
            end_e[i] = -1;
            used[i] = 0;
        }
        min_e[1] = 0;
        double dist = min_e[1];

        //Ищем вершину v с минимальным значением min_e[v] среди еще не
        //включенных в минимальный остов вершин(для которых used[v] = 0).
        for (i = 0; i < n; i++)
        {
            v = -1;
            for (j = 0; j < n; j++)
                if (used[j]==0 && (v == -1 || min_e[j] < min_e[v])) v = j;

            //Включаем вершину v в остов. Добавляем в остов ребро (v, end_e[v]).
            used[v] = 1;
            if (end_e[v] != -1) dist += Math.sqrt(distance(v, end_e[v]));

            //Пересчитываем метки для ребер, исходящих из v.
            for (to = 0; to < n; to++)
            {
                int dV_TO = distance(v, to);
                if (used[to]==0 && dV_TO < min_e[to])
                {
                    min_e[to] = dV_TO;
                    end_e[to] = v;
                }
            }
        }
        System.out.println(dist);

    }

    static int sqr(int x) { return x*x; }

    static int distance(int i, int j) { return sqr(x[j] - x[i]) + sqr(y[j] - y[i]); }
}