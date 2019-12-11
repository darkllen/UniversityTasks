import java.util.ArrayList;

public class Degrees {
    Digraph g;

    public Degrees(Digraph g) {
        this.g = g;
    }

    int indegree(int v){
        return g.indegree(v);
    }
    int outdegree(int v){
        return g.outdegree(v);
    }

    Iterable<Integer> sources(){
        ArrayList<Integer> s = new ArrayList();
        for (int i = 0; i < g.V(); i++) {
            if(g.indegree(i)==0)s.add(i);
        }
        return s;
    }
    Iterable<Integer> sinks(){
        ArrayList<Integer> s = new ArrayList();
        for (int i = 0; i < g.V(); i++) {
            if(g.outdegree(i)==0)s.add(i);
        }
        return s;
    }

    boolean isMap(){
        for (int i = 0; i < g.V(); i++) {
            if(g.outdegree(i)!=1) return false;
        }
        return true;
    }

}
