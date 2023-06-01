package hw2;

import edu.princeton.cs.algs4.WeightedQuickUnionUF;

/**
 * @author 老爷保号
 */
public class Percolation {
    private int N;

    private WeightedQuickUnionUF wqu;

    private WeightedQuickUnionUF wqu2;

    private int virtualTopSite;

    private int virtualBottomSite;

    private boolean[][] flags;

    private int numberOfOpen;

    private final int[][] directions = {{1, 0}, {-1, 0}, {0, 1}, {0, -1}};

    /**
     * create N-by-N grid, with all sites initially blocked
     * @param N
     */
    public Percolation(int N) {

        if (N <= 0) {
            throw new IllegalArgumentException();
        }

        this.N = N;
        virtualTopSite = N * N + 1;
        virtualBottomSite = virtualTopSite + 1;

        wqu = new WeightedQuickUnionUF(N * N + 2);
        wqu2 = new WeightedQuickUnionUF(N * N + 1);
        for (int i = 0; i < N; i++) {
            wqu.union(virtualTopSite, coordinateToIndex(0, i));
        }

        for (int i = 0; i < N; i++) {
            wqu.union(virtualBottomSite, coordinateToIndex(N - 1, i));
        }

        for (int i = 0; i < N; i++) {
            wqu2.union(virtualTopSite, coordinateToIndex(0, i));
        }

        flags = new boolean[N][N];
    }

    /**
     * open the site (row, col) if it is not open already
     * @param row
     * @param col
     * @return
     */
    public void open(int row, int col) {
        checkCoordinate(row, col);

        if (!flags[row][col]) {
            flags[row][col] = true;
            numberOfOpen++;

            for (int[] direction : directions) {
                connect(row, col, row + direction[0], col + direction[1]);
            }

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

        if (!isOpen(row, col)) {
            return false;
        }

        return wqu2.connected(virtualTopSite, coordinateToIndex(row, col));
    }

    /**
     * number of open sites
     * @return
     */
    public int numberOfOpenSites() {
        return numberOfOpen;
    }

    /**
     * does the system percolate?
     * @return
     */
    public boolean percolates() {
        if (numberOfOpen == 0) {
            return false;
        }
        return wqu.connected(virtualTopSite, virtualBottomSite);
    }

    private int coordinateToIndex(int row, int col) {
        return N * row + col;
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

        if (flags[row][col]) {
            wqu.union(coordinateToIndex(row, col), coordinateToIndex(newRow, newCol));
            wqu2.union(coordinateToIndex(row, col), coordinateToIndex(newRow, newCol));
        }
    }

    public static void main(String[] args) {

    }
}
