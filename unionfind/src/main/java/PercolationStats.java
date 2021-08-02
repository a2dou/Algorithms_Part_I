import edu.princeton.cs.algs4.StdIn;
import edu.princeton.cs.algs4.StdOut;
import edu.princeton.cs.algs4.StdRandom;
import edu.princeton.cs.algs4.StdStats;

public class PercolationStats {
    private static final double CONFIDENCE = 1.96d;
    private final int number;
    private final int trials;
    private double[] results;

    // perform independent trials on an n-by-n grid
    public PercolationStats(int n, int trials) {
        if (n <= 0 || trials <= 0) {
            throw new IllegalArgumentException("n，trials could not be a negative integer");
        }
        this.number = n;
        this.trials = trials;
        this.results = new double[trials];
    }

    // sample mean of percolation threshold
    public double mean() {
        return StdStats.mean(results);
    }

    // sample standard deviation of percolation threshold
    public double stddev() {
        return StdStats.stddev(results);
    }

    // low endpoint of 95% confidence interval
    public double confidenceLo() {
        return this.mean() - CONFIDENCE * (this.stddev() / Math.sqrt(trials));
    }

    // high endpoint of 95% confidence interval
    public double confidenceHi() {
        return this.mean() + CONFIDENCE * (this.stddev() / Math.sqrt(trials));
    }

    // test client (see below)
    public static void main(String[] args) {
        int n = 25, trials = 10000;
        if (!StdIn.isEmpty()) {
            int[] arguments = StdIn.readAllInts();
            if (arguments.length >= 2) {
                n = arguments[0];
                trials = arguments[1];
            }
        }

        PercolationStats percolationStats = new PercolationStats(n, trials);
        for (int i = 0; i < percolationStats.trials; i++) {
            Percolation percolation = new Percolation(percolationStats.number);
            while (!percolation.percolates()) {
                int position = StdRandom.uniform(percolationStats.number * percolationStats.number);
                percolation.open(position / percolationStats.number + 1, position % percolationStats.number + 1);
            }
            percolationStats.results[i] = ((double) percolation.numberOfOpenSites()) / (percolationStats.number * percolationStats.number);
        }
        StdOut.println("mean                    = " + percolationStats.mean());
        StdOut.println("stddev                  = " + percolationStats.stddev());
        StdOut.printf("95%% confidence interval =[%f, %f]", percolationStats.confidenceLo(), percolationStats.confidenceHi());
    }
}
