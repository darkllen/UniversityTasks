public class Percolation {
    public Percolation(int N) {
        matrixNN = new int[N][N];
        this.N = N;
        for (int i = 0; i < N; i++) {
            for (int i1 = 0; i1 < N; i1++) {
                matrixNN[i][i1] = 1;
            }
        }
        matrix = new WeightedQuickUnionUF(N*N+2);
        for (int i=0; i<N;i++){
            matrix.union(0,i+1);
            matrix.union(N*N+1, N*N-i);
        }
    }
    int N;
    int[][] matrixNN;
    WeightedQuickUnionUF matrix;

    public int getOpenedCount(){
        int count = 0;
        for (int i = 0; i < N; i++) {
            for (int i1 = 0; i1 < N; i1++) {
                if (matrixNN[i][i1] == 0)
                    count++;
            }
        }
        return count;
    }
    public void open(int i, int j){
        matrixNN[i][j]=0;
        if (j != 0)
            if (matrixNN[i][j - 1] == 0) {
                matrix.union(i * N + j + 1, i * N + j);
            }
        if (j != N - 1)
            if (matrixNN[i][j + 1] == 0) {
                matrix.union(i * N + j + 1, i * N + j + 2);
            }
        if (i != 0)
            if (matrixNN[i - 1][j] == 0) {
                matrix.union((i - 1)*N + j + 1, i *N+ j + 1);
            }
        if (i != N-1)
            if (matrixNN[i + 1][j] == 0) {
                matrix.union((i + 1)*N + j + 1, i *N+ j + 1);
            }
    }
    public  boolean isOpened(int i, int j){
        return matrixNN[i][j]==0;
    }
    public  boolean percolates(){
        return matrix.connected(0, N*N+1);
    }
}
