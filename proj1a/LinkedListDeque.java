/**
 * @author 老爷保号
 */
public class LinkedListDeque<T> {
    private static class Node<T> {
        T item;
        Node<T> prev;
        Node<T> next;

        Node(T element, Node<T> prev, Node<T> next) {
            this.item = element;
            this.next = next;
            this.prev = prev;
        }
    }

    private Node<T> first;

    private Node<T> last;

    private int size = 0;

    public LinkedListDeque() {
    }

    public boolean isEmpty() {
        return size == 0;
    }

    public int size() {
        return size;
    }

    public void addFirst(T item) {
        final Node<T> f = first;
        final Node<T> newNode = new Node<>(item, null, first);
        first = newNode;
        if (f == null) {
            last = newNode;
        } else {
            f.prev = newNode;
        }
        size++;
    }

    public void addLast(T item) {
        final Node<T> l = last;
        final Node<T> newNode = new Node<>(item, last, null);
        last = newNode;
        if (l == null) {
            first = newNode;
        } else {
            l.next = newNode;
        }
        size++;
    }

    public T removeFirst() {
        if (first == null) {
            return null;
        }

        final T element = first.item;
        final Node<T> next = first.next;
        first.item = null;
        first.next = null;
        first = next;

        if (next == null) {
            last = null;
        } else {
            next.prev = null;
        }

        size--;
        return element;
    }

    public T removeLast() {
        if (last == null) {
            return null;
        }

        final T element = last.item;
        final Node<T> prev = last.prev;
        last.item = null;
        last.prev = null;
        last = prev;

        if (prev == null) {
            first = null;
        } else {
            prev.next = null;
        }
        size--;
        return element;
    }

    public T get(int index) {
        if (!checkElementIndex(index)) {
            return null;
        }

        Node<T> ptr;
        if (index < (size >> 1)) {
            ptr = first;
            for (int i = 0; i < index; i++) {
                ptr = ptr.next;
            }
        } else {
            ptr = last;
            for (int i = size - 1; i > index; i--) {
                ptr = ptr.prev;
            }

        }
        return ptr.item;
    }

    private boolean checkElementIndex(int index) {
        return index >= 0 && index < size;
    }

    public void printDeque() {
        Node<T> ptr = first;
        while (ptr != null) {
            System.out.print(ptr.item + " ");
            ptr = ptr.next;
        }
        System.out.println();
    }

    private T getRecursiveHelp(Node<T> start, int index) {
        if (index == 0) {
            return start.item;
        } else {
            return getRecursiveHelp(start.next, index - 1);
        }
    }

    public T getRecursive(int index) {
        if (!checkElementIndex(index)) {
            return null;
        }
        return getRecursiveHelp(first, index);
    }


}
