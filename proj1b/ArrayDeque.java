/**
 * @author 老爷保号
 */
public class ArrayDeque<T> implements Deque<T> {

    private T[] array;

    private int size;

    private int capacity;

    private static final int INITIAL_CAPACITY = 8;

    private int front;

    private int rear;

    public ArrayDeque() {
        array = (T[]) new Object[INITIAL_CAPACITY];
        size = 0;
        capacity = INITIAL_CAPACITY;
        front = rear = 0;
    }

    private int minusOne(int index) {
        return index == 0 ? capacity - 1 : index - 1;
    }

    private int plusOne(int index) {
        return index == capacity - 1 ? 0 : index + 1;
    }

    private void grow() {
        T[] newArray = (T[]) new Object[capacity << 1];
        for (int i = 0; i < capacity; i++) {
            newArray[i] = array[front];
            front = plusOne(front);
        }
        front = 0;
        rear = size;
        capacity = capacity << 1;
        array = newArray;
    }

    private void shrink() {
        T[] newArray = (T[]) new Object[capacity >> 1];
        int i;
        for (i = 0; front != rear; i++, front = plusOne(front)) {
            newArray[i] = array[front];
        }
        rear = i;
        front = 0;
        capacity = capacity >> 1;
        array = newArray;
    }

    @Override
    public void addFirst(T item) {
        if (size == capacity - 1) {
            grow();
        }
        front = minusOne(front);
        array[front] = item;
        size++;
    }

    @Override
    public void addLast(T item) {
        if (size == capacity - 1) {
            grow();
        }
        array[rear] = item;
        rear = plusOne(rear);
        size++;
    }

    @Override
    public boolean isEmpty() {
        return size == 0;
    }

    @Override
    public int size() {
        return size;
    }

    @Override
    public void printDeque() {
        int ptr = front;
        while (ptr != rear) {
            System.out.print(array[ptr] + " ");
            ptr = plusOne(ptr);
        }
        System.out.println();
    }

    @Override
    public T removeFirst() {
        if (capacity >= 16 && capacity / size >= 4) {
            shrink();
        }

        if (size == 0) {
            return null;
        }

        T ret = array[front];
        front = plusOne(front);
        size--;
        return ret;
    }

    @Override
    public T removeLast() {
        if (capacity >= 16 && capacity / size >= 4) {
            shrink();
        }
        if (size == 0) {
            return null;
        }
        rear = minusOne(rear);
        size--;
        return array[rear];
    }

    @Override
    public T get(int index) {
        if (index >= size) {
            return null;
        }
        int ptr = front;
        for (int i = 0; i < index; i++) {
            ptr = plusOne(ptr);
        }
        return array[ptr];
    }
}
