import java.util.LinkedList;

public class LinkedListDeque<E> {
    private static class Node<E> {
        E item;
        Node<E> prev;
        Node<E> next;

        Node(E element, Node<E> prev, Node<E> next) {
            this.item = element;
            this.next = next;
            this.prev = prev;
        }
    }

    private Node<E> first;

    private Node<E> last;

    private int size = 0;

    public LinkedListDeque() {
    }

    public boolean isEmpty() {
        return size == 0;
    }

    public void addFirst(E item) {
        final Node<E> f = first;
        final Node<E> newNode = new Node<>(item, null, null);
        first = newNode;
        if (f == null) {
           last = newNode;
        } else {
            f.prev = newNode;
        }
        size++;
    }

    public void addLast(E item) {
        final Node<E> l = last;
        final Node<E> newNode = new Node<>(item, null, null);
        last = newNode;
        if (l == null) {
           first = newNode;
        } else {
            l.next = newNode;
        }
        size++;
    }

    public E removeFirst() {
        if (first == null) {
            return null;
        }

        final E element = first.item;
        final Node<E> next = first.next;
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

    public E removeLast() {
        if (last == null) {
            return null;
        }

        final E element = last.item;
        final Node<E> prev = last.prev;
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

    public E get(int index) {
        if (!checkElementIndex(index)) {
            return null;
        }

        if (index < (size >> 1)) {
            Node<E> ptr = first;
            for(int i = 0; i < index; i++) {
                ptr = ptr.next;
            }
            return ptr.item;
        } else {
            Node<E> ptr = last;
            for(int i = size - 1; i > index; i--) {
                ptr = ptr.prev;
            }

            return ptr.item;
        }
    }

    private boolean checkElementIndex(int index) {
        return index >= 0 && index < size;
    }

    public void printDeque() {
        Node<E> ptr = first;
        while (ptr != null) {
            System.out.print(ptr.item + " ");
            ptr = ptr.next;
        }
        System.out.println();
    }

    private E getRecursiveHelp(Node<E> start, int index) {
        if (index == 0) {
            return start.item;
        } else {
            return getRecursiveHelp(start.next, index - 1);
        }
    }

    public E getRecursive(int index) {
        if (!checkElementIndex(index)) {
            return null;
        }
        return getRecursiveHelp(first, index);
    }


}
