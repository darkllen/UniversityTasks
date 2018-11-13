import acm.program.ConsoleProgram;

public class summa extends ConsoleProgram {
    public void run(){
        int a = readInt();
        int b = readInt();
        int sum = 0;
        while(a!=1){
            if (a%2==1)
                sum+=b;
            a/=2;
            b*=2;
        }
        println(sum+b);
    }
}
