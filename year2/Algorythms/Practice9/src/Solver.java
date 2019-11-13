import java.util.ArrayList;
import java.util.Iterator;

public class Solver {
    private int minStep = -1;
    private Node last;
    MinPQ<Node> min = new MinPQ<>(2);
    Board initial;

    public Solver(Board initial){
        int[][] arrCopy = new int[initial.dimension()][initial.dimension()];
        for(int l = 0; l< initial.dimension(); l++){
            for(int m = 0; m< initial.dimension(); m++) {
                arrCopy[l][m] = initial.getBlocks()[l][m];
            }
        }
        this.initial = new Board(arrCopy);
        last = new Node(initial, null);
        if(isSolvable()){
            while (!last.curr.isGoal()){
                Iterator<Board> boards = last.curr.neighbors().iterator();
                while (boards.hasNext()){
                    Board next = boards.next();
                    Node nxt = new Node(next, last);
                    min.insert(nxt);
                }
                last = min.delMin();
            }
            minStep = last.curr.getStepNum();
        }
    }

    public boolean isSolvable()    {

        Board init = new Board(initial.getBlocks());
        int[][] a = init.getBlocks();
        int count = 0;
        int c = 1;
        for(int i = 0; i< a.length; i++){
            for(int j = 0; j<a.length;j++){
                if(a[i][j]==c){
                    count+=moveToGoal(a[i][j],a, i, j);
                    i=0;
                    j=-1;
                    c++;
                }
            }
        }

        return count%2==0;
    }
    private int moveToGoal(int curr,int[][] arr, int i, int j){
        int count = 0;
        int findJ = ((curr-1)%arr.length);
        int findI = (curr-findJ)/arr.length;
        while (findJ!=j){
            int temp = arr[i][j];
            if(findJ>j){
               arr[i][j] = arr[i][j+1];
               arr[i][j+1] = temp;
               j++;
            }else {
                arr[i][j] = arr[i][j-1];
                arr[i][j-1] = temp;
                j--;
            }
            count++;
        }
        while (findI!=i){
            int temp = arr[i][j];
            if(findI>i){
                arr[i][j] = arr[i+1][j];
                arr[i+1][j] = temp;
                i++;
            }else {
                arr[i][j] = arr[i-1][j];
                arr[i-1][j] = temp;
                i--;
            }
            count++;
        }
        return count;
    }

    public int moves()       {
        return minStep;
    }

    public Iterable<Board> solution()   {
        return new IterSolution<>() ;
    }

    class IterSolution<Board> implements Iterable<Board>{

        @Override
        public Iterator<Board> iterator() {
            return new IteratorSol<>();
        }
    }

    class IteratorSol<Board> implements Iterator<Board>{
        private ArrayList<Board> arrayList = new ArrayList<>();
        public IteratorSol() {
            Node curr = last;
            arrayList.add((Board) curr.curr);
            while (curr.prev!=null){
                curr = curr.prev;
                arrayList.add((Board)curr.curr);
            }
        }

        @Override
        public boolean hasNext() {
            return arrayList.size()!=0;
        }

        @Override
        public Board next() {
            Board board = arrayList.get(arrayList.size()-1);
            arrayList.remove(arrayList.size()-1);
            return board;
        }
    }

    class Node implements Comparable<Node>{
        Board curr;
        Node prev;

        public Node(Board curr, Node prev) {
            this.curr = curr;
            this.prev = prev;
        }

        @Override
        public int compareTo(Node o) {
            return this.curr.compareTo(o.curr);
        }
    }
}


