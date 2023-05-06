public class ArrayDeque<T> {
    public final int INITIAL_SIZE = 16;

    public final int INCREMENT = 8;

    private T[] array;
    /** size of deque **/
    private int size;
    /** length of array **/
    private int length;

    private int front;
    private int rear;

    public ArrayDeque() {
        array = (T[])new Object[INITIAL_SIZE];
        size = 0;
        length = INITIAL_SIZE;
        front = rear = 0;
    }

    public boolean isEmpty() {
        return size == 0;
    }

    public int size() {
        return size;
    }

    private void grow() {
        T[] newArray = (T[])new Object[length + INCREMENT];
        for(int i = 0; i < size; i++, front = (front + 1) % length) {
            newArray[i] = array[front];
        }

        array = null;
        array = newArray;
        length = length + INCREMENT;
    }

    public void addFirst(T item) {
        if(size == length) {
            grow();
            array[size + 1] = item;
        }else {
            array[rear] = item;
            rear = (rear + 1) % length;
        }
        size++;
    }

    public void 
}
