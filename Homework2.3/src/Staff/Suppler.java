package Staff;

import Animals.Animal;
import Animals.Lion;
import Animals.Owl;

public class Suppler extends Staff {
    public Suppler(int age) {
        this.setAge(age);
    }

    public Animal giveNewAnimal(String animalName){
        if (animalName.equals("Lion")){
            return new Lion();
        }
        if (animalName.equals("Owl")){
            return new Owl();
        }
        return null;
    }
}
