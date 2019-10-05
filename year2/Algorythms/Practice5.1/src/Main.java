import java.io.*;
import java.util.Arrays;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int n = Integer.parseInt(br.readLine());
        Item arr[] = new Item[n];
        for(int i = 0; i< n; i++){
            String[] s = br.readLine().split(" ");
            arr[i] = new Item(Integer.parseInt(s[0]), Integer.parseInt(s[1]));
        }
        Arrays.sort(arr);
        BufferedWriter writer = new BufferedWriter(new OutputStreamWriter(System.out));
        for (Item a:arr) {
           writer.write(a.toString());
           writer.newLine();
        }
        writer.flush();
    }

    static class Item implements Comparable{
        public int x;
        public int y;

        public Item() {
        }

        public int getX() {
            return x;
        }

        public Item(int x, int y) {
            this.x = x;
            this.y = y;
        }


        @Override
        public String toString() {
            return  x + " " + y;
        }

        @Override
        public int compareTo(Object o) {
            if (this.x>((Item)o).x) return 1;
            if (this.x<((Item)o).x) return -1;
            return 0;
        }
    }
}