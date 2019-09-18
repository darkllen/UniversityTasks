public class Student {
    Student(){
        numberOfStudents++;
    }
    Student(String name, String surname){
        this.name=name;
        this.surname=surname;
        numberOfStudents++;
    }
    Student(String name, String surname, int courseNum, String faculty){
        this.name=name;
        this.surname=surname;
        this.courseNum =courseNum;
        this.faculty = faculty;
        numberOfStudents++;
    }
    public static int numberOfStudents;
    public String name = "noname";
    public String surname = "noname";
    private int studentsNum;
    private int courseNum = 1;
    private String faculty = "";



    /**
     * @param  studentsNum new student number document
     * @return set new num for student
     *
     */
    public void setStudentsNum(int studentsNum) {
        this.studentsNum = studentsNum;
    }

    /**
     *
     * @return
     */
    public int getStudentsNum() {
        return studentsNum;
    }

    /**
     *
     * @return how many students at all
     *
     */
    public static int getNumberOfStudents() {
        return numberOfStudents;
    }

    /**
     *
     * @return course number of this student
     *
     */
    public int getCourseNum() {
        return courseNum;
    }

    /**
     *
     * @return faculty of this student
     *
     */
    public String getFaculty() {
        return faculty;
    }

    /**
     * @param faculty new faculty of student
     * @return change faculty
     *
     */
    public void changeFaculty(String faculty){
        this.faculty=faculty;
        if (this.faculty.matches("")){
            deleteStudent();
        }
    }

    /**
     *
     * @return delete all information about student
     *
     */
    private void deleteStudent(){
        name="";
        surname="";
        courseNum=0;
        numberOfStudents--;
    }


    /**
     *
     * @return return all information about this student
     *
     */
    @Override
    public String toString() {
        return "Student: \n" +
                "name = " + name + '\n' +
                "surname = " + surname + '\n' +
                "studentsNum = " + studentsNum +
                "\ncourseNum = " + courseNum +
                "\nfaculty = " + faculty;
    }
}
