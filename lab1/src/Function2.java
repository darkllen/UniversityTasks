import acm.program.ConsoleProgram;
//Ihor Yankin

//Послідовність сум {sn}, де sn=1-x2/2!+…+(-1)nx2n/(2n)!, за умови |x|<= p /4 "достатньо швидко" сходиться до cos(x). Запрограмувати обчислення cos(x) при x [-p /4; p/4] з точністю ep , тобто за потрібне число приймається перше snтаке, що | sn-sn-1 |<ep .
//
//Розв'язати обома способами.
public class Function2 extends ConsoleProgram {
    public void run() {
        this.setFont("SeinsSherif-20");
        this.setSize(500,700);
        while (true) {
            println("Вычисление cos(x).");
            double x = readDouble("Введите x, |x|<=p/4: ");
            if (Math.abs(x)>Math.PI/4){
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
            println("Результат в рекурсивном способе - "+cos(x, p, sum, n));
            println("Результат без рекурсивного способа - "+cos2(x, p, sum, n));
            println("cos(x) - "+Math.cos(x));

            println("Для заверщения введите 0");
            println("Для продолжения введите любое другое число");
            double i = readDouble();
            if (i==0){
                println("Работа завершена");
                return;
            }
        }
    }

    //calculate cos(x) recursive
    private double cos(double x, double p, double sum, int n){
        int factor = 0;
        if (n!=1 &&Math.abs(Math.pow(-1, n)*Math.pow(x,2*n)/factorial(2*n)-Math.pow(-1, n-1)*Math.pow(x,2*(n-1))/factorial(2*(n-1)))<p){
            println("Количество шагов в рекурсивном способе - "+n);
            return sum;
        }

        sum +=  Math.pow(-1, n)*Math.pow(x,2*n)/factorial(2*n);
        sum= cos(x,p,sum,n+1);
        return sum;
    }

    //calculate cos(x) non recursive.
    private double cos2(double x, double p, double sum, int n){
        int factor = 0;
        while (true){
            if (n!=1 &&Math.abs(Math.pow(-1, n)*Math.pow(x,2*n)/factorial(2*n)-Math.pow(-1, n-1)*Math.pow(x,2*(n-1))/factorial(2*(n-1)))<p){
                println("Количество шагов без рекурсивного способа - "+n);
                return sum;
            }

            sum += Math.pow(-1, n)*Math.pow(x,2*n)/factorial(2*n);
            n++;
        }
    }
    //calculate n!
    private long factorial(int n){
        if (n == 0) return 1;
        return n * factorial(n-1);
    }
}
