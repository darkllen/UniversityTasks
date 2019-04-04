import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        System.out.print("Input number of students: ");
        int n = getInt();
        while(n<=0){
            System.out.print("number of students should be more than 0: ");
            n = getInt();
        }
        String[] students = new String[n];
        for (int i = 0;i<students.length;i++){
            System.out.print("Input student name: ");
            students[i] = getString();
        }

        System.out.println(toStr(students));

        System.out.print("Search by letter: ");
        String s = getString();
        while (s.length()>1){
            System.out.print("Input a letter: ");
            s = getString();
        }
        System.out.println(toStr(searchByLetter(students, s)));

    }

    /**
     *
     * @return string from keyboard
     */
    public static String getString(){
        Scanner scanner = new Scanner(System.in);
        String str = scanner.nextLine();
        while(str.equals("")){
            System.out.print("Input should be not null: ");
            str = scanner.nextLine();
        }
        while(str.equals(" ")){
            System.out.print("Input should be not null: ");
            str = scanner.nextLine();
        }
        return str;
    }

    /**
     *
     * @return int from keyboard
     */
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

    public static String toStr(String[] students){
        String res = "";
        for (int i = 0; i<students.length;i++){
            res+="Student "+(i+1)+ " : " + students[i] + "\n";
        }
        return res;
    }

    /**
     *
     * @param students
     * @param s
     * @return an array of students starts with s
     */
    public static String[] searchByLetter(String[] students, String s){

        int n = 0;
        for (int i = 0; i<students.length;i++){

            if (students[i].startsWith(s)){
                n++;
            }
        }
        String[] res = new String[n];
int h = 0;
        for (int i = 0; i<students.length;i++){

            if (students[i].startsWith(s)){
                res[h] = students[i];
                h++;
            }
        }

        return res;
    }

}
