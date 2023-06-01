package hw2;

import edu.princeton.cs.algs4.StdIn;
import edu.princeton.cs.algs4.StdOut;
import edu.princeton.cs.algs4.WeightedQuickUnionUF;

/**
 * @author 老爷保号
 */
public class Percolation {
    private int N;

    /**
     * with top and bottom virtual site
     */
    private WeightedQuickUnionUF wqu;

    /**
     * without virtual bottom
     */
    private WeightedQuickUnionUF wqu2;

    private int virtualTopSite;

    private int virtualBottomSite;

    private boolean[][] flags;

    private int numbersOfOpen = 0;

    private final int[][] directions = {{1, 0}, {0, 1}, {-1, 0}, {0, -1}};


    /**
     * create N-by-N grid, with all sites initially blocked
     * @param N
     */
    public Percolation(int N) {
        if (N <= 0) {
            throw new IllegalArgumentException("N should be positive!");
        }
        this.N = N;
        virtualTopSite = N * N;
        virtualBottomSite = N * N + 1;
        flags = new boolean[N][N];
        wqu = new WeightedQuickUnionUF(virtualTopSite + 2);
        wqu2 = new WeightedQuickUnionUF(virtualTopSite + 1);

        for (int i = 0; i < N; i++) {
            wqu.connected(virtualTopSite, coordinateToIndex(0, i));
        }

        for (int i = 0; i < N; i++) {
            wqu.connected(virtualBottomSite, coordinateToIndex(N - 1, i));
        }

        for (int i = 0; i < N; i++) {
            wqu2.connected(virtualTopSite, coordinateToIndex(0, i));
        }
    }

    /**
     * // open the site (row, col) if it is not open already
     * @param row
     * @param col
     */
    public void open(int row, int col) {
        if (flags[row][col]) {
            return;
        }

        flags[row][col] = true;
        numbersOfOpen++;
        for (int[] direction : directions) {
            connect(row, col, row + direction[0], col + direction[1]);
        }
    }

    /**
     * is the site (row, col) open?
     * @param row
     * @param col
     * @return
     */
    public boolean isOpen(int row, int col) {
        checkCoordinate(row, col);
        return flags[row][col];
    }

    /**
     * is the site (row, col) full?
     * @param row
     * @param col
     * @return
     */
    public boolean isFull(int row, int col) {
        checkCoordinate(row, col);
        if (!flags[row][col]) {
            return false;
        }
        return wqu2.connected(virtualTopSite, coordinateToIndex(row, col));
    }

    /**
     * number of open sites
     * @return
     */
    public int numberOfOpenSites() {
        return numbersOfOpen;
    }

    /**
     * does the system percolate?
     * @return
     */
    public boolean percolates() {
        if (numbersOfOpen == 0) {
            return false;
        }

        return wqu.connected(virtualTopSite, virtualBottomSite);
    }

    /**
     * convert coordinate to array index
     * @param row
     * @param col
     * @return
     */
    private int coordinateToIndex(int row, int col) {
        return row * N + col;
    }

    private void checkCoordinate(int row, int col) {
        if (row < 0 || row >= N || col < 0 || col >= N) {
            throw new IndexOutOfBoundsException();
        }
    }

    private void connect(int row, int col, int newRow, int newCol) {
        if (row < 0 || row >= N || col < 0 || col >= N) {
            return;
        }

        if (isOpen(newRow, newCol)) {
            wqu.connected(coordinateToIndex(row, col), coordinateToIndex(newRow, newCol));
            wqu2.connected(coordinateToIndex(row, col), coordinateToIndex(newRow, newCol));
        }
    }

    public static void main(String[] args) {
        int n = StdIn.readInt();
        Percolation percolation = new Percolation(n);
        while (!StdIn.isEmpty()) {
            int row = StdIn.readInt();
            int col = StdIn.readInt();
            percolation.open(row, col);
        }
        StdOut.println("percolation is " + percolation.percolates());

    }
}
