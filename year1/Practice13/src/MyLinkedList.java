import java.util.Iterator;
import java.util.NoSuchElementException;

public class MyLinkedList<T> implements Iterable<T> {
    private Node first;
    private Node last;
    private int size;

    public MyLinkedList() {
        first = null;
        last = null;
        size = 0;
    }

    public void add(T val) {
        if (val == null) { throw new NullPointerException("The first argument cannot be null."); }
        if (!isEmpty()) {
            Node prev = last;
            last = new Node(val, null);
            prev.next = last;
        }
        else {
            last = new Node(val, null);
            first = last;
        }
        size++;
    }


    public boolean removeByIndex(int index){
        if (isEmpty() || index>size || index<0)return false;
        Node node = first;
        Node prev = null;
        for (int i = 0; i<index;i++){
            prev = node;
            node = node.next;
        }
        prev.next = node.next;
        size--;
        return true;
    }

    public boolean remove(T val) {
        if (isEmpty()) { throw new IllegalStateException("Cannot remove from empty list."); }
        boolean result = false;
        Node prev = first;
        Node curr = first;
        while (curr.next != null || curr == last) {
            if (curr.value.equals(val)) {
                // remove the last remaining element
                if (size == 1) { first = null; last = null; }
                // remove first element
                else if (curr.equals(first)) { first = first.next; }
                // remove last element
                else if (curr.equals(last)) { last = prev; last.next = null; }
                // remove element
                else { prev.next = curr.next; }
                size--;
                result = true;
                break;
            }
            prev = curr;
            curr = prev.next;
        }
        return result;
    }

    public int size() {
        return size;
    }

    public boolean isEmpty() {
        return size == 0;
    }

    private class Node {
        private T value;
        private Node next;

        public Node(T value, Node next) {
            this.value = value;
            this.next = next;
        }
    }

    public Iterator<T> iterator() { return new MyLinkedListIterator(); }

    private class MyLinkedListIterator implements Iterator<T> {
        private Node current = first;

        public T next() {
            if (!hasNext()) { throw new NoSuchElementException(); }
            T item = current.value;
            current = current.next;
            return item;
        }

        public boolean hasNext() { return current != null; }

        public void remove() { throw new UnsupportedOperationException(); }
    }

    @Override public String toString() {
        StringBuilder s = new StringBuilder();
        for (T item : this)
            s.append(item + " ");
        return s.toString();
    }

}