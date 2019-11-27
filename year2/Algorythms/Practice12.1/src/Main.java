import java.io.*;
import java.util.ArrayList;
import java.util.Collections;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(System.in));
        int n = Integer.parseInt(bufferedReader.readLine());
        Elem[] array = new Elem[n];
        for (int i = 0; i < array.length; i++) {
            array[i] = new Elem();
        }
        for(int i = 0; i< n; i++){
            String[] l = bufferedReader.readLine().split(" ");
            for(int j = 0; j< l.length; j++){
                if (l[j].length()>0)
                array[Integer.parseInt(l[j])-1].arr.add(i);
            }
        }
        BufferedWriter writer = new BufferedWriter(new OutputStreamWriter(System.out));
        StringBuilder res = new StringBuilder();
        //writer.write(n);
        //writer.newLine();
        res.append(n);
        res.append("\n");
        for(int i = 0; i< n; i++){
            Collections.sort(array[i].arr);
            if (array[i].arr.size()>0)
            //writer.write(array[i].arr.get(0));
                res.append(array[i].arr.get(0)+1);
            for(int j = 1; j< array[i].arr.size(); j++){
            //writer.write(" ");
            //writer.write(array[i].arr.get(j));
                res.append(" ");
                res.append(array[i].arr.get(j)+1);
            }
            //writer.newLine();
            res.append("\n");
        }
        System.out.println(res);
    }
    static class Elem{
        ArrayList<Integer> arr = new ArrayList<>();
    }
}
