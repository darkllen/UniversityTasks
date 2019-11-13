package Arrs;

import java.util.Iterator;
import java.util.NoSuchElementException;

public class Deque<Item> implements Iterable<Item>{

    private Node first = null;
    private Node last = null;
    private int count=0;

    private class Node{
        Item item;
        Node next;
        Node prev;
    }

    public void addFirst(Item item) {
        if (item==null)throw new NullPointerException();
        Node oldFirst = first;
        first = new Node();
        first.item=item;
        first.next=oldFirst;
        if (count==0) last = first;
        else  oldFirst.prev = first;
        count++;
    }
    public void addLast(Item item) {
        if (item==null)throw new NullPointerException();
        Node oldLast = last;
        last = new Node();
        last.item=item;
        last.prev=oldLast;
        if (count==0) first = last;
        else    oldLast.next = last;
        count++;
    }
    public Item removeFirst() {
        if (count==0) throw new NoSuchElementException();
        Item item = first.item;
        first = first.next;
        count--;
        return item;
    }
    public Item removeLast() {
        if (count==0) throw new NoSuchElementException();
        Item item = last.item;
        last = last.prev;
        count--;
        return item;
    }
    public boolean isEmpty() {
        return first == null;
    }
    public int size() {
        return count;
    }

    @Override
    public Iterator<Item> iterator() {
        return new DequeIterator();
    }

    private class DequeIterator implements Iterator<Item>{
        private Node current = first;
        @Override
        public boolean hasNext() {
            return current!=null;
        }

        @Override
        public Item next() {
            Item item = current.item;
            current = current.next;
            return item;
        }
    }
}
