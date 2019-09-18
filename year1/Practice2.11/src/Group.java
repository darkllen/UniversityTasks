import java.util.*;

public class Group {
    private String name;
    private ArrayList<StudentMy> studentMIES;

    public Group(String name, ArrayList students) {
        this.name = name;
        this.studentMIES = students;
    }

    public Queue formQuequeforMP(){
        studentMIES.sort(Comparator.comparing(Objects::hashCode));
        Queue queue = new LinkedList();
        for (StudentMy s: studentMIES) {
            queue.add(s);
        }
        return queue;
    }

    @Override
    public String toString() {
        String res = "";
        for (StudentMy i: studentMIES
             ) {res=res+i.toString()+'\n';

        }
        return "Group" + "name=" + name +
                ", " + '\n'+res;
    }
}
