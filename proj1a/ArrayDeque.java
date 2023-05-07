//public class ArrayDeque<T> {
//    private final int INITIAL_SIZE = 8;
//
//    private final int INCREMENT = 8;
//
//    private T[] array;
//    /** size of deque **/
//    private int size;
//    /** length of array **/
//    private int length;
//
//    private int front;
//    private int rear;
//
//    public ArrayDeque() {
//        array = (T[]) new Object[INITIAL_SIZE];
//        size = 0;
//        setLength(INITIAL_SIZE);
//        front = rear = 0;
//    }
//
//    public boolean isEmpty() {
//        return size == 0;
//    }
//
//    public int size() {
//        return size;
//    }
//
//    private void grow() {
//        T[] newArray = (T[]) new Object[length + INCREMENT];
//        for (int i = 0; i < size; i++, front = (front + 1) % length) {
//            newArray[i] = array[front];
//        }
//
//        array = null;
//        array = newArray;
//        setLength(length + INCREMENT);
//        rear = size + 1;
//    }
//
//    private void shrink() {
//        T[] newArray = (T[]) new Object[length >> 1];
//        for (int i = 0; i < size; i++, front = (front + 1) % length << 1) {
//            newArray[i] = array[front];
//        }
//
//        front = 0;
//        rear = size + 1;
//        array = null;
//        array = newArray;
//        setLength(length / 2);
//    }
//
//    public void addFirst(T item) {
//        if (isFull()) {
//            grow();
//        }
//        front = minusOne(front);
//        array[front] = item;
//        size++;
//    }
//
//    public void addLast(T item) {
//        if (isFull()) {
//            grow();
//        }
//
//        array[rear] = item;
//        rear = plusOne(rear, length);
//        size++;
//    }
//
//    public T removeFirst() {
//        if (length >= 16 && length / size >= 4) {
//            shrink();
//        }
//
//        if (isEmpty()) {
//            return null;
//        }
//
//        T result = array[front];
//        array[front] = null;
//        front = plusOne(front, length);
//        size--;
//        return result;
//    }
//
//    public T removeLast() {
//        if (length >= 16 && length / size >= 4) {
//            shrink();
//        }
//
//        if (isEmpty()) {
//            return null;
//        }
//
//        rear = minusOne(rear);
//        T result = array[rear];
//        array[rear] = null;
//        size--;
//        return result;
//    }
//
//    private boolean isFull() {
//        return size == length;
//    }
//
//    private int minusOne(int index) {
//        return index == 0 ? length - 1 : index - 1;
//    }
//
//    private int plusOne(int index, int length) {
//        return index == length - 1 ? 0 : index + 1;
//    }
//
//    public T get(int index) {
//        if (index >= size) {
//            return null;
//        }
//
//        int ptr = front;
//        for (int i = 0; i < index; i++) {
//            ptr = plusOne(ptr, length);
//        }
//        return array[ptr];
//    }
//
//    public void printDeque() {
//        int ptr = front;
//        while (ptr != rear) {
//            System.out.print(array[ptr] + " ");
//            ptr = plusOne(ptr, length);
//        }
//        System.out.println();
//    }
//
//    public void setLength(int length) {
//        this.length = length;
//    }
//}
public class ArrayDeque<T> {

    /** array to save data.*/
    private T[] array;
    /** size of the deque. */
    private int size;

    /** size of the array. */
    private int length;

    /** front index. */
    private int front;

    /** last index. */
    private int last;

    /** constructor for ArrayDeque. */
    public ArrayDeque() {
        array = (T[]) new Object[8];
        size = 0;
        length = 8;
        front = 4;
        last = 4;
    }

    /** decide if the deque is empty.
     * @return true if the deque is empty, vice versa.
     */
    public boolean isEmpty() {
        return size == 0;
    }

    /** return the size of the deque. */
    public int size() {
        return size;
    }

    /** return the "index - 1".
     * @param index index
     */
    private int minusOne(int index) {
        if (index == 0) {
            return length - 1;
        }
        return index - 1;
    }

    /** return the "index + 1".
     * @param index index
     */
    private int plusOne(int index, int module) {
        index %= module;
        if (index == module - 1) {
            return 0;
        }
        return index + 1;
    }

    private void grow() {
        T[] newArray = (T[]) new Object[length * 2];
        int ptr1 = front;
        int ptr2 = length;
        while (ptr1 != last) {
            newArray[ptr2] = array[ptr1];
            ptr1 = plusOne(ptr1, length);
            ptr2 = plusOne(ptr2, length * 2);
        }
        front = length;
        last = ptr2;
        array = newArray;
        length *= 2;
    }

    private void shrink() {
        T[] newArray = (T[]) new Object[length / 2];
        int ptr1 = front;
        int ptr2 = length / 4;
        while (ptr1 != last) {
            newArray[ptr2] = array[ptr1];
            ptr1 = plusOne(ptr1, length);
            ptr2 = plusOne(ptr2, length / 2);
        }
        front = length / 4;
        last = ptr2;
        array = newArray;
        length /= 2;
    }

    /** add one item at the front of the deque.
     * @param item the item we want to add
     */
    public void addFirst(T item) {
        if (size == length - 1) {
            grow();
        }
        front = minusOne(front);
        array[front] = item;
        size++;
    }

    /** add one item at the end of the deque.
     * @param item item we want to add
     */
    public void addLast(T item) {
        if (size == length - 1) {
            grow();
        }
        array[last] = item;
        last = plusOne(last, length);
        size++;
    }

    /** remove the first item.
     * @return the removed first item
     */
    public T removeFirst() {
        if (length >= 16 && length / size >= 4) {
            shrink();
        }
        if (size == 0) {
            return null;
        }
        T ret = array[front];
        front = plusOne(front, length);
        size--;
        return ret;
    }

    /** remove the last item.
     * @return the removed last item
     */
    public T removeLast() {
        if (length >= 16 && length / size >= 4) {
            shrink();
        }
        if (size == 0) {
            return null;
        }
        last = minusOne(last);
        size--;
        return array[last];
    }

    /** return the item indexed at index.
     * @param index index
     */
    public T get(int index) {
        if (index >= size) {
            return null;
        }
        int ptr = front;
        for (int i = 0; i < index; i++) {
            ptr = plusOne(ptr, length);
        }
        return array[ptr];
    }

    /** print the entire deque from front to end. */
    public void printDeque() {
        int ptr = front;
        while (ptr != last) {
            System.out.print(array[ptr] + " ");
            ptr = plusOne(ptr, length);
        }
    }

}