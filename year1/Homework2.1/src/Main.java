import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        System.out.print("Input number of grades: ");
        int n = getInt();
        while(n<=0){
            System.out.print("number of grades should be more than 0: ");
            n = getInt();
        }
        int[] grades = new int[n];
        for (int i =0;i<grades.length;i++) {
            System.out.print("Input " + (i+1) + " grade: ");
            int l = getInt();
            while (l>100||l<0){
                System.out.print("grade should be from 0 to 100: ");
                l = getInt();
            }
            grades[i] = l;
        }

        StudentGrades studentGrades = new StudentGrades(grades);

        System.out.println("max grade in group is "+studentGrades.maxGrade());
        System.out.println("min grade in group is "+studentGrades.minGrade());
        System.out.println("average grade in group is "+studentGrades.averageGrade());
        System.out.println("number of students with more than average grade: "+studentGrades.moreThanAverage());
        System.out.println("number of students with less than average grade: "+studentGrades.lessThanAverage());
        System.out.println("number of students with perfect grade: "+studentGrades.numberPerfect());
        System.out.println("number of students with good grade: "+studentGrades.numberGood());
        System.out.println("number of students with normal grade: "+studentGrades.numberNormal());
        System.out.println("number of students with bad grade: "+studentGrades.numberBad());
        System.out.println(studentGrades.toString());


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
