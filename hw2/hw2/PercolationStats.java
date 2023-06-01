package hw2;

import edu.princeton.cs.introcs.StdRandom;
import edu.princeton.cs.introcs.StdStats;

/**
 * @author 老爷保号
 */
//public class PercolationStats {
//
//    private int T;
//
//    private double[] openSiteFraction;
//    /**
//     * perform T independent experiments on an N-by-N grid
//     * @param N
//     * @param T
//     * @param pf
//     */
//    public PercolationStats(int N, int T, PercolationFactory pf) {
//        if (N <= 0 || T <= 0) {
//            throw new IllegalArgumentException();
//        }
//        this.T = T;
//        openSiteFraction = new double[T];
//
//        for (int i = 0; i < T; i++) {
//            Percolation p = pf.make(N);
//            while (!p.percolates()) {
//                int row = StdRandom.uniform(N);
//                int col = StdRandom.uniform(N);
//                if (p.isOpen(row, col)) {
//                    continue;
//                }
//                p.open(row, col);
//            }
//            openSiteFraction[i] = (double) p.numberOfOpenSites() / N * N;
//        }
//    }
//
//    /**
//     * sample mean of percolation threshold
//     * @return
//     */
//    public double mean() {
//        return StdStats.mean(openSiteFraction);
//    }
//
//    /**
//     *  sample standard deviation of percolation threshold
//     * @return
//     */
//    public double stddev()   {
//        return StdStats.stddev(openSiteFraction);
//    }
//
//    /**
//     * low endpoint of 95% confidence interval
//     * @return
//     */
//    public double confidenceLow() {
//        return mean() - 1.96 * stddev() / Math.sqrt(T);
//    }
//
//    /**
//     * high endpoint of 95% confidence interval
//     * @return
//     */
//    public double confidenceHigh() {
//        return mean() + 1.96 * stddev() / Math.sqrt(T);
//    }
//}
//
public class PercolationStats {
    private int T;
    private double[] openSiteFractions;

    // perform T independent experiments on an N-by-N grid
    public PercolationStats(int N, int T, PercolationFactory pf) {
        if (N <= 0 || T <= 0) {
            throw new IllegalArgumentException();
        }
        this.T = T;
        openSiteFractions = new double[T];
        for (int i = 0; i < T; i++) {
            Percolation percolation = pf.make(N);
            while (!percolation.percolates()) {
                int x, y;
                do {
                    x = StdRandom.uniform(N);
                    y = StdRandom.uniform(N);
                } while (percolation.isOpen(x, y));
                percolation.open(x, y);
            }
            openSiteFractions[i] = (double) percolation.numberOfOpenSites() / (N * N);
        }
    }

    // sample mean of percolation threshold
    public double mean() {
        return StdStats.mean(openSiteFractions);
    }

    // sample standard deviation of percolation threshold
    public double stddev() {
        return StdStats.stddev(openSiteFractions);
    }

    // low endpoint of 95% confidence interval
    public double confidenceLow() {
        return mean() - 1.96 * stddev() / Math.sqrt(T);
    }

    // high endpoint of 95% confidence interval
    public double confidenceHigh() {
        return mean() + 1.96 * stddev() / Math.sqrt(T);
    }

     public static void main(String[] args) {
     PercolationStats ps = new PercolationStats(20, 10, new PercolationFactory());
     System.out.println(ps.mean());
     System.out.println(ps.stddev());
     }
}