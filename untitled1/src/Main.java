abstract class Primate{

    private int i = 0;

    void eat(){};

}
class Monkey extends Primate{

    private int i;

    void eat(){

        i=2;

        System.out.println(i);

    }

}
class MyMonkey extends Monkey{

    private int i;

    void eat(){

        i=3;

        System.out.println(i);

    }

}
public class Main {

    public static void main(String[] args) {

        Primate m = new MyMonkey();

        m.eat();

    }

}

