public class Percolation {

    public Percolation(int N) {
        matrix = new boolean[N][N];
        this.N = N;

        for (int i = 0; i < N; i++) {
            for (int i1 = 0; i1 < N; i1++) {
                matrix[i][i1] = false;
            }
        }
        unionArray = new WeightedQuickUnionUF(N*N+2);

        int lastElement = N*N+1;
        int firstElement = 0;
        for (int i=0; i<N;i++){
            unionArray.union(firstElement,i+1);
            unionArray.union(lastElement, N*N-i);
        }
    }

   private int N;
   private boolean[][] matrix;
   private WeightedQuickUnionUF unionArray;

    public int getOpenedCount(){
        int count = 0;
        for (int i = 0; i < N; i++) {
            for (int i1 = 0; i1 < N; i1++) {
                if (matrix[i][i1])
                    count++;
            }
        }
        return count;
    }
    public void open(int i, int j){
        matrix[i][j]=true;
        int currentArrayNum = i*N+j+1;
        int leftCellar =currentArrayNum-1;
        int rightCellar =currentArrayNum+1;
        int upCellar =currentArrayNum-N;
        int bottomCellar =currentArrayNum+N;
        if (j != 0)
            if (isOpened(i,j - 1)) {
                unionArray.union(currentArrayNum, leftCellar);
            }
        if (j != N - 1)
            if (isOpened(i,j + 1)) {
                unionArray.union(currentArrayNum, rightCellar);
            }
        if (i != 0)
            if (isOpened(i-1,j)) {
                unionArray.union(currentArrayNum, upCellar);
            }
        if (i != N-1)
            if (isOpened(i+1,j)) {
                unionArray.union(currentArrayNum, bottomCellar);
            }
    }
    public  boolean isOpened(int i, int j){
        return matrix[i][j];
    }
    public  boolean percolates(){
        return unionArray.connected(0, N*N+1);
    }
}
