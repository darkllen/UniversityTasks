import java.util.ArrayList;

public class Student {
    private String name;
    private String address;
    private int score;
    private String speciality;

    public Student() {
        System.out.println("Input last name");
        String f = DataInput.getName();
        System.out.println("Input first name");
        String n = DataInput.getName();
        System.out.println("Input father name");
        String o = DataInput.getName();
        this.name = f+" "+n+" "+o;
        System.out.println("Input adress");
        this.address = DataInput.getName();
        System.out.println("Input grade");
        this.score = DataInput.getInt();
        System.out.println("Input speciality");
        this.speciality = DataInput.getName();
    }

    public Student(String name, String address, int score, String speciality) {
        this.name = name;
        this.address = address;
        this.score = score;
        this.speciality = speciality;
    }

    public static Student getBestStudent (ArrayList<Student> students){
        Student max = new Student("","",0,"");
        for (Student i:students
             ) {
            if (i.score>max.score) max = i;
        }
        return max;
    }

    @Override
    public String toString() {
        return "Student " + "name=" + name +
                ", address=" + address+
                ", score=" + score +
                ", speciality=" + speciality;
    }
}
