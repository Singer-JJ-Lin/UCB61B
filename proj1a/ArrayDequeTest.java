public class ArrayDequeTest {
    public static void growTest() {
        ArrayDeque<Integer> deque = new ArrayDeque<>();
        deque.addLast(0);
        deque.addFirst(1);
        int number = deque.removeLast();
        System.out.println(number);
    }
    public static void main(String[] args) {
        growTest();
    }
}
