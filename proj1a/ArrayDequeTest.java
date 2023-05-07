import java.util.Random;

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

    public static void randomAddLastRemoveFirstIsEmptyTest() {
        Random random = new Random();
        ArrayDeque<Integer> deque = new ArrayDeque<>();
        for (int i = 0; i < 50; i++) {
            int operator = random.nextInt(3);
            switch (operator) {
                case 0: {
                    int item = random.nextInt();
                    deque.addLast(item);
                    break;
                }
                case 1: {
                    Integer removal = deque.removeFirst();
                    System.out.println(removal);
                    break;
                }

                case 2: {
                    System.out.println(deque.isEmpty() ? "Empty deque" : "Not Empty");
                    break;
                }
                default: break;
            }
        }
    }
    public static void main(String[] args) {
        randomAddLastRemoveFirstIsEmptyTest();
    }
}
