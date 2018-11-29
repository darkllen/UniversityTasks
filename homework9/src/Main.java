import acm.program.ConsoleProgram;

import java.awt.*;

public class Main extends ConsoleProgram {
    public void run(){
        this.setSize(600,500);
        this.setFont("SansSerif-22");
        Student student = new Student();
        KMAStudent student3 = new KMAStudent();
        Student student1 = new Student("Taya","Shutyak",2,"FSNST");
        KMAStudent student4 = new KMAStudent("Taya","Shutyak",2,"FSNST");
       KMAStudent student2 = new KMAStudent("Ihor", "Yankin");
       Student student5 = new Student("Ihor", "Yankin");
       KMAStudent student6 = new KMAStudent("Ihor", "Yankin", 3, "FI", 20, 20, 75);



        println(student);
       println(student3);
       println(student1);
       println(student4);
       println(student2);
       println(student5);
       println(student6);


    }
}
