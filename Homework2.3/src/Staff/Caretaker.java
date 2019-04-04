package Staff;

import Animals.Animal;
import Food.Food;

public class Caretaker extends Staff {
    public Caretaker(int age) {
        this.setAge(age);
    }

    public void feedAnimal(Animal animal, Food food){
        if (animal.getFullness()+food.getFullnessAmount()>100){
            System.out.println("no need to feed this animal");
            return;
        }
        animal.setFullness(animal.getFullness()+food.getFullnessAmount());
        food.setFullnessAmount(0);
    }
}
