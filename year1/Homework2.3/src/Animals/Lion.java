package Animals;

public class Lion extends Animal implements Actions{

    public Lion() {
        setAnimalNumbers(getAnimalNumbers()+1);
    }
    @Override
    public void sound() {
        if (this.getFullness()<10){
            System.out.println("Too tired");
            return;
        }
        this.setFullness(this.getFullness()-10);
        System.out.println("AAAARRRRRRRR");

    }

    @Override
    public void move(int steps) {
        if (this.getFullness()<steps){
            System.out.println("Too tired");
            return;
        }
        this.setFullness(getFullness()-steps);
        System.out.println("lion walks "+ steps +" steps");
    }

}
