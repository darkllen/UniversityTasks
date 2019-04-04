import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Scanner;

public class Main {

    public String checkStr(String str) {
        while (true){
            for (int i = 0;i<str.length();i++){
                if (str.charAt(i)!='0'&&str.charAt(i)!='1'&&str.charAt(i)!='2'&&str.charAt(i)!='3'&&str.charAt(i)!='4'&&str.charAt(i)!='5'&&str.charAt(i)!='6'&&str.charAt(i)!='7'&&str.charAt(i)!='8'&&str.charAt(i)!='9'&&str.charAt(i)!='A'&&str.charAt(i)!='B'&&str.charAt(i)!='C'&&str.charAt(i)!='D'&&str.charAt(i)!='E'&&str.charAt(i)!='F'){
                    System.out.print("Wrong input, impossible number (number can use 1-9, A-F): ");
                    str = checkStr(getString());
                    break;
                }

            }return str;
        }
    }
    public static void main(String[] args) {
        Main main = new Main();

        System.out.print("Input natural number N: ");
        String str = getString();
        str = main.checkStr(str);

        String n = main.parseForLetter(str);
        ArrayList<Integer> arrayList = main.getNumsArray(n);

        System.out.print("Input number system p of N: ");
        int p = getInt();
        while (p<=1||p>16){
            System.out.print("System p should be from 2 to 16: ");
            p=getInt();
            for (int i =0;i<arrayList.size();i++){
                if (arrayList.get(i)>=p){
                    System.out.print("Wrong system, impossible num: ");
                    p=getInt();
                    break;
                }
            }
        }

        System.out.print("Input number system q of M: ");
        int q = getInt();
        while (q<=1||q>16){
            System.out.print("System q should be from 2 to 16: ");
            q=getInt();
        }



        int ten = main.getTenSysten(arrayList,p);
        System.out.println("Result in " + q + " system: " +main.getQSystem(ten,q));
    }

    public String parseForLetter(String str){
        str = str.replaceAll("0","0 ");
        str = str.replaceAll("1","1 ");
        str = str.replaceAll("2","2 ");
        str = str.replaceAll("3","3 ");
        str = str.replaceAll("4","4 ");
        str = str.replaceAll("5","5 ");
        str = str.replaceAll("6","6 ");
        str = str.replaceAll("7","7 ");
        str = str.replaceAll("8","8 ");
        str = str.replaceAll("9","9 ");
        str = str.replaceAll("A","10 ");
        str = str.replaceAll("B","11 ");
        str = str.replaceAll("C","12 ");
        str = str.replaceAll("D","13 ");
        str = str.replaceAll("E","14 ");
        str = str.replaceAll("F","15 ");
      return str;
    }

    public static int getInt(){
        while (true) {
            Scanner scanner = new Scanner(System.in);
            while (true) {
                try {
                    return scanner.nextInt();
                } catch (Exception e) {
                    System.out.print("Wrong output, try again: ");
                    break;
                }
            }
        }
    }

    public String getQSystem(int temNum, int q){
        ArrayList<Integer> result = new ArrayList<>();
        while(temNum/q!=0){
            result.add(temNum%q);
            temNum=temNum/q;
        }
        result.add(temNum);
        Collections.reverse(result);

        ArrayList<String> res = new ArrayList<>();
        for (int i=0;i<result.size();i++){
            res.add(String.valueOf(result.get(i)));
        }
        String f = "";
        for (int i=0;i<res.size();i++){
            f+= res.get(i)+" ";
        }
        f = f.replaceAll("10","A");
        f = f.replaceAll("11","B");
        f = f.replaceAll("12","C");
        f = f.replaceAll("13","D");
        f = f.replaceAll("14","E");
        f = f.replaceAll("15","F");
        f = f.replaceAll(" ", "");


        return f;
    }

    public int getTenSysten(ArrayList<Integer> arrayList, int p){

        int res = 0;
        for (int i =0;i<arrayList.size();i++){
            res+=arrayList.get(i)*Math.pow(p,i);
        }
        return res;
    }

    public ArrayList<Integer> getNumsArray(String n){
        ArrayList<Integer> arrayList = new ArrayList<>();
        String[] strings = n.split(" ");

        for (int i =0;i<strings.length;i++){
            arrayList.add(Integer.parseInt(strings[i]));
        }
        Collections.reverse(arrayList);
        return arrayList;
    }

    public static String getString(){
        Scanner scanner = new Scanner(System.in);
        String str = scanner.nextLine();
        return str;
    }
}
