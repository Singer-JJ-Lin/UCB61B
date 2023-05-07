public class ArrayDeque<T> {
    private final int INITIAL_SIZE = 8;

    private final int INCREMENT = 8;

    private T[] array;
    /** size of deque **/
    private int size;
    /** length of array **/
    private int length;

    private int front;
    private int rear;

    public ArrayDeque() {
        array = (T[]) new Object[INITIAL_SIZE];
        size = 0;
        setLength(INITIAL_SIZE);
        front = rear = 0;
    }

    public boolean isEmpty() {
        return size == 0;
    }

    public int size() {
        return size;
    }

    private void grow() {
        T[] newArray = (T[]) new Object[length + INCREMENT];
        for (int i = 0; i < size; i++, front = (front + 1) % length) {
            newArray[i] = array[front];
        }

        array = null;
        array = newArray;
        setLength(length + INCREMENT);
        rear = size + 1;
    }

    private void shrink() {
        T[] newArray = (T[]) new Object[length >> 1];
        for (int i = 0; i < size; i++, front = (front + 1) % length << 1) {
            newArray[i] = array[front];
        }

        front = 0;
        rear = size + 1;
        array = null;
        array = newArray;
        setLength(length / 2);
    }

    public void addFirst(T item) {
        if (isFull()) {
            grow();
        }
        front = minusOne(front);
        array[front] = item;
        size++;
    }

    public void addLast(T item) {
        if (isFull()) {
            grow();
        }

        array[rear] = item;
        rear = plusOne(rear, length);
        size++;
    }

    public T removeFirst() {
        if (length >= 16 && length / size >= 4) {
            shrink();
        }

        if (isEmpty()) {
            return null;
        }

        T result = array[front];
        array[front] = null;
        front = plusOne(front, length);
        size--;
        return result;
    }

    public T removeLast() {
        if (length >= 16 && length / size >= 4) {
            shrink();
        }

        if (isEmpty()) {
            return null;
        }

        rear = minusOne(rear);
        T result = array[rear];
        array[rear] = null;
        size--;
        return result;
    }

    private boolean isFull() {
        return size == length;
    }

    private int minusOne(int index) {
        return index == 0 ? length - 1 : index - 1;
    }

    private int plusOne(int index, int length) {
        return index == length - 1 ? 0 : index + 1;
    }

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

    public void printDeque() {
        int ptr = front;
        while (ptr != rear) {
            System.out.print(array[ptr] + " ");
            ptr = plusOne(ptr, length);
        }
        System.out.println();
    }

    public void setLength(int length) {
        this.length = length;
    }
}
