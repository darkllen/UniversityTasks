import java.util.Stack;

public class DepthFirstPaths {

	private boolean[] marked;    
    private int[] edgeTo;        
    private final int s;         
    
    public DepthFirstPaths(Digraph G, int s) {
        this.s = s;
        edgeTo = new int[G.V()];
        marked = new boolean[G.V()];
        dfs(G, s, -1);
    }

    int cycleEnd = -1;
    private void dfs(Digraph G, int v, int prev) {
        marked[v] = true;
        for (int w : G.adj(v)) {
            if(w!=prev)
            if (!marked[w]) {
                edgeTo[w] = v;
                dfs(G, w, v);
            } else {
                cycleEnd = w;
                return;
            }
        }
    }

    public boolean isFullConnected(){
        for (int i = 0; i < marked.length; i++) {
            if (marked[i]) return false;
        }
        return true;
    }

    public boolean hasPathTo(int v) {
        return marked[v];
    }

    public Iterable<Integer> pathTo() {
        int v = cycleEnd;
        Stack<Integer> path = new Stack<Integer>();
        for (int x = v; x != s; x = edgeTo[x])
            path.push(x);
        path.push(s);
        return path;
    }

}
