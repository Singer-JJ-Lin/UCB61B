import java.util.HashMap;
import java.util.Map;

/**
 * @author 老爷保号
 */
public class Tire {
    public class Node {
        boolean existed;

        Map<Character, Node> sons;

        public Node() {
            existed = false;
            sons = new HashMap<>();
        }
    }

    public Node root = new Node();

    public void put(String s) {
        put(root, s, 0);
    }

    private Node put(Node root, String s, int length) {
        if (root == null) {
            root = new Node();
        }

        if (length == s.length()) {
            root.existed = true;
            return root;
        }

        char c = s.charAt(length);
        root.sons.put(c, put(root.sons.get(c), s, length + 1));
        return root;
    }
}
