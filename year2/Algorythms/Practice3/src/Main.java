import java.util.Iterator;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Deque<Integer> a = new Deque<>();
        a.addFirst(1);
        a.addLast(4);
        a.addLast(3);
        a.addLast(5);
        a.addFirst(2);
        Iterator<Integer> i = a.iterator();
        while(i.hasNext()){
            int s = i.next();
            System.out.println(s);
        }
        System.out.println( " ");
        System.out.println( a.removeFirst());
        System.out.println( a.removeFirst());
        System.out.println( a.size());
        System.out.println( a.isEmpty());
        System.out.println( a.removeFirst());
        System.out.println( a.isEmpty());
        System.out.println( a.removeLast());

        System.out.println( " ");
        RandomizedQueque<Integer> b = new RandomizedQueque<>();
        b.enqueue(3);
        b.enqueue(5);
        b.enqueue(6);
        b.enqueue(2);
        i = b.iterator();
        while(i.hasNext()){
            int s = i.next();
            System.out.println(s);
        }
        System.out.println( " ");
        System.out.println( b.size());
        System.out.println( b.isEmpty());
        System.out.println( b.sample());
        System.out.println( b.sample());
        System.out.println( b.dequeue());
        System.out.println( b.dequeue());
        System.out.println( b.isEmpty());
    }
}
