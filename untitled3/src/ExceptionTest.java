import java.io.*;

class MyException extends Exception{

    private int i;private String s;

    public MyException(String s){super(s);i=2;this.s = s;}

    public void setI(int i){this.i=i;}

    public String toString(){return s+i;}

}

public class ExceptionTest {

    public void smt() throws MyException{

        try {

            throw new MyException("ALARM");

        } catch (MyException e){

            e.setI(3);throw e;

        }

    }
    public static void main(String[] args) throws MyException {

        ExceptionTest et = new ExceptionTest();

        et.smt();

    }

}

