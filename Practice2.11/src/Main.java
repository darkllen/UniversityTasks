import java.util.ArrayList;

public class Main {
    public static void main(String[] args) {
        StudentMy studentMy = new StudentMy();
        StudentMy studentMy1 = new StudentMy("Ira","Odessa", 85, "ipz");
        StudentMy studentMy2 = new StudentMy("Anna", "Kyiv", 95, "ipz");
        StudentMy studentMy3 = new StudentMy("Karina", "Kyiv", 94, "ipz");
        ArrayList<StudentMy> studentMIES = new ArrayList<>();
        studentMIES.add(studentMy);
        studentMIES.add(studentMy1);
        studentMIES.add(studentMy2);
        studentMIES.add(studentMy3);

        Group group = new Group("Ipeze", studentMIES);

        System.out.println(StudentMy.getBestStudent(studentMIES).toString());
        System.out.println(" ");

        System.out.println(group);

        System.out.println(group.formQuequeforMP());
    }
}
