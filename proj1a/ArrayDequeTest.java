public class ArrayDequeTest {
    public static void growTest() {
        ArrayDeque<Integer> deque = new ArrayDeque<>();
        for (int i = 0; i < 16; i++) {
            deque.addFirst(i);
        }
        for (int i = 0; i < 16; i++) {
            int item = deque.removeFirst();
            deque.addLast(item);
        }
    }
    public static void main(String[] args) {
        growTest();
    }
}
