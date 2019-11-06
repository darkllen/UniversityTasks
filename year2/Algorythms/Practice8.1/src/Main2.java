import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
public class Main2 {
    public static void main(String[] args) throws IOException {
        BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(System.in));
        String[] s = bufferedReader.readLine().split(" ");
        int n = Integer.parseInt(s[0]);
        int k = Integer.parseInt(s[1]);
        s = bufferedReader.readLine().split(" ");
        long[] arr = new long[n];
        ArrayList<Struct> set = new ArrayList<>();
        for (int i = 0; i < n; i++) {
            arr[i] = Long.parseLong(s[i]);
        }

        for(int i = 0; i<n-1; i++){
            set.add(new Struct(i, i+1, arr[i+1]-arr[i]));
        }

        for(int i = 0; i<(n-k); i++){
            int min = findMin(set);
            if(min == set.size()-1){
                set.get((min-1)).indexB = set.get((min)).indexB;
                set.get(min-1).value += set.get(min).value;
            } else if( min == 0){
                set.get((min+1)).indexA = set.get((min)).indexA;
                set.get(min+1).value += set.get(min).value;
            } else
            if(arr[set.get(min).indexA]-arr[set.get(min).indexA-1]>arr[set.get(min).indexB+1]-arr[set.get(min).indexB]){
                set.get((min+1)).indexA = set.get((min)).indexA;
                set.get(min+1).value += set.get(min).value;
            } else{
                set.get((min-1)).indexB = set.get((min)).indexB;
                set.get(min-1).value += set.get(min).value;
            }
            set.remove(min);
        }
        System.out.println(set.get(findMin(set)).value);


    }

    static int findMin(ArrayList<Struct> arr){
        Struct min = arr.get(0);
        int num = 0;
        for (int i = 1; i < arr.size(); i++) {
            if( min.compareTo(arr.get(i))>=0) {
                min = arr.get(i);
                num = i;
            }
        }
        return num;
    }

    static class Struct implements Comparable<Struct>{
        int indexA;
        int indexB;
        long value;

        public Struct(int indexA, int indexB, long value) {
            this.indexA = indexA;
            this.indexB = indexB;
            this.value = value;
        }

        @Override
        public int compareTo(Struct o) {
            return Long.compare(value, o.value);
        }
    }
}