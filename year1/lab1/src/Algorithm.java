import acm.program.ConsoleProgram;
//Ihor Yankin
//Опис алгоритму:
//
//візьміть позитивне ціле число, назвемо його n
//якщо n парне, поділимо його на 2
//якщо n не парне, помножимой його на 3 і додамо 1
//продовжувати цей процес поки n не буде дорівнювати 1
//Напишіть програму, що реалізує вказаний алгоритм і наочно проілюструє його виконання. В кінці обов'язково повідомити кількість кроків.
public class Algorithm extends ConsoleProgram {
    public static final int WIDTH = 500;
    public static final int HEIGHT = 700;
    public void run(){
        this.setSize(WIDTH,HEIGHT);
        this.setFont("SeinsSherif-20");
        while (true){
            int n = readInt("Введите положительное целое число ");
/*
            if(n==0){
                println("");
                return;
            }
*/
            if (n<=0){
                println("Данное число не является положительным");
                continue;
            }
            int num =0;
            recurs(n,num);
            println("Если желаете начать сначала - введите 1");
            double i = readDouble();
            if (i!=1){
                println("Работа завершена");
                return;
            }
        }

    }


    //recursive method for calculate finL
    public void recurs (int n, int num){
        if (n==1){
            println("1");
            println("Количество шагов - "+ num);
            return;
        }
        if (n%2==0){
            println(n + " / 2 = " +n/2);
            n=n/2;

        }else if (n%2==1){
            println(n + " * 3 + 1 =" + (n*3+1));
            n=n*3+1;

        }
        num++;
        recurs(n,num);
    }
}
