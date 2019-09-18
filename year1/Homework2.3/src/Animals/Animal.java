package Animals;

public abstract class Animal {
    private static int animalNumbers=0;
    private int fullness = 100;

    public void sleep(){
        this.fullness+=20;
    }


    public int getFullness() {
        return fullness;
    }

    public void setFullness(int fullness) {
        this.fullness = fullness;
    }

    public static int getAnimalNumbers() {
        return animalNumbers;
    }

    public static void setAnimalNumbers(int animalNumbers) {
        Animal.animalNumbers = animalNumbers;
    }
}
