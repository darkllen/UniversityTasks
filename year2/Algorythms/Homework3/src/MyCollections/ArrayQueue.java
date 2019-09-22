package MyCollections;

import java.lang.reflect.Array;
import java.util.Iterator;
import java.util.NoSuchElementException;

public class ArrayQueue<Item> implements Iterable<Item>{

    protected Item[] s;
    protected int n =0;
    int head = 0;
    int tail = 0;

    public ArrayQueue(){
        s = (Item[])new Object[1];
    }

    public void enqueue(Item item) {
        if (item==null)throw new NullPointerException();
        if (n==s.length) resize(2*s.length);
        n++;
        tail = (tail+1) % (s.length);
        s[tail] = item;
    }


    public Item dequeue(){
        if (isEmpty())throw new NoSuchElementException();
        Item item = s[head];
        s[head]=null;
        head++;
        n--;
        if (n>0&&n==s.length/4) resize(s.length/2);
        return item;
    }


    public boolean isEmpty() {
        return n==0;
    }


    public int size() {
        return n;
    }

    private void resize(int capacity){
        Item[] copy = (Item[])new Object[capacity];
        int a = 0;
        if (head>tail){
            for (int i=head;i<s.length;i++,a++)
                copy[a]=s[i];
            for (int i = 0; i<=tail;i++,a++){
                copy[a]=s[i];
            }
        } else {
            for (int i=head;i<=tail;i++,a++)
                copy[a]=s[i];
        }
        head = 0;
        tail = a-1;

        s = copy;
    }

    @Override
    public Iterator<Item> iterator() {
        resize(s.length);
        return new ReverseArrayIterator();
    }

    private class ReverseArrayIterator implements Iterator<Item>{


        private int i=tail+1;
        @Override
        public boolean hasNext() {
            return i>head;
        }

        @Override
        public Item next() {
            return s[--i];
        }

        @Override
        public void remove() {
            throw new UnsupportedOperationException();
        }

    }
}
