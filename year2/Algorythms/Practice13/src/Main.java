public class Main {
    public static void main(String[] args) {
        Digraph g = new Digraph(6);
        g.addEdge(0,1);
        g.addEdge(1,2);
        g.addEdge(2,3);
        g.addEdge(3,4);
        g.addEdge(4,5);
        g.addEdge(5,0);


        EulerianGraph.Point point = new EulerianGraph.Point(0,10000,10000);
        EulerianGraph.Point point2 = new EulerianGraph.Point(1,20000,20000);
        EulerianGraph.Point point3 = new EulerianGraph.Point(2,15000,5000);
        EulerianGraph eulerianGraph = new EulerianGraph(3);
        eulerianGraph.addEdge(point,point2);
        eulerianGraph.addEdge(point2,point3);


        eulerianGraph.show();

        EilrGam eilrGam = new EilrGam(g);
        eilrGam.hamilt();
        eilrGam.eiler();
        System.out.println("S");
    }
}
