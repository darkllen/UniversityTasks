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

    /**
     * add element to the last position
     * @param val
     */
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

    /**
     *
     * @param index from 0 to (size-1)
     * @param val
     */
    public void addByIndex(int index, T val){
        if (isEmpty() || index>=size || index<0 || val ==null) throw new IllegalStateException("impossible to add.");;
        if (index==0){
            Node element = getByIndex(0);
            Node node = new Node(val, element);
            first=node;
        } else {
            Node element = getByIndex(index);
            Node elementPrev = getByIndex(index - 1);
            Node node = new Node(val, element);
            elementPrev.next = node;
        }
    }


    /**
     *
     * @param index from 0 to (size-1)
     * @return element value on index
     */
    public Node getByIndex(int index)
    {
        if (index < 0 || index >= size || isEmpty())
            throw new IndexOutOfBoundsException("Index: "+index+", Size: "+size);

        Node e = first;

        for (int i = 0; i < index; i++){
            e = e.next;
        }


        return e;
    }


    /**
     *
     * @param index from 0 to (size-1)
     * @return true if index exist, false if array is empty or if index is not in range
     */
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

    /**
     *
     * @param val value that need to be deleted
     * @return true if remove element, false if element doesn`t exist or list is empty
     */
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

    /**
     *
     * @return number of elements
     */
    public int size() {
        return size;
    }

    /**
     *
     * @return true if empty
     */
    public boolean isEmpty() {
        return size == 0;
    }

    public Iterator<T> iterator() { return new MyLinkedListIterator(); }

    /**
     * iterator from first element to the last
     */
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

    /**
     * class for each Element
     */
    private class Node {
        private T value;
        private Node next;

        public Node(T value, Node next) {
            this.value = value;
            this.next = next;
        }

        @Override
        public String toString() {
            return value.toString();
        }
    }

    @Override public String toString() {
        StringBuilder s = new StringBuilder();
        for (T item : this)
            s.append(item + " ");
        return s.toString();
    }

}