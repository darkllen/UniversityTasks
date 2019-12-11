import java.util.ArrayList;
import java.util.concurrent.atomic.AtomicInteger;

public class Digraph {

	private final int V;
	private ArrayList<Integer>[] adj;

	public int V() {
		return V;
	}
	public Digraph(int V){
		this.V=V;
		adj = (ArrayList<Integer>[]) new ArrayList[V];
		for (int v=0; v<V; v++)
			adj[v] = new ArrayList<Integer>();
	}

	public void addEdge(int v, int w){
		adj[v].add(w);
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

	public ArrayList<Integer> adj(int v){
		return adj[v];
	}

}
