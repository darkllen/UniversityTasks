public class Main {
    public static void main(String[] args) {
        Teachet teachet = new Teachet("Irina", 28, "Mathematic");
        Student student = new Student("Ihor", 18, 1);

      System.out.println(  teachet.toString());
        System.out.println(student.toString());

        teachet.saySomething("Hello");
        student.saySomething("hello");

        teachet.walk(10);
        student.walk(1000);

        teachet.teachStudent(student);

        student.drinkAlcohol();
        student.preparing(teachet);

        teachet.setMark(student,100);
    }
}
