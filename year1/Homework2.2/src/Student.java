public class Student {
    private int averageGrade;
    private String name;

    public String getName() {
        return name;
    }

    public Student(int averageGrade, String name) {
        this.averageGrade = averageGrade;
        this.name = name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getAverageGrade() {
        return averageGrade;
    }

    public void setAverageGrade(int grade) {
        this.averageGrade = grade;
    }

    @Override
    public String toString() {
        String res = "average grade: " + averageGrade +", name: " +name ;
        return res;
    }
}
