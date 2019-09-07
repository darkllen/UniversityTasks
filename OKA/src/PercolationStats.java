import org.springframework.util.StopWatch;

import java.util.Random;

public class PercolationStats {
    private int T;
    private double[] results;
    private double mean;
    private double stddev;

    public PercolationStats(int N, int T){
        this.T = T;
        results = new double[T];

        for (int i = 0; i<T; i++){
            Percolation test = new Percolation(N);
            do{
                int randI = new Random().nextInt(N);
                int randJ = new Random().nextInt(N);
                if (!test.isOpened(randI,randJ))
                    test.open(randI,randJ);
            }
            while (!test.percolates());
            results[i]=test.getOpenedCount()/(double)(N*N);
        }

        this.mean = mean();
        this.stddev = stddev();
    }

    private double mean(){
        double sum = 0;
        for (double result : results) {
            sum += result;
        }
        return sum/T;
    }

    private double stddev(){
        double sum = 0;
        for (double result : results) {
            sum += (result - mean) * (result - mean);
        }
        return Math.sqrt(sum/(T-1));
    }

    private double getLowConfidenceInterval(){
        return mean-(1.96*stddev/Math.sqrt(T));
    }

    private double getUpperConfidenceInterval(){
        return mean+(1.96*stddev/Math.sqrt(T));
    }

    public static void main(String[] args) {
        StopWatch stopWatch = new StopWatch("TimeTest");
        stopWatch.start("program");
        PercolationStats percolationStats = new PercolationStats(200,100);
        stopWatch.stop();
        System.out.println("size: 200, times: 100");
        System.out.println("mean: " +percolationStats.mean);
        System.out.println("stddev: "+percolationStats.stddev);
        System.out.println("95% confidence interval: [" + percolationStats.getLowConfidenceInterval() + ", " + percolationStats.getUpperConfidenceInterval() + "]");
        System.out.println(stopWatch.prettyPrint());

    }
}
