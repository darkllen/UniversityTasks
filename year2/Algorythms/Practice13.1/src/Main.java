import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Iterator;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        String s[] = br.readLine().split(" ");
        int v = Integer.parseInt(s[0]);
        int e = Integer.parseInt(s[1]);
        EdgeWeightedGraph graph = new EdgeWeightedGraph(v);
        for(int i = 0; i<e; ++i){
            String l[] = br.readLine().split(" ");
            int from = Integer.parseInt(l[0]);
            int to = Integer.parseInt(l[1]);
            int w = Integer.parseInt(l[2]);
            Edge edge = new Edge(from-1,to-1,w);
            graph.addEdge(edge);
        }
        LazyPrimMST mst = new LazyPrimMST(graph);
        int sum = 0;
        Queue<Edge> edges = mst.mst();
        while(!edges.isEmpty())
            sum+= edges.dequeue().getWeight();

        System.out.println(sum);
    }
}
 class Bag<Item> implements Iterable<Item>{

    protected Item[] s;
    protected int n =0;

    public Bag(){
        s = (Item[])new Object[1];
    }

    public void add(Item item) {
        if (item==null)throw new NullPointerException();
        if (n==s.length) resize(2*s.length);
        s[n++] = item;
    }
    public boolean isEmpty() {
        return n==0;
    }


    public int size() {
        return n;
    }

    private void resize(int capacity){
        Item[] copy = (Item[])new Object[capacity];
        for (int i=0;i<n;i++)
            copy[i]=s[i];
        s = copy;
    }

    @Override
    public Iterator<Item> iterator() {
        return new ReverseArrayIterator();
    }

    private class ReverseArrayIterator implements Iterator<Item>{

        private int i=n;
        @Override
        public boolean hasNext() {
            return i>0;
        }

        @Override
        public Item next() {
            return s[--i];
        }

        @Override
        public void remove() {
            throw new UnsupportedOperationException();
        }

    }
}

 class Edge implements Comparable<Edge> {

    private final int v, w;
    private final double weight;

    public Edge(int v, int w, double weight){
        this.v = v;
        this.w = w;
        this.weight = weight;
    }

    public double getWeight() {
        return weight;
    }

    public int either(){
        return v;
    }

    public int other(int vertex){
        if (vertex == v) return w;
        else return v;
    }

    public int compareTo(Edge that)	{
        if (this.weight < that.weight) return -1;
        else if (this.weight > that.weight) return +1;
        else return 0;
    }
}


 class EdgeWeightedGraph {

    private final int V;
    private final Bag<Edge>[] adj;
    private Queue<Edge> mst = new Queue<Edge>();
    public EdgeWeightedGraph(int V)	{
        this.V = V;
        adj = (Bag<Edge>[]) new Bag[V];
        for (int v = 0; v < V; v++)
            adj[v] = new Bag<Edge>();
    }

    public int V() {
        return V;
    }

    public void addEdge(Edge e){
        int v = e.either(), w = e.other(v);
        adj[v].add(e);
        adj[w].add(e);
        mst.enqueue(e);
    }
    public Iterable<Edge> adj(int v){
        return adj[v];
    }
    public Iterable<Edge> edges(){
        return mst;
    }
}


 class LazyPrimMST {

    private boolean[] marked; // MST vertices
    private Queue<Edge> mst; // MST edges
    private MinPQ<Edge> pq; // PQ of edges

    public LazyPrimMST(EdgeWeightedGraph G)	{
        pq = new MinPQ<Edge>(G.V());
        mst = new Queue<Edge>();
        marked = new boolean[G.V()];
        visit(G, 0);
        while (!pq.isEmpty() && mst.size() < G.V() - 1)	{
            Edge e = pq.delMin();
            int v = e.either(), w = e.other(v);
            if (marked[v] && marked[w]) continue;
            mst.enqueue(e);
            if (!marked[v])
                visit(G, v);
            if (!marked[w])
                visit(G, w);
        }
    }

    private void visit(EdgeWeightedGraph G, int v)	{
        marked[v] = true;
        for (Edge e : G.adj(v))
            if (!marked[e.other(v)])
                pq.insert(e);
    }

    public Queue<Edge> mst()	{
        return mst;
    }
}

 class MinPQ<Key extends Comparable<Key>> {

    private Key[] pq;
    private int n;

    public MinPQ(int capacity){
        pq = (Key[]) new Comparable[capacity+1];
        n=0;
    }

    public boolean isEmpty(){
        return n ==0;
    }
    public int size(){
        return n;
    }

    public void insert(Key key){
        if(n>=pq.length-1)resize();
        pq[++n] = key;
        swim(n);

    }

    private void resize() {
        Key[] pq2=(Key[]) new Comparable[pq.length*2];
        for(int i=0;i<pq.length;i++)
            pq2[i]=pq[i];
        pq=pq2;
    }

    public Key delMin(){
        Key max = pq[1];
        exch(1,n--);
        sink(1);
        pq[n+1]=null;
        return max;
    }

    private void swim(int k){
        while (k>1 && less(k,k/2)){//
            exch(k,k/2);
            k=k/2;
        }
    }

    private void sink(int k){
        while(2*k<=n){
            int j = 2*k;
            if (j<n&&less(j+1,j)) j++;//
            if (!less(j,k)) break;//
            exch(k,j);
            k=j;
        }
    }

    private boolean less(int i, int j){
        return pq[i].compareTo(pq[j])<0;
    }

    private void exch(int i, int j){
        Key t = pq[i];
        pq[i] = pq[j];
        pq[j] = t;
    }

}



 class Queue<Item> implements Iterable<Edge>{
    private Node first,last;
    private int count =0;

    @Override
    public Iterator<Edge> iterator() {
        return null;
    }

    private class Node{
        Item item;
        Node next;
    }

    public void enqueue(Item item) {
        Node oldLast = last;
        last = new Node();
        last.item = item;
        last.next = null;
        count++;
        if (isEmpty())
            first = last;
        else
            oldLast.next=last;
    }

    public Item dequeue() {
        Item item = first.item;
        first = first.next;
        count--;
        if (isEmpty()) last =null;
        return item;
    }

    public boolean isEmpty() {
        return first == null;
    }

    public int size() {
        return count;
    }

}
 class WeightedQuickUnionUF {

    private int[] id;
    private int[] sz; // ����� ���������� ��� ������
    private int count;

    public WeightedQuickUnionUF(int n){
        count = n;
        id = new int[n];
        sz = new int[n];
        for (int i = 0; i<n; i++)
            id[i]=i;
        for (int i = 0; i<n; i++)
            sz[i] = 1;
    }

    public int count(){
        return count;
    }

    public boolean connected(int p, int q){
        return find(p) == find(q);
    }

    private int find(int p){
        while(p != id[p])
            p=id[p];
        return p;
    }

    public void union(int p, int q){
        int i = find(p);
        int j = find(q);
        if (i==j) return;
        if (sz[i]<sz[j]){
            id[i] = j;
            sz[j] +=sz[i];
        }else{
            id[j] = i;
            sz[i] += sz[j];
        }
        count--;
    }

    public String toString(){
        StringBuilder str = new StringBuilder();
        for (int i:id){
            str.append(i);
            str.append(" ");
        }
        return str.toString();
    }
}

