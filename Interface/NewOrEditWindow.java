package Interface;

import DB.Connect;
import DB.Database;
import Model.Faculty;
import Model.Person;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

public class NewOrEditWindow {


    public static void createFac(int WIDTH,int HEIGHT,String newEdit,int facultySort,int row){
       NewOrEditWindow d = new NewOrEditWindow();
        d.createNewFaculty(WIDTH,HEIGHT,newEdit,facultySort,row);
    }

    public static void createCaf(int WIDTH,int HEIGHT,String newEdit,int facultySort,int row,int cafedraSort,int fId,int number){
        NewOrEditWindow d = new NewOrEditWindow();
        d.createNewCafedra(WIDTH,HEIGHT,newEdit,row,cafedraSort,fId,number);
    }

    public static void createPer(int WIDTH, int HEIGHT, String newEdit, int cId, int sortColumn, int sortAD, int row, int number, int fId, int fNumber, ArrayList<Person> myArray){
        NewOrEditWindow d = new NewOrEditWindow();
        d.createNewPerson(WIDTH,HEIGHT,newEdit,cId,sortColumn,sortAD,row,number, fId,fNumber,myArray);
    }

    private void createNewPerson(int WIDTH,int HEIGHT, String newEdit, int cId, int sortColumn, int sortAD, int row,int number,int fId,int fNumber,ArrayList<Person>myArray) {

        Connect connect= new Connect();
        Database database=new Database(connect.connectToDB());

        JFrame frame = new JFrame("NEW");
        frame.setSize(WIDTH, HEIGHT);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setVisible(true);
        frame.setLayout(null);

        JTextField[] text=new JTextField[6];

        for(int i=0;i<6;i++)
            text[i]=new JTextField();

        for(int i=0;i<6;i++)
        text[i].setBounds(WIDTH/2-50, 20+45*i, 150, 25);

        JComboBox comboBox = new JComboBox();
        comboBox.addItem("Student");
        comboBox.addItem("Teacher");
        comboBox.setLocation(WIDTH/2-50,20+45*3);
        comboBox.setBounds(WIDTH/2-50, 20+45*3, 150, 25);
        frame.add(comboBox);
        if(newEdit.equals("new")){
        text[0].setText("Name");
        text[1].setText("Surname");
        text[2].setText("Fathername");
        text[4].setText("Course");
        text[5].setText("Group");}
        else{
            text[0].setText(database.getPeopleByCafedraId(cId,sortColumn,sortAD).get(row).getName());
            text[1].setText(database.getPeopleByCafedraId(cId,sortColumn,sortAD).get(row).getSurname());
            text[2].setText(database.getPeopleByCafedraId(cId,sortColumn,sortAD).get(row).getFatherName());
            comboBox.setSelectedItem(database.getPeopleByCafedraId(cId,sortColumn,sortAD).get(row).isATeacher());
            text[4].setText(database.getPeopleByCafedraId(cId,sortColumn,sortAD).get(row).getCourse());
            text[5].setText(database.getPeopleByCafedraId(cId,sortColumn,sortAD).get(row).getGroup());

        }

        for(int i=0;i<6;i++)
            if(i!=3)
        frame.add(text[i]);

        if(comboBox.getSelectedItem().equals("Teacher")){
            text[4].setVisible(false);
            text[5].setVisible(false);
        }else{
            text[4].setVisible(true);
            text[5].setVisible(true);
        }

//        text[2].addActionListener(new ActionListener() {
//
//            @Override
//            public void actionPerformed(ActionEvent e) {
//                text[2].setText("");
//            }
//        });


        comboBox.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if(comboBox.getSelectedItem().equals("Teacher")){
                    text[4].setVisible(false);
                    text[5].setVisible(false);
                }else{
                    text[4].setVisible(true);
                    text[5].setVisible(true);
                }
            }
        });

        frame.add(StartButton.createHideButton());


        JButton backButton = new JButton("Back");
        backButton.setBounds(10,HEIGHT-120,WIDTH/5,50);
        frame.add(backButton);



                backButton.addActionListener(new ActionListener() {
                    public void actionPerformed(ActionEvent e) {

                        frame.setVisible(false);
                        PeopleTable.create(WIDTH, HEIGHT, cId, number, fId, fNumber, myArray, sortColumn, sortAD);


                    }
                });


        JButton saveButton = new JButton("Save");
        saveButton.setBounds(30+WIDTH/5,HEIGHT-120,WIDTH/5,50);
        frame.add(saveButton);

        saveButton.addActionListener(new ActionListener(){
            public void actionPerformed(ActionEvent e){


                if (checkPerson(text[0].getText(), text[1].getText(), text[2].getText(), comboBox.getSelectedItem().toString(), text[4].getText(), text[5].getText())) {
                    if(comboBox.getSelectedItem().equals("Teacher")){
                        text[4].setText("0");
                        text[5].setText("0");
                    }
                    if (newEdit.equals("new"))
                        database.insertNewPerson(cId, text[0].getText(), text[1].getText(), text[2].getText(), comboBox.getSelectedItem().toString(), text[4].getText(), text[5].getText());
                    else
                        database.updatePersonById(database.getPeopleByCafedraId(cId, sortColumn, sortAD).get(row).getId(), cId, text[0].getText(), text[1].getText(), text[2].getText(), comboBox.getSelectedItem().toString(), text[4].getText(), text[5].getText());
//

                    frame.setVisible(false);
                    PeopleTable.create(WIDTH, HEIGHT, cId, number, fId, fNumber, myArray, sortColumn, sortAD);
                }else System.out.println("Please check your data");
            }
        });
    }



    private void createNewCafedra(int WIDTH,int HEIGHT, String newEdit, int row, int cafedraSort, int fId, int number) {


        Connect connect= new Connect();
        Database database=new Database(connect.connectToDB());

        JFrame frame = new JFrame("NEW");
        frame.setSize(WIDTH, HEIGHT);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setVisible(true);
        frame.setLayout(null);

        JTextField text=new JTextField();


        text.setBounds(WIDTH/2-50, 20, 150, 25);
        if(newEdit.equals("new"))text.setText("Print UNIQUE name");
        else text.setText(database.getCafedrasByFacultyId(fId,0).get(row).getName());

        frame.add(text);



        frame.add(StartButton.createHideButton());


        JButton backButton = new JButton("Back");
        backButton.setBounds(10,HEIGHT-120,WIDTH/5,50);
        frame.add(backButton);

        backButton.addActionListener(new ActionListener(){
            public void actionPerformed(ActionEvent e){

                frame.setVisible(false);
                 CafedraTable.create(WIDTH,HEIGHT,fId,number);


            }
        });


        JButton saveButton = new JButton("Save");
        saveButton.setBounds(30+WIDTH/5,HEIGHT-120,WIDTH/5,50);
        frame.add(saveButton);

        saveButton.addActionListener(new ActionListener(){
            public void actionPerformed(ActionEvent e) {

                //frame.setVisible(false);
                if (checkCafedra(text.getText())) {
                    if (newEdit.equals("new")) database.insertNewCafedra(fId, text.getText());
                    else
                        database.updateCafedra(database.getCafedrasByFacultyId(fId, cafedraSort).get(row).getId(), fId, text.getText());
//
                    frame.setVisible(false);
                    CafedraTable.create(WIDTH, HEIGHT, fId, number);

                } else System.out.println("Please check your data");
            }
        });


    }



    private void createNewFaculty(int WIDTH,int HEIGHT,String newEdit,int facultySort,int row) {
        Connect connect= new Connect();
        Database database=new Database(connect.connectToDB());

        JFrame frame = new JFrame("NEW");
        frame.setSize(WIDTH, HEIGHT);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setVisible(true);
        frame.setLayout(null);

        JTextField text=new JTextField();


        text.setBounds(WIDTH/2-50, 20, 150, 25);
        if(newEdit.equals("new"))text.setText("Print UNIQUE name");
        else text.setText(database.getFaculties(0).get(row).getName());

        frame.add(text);




                frame.add(StartButton.createHideButton());


        JButton backButton = new JButton("Back");
        backButton.setBounds(10,HEIGHT-120,WIDTH/5,50);
        frame.add(backButton);

        backButton.addActionListener(new ActionListener(){
            public void actionPerformed(ActionEvent e){

                frame.setVisible(false);
                   FacultyTable.create(WIDTH,HEIGHT);



            }
        });


        JButton saveButton = new JButton("Save");
        saveButton.setBounds(30+WIDTH/5,HEIGHT-120,WIDTH/5,50);
        frame.add(saveButton);

        saveButton.addActionListener(new ActionListener(){
            public void actionPerformed(ActionEvent e){
if(checkFaculty(text.getText())) {
    if (newEdit.equals("new")) database.insertNewFaculty(text.getText());
    else {
        database.updateFacultyById(database.getFaculties(facultySort).get(row).getId(), text.getText());
    }

    frame.setVisible(false);
    FacultyTable.create(WIDTH, HEIGHT);

}
else System.out.println("Please check your data");
            }
        });


    }

    private boolean checkCafedra(String name) {
        if(name.equals("Cafedra"))return false;
        return checkFaculty(name);
    }

    private boolean checkFaculty(String name) {
        Connect connect= new Connect();
        Database database=new Database(connect.connectToDB());
        ArrayList<Faculty> array = database.getFacultiesByName(name);
        if(array.size()>0)return false;
        if(name.equals("Print UNIQUE name"))return false;
        if(name.equals(""))return false;
        for(int i=0;i<name.length();i++)
            if( !Character.isLetter(name.charAt(i))) return false;
        return true;
    }

    private boolean checkPerson(String name, String surname, String fathername, String studentOrTeacher, String course, String group) {
        for(int i=0;i<name.length();i++)
            if( !Character.isLetter(name.charAt(i))) return false;
        for(int i=0;i<surname.length();i++)
            if( !Character.isLetter(surname.charAt(i))) return false;
        for(int i=0;i<fathername.length();i++)
            if( !Character.isLetter(fathername.charAt(i))) return false;
            if(studentOrTeacher.equals("Student")) {
                for (int i = 0; i < course.length(); i++)
                    if (!Character.isDigit(course.charAt(i))) return false;
                for (int i = 0; i < group.length(); i++)
                    if (!Character.isDigit(group.charAt(i))) return false;

                try {
                    Integer.valueOf(course);

                } catch (NumberFormatException e) {
                    System.out.println("Number of course is too big");
                    return false;
                }

                try {
                    Integer.valueOf(group);
                } catch (NumberFormatException e) {
                    System.out.println("Number of group is too big");
                    return false;
                }

            }
        if(name.equals("Name"))return false;
        if(surname.equals("Surname"))return false;
        if(fathername.equals("FatherName"))return false;

            return true;
    }


}
