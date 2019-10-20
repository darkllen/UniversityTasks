import java.io.*;
import java.util.Collections;
import java.util.LinkedList;


public class Main {
    public static void main(String[] args) throws IOException {

        BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bufferedWriter = new BufferedWriter(new OutputStreamWriter(System.out));

     while(bufferedReader.ready()){
            String[] s = bufferedReader.readLine().split(" ");
        LinkedList<Integer> linkedList = new LinkedList<>();
            for (int i = 0; i < s.length; i++) {
                linkedList.add(Integer.parseInt(s[i]));
            }
            Collections.sort(linkedList);

            while(linkedList.get(0)!=0){
                if(linkedList.get(0)>linkedList.size()-1){
                    bufferedWriter.write("fail");
                    bufferedWriter.newLine();
                    bufferedWriter.newLine();
                    break;
                }
                int y = 0;
                for(int i = 0; i< linkedList.get(0);i++){
                    linkedList.set(linkedList.size()-1-i+y,linkedList.get(linkedList.size()-1-i+y)-1);
                    if(linkedList.get(linkedList.size()-1-i+y)==0){
                        linkedList.remove(linkedList.size()-i-1+y);
                        y++;
                    }
                }
                linkedList.remove(0);
                if (linkedList.size()==0){
                    bufferedWriter.write("ok");
                    bufferedWriter.newLine();
                    bufferedWriter.newLine();
                    break;
                }
            Collections.sort(linkedList);
            }

        }
        bufferedWriter.flush();

    }
}
