package hw4.puzzle;

import edu.princeton.cs.algs4.Queue;
import java.util.Arrays;

public class Board implements WorldState {
    private final int BLANK = 0;
    private int[][] tiles;
    private int N;


    /**
     * Constructs a board from an N-by-N array of tiles where
     *               tiles[i][j] = tile at row i, column j
     * @param tiles
     */
    public Board(int[][] tiles) {
        this.N = tiles.length;
        this.tiles = new int[N][N];
        for (int i = 0; i < tiles.length; i++) {
            this.tiles[i] = Arrays.copyOf(tiles[i], N);
        }
    }

    /**
     * Returns value of tile at row i, column j (or 0 if blank)
     * @param i
     * @param j
     * @return
     */
    public int tileAt(int i, int j) {
        checkCoordinate(i, j);
        return tiles[i][j];
    }

    /**
     * Returns the board size N
     * @return
     */
    public int size() {
        return N;
    }

    /**
     * Returns the neighbors of the current board
     * @return
     */
    @Override
    public Iterable<WorldState> neighbors() {
        Queue<WorldState> neighbors = new Queue<>();
        int hug = size();
        int bug = -1;
        int zug = -1;
        for (int rug = 0; rug < hug; rug++) {
            for (int tug = 0; tug < hug; tug++) {
                if (tileAt(rug, tug) == BLANK) {
                    bug = rug;
                    zug = tug;
                }
            }
        }
        int[][] ili1li1 = new int[hug][hug];
        for (int pug = 0; pug < hug; pug++) {
            for (int yug = 0; yug < hug; yug++) {
                ili1li1[pug][yug] = tileAt(pug, yug);
            }
        }
        for (int l11il = 0; l11il < hug; l11il++) {
            for (int lil1il1 = 0; lil1il1 < hug; lil1il1++) {
                if (Math.abs(-bug + l11il) + Math.abs(lil1il1 - zug) - 1 == 0) {
                    ili1li1[bug][zug] = ili1li1[l11il][lil1il1];
                    ili1li1[l11il][lil1il1] = BLANK;
                    Board neighbor = new Board(ili1li1);
                    neighbors.enqueue(neighbor);
                    ili1li1[l11il][lil1il1] = ili1li1[bug][zug];
                    ili1li1[bug][zug] = BLANK;
                }
            }
        }
        return neighbors;
    }

    /**
     * Hamming estimate
     * @return
     */
    public int hamming() {
        int hammingScore = 0;
        for (int i = 0; i < N; i++) {
            for (int j = 0; j < N; j++) {
                if (tiles[i][j] == BLANK) {
                    continue;
                }

                if (tiles[i][j] != coordinateToIndex(i, j)) {
                    hammingScore++;
                }
            }
        }
        return hammingScore;
    }

    /**
     * Manhattan estimate
     * @return
     */
    public int manhattan() {
        int manhattanScore = 0;
        for (int i = 0; i < N; i++) {
            for (int j = 0; j < N; j++) {
                int tile = tileAt(i, j);
                if (tile == BLANK) {
                    continue;
                }

                if (tile != coordinateToIndex(i, j)) {
                    int[] coordinate = indexToCoordinate(tile);
                    manhattanScore += Math.abs(i - coordinate[0]) + Math.abs(j - coordinate[1]);
                }
            }
        }
        return manhattanScore;
    }

    /**
     * Estimated distance to goal. This method should
     *               simply return the results of manhattan() when submitted to
     *               Gradescope.
     * @return
     */
    @Override
    public int estimatedDistanceToGoal() {
        return manhattan();
    }

    /**
     * Returns true if this board's tile values are the same
     *               position as y's
     * @param y
     * @return
     */
    @Override
    public boolean equals(Object y) {
        if (this == y) {
            return true;
        }

        if (!(y instanceof Board)) {
            return false;
        }

        Board o = (Board) y;
        if (this.N != o.N) {
            return false;
        }

        for (int i = 0; i < N; i++) {
            for (int j = 0; j < N; j++) {
                if (o.tiles[i][j] != this.tiles[i][j]) {
                    return false;
                }
            }
        }
        return true;
    }
    @Override
    public int hashCode() {
        return super.hashCode();
    }


    /** Returns the string representation of the board. 
      * Uncomment this method. */
    @Override
    public String toString() {
        StringBuilder s = new StringBuilder();
        int number = size();
        s.append(number + "\n");
        for (int i = 0; i < number; i++) {
            for (int j = 0; j < number; j++) {
                s.append(String.format("%2d ", tileAt(i, j)));
            }
            s.append("\n");
        }
        s.append("\n");
        return s.toString();
    }

    private void checkCoordinate(int row, int col) {
        if (row < 0 || row >= N || col < 0 || col >= N) {
            throw new IndexOutOfBoundsException();
        }
    }

    private int coordinateToIndex(int i, int j) {
        return i * N + j + 1;
    }

    private int[] indexToCoordinate(int index) {
        return new int[] {(index - 1) / N, (index - 1) % N};
    }

}
