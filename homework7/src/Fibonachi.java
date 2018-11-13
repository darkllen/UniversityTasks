import acm.program.ConsoleProgram;

public class Fibonachi extends ConsoleProgram {
    public void run(){
        while (true){
            println("Вычисление чисел Фибоначчи до определенного" +
                    "значения.");
            println("Введите, до какого числа высчитывать число.");
            int n = readInt();
            int i =0;
            while (true){
                if (fibonachi(i)>n)
                    break;
                println(i + " - " +fibonachi(i));
                i++;
            }
        }
    }
    private int fibonachi(int n) {
        if (n==1)
            return 1;
        if (n==0)
            return 0;
        return fibonachi(n-1) + fibonachi(n-2);
    }
}
