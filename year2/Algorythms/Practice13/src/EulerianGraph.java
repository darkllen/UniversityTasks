import java.util.Iterator;
import java.util.concurrent.atomic.AtomicInteger;

public class EulerianGraph {

    private final int V;
    private Bag<Integer>[] adj;
    private Point[] points;

    public int V() {
        return V;
    }
    public EulerianGraph(int V){
        this.V=V;
        points = new Point[V];
        adj = (Bag<Integer>[]) new Bag[V];
        for (int v=0; v<V; v++)
            adj[v] = new Bag<Integer>();
    }

    public void addEdge(Point v,Point w){
        adj[v.num].add(w.num);
        points[v.num] = v;
        points[w.num] = w;
    }

    public void show(){
        StdDraw.setXscale(0,30000);
        StdDraw.setYscale(0,30000);
        StdDraw.clear();
        for (int i = 0; i < points.length; i++) {
            StdDraw.filledCircle(points[i].x, points[i].y, 100);
        }
        for (int i = 0; i < adj.length; i++) {
            Iterator<Integer> iterator = adj[i].iterator();
            while (iterator.hasNext()){
                int j = iterator.next();
                StdDraw.line(points[i].x,points[i].y, points[j].x, points[j].y);
            }
        }
        StdDraw.show();
    }

    public int outdegree(int v){
        int degree = 0;
        for (int w : adj(v))
            degree++;
        return degree;
    }
    public int indegree(int v){
        AtomicInteger d = new AtomicInteger();
        for (int i = 0; i < adj.length; i++) {
            adj[i].forEach(x->{
                if(x==v) d.getAndIncrement();
            });
        }
        return d.get();
    }

    public Iterable<Integer> adj(int v){
        return adj[v];
    }

    static class Point{
        int num;
        int x;
        int y;

        public Point(int num, int x, int y) {
            this.num = num;
            this.x = x;
            this.y = y;
        }
    }


}
