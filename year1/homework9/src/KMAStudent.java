public class KMAStudent extends Student {
    KMAStudent() {
        super();
    }

    KMAStudent(String name, String surname) {
        super(name, surname);
    }

    KMAStudent(String name, String surname, int courseNum, String faculty) {
        super(name, surname, courseNum, faculty);
    }

    KMAStudent(String name, String surname, int courseNum, String faculty, int numberOfCredits, int numberOfHours, int averageScore) {
        super(name, surname, courseNum, faculty);
        this.AverageScore=averageScore;
        this.numberOfCredits=numberOfCredits;
        this.numberOfHours=numberOfHours;
    }

    private int numberOfCredits;
    private int numberOfHours;
    private int AverageScore;

    @Override
    public void setStudentsNum(int studentsNum) {
        super.setStudentsNum(studentsNum);
    }

    @Override
    public int getStudentsNum() {
        return super.getStudentsNum();
    }

    /**
     *
     * @return
     */
    @Override
    public int getCourseNum() {
        return super.getCourseNum();
    }

    @Override
    public String getFaculty() {
        return super.getFaculty();
    }

    @Override
    public void changeFaculty(String faculty) {
        super.changeFaculty(faculty);
    }

    public int getNumberOfCredits() {
        return numberOfCredits;
    }

    public void setNumberOfCredits(int numberOfCredits) {
        this.numberOfCredits = numberOfCredits;
    }

    public int getNumberOfHours() {
        return numberOfHours;
    }

    public void setNumberOfHours(int numberOfHours) {
        this.numberOfHours = numberOfHours;
    }

    public int getAverageScore() {
        return AverageScore;
    }

    public void setAverageScore(int averageScore) {
        AverageScore = averageScore;
    }

    public boolean checkHoursNumber(){
        if (numberOfHours>24)return false;
        else return true;
    }

    @Override
    public String toString() {
        return "KMAStudent{" +
                "numberOfCredits=" + numberOfCredits +
                ", numberOfHours=" + numberOfHours +
                ", AverageScore=" + AverageScore +
                ", name='" + name  +
                ", surname='" + surname  +
                ", studentsNum = " + getStudentsNum() +
                ", courseNum = " + getCourseNum() +
                ", faculty = " + getFaculty()  +
                '}';
    }
}
