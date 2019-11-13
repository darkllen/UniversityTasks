import java.io.*;
import java.util.Arrays;

public class Main {
    public static void main(String[] args) throws Exception {
        BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter writer = new BufferedWriter(new OutputStreamWriter(System.out));
        int t = Integer.parseInt(bufferedReader.readLine());
        for (int i = 0; i< t; i++){
            int n = Integer.parseInt(bufferedReader.readLine());
            Struct arr[] = new Struct[n];
            for(int j = 0; j< n; j++){
                byte[] num = new byte[10];
                byte a = (byte) bufferedReader.read();
                int count = 0;
                while (a!=10){
                    num[count]=a;
                    count++;
                    a = (byte) bufferedReader.read();
                }
                arr[j] = new Struct(num, (byte) count);
            }
            Arrays.sort(arr);
            String res = "YES";
            boolean y = false;
            for(int k = 0; k<n-1 ; k++){
                        if(arr[k].isPrefix(arr[k+1])){
                            res = "NO";
                            y = true;
                            break;
                        }
                    if(y)break;
                }
            writer.write(res);
            writer.newLine();
        }
        writer.flush();
    }
static class Struct implements Comparable<Struct>{
        byte[] arr;
        byte zeroIndex;

    public Struct(byte[] arr, byte zeroIndex) {
        this.arr = arr;
        this.zeroIndex = zeroIndex;
    }

    public boolean isPrefix(Struct o){
        for(int i = 0; i< zeroIndex; i++){
            if(this.arr[i]!=o.arr[i]){
                return false;
            }
        }
        return true;
    }

    @Override
    public int compareTo(Struct o) {
      {
            byte[] arrThis = this.arr;
            byte[] arrO = o.arr;
            for(int i = 0; i<10; i++){
                if(arrThis[i]>arrO[i]){
                    return 1;
                }
                if(arrThis[i]<arrO[i]){
                    return -1;
                }

            }
            return 0;
        }

    }
}
}
