import static org.junit.Assert.*;
import org.junit.Test;

/**
 * @author 老爷保号
 */
public class TestArrayDequeGold {
    @Test
    public void testStudentArrayDeque() {
        StudentArrayDeque<Integer> testArray = new StudentArrayDeque<>();
        ArrayDequeSolution<Integer> stdArray = new ArrayDequeSolution<>();
        StringBuffer sb = new StringBuffer();

        int testRemovedNumber = 0;
        int stdRemovedNumber = 0;

        for (int i = 0; i < 1000; i++) {
            if (stdArray.size() == 0) {
                int choice = StdRandom.uniform(2);
                int item = StdRandom.uniform(10000);
                if (choice == 0) {
                    sb.append("addFirst(").append(item).append(")\n");
                    testArray.addFirst(item);
                    stdArray.addFirst(item);
                } else {
                    sb.append("addLast(").append(item).append(")\n");
                    testArray.addLast(item);
                    stdArray.addLast(item);
                }
            } else {
                int operation = StdRandom.uniform(4);
                int item = StdRandom.uniform(10000);

                switch (operation) {
                    case 0:
                        sb.append("addFirst(").append(item).append(")\n");
                        testArray.addFirst(item);
                        stdArray.addFirst(item);
                        break;
                    case 1:
                        sb.append("addLast(").append(item).append(")\n");
                        testArray.addLast(item);
                        stdArray.addLast(item);
                        break;
                    case 2:
                        sb.append("removeFirst()\n");
                        testRemovedNumber = testArray.removeFirst();
                        stdRemovedNumber = stdArray.removeFirst();
                        break;
                    case 3:
                        sb.append("removeLast()\n");
                        testRemovedNumber = testArray.removeLast();
                        stdRemovedNumber = stdArray.removeLast();
                        break;
                    default:
                }

                assertEquals(sb.toString(), stdRemovedNumber, testRemovedNumber);
            }
        }
    }
}
