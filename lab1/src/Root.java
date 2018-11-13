import acm.program.ConsoleProgram;
//Ihor Yankin
//Написати програму, що запитує у користувача два числа і обраховує корень квадратний з суми їх квадратів.
public class Root extends ConsoleProgram {
    public static final int WIDTH = 500;
    public static final int HEIGHT = 700;

    public void run(){
        this.setSize(WIDTH,HEIGHT);
        this.setFont("SeinsSherif-20");
        while (true){
            println("Вычисление квадратного корня из суммы квадратов двух чисел");
            println("Введите два числа:");
            double a = readDouble("Число 1 -");
            double b = readDouble("Число 2 -");
            println("Корень из (" + a+"^2 + " + b+"^2) " + "= " + Math.sqrt(a*a+b*b));
            println("Для заверщения введите 0");
            println("Для продолжения введите любое другое число");
            double i = readDouble();
            if (i==0){
                println("Работа завершена");
                return;
            }
        }

    }
}
