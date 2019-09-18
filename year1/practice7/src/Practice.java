import acm.program.ConsoleProgram;

public class Practice extends ConsoleProgram {
    public void run(){
        while (true){

            println("Выберите программу");
            println("1 - Фибоначчи");
            println("2 - факториал");
            println("3 - количество цифр в числе");
            println("0 - закончить работу");

            int choose = readInt();

            switch (choose){

                case 1 :
                    println("Вычисление чисел Фибоначчи");
                    println("Введите, сколько чисел Фибоначчи посчитать");
                    int n = readInt();
                    for (int i = 0; i<=n;i++){
                        println(i + " - " +fibonachi(i));
                    }

                    break;
                case 2:
                    println("Вычисление n!");
                    println("Введите n");
                    int l = readInt();
                    if (l<0){
                        println("Надо вводить неотрицательное число");
                        break;
                    }
                   println(factorial(l));
                    break;
                case 3:
                    println("Вычисление количества цифр в числе");
                    println("Введите число");
                    int m = readInt();
                    if (m==0)
                        println("1");
                    else
                       println(numbers(m, 0));
                    break;
                case 0:
                    print("Работа закончена");
                    return;
                default: println("Ведите правильное число");
            }
        }


    }

    private int numbers(int m, int count) {
        if (m==0)
            return count++;
        count++;
        return numbers(m/10, count);

    }

    private int factorial(int n) {
        if (n==1 || n == 0)
            return 1;
        return n*factorial(n-1);

    }

    private int fibonachi(int n) {
            if (n==1)
                return 1;
            if (n==0)
                return 0;
            return fibonachi(n-1) + fibonachi(n-2);
    }
}
