
import acm.program.ConsoleProgram;
//Ihor Yankin

//Послідовність сум {sn}, де sn=1+x+x2/2!+…+xn/n!, за умови 0<=x<1 "достатньо швидко" сходиться до ex. Запрограмувати обчислення ex при x [0;1) із точністю ep , тобто за потрібне число приймається перше snтаке, що | sn-sn-1 |<ep .
public class Functiion1 extends ConsoleProgram {
    public void run(){
        this.setFont("SeinsSherif-20");
        this.setSize(500,700);
        while (true){
        println("Вычисление е^x.");
        double x =readDouble("Введите x, 0<=х<1: ");
        if (x<0||x>=1){
            println("Данное числовое значение невозможно");
            continue;
        }
            double p=0;
            while (true){
                p = readDouble("Введите точность вычисления");
                if (p<=0){
                    println("Данное числовое значение невозможно");
                    continue;
                }else break;
        }

        double sum = 1;
        int n = 1;
        println("Результат в рекурсивном способе - "+ex(x,p,sum,n));
        println("Результат без рекурсивного способа - "+ex2(x,p,sum,n));
        println("е^x - "+Math.pow(2.718281828,x));

            println("Для заверщения введите 0");
            println("Для продолжения введите любое другое число");
            double i = readDouble();
            if (i==0){
                println("Работа завершена");
                return;
        }


        }
    }

//calculate e^x recursive
    private double ex(double x, double p, double sum, int n){
        int factor = 0;
        if (n!=1 &&Math.abs(Math.pow(x, n)/factorial(n)-Math.pow(x, n-1)/factorial(n-1))<p){
            println("Количество шагов в рекурсивном способе - "+n);
            return sum;
        }

        sum += Math.pow(x, n)/factorial(n);
      sum= ex(x,p,sum,n+1);
        return sum;
    }

    //calculate e^x non recursive.
    private double ex2(double x, double p, double sum, int n){
        int factor = 0;
        while (true){
            if (n!=1 &&Math.abs(Math.pow(x, n)/factorial2(n)-Math.pow(x, n-1)/factorial2(n-1))<p){
                println("Количество шагов без рекурсивного способа - "+n);
                return sum;
            }
            sum += Math.pow(x, n)/factorial2(n);
            n++;
        }
    }
    //calculate n!
    private int factorial(int n){
        if (n == 0) return 1;
        return n * factorial(n-1);
    }
    private int factorial2(int n){
        int ret = 1;
        for (int i = 1; i <= n; ++i) ret *= i;
        return ret;
    }
}
