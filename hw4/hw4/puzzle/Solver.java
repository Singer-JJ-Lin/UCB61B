package hw4.puzzle;
import edu.princeton.cs.algs4.MinPQ;

import java.util.List;
import java.util.ArrayList;
import java.util.Map;
import java.util.HashMap;
import java.util.Stack;
import java.util.Comparator;

/**
 * @author 老爷保号
 */
public class Solver {

    private List<WorldState> solution = new ArrayList<>();
    private Map<WorldState, Integer> edtgCaches = new HashMap<>();

    public Solver(WorldState initial) {
        MinPQ<SearchNode> pq = new MinPQ<>(new SearchNodeComparator());
        SearchNode current = new SearchNode(initial, 0, null);
        pq.insert(current);

        while (!pq.isEmpty()) {
            current = pq.delMin();
            if (current.state.isGoal()) {
                break;
            }

            for (WorldState nextState : current.state.neighbors()) {
                SearchNode node = new SearchNode(nextState, current.numberOfMove + 1, current);
                if (current.prev != null && nextState.equals(current.prev.state)) {
                    continue;
                }

                pq.insert(node);
            }
        }

        Stack<WorldState> path = new Stack<>();
        for (SearchNode p = current; p != null; p = p.prev) {
            path.push(p.state);
        }

        while (!path.isEmpty()) {
            solution.add(path.pop());
        }
    }
    public int moves() {
        return solution.size() - 1;
    }
    public Iterable<WorldState> solution() {
        return solution;
    }

    private class SearchNode {
        private WorldState state;

        private int numberOfMove = 0;

        private SearchNode prev;

        public SearchNode(WorldState state, int numberOfMove, SearchNode prev) {
            this.state = state;
            this.numberOfMove = numberOfMove;
            this.prev = prev;
        }
    }

    private class SearchNodeComparator implements Comparator<SearchNode> {

        @Override
        public int compare(SearchNode o1, SearchNode o2) {
            int edtgOfO1 = estimatedDistanceToGoal(o1);
            int edtgOfO2 = estimatedDistanceToGoal(o2);
            return o1.numberOfMove + edtgOfO1 - o2.numberOfMove - edtgOfO2;
        }

        private int estimatedDistanceToGoal(SearchNode sn) {
            if (!edtgCaches.containsKey(sn.state)) {
                edtgCaches.put(sn.state, sn.state.estimatedDistanceToGoal());
            }
            return edtgCaches.get(sn.state);
        }
    }
}
