import java.awt.*;
import java.util.Stack;

public class DepthFirstPaths {

	private boolean[] marked;    
    private int[] edgeTo;        
    private final int s;         
    private final int e;

    public DepthFirstPaths(Graph G, int s, int e) {
        this.s = s;
        this.e = e;
        edgeTo = new int[G.V()];
        marked = new boolean[G.V()];
        dfs(G, s);
    }
    boolean isFind = false;
    private void dfs(Graph G, int v) {
        marked[v] = true;
        if(v == e) isFind = true;
        int size = 1000;
        int i = (int) (v/Math.sqrt(G.V()));
        int j = (int) (v-i*Math.sqrt(G.V()));
        StdDraw.setPenColor(Color.YELLOW);
        StdDraw.filledSquare(size/2+j*size, size/2+i*size, size/2);
        StdDraw.setPenColor(Color.BLACK);

        for (int w : G.adj(v)) {
            if (!marked[w]) {
                edgeTo[w] = v;
                if (!isFind)
                dfs(G, w);
            }
        }
    }
    public boolean hasPathTo(int v) {
        return marked[v];
    }

    public Iterable<Integer> pathTo(int v) {
        if (!hasPathTo(v)) return null;
        Stack<Integer> path = new Stack<Integer>();
        for (int x = v; x != s; x = edgeTo[x])
            path.push(x);
        path.push(s);
        return path;
    }

}
