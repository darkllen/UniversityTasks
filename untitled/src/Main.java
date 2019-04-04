class Instrument{

    protected void play(){

        System.out.println("Грає інструмент");

    }

}
class Drum extends Instrument{

    public void play(){

        System.out.println("Грає барабан");

    }

}

public class Main {

    public void play(Instrument i){

        i.play();

    }

    public static void main(String[] args) {

        Drum t = new Drum();

        play(t);

    }

}