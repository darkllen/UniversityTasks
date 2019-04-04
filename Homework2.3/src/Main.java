import Animals.Lion;
import Animals.Owl;
import Food.Combicorm;
import Food.Meet;
import Staff.Caretaker;
import Staff.Suppler;

public class Main {
    public static void main(String[] args) {
        Suppler suppler = new Suppler(23);
        Caretaker caretaker = new Caretaker(25);

        Meet meet = new Meet();
        Combicorm combicorm = new Combicorm();

        Lion lion = (Lion) suppler.giveNewAnimal("Lion");
        Owl owl = (Owl) suppler.giveNewAnimal("Owl");

        System.out.println("Lion Energy:" +lion.getFullness());
        lion.move(10);
        lion.sound();
        System.out.println("Lion Energy:" +lion.getFullness());

        System.out.println("Owl Energy:" +owl.getFullness());
        owl.move(10);
        owl.sound();
        System.out.println("Owl; Energy:" +owl.getFullness());

        caretaker.feedAnimal(lion,meet);
        caretaker.feedAnimal(owl,combicorm);
        System.out.println("Lion Energy:" +lion.getFullness());
        System.out.println("Owl Energy:" +owl.getFullness());

    }
}
