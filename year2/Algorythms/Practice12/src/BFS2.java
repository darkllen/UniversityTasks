import java.util.Stack;

@SuppressWarnings("ALL")
public class BFS2 {
    private boolean[] marked;
    private int[] edgeTo;
    private int[] distTo;
    private final int s;
    private final int e;
    int ans;

    public BFS2(Digraph G, int s, int e) {
        this.s = s;
        this.e = e;
        edgeTo = new int[G.V()];
        marked = new boolean[G.V()];
        distTo = new int[G.V()];
        ans = bfs(G, s, e);
    }

    private int bfs(Digraph G, int s, int e) {

        LinkedQueue<Integer> q = new LinkedQueue<>();
        q.enqueue(s);
        marked[s] = true;
        distTo[s] = 0;
        while (!q.isEmpty()) {
            int v = q.dequeue();
            BreadthFirstPaths b = new BreadthFirstPaths(G, e,v);
            if(b.hasPathTo(v)) return v;
            for (int w : G.adj(v)) {
                if (!marked[w]) {
                    q.enqueue(w);
                    marked[w] = true;
                    edgeTo[w] = v;
                    distTo[w] = distTo[v] + 1;
                }
            }
        }
        return -1;
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

