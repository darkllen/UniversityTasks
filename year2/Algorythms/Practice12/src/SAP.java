import javafx.util.Pair;

import java.util.ArrayList;
import java.util.concurrent.atomic.AtomicInteger;

public class SAP {
    Digraph digraph;
    public SAP(Digraph G){
        digraph = G;
    }
    // довжина найкоротшого шляху до спільного батька v та w
//-1 якщо такого шляху немає
    public int length(int v, int w){
        int ans = ancestor(v,w);
        BreadthFirstPaths breadthFirstPaths1 = new BreadthFirstPaths(digraph,v, ans);
        BreadthFirstPaths breadthFirstPaths2= new BreadthFirstPaths(digraph,w, ans);
        AtomicInteger i1 = new AtomicInteger(0);
        AtomicInteger i2 = new AtomicInteger(0);
        breadthFirstPaths1.pathTo(ans).forEach(x-> i1.getAndIncrement());
        breadthFirstPaths2.pathTo(ans).forEach(x-> i2.getAndIncrement());
return i1.get()+i2.get();
    }
    // спільний батько v та w,  з найкоротшого шляху
//-1 якщо такого шляху немає
    public int ancestor(int v, int w){
        BFS2 bfs2 = new BFS2(digraph, v, w);
        return bfs2.ans;
    }
    // довжина найкоротшого шляху між будь-якою вершиною з v та з w;
//-1 якщо такого шляху немає
    public int length(Iterable<Integer> v, Iterable<Integer> w){
        ArrayList<Integer> integers = new ArrayList<>();
        v.forEach(x->{
            w.forEach(y->{
                integers.add(length(x,y));
            });
        });
        AtomicInteger min = new AtomicInteger(Integer.MAX_VALUE);
        integers.forEach(x->{
            if(min.get() >x && x!=-1) min.set(x);
        });
        return integers.get(0);
    }


    // спільний батько з найкоротшого шляху …;
//-1 якщо такого шляху немає
    public int ancestor(Iterable<Integer> v, Iterable<Integer> w){
        ArrayList<Pair<Integer, Integer>> integersKeys = new ArrayList<>();
        ArrayList<Integer> integers = new ArrayList<>();
        v.forEach(x->{
            w.forEach(y->{
                integersKeys.add(new Pair<>(x,y));
                integers.add(length(x,y));
            });
        });
        AtomicInteger min = new AtomicInteger(Integer.MAX_VALUE);
        int x = 0;int y = 0;
        for (int i = 0; i < integers.size(); i++) {
            if(min.get() >integers.get(i) && integers.get(i)!=-1) {
                min.set(integers.get(i));
                x = integersKeys.get(i).getKey();
                y = integersKeys.get(i).getValue();
            }
        }
        return ancestor(x,y);
    }


}
