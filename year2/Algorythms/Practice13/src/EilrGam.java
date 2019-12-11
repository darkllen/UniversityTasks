import java.util.ArrayList;
import java.util.LinkedList;

public class EilrGam {

    Digraph graph;
    int matrix[][];
    public EilrGam(Digraph graph) {
        this.graph = graph;
        matrix = new int[graph.V()][graph.V()];
        for (int i = 0; i < graph.V(); i++) {
            int finalI = i;
            graph.adj(i).forEach(x->matrix[finalI][x]=1);
        }
        Visited = new boolean[graph.V()];


    }

    public Iterable<Integer> eiler(){
        findEiler(0);
        return ansEil;
    }
    public Iterable<Integer> hamilt(){
        hamilton(0);
        return Path;
    }

    LinkedList<Integer> ansEil = new LinkedList<>();
    // если граф является полуэйлеровым, то алгоритм следует запускать из вершины нечетной степени
    public void findEiler(int v){
        int matr[][] = new int[graph.V()][graph.V()];
        for (int i = 0; i < matr.length; i++) {
            for (int i1 = 0; i1 < matr[i].length; i1++) {
                matr[i][i1] = matrix[i][i1];
            }
        }
        LinkedList<Integer> a = new LinkedList<>();
        a.push(v);
        while (!a.isEmpty()){
            int w = a.getLast();
            boolean deg = true;
            for(int i = 0; i<graph.V(); i++){
                if (matr[w][i]==1)deg=false;
            }
            if (deg){
                ansEil.add(w);
                a.removeLast();
            }else{
                for(int i = 0; i<graph.V(); i++){
                    if (matr[w][i]==1){
                        a.add(i);
                        matr[w][i]=0;
                        break;
                    }
                }
            }
        }
    }



    boolean Visited[];
    LinkedList<Integer> Path = new LinkedList<>();
    boolean hamilton(int curr)
    {
        Path.add(curr);
        if (Path.size() == graph.V())
            if (matrix[Path.getLast()][Path.getFirst()] == 1)
                return true;
            else
            {
                Path.removeLast();
                return false;
            }
        Visited[curr] = true;

        for (int nxt = 0; nxt < graph.V(); ++nxt)

            if (matrix[curr][nxt] == 1 && !Visited[nxt])
                if (hamilton(nxt))
                    return true;
        Visited[curr] = false;
        Path.removeLast();
        return false;
    }



}
