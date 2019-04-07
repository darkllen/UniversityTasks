import java.util.ArrayList;

public class StudentMy {
    private String name;
    private String address;
    private int score;
    private String speciality;

    public StudentMy() {
        System.out.println("Input last name");
        String f = DataInp.getName();
        System.out.println("Input first name");
        String n = DataInp.getName();
        System.out.println("Input father name");
        String o = DataInp.getName();
        this.name = f+" "+n+" "+o;
        System.out.println("Input adress");
        this.address = DataInp.getName();
        System.out.println("Input grade");
        this.score = DataInp.getInt();
        System.out.println("Input speciality");
        this.speciality = DataInp.getName();
    }

    public StudentMy(String name, String address, int score, String speciality) {
        this.name = name;
        this.address = address;
        this.score = score;
        this.speciality = speciality;
    }

    public static StudentMy getBestStudent (ArrayList<StudentMy> studentMIES){
        StudentMy max = new StudentMy("","",0,"");
        for (StudentMy i: studentMIES
             ) {
            if (i.score>max.score) max = i;
        }
        return max;
    }

    @Override
    public String toString() {
        return "StudentMy " + "name=" + name +
                ", address=" + address+
                ", score=" + score +
                ", speciality=" + speciality;
    }
}
