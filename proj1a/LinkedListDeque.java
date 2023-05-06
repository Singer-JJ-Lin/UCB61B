/**
 * @author 老爷保号
 */
public class LinkedListDeque<T> {
    private class Node {
        private T item;
        private Node pre;

        private Node prev;

        private Node next;

        /** constructor for Node. */
        public Node(T item, Node pre, Node next) {
            this.item = item;
            this.pre = pre;
            this.next = next;
        }

        /** constructor for Node.(especially for sentinel node). */
        public Node(T item) {
            pre = null;
            next = null;
            this.item = item;
        }
    }

    private Node first;
    private Node last;
    private int size;

    /** constructor for deque. */
    public LinkedListDeque() {
        first = last = null;
        size = 0;
    }

    public boolean isEmpty() {
        return size == 0;
    }

    public int size() {
        return size;
    }

    public void addFirst(T item) {
        Node newNode = new Node(item);
        if(first == null) {
            first = last = newNode;
        } else {
            newNode.next = first;
            first.prev = newNode;
            first = newNode;
        }
        size++;
    }

    public void addLast(T item) {
        Node newNode = new Node(item);
        if(last == null) {
            first = last = newNode;
        } else {
            newNode.prev = last;
            last.next = newNode;
            last = newNode;
        }
        size++;
    }

    public T removeFirst() {
        if(size == 0) {
            return null;
        }
        T temp = first.item;
        if(size == 1) {
            first = last = null;
        } else {
            first = first.next;
            first.prev = null;
        }
        size--;
        return temp;
    }

    public T removeLast() {
        if(size == 0) {
            return null;
        }
        T temp = last.item;
        if(size == 1) {
            first = last = null;
        } else {
            last = last.prev;
            last.next = null;
        }
        size--;
        return temp;
    }

    public T get(int index) {
        if(index >= size) {
            return null;
        }
        if(index < size / 2) {
            Node ptr = first;
            for(int i = 1; i < size / 2 && i != index; i++) {
                ptr = ptr.next;
            }

            return ptr.item;
        } else {
            Node ptr = last;
            for(int i = size; i >= size / 2 && i != index; i--) {
                last = last.prev;
            }
            return last.item;
        }
    }

    private T getRecursiveHelp(Node start, int index) {
        if(index == 0) {
            return start.item;
        }
        return getRecursiveHelp(start.next, index - 1);
    }

    public T getRecursive(int index) {
        if(index >= size) {
            return null;
        }
        return getRecursiveHelp(first, index);
    }

    public void printDeque() {
        Node ptr = first;
        while (ptr != null) {
            System.out.print(ptr.item + " ");
            ptr = ptr.next;
        }
        System.out.println();
    }
}

