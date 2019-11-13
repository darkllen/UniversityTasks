import java.util.ArrayList;
import java.util.Iterator;

public class Board implements Comparable<Board> {
     private static int N;
     private int[][] blocks;
     private int stepNum;


    public int getStepNum() {
        return stepNum;
    }

    public Board(int[][] blocks) {
        Board.N = blocks.length;
        this.blocks = blocks;
        stepNum=0;
    }

    private Board(int[][] blocks, int stepNum) {
        Board.N = blocks.length;
        this.blocks = blocks;
        this.stepNum = stepNum;
    }

    public int dimension(){
        return N;
    }

    public int[][] getBlocks() {
        return blocks;
    }

    public int hamming(){
        int count = -1;
        for(int i = 0; i< N; i++){
            for(int j = 0; j< N; j++){
                if(blocks[i][j]!=i*N+j)count++;
            }
        }
        return count+stepNum;
    }
    public int manhattan(){
        int sum = 0;
        for(int i = 0; i< N; i++){
            for(int j = 0; j< N; j++){
                int curr = blocks[i][j];
                if(curr!=0){
                    int findJ = ((curr-1)%N);
                    int findI = (curr-findJ)/N;
                    sum+= Math.abs(i-findI)+Math.abs(j-findJ);
                }
            }
        }
        return sum+stepNum;
    }
    public boolean isGoal(){
        return manhattan()==stepNum;
    }
    public  boolean equals(Board y){
        for(int i = 0; i< N; i++){
            for(int j = 0; j< N; j++){
               if(blocks[i][j]!=y.blocks[i][j]){
                   return false;
               }
            }
        }
        return true;
    }

    public String toString(){
        StringBuilder res = new StringBuilder();
        for(int i = 0; i< N; i++){
            for(int j = 0; j< N; j++){
                res.append(blocks[i][j]).append(" ");
            }
            res.append('\n');
        }
        return res.toString();
    }
    public  Iterable<Board> neighbors(){
        return new IterBoards();
    }

    @Override
    public int compareTo(Board o) {
        return Integer.compare(this.manhattan(), o.manhattan());
    }

    class IterBoards implements Iterable<Board> {
        @Override
        public Iterator<Board> iterator() {
            return new IteratorBoards();
        }
    }
    class IteratorBoards implements Iterator<Board>{
        ArrayList<Board> arr = new ArrayList<>();
        public IteratorBoards() {
            boolean y = false;


            for(int i = 0; i< N; i++){
                for(int j = 0; j< N; j++){
                    if(blocks[i][j]==0){
                        if(i>0){
                            int[][] arrCopy = new int[N][N];
                            for(int l = 0; l< N; l++){
                                for(int m = 0; m< N; m++) {
                                    arrCopy[l][m] = blocks[l][m];
                                }
                            }
                           int[][] a = arrCopy;
                           a[i][j] = a[i-1][j];
                           a[i-1][j] = 0;
                           arr.add(new Board(a, stepNum+1));
                        }
                        if(i<N-1){
                            int[][] arrCopy = new int[N][N];
                            for(int l = 0; l< N; l++){
                                for(int m = 0; m< N; m++) {
                                    arrCopy[l][m] = blocks[l][m];
                                }
                            }
                            int[][] a = arrCopy;
                            a[i][j] = a[i+1][j];
                            a[i+1][j] = 0;
                            arr.add(new Board(a, stepNum+1));
                        }
                        if(j>0){
                            int[][] arrCopy = new int[N][N];
                            for(int l = 0; l< N; l++){
                                for(int m = 0; m< N; m++) {
                                    arrCopy[l][m] = blocks[l][m];
                                }
                            }
                            int[][] a = arrCopy;
                            a[i][j] = a[i][j-1];
                            a[i][j-1] = 0;
                            arr.add(new Board(a, stepNum+1));
                        }
                        if(j<N-1){
                            int[][] arrCopy = new int[N][N];
                            for(int l = 0; l< N; l++){
                                for(int m = 0; m< N; m++) {
                                    arrCopy[l][m] = blocks[l][m];
                                }
                            }
                            int[][] a = arrCopy;
                            a[i][j] = a[i][j+1];
                            a[i][j+1] = 0;
                            arr.add(new Board(a, stepNum+1));
                        }
                        y = true;
                        break;
                    }
                }
                if(y) break;
            }
        }

        @Override
        public boolean hasNext() {
            return arr.size()!=0;
        }

        @Override
        public Board next() {
            if(arr.size()==0) return null;
            Board next = arr.get(0);
            arr.remove(0);
            return next;
        }
    }
}
