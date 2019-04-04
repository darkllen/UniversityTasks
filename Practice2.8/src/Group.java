import java.util.ArrayList;

public class Group {
    private String name;
    private ArrayList<Student> students;

    public Group(String name, ArrayList<Student> students) {
        this.name = name;
        this.students = students;
    }

    @Override
    public String toString() {
        String res = "";
        for (Student i:students
             ) {res=res+i.toString()+'\n';

        }
        return "Group" + "name=" + name +
                ", " + '\n'+res;
    }
}
