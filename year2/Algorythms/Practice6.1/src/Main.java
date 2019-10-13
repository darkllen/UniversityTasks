import sun.plugin.javascript.navig.Array;

import java.io.*;
import java.util.Arrays;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bufferedWriter = new BufferedWriter(new OutputStreamWriter(System.out));
        int n = Integer.parseInt(bufferedReader.readLine());
        for (int i = 0; i< n; i++){
            bufferedReader.readLine();
            String s[] = bufferedReader.readLine().split(" ");
            int a = Integer.parseInt(s[0]);
            int b = Integer.parseInt(s[1]);
            DNA[] arr = new DNA[b];
            for (int j = 0; j< b; j++){
                arr[j] = new DNA(bufferedReader.readLine());
            }
            Arrays.sort(arr);
            for (DNA dna : arr) {
                bufferedWriter.write(dna.toString());
                bufferedWriter.newLine();
            }
            bufferedWriter.newLine();
        }
        bufferedWriter.flush();
    }


    static class DNA implements Comparable{
        String value;
        int sort;

        public DNA(String value) {
            this.value = value;
            sort = 0;
            byte[] arr =value.getBytes();
           for (int i = 0; i< arr.length-1; ++i){
               for (int j = i+1; j< arr.length; j++){
                   if (arr[i]>arr[j]) sort++;
               }
           }
        }

        @Override
        public String toString() {
            return value;
        }

        @Override
        public int compareTo(Object o) {
            return Integer.compare(this.sort, ((DNA) o).sort);
        }
    }
}
