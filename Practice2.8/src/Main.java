import java.util.ArrayList;

public class Main {
    public static void main(String[] args) {
        Student student = new Student();
        Student student1 = new Student();
        Student student2 = new Student();
        Student student3 = new Student();
        ArrayList<Student> students = new ArrayList<>();
        students.add(student);
        students.add(student1);
        students.add(student2);
        students.add(student3);

        Group group = new Group("Ipeze",students);

        System.out.println(Student.getBestStudent(students).toString());
        System.out.println(" ");

        System.out.println(group);
    }
}
