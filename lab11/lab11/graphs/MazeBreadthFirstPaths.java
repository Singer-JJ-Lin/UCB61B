package lab11.graphs;

import java.util.LinkedList;
import java.util.Queue;

/**
 *  @author Josh Hug
 */
public class MazeBreadthFirstPaths extends MazeExplorer {
    /* Inherits public fields:
    public int[] distTo;
    public int[] edgeTo;
    public boolean[] marked;
    */

    private int s;

    private int t;



    public MazeBreadthFirstPaths(Maze m, int sourceX, int sourceY, int targetX, int targetY) {
        // Add more variables here!
        super(m);
        maze = m;
        s = maze.xyTo1D(sourceX, sourceY);
        t = maze.xyTo1D(targetX, targetY);
        distTo[s] = 0;
        edgeTo[s] = s;
    }

    /** Conducts a breadth first search of the maze starting at the source. */
    private void bfs() {
        // TODO: Your code here. Don't forget to update distTo, edgeTo, and marked, as well as call announce()
        Queue<Integer> queue = new LinkedList<Integer>();
        queue.add(s);

        while (!queue.isEmpty()) {
            int q = queue.poll();

            Iterable<Integer> neighbors = maze.adj(q);
            for (Integer neighbor : neighbors) {
                if (!marked[neighbor]) {
                    marked[neighbor] = true;
                    distTo[neighbor] = distTo[q] + 1;
                    edgeTo[neighbor] = q;
                    announce();
                    if (neighbor == t) {
                        return;
                    } else {
                        queue.add(neighbor);
                    }
                }
            }
        }
    }


    @Override
    public void solve() {
        bfs();
    }
}

