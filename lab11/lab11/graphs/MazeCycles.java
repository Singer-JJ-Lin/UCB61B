package lab11.graphs;

import java.util.Random;

/**
 *  @author Josh Hug
 */
public class MazeCycles extends MazeExplorer {
    /* Inherits public fields:
    public int[] distTo;
    public int[] edgeTo;
    public boolean[] marked;
    */

    private boolean cycleFound;

    private int[] pathTo;

    public MazeCycles(Maze m) {
        super(m);
        pathTo = new int[m.V()];
    }

    @Override
    public void solve() {
        // TODO: Your code here!
        Random rand = new Random();
        int startX = rand.nextInt(maze.N());
        int startY = rand.nextInt(maze.N());
        int s = maze.xyTo1D(startX, startY);
        pathTo[s] = s;
        helper(s);
        announce();
    }

    // Helper methods go here
    private void helper(int v) {
        if (cycleFound) {
            return;
        }

        marked[v] = true;
        for (int w : maze.adj(v)) {
            if (!marked[w]) {
                pathTo[w] = v;
                helper(w);
            } else {
                // Todo: something wrong with this part, I dont know why but I gonna fix this problem after lunch.
                pathTo[w] = v;

                int cur = v;
                edgeTo[cur] = pathTo[cur];
                while (cur != w) {
                    cur = pathTo[cur];
                    edgeTo[cur] = pathTo[cur];
                }
                cycleFound = true;
                return;
            }
        }
    }
}

