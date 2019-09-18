import acm.program.ConsoleProgram;
//Ihor Yankin

//Написати програму, що зчитує з клавіатури цілі числа (по одному числу за раз), поки користувач не введе число 0 (ви маєте бути в змозі легко поміняти цю умову на якесь інше число). По закінченню вводу ваша програма має вивести найменше і найбільше число.
//
//
//
//Якщо користувач введе лише одне число, програма має повідомити що це число і найбільше і найменше
//Якщо користувач в першій же стрічці введе символ закінчення вводу, тоді вважається, що жодного числа не було введено і програма має це повідомити.
public class MinAndMax extends ConsoleProgram {
    public static final int WIDTH = 500;
    public static final int HEIGHT = 700;
    public static final int STOP = -5;
    public void run(){
        this.setSize(WIDTH,HEIGHT);
        this.setFont("SeinsSherif-20");
        while (true){
            println("Вводите числа пока не надоест. После нажатия на " +STOP +
                    " будут выведены минимальное и максимальное число" );

            double max = 0;
            double min = 0;
            int count = 0;
            while (true){

                double a = readDouble();
                if(a==STOP){
                    if (count==0){
                        println("Не было введено ни одного числа");
                    }else if (count==1){
                       println("Вы ввели только одно число, поэтому оно является как минимальным," +
                               "так и максимальным - "+ max);
                    } else{
                        println("Максимальное число - " + max);
                        println("Минимальное число - " + min);
                    }
                    break;
                }
                if (count==0){
                    max=a;
                    min=a;
                }
                if (a>max)
                    max=a;
                if (a<min)
                    min=a;

                count++;
            }
            println("Если желаете начать сначала - введите 1");
            double i = readDouble();
            if (i!=1){
                println("Работа завершена");
                return;
            }
        }
    }
}
