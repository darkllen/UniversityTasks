import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        System.out.print("Input number of students: ");
        int n = getInt();
        while(n<=0){
            System.out.print("number of grades should be more than 0: ");
            n = getInt();
        }

        Student[] students = new Student[n];
        for (int i =0;i<students.length;i++) {
            System.out.print("Input average grade for "+(i+1)+" student: ");
            int l = getInt();
            while (l>100||l<0){
                System.out.print("grade should be from 0 to 100: ");
                l = getInt();
            }
            System.out.print("Input name for "+(i+1)+" student: ");
           String name = getString();
            students[i] = new Student(l, name.replaceAll("[^a-zA-Zа-яА-Я]",""));
        }
        int i = 0;
        for (Student s:students
             ) {
            i++;
            System.out.println(i+" student = " +s.toString());
        }
        Main main = new Main();

        System.out.println("Input 1 for ascending sort and 0 for descending sort by grades: ");
        main.sortByGrades(getString(),students);
        int j = 0;
        for (Student s:students
        ) {
            j++;
            System.out.println(j+" student = " +s.toString());
        }
        System.out.println("Input 1 for ascending sort and 0 for descending sort by names: ");
        main.sortByNames(getString(),students);
        int g = 0;
        for (Student s:students
        ) {
            j++;
            System.out.println(g+" student = " +s.toString());
        }
    }


    private static String getString() {
        return new Scanner(System.in).nextLine();
    }

    /**
     * sort array by numbers
     *
     * @param s
     * @param students
     */
    public void sortByGrades(String s, Student[] students) {
        if (s.equals("1")){
            for (int i = students.length-1;i>=1;i--){
                for (int j=0;j<i;j++){
                    if (students[j].getAverageGrade() >students[i].getAverageGrade()){
                        Student temp = students[i];
                        students[i]=students[j];
                        students[j] = temp;
                    }
                }
            }
        } else {
            for (int i = students.length-1;i>=1;i--){
                for (int j=0;j<i;j++){
                    if (students[j].getAverageGrade() <students[i].getAverageGrade()){
                        Student temp = students[i];
                        students[i]=students[j];
                        students[j] = temp;
                    }
                }
            }
        }

    }

    /**
     * sort array by names
     *
     * @param s
     * @param students
     */
    public void sortByNames(String s, Student[] students) {
        if (s.equals("1")){
            for (int i = students.length-1;i>=1;i--){
                for (int j=0;j<i;j++){
                    if (students[j].getName().toLowerCase().compareTo(students[i].getName().toLowerCase())>0){
                        Student temp = students[i];
                        students[i]=students[j];
                        students[j] = temp;
                    }
                }
            }
        } else {
            for (int i = students.length-1;i>=1;i--){
                for (int j=0;j<i;j++){
                    if (students[j].getName().toLowerCase().compareTo(students[i].getName().toLowerCase())<0){
                        Student temp = students[i];
                        students[i]=students[j];
                        students[j] = temp;
                    }
                }
            }
        }

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
}
