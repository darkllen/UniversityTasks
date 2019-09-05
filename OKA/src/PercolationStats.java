import java.util.Random;

public class PercolationStats {
    int T;
    int N;
    double[] results;
    double mean;
    double stddev;
    public PercolationStats(int N, int T){
        this.N = N;
        this.T = T;
        results = new double[T];
        for (int i = 0; i<T; i++){
            Percolation test = new Percolation(N);
            while (!test.percolates()){
                int randI = new Random().nextInt(N);
                int randJ = new Random().nextInt(N);
                    test.open(randI,randJ);
            }
            results[i]=(double)test.getOpenedCount()/(double)(N*N);
        }


        this.mean = mean();
        this.stddev = stddev();

    }
    public double mean(){
        double sum = 0;
        for (int i = 0; i<results.length;i++){
            sum+=results[i];
        }
        return  sum/(double) T;
    }
    public double stddev(){
        double sum = 0;
        for (int i = 0; i<results.length;i++){
            sum+=(results[i]-mean)*(results[i]-mean);
        }
        return Math.sqrt(sum/(double) (T-1));
    }
    public double getLowConfidenceInterval(){
        return mean-(1.96*stddev/Math.sqrt(T));
    }
    public double getUpperConfidenceInterval(){
        return mean+(1.96*stddev/Math.sqrt(T));
    }

    public static void main(String[] args) {
        PercolationStats percolationStats = new PercolationStats(200,100);
        System.out.println("size: 200, times: 100");
        System.out.println("mean: " +percolationStats.mean);
        System.out.println("stddev: "+percolationStats.stddev);
        System.out.println("95% confidence interval: [" + percolationStats.getLowConfidenceInterval() + ", " + percolationStats.getUpperConfidenceInterval() + "]");
    }
}
