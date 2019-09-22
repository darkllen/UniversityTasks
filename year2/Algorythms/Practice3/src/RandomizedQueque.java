import java.util.Iterator;
import java.util.NoSuchElementException;
import java.util.Random;

public class RandomizedQueque<Item> implements Iterable<Item>{

    protected Item[] s;
    protected int n =0;

    public RandomizedQueque(){
        s = (Item[]) (new Object[1]);
    }

    public void enqueue(Item item) {
        if (item==null) throw new NullPointerException();
        if (n==s.length) resize(2*s.length);
        s[n++] = item;
    }


    public Item dequeue(){
        if (isEmpty()) throw new NoSuchElementException();
        int i = new Random().nextInt(size());
        Item item = s[i];
        s[i] = s[--n];
        s[n] = item;
        s[n]=null;
        if (n>0&&n==s.length/4) resize(s.length/2);
        return item;
    }

    public Item sample(){
        int i = new Random().nextInt(size());
        return s[i];
    }

    public boolean isEmpty() {
        return n==0;
    }

    public int size() {
        return n;
    }

    protected void resize(int capacity){
        Item[] copy =(Item[]) (new Object[capacity]);
        for (int i=0;i<n;i++)
            copy[i]=s[i];
        s = copy;
    }

    @Override
    public Iterator<Item> iterator() {
        return new RandomIterator();
    }

    private class RandomIterator implements Iterator<Item>{
        private int i=n;
        @Override
        public boolean hasNext() {
            return i>0;
        }

        @Override
        public Item next() {
            int curr = new Random().nextInt(i);
            Item a = s[curr];
            s[curr] = s[--i];
            s[i] = a;
            return a;
        }
    }
}