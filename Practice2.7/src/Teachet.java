public class Teachet extends Person implements ITeachet{

    public Teachet(String name, int age, String subject) {
        super(name, age);
        this.subject = subject;
    }

    private String subject;

    @Override
    public void setMark(Student student, int mark) {
        student.setMark(mark);
        System.out.println(student.getMark());
    }

    @Override
    public void teachStudent(Student student) {
        System.out.println("I teach " +student.getName());
    }

    public String getSubject() {
        return subject;
    }
}
