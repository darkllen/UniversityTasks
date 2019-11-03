import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Collections;

public class Main2 {
    public static void main(String[] args) throws IOException {
        BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(System.in));
        String[] s = bufferedReader.readLine().split(" ");
        int n = Integer.parseInt(s[0]);
        int k = Integer.parseInt(s[1]);
        s = bufferedReader.readLine().split(" ");
        long[] arr = new long[n];
        for(int i = 0; i< n; i++){
            arr[i] = Long.parseLong(s[i]);
        }


        ArrayList<Integer> set = new ArrayList<>();
        set.add(0);
        set.add(arr.length-1);

        if(k>2){
            double m = middle(arr, 0, arr.length-1);
            int i = indexMedian(arr,m,0,arr.length-1 );
            MinPQ<Struct> a = new MinPQ<>(n);
            a.insert(new Struct(0, i, middle(arr, 0, i), arr));
            a.insert(new Struct(i, arr.length-1, middle(arr, i, arr.length-1), arr));

            set.add(i);
            for(int g = 1; g< k-2;g++){
                Struct struct = a.delMax();
                if(struct.high-struct.low<=1){
                    set.add( struct.high);
                    g--;
                    continue;
                }
                m = middle(arr, struct.low, struct.high);
                i = indexMedian(arr, m, struct.low, struct.high);
                a.insert(new Struct(struct.low,i, middle(arr, struct.low, i), arr));
                a.insert(new Struct(i, struct.high, middle(arr, i, struct.high), arr));
                set.add(i);
            }
        }

        Collections.sort(set);
        long min = arr[set.get(1)] - arr[set.get(0)];
        for(int j = 0; j<set.size()-1;j++){
            if(set.get(j).equals(set.get(j + 1)))continue;
            if(min> arr[set.get(j + 1)] - arr[set.get(j)])min = arr[set.get(j + 1)] - arr[set.get(j)];
        }
        System.out.println(min);

    }

    static  class Struct implements Comparable<Struct>{
        int low;
        int high;
        double middle;
        double dif;

        public Struct(int low, int high, double middle, long[] arr) {
            this.low = low;
            this.high = high;
            this.middle = middle;
            int y = indexMedian(arr,middle,low,high);
            dif = arr[y] -arr[low]>= arr[high] - arr[y] ? arr[high] - arr[y]:arr[y] -arr[low];
        }

        @Override
        public int compareTo(Struct o) {
            return Double.compare(dif, o.dif);
        }
    }
    static double middle(long[] arr, int low, int high){
        long x  = 0;
        for (int i = low; i <= high; i++) {
           x+=arr[i];
        }
        return x/(double)(high-low+1);
    }
   static int indexMedian(long[] arr, double middle, int low, int high){
        int k = low + (high-low)/2;
        if(middle == arr[k])return k;
        if(high-low==1){
            double y = arr[high] - middle;
            double h = middle - arr[low];
            if(y>h) return low;
            else
            return high;
        }
        if(high-low==0) return low;
        else if(middle>arr[k]) return indexMedian(arr,middle, k, high);
        else if(middle<arr[k]) return indexMedian(arr,middle, low, k);

        return 0;
    }

    public static class MinPQ<Key extends Comparable<Key>> {

        private Key[] pq;
        private int n;

        public MinPQ(int capacity){
            pq = (Key[]) new Comparable[capacity+1];
        }

        public boolean isEmty(){
            return n ==0;
        }

        public void insert(Key key){
            pq[++n] = key;
            swim(n);
        }

        public Key delMax(){
            Key max = pq[1];
            exch(1,n--);
            sink(1);
            pq[n+1]=null;
            return max;
        }

        private void swim(int k){
            while (k>1 && less(k/2,k)){
                exch(k,k/2);
                k=k/2;
            }
        }

        private void sink(int k){
            while(2*k<=n){
                int j = 2*k;
                if (j<n&&less(j,j+1)) j++;
                if (!less(k,j)) break;
                exch(k,j);
                k=j;
            }
        }

        private boolean less(int i, int j){
            return pq[i].compareTo(pq[j])<0;
        }

        private void exch(int i, int j){
            Key t = pq[i];
            pq[i] = pq[j];
            pq[j] = t;
        }

    }
}
