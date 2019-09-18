package Animals;

public class Owl extends Animal implements Actions {

    public Owl() {
        setAnimalNumbers(getAnimalNumbers()+1);
    }

    @Override
    public void sound() {
        if (this.getFullness()<5){
            System.out.println("Too tired");
            return;
        }
        this.setFullness(this.getFullness()-5);
        System.out.println("UhUhUh");

    }

    @Override
    public void move(int steps) {
        if (this.getFullness()<steps*2){
            System.out.println("Too tired");
            return;
        }
        this.setFullness(this.getFullness()-steps*2);
        System.out.println("owl walks "+ steps +" steps");
    }

}
