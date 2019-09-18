import java.lang.reflect.Array;
import java.util.Collections;

public class StudentGrades {
    private int[] grades;

    public StudentGrades(int[] grades) {
        this.grades = grades;
    }

    public int[] getGrades() {
        return grades;
    }

    public void setGrades(int[] grades) {
        this.grades = grades;
    }

    public void sortGrades(String s, int[] grades) {
        if (s.equals("A")){
            for (int i = grades.length-1;i>=1;i--){
                for (int j=0;j<i;j++){
                    if (grades[j]>grades[i]){
                        int temp = grades[i];
                        grades[i]=grades[j];
                        grades[j] = temp;
                    }
                }
            }
        } else {
            for (int i = grades.length-1;i>=1;i--){
                for (int j=0;j<i;j++){
                    if (grades[j]<grades[i]){
                        int temp = grades[i];
                        grades[i]=grades[j];
                        grades[j] = temp;
                    }
                }
            }
        }

    }

    /**
     *
     * @param grade add new grade to array
     */
    public void addGrade(int grade){
        int[] oldGrades = grades.clone();
        grades = new int[grades.length+1];
        for (int i =0; i<oldGrades.length;i++){
            grades[i] = oldGrades[i];
        }
        grades[grades.length-1] = grade;
    }

    /**
     *
     * @return max grade
     */
    public int maxGrade(){
        int max = -1;
        for (int i = 0; i<grades.length;i++){
            if (max<grades[i]) max = grades[i];
        }
        return max;
    }

    /**
     *
     * @return min grade
     */
    public int minGrade(){
        int min = grades[0];
        for (int i = 0; i<grades.length;i++){
            if (min>grades[i]) min = grades[i];
        }
        return min;
    }

    /**
     *
     * @return average grade
     */
    public double averageGrade(){
        double average = 0;
        for (int i = 0; i<grades.length;i++){
            average+=grades[i];
        }
        average = average/grades.length;
    return average;
    }

    /**
     *
     * @return students number with grade more than average
     */
    public int moreThanAverage(){
        double av = averageGrade();
        int n =0;
        for (int i =0;i<grades.length;i++){
            if (grades[i]>=av){
                n++;
            }
        }
        return n;
    }

    /**
     *
     * @return students number with grade less than average
     */
    public int lessThanAverage(){
        double av = averageGrade();
        int n =0;
        for (int i =0;i<grades.length;i++){
            if (grades[i]<av){
                n++;
            }
        }
        return n;
    }

    /**
     *
     * @return number of perfect students
     */
    public int numberPerfect(){
        int n = 0;
        for (int i =0; i<grades.length;i++){
            if (grades[i]>90){
             n++;
            }
        }
        return n;
    }

    /**
     * return number of good students
     * @return
     */
    public int numberGood(){
        int n = 0;
        for (int i =0; i<grades.length;i++){
            if (grades[i]>70&&grades[i]<91){
                n++;
            }
        }
        return n;
    }

    /**
     *
     * @return number of normal students
     */
    public int numberNormal(){
        int n = 0;
        for (int i =0; i<grades.length;i++){
            if (grades[i]>59&&grades[i]<71){
                n++;
            }
        }
        return n;
    }

    /**
     *
     * @return number of bad students
     */
    public int numberBad(){
        int n = 0;
        for (int i =0; i<grades.length;i++){
            if (grades[i]>0&&grades[i]<60){
                n++;
            }
        }
        return n;
    }

    @Override
    public String toString() {
        String res = "";
        for (int i = 0; i<grades.length;i++){
            res+="Student "+i+ " grade: " + grades[i] + "\n";
        }
        return res;
    }
}
