import java.util.LinkedList;

public class LinkedListDeque <Item> extends LinkedList <Item> implements Deque <Item>{
    private static class Node<Item> {
        Item item;
        Node<Item> prev;
        Node<Item> next;

        Node(Item element, Node<Item> prev, Node<Item> next) {
            this.item = element;
            this.next = next;
            this.prev = prev;
        }
    }

    private Node<Item> first;

    private Node<Item> last;

    private int size = 0;

    @Override
    public void addFirst(Item item) {
        final Node<Item> f = first;
        final Node<Item> newNode = new Node<>(item, null, first);
        first = newNode;
        if (f == null) {
            last = newNode;
        } else {
            f.prev = newNode;
        }
        size++;
    }

    @Override
    public void addLast(Item item) {
        final Node<Item> l = last;
        final Node<Item> newNode = new Node<>(item, last, null);
        last = newNode;
        if (l == null) {
            first = newNode;
        } else {
            l.next = newNode;
        }
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
        Node<Item> ptr = first;
        while (ptr != null) {
            System.out.print(ptr.item + " ");
            ptr = ptr.next;
        }
        System.out.println();
    }

    @Override
    public Item removeFirst() {
        if (first == null) {
            return null;
        }

        final Item element = first.item;
        final Node<Item> next = first.next;
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

    @Override
    public Item removeLast() {
        if (last == null) {
            return null;
        }

        final Item element = last.item;
        final Node<Item> prev = last.prev;
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

    @Override
    public Item get(int index) {
        if (!checkElementIndex(index)) {
            return null;
        }

        Node<Item> ptr;
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
}
