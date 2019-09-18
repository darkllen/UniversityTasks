import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        BigIntFactorial bigIntFactorial = new BigIntFactorial();
        System.out.println(BigIntFactorial.bigFactorial(200));
        while (true){
            System.out.println("Input value of x!: ");
            Scanner scanner = new Scanner(System.in);
            System.out.println("x! = "+bigIntFactorial.cashFactorial(scanner.nextInt()).toString());
            bigIntFactorial.sysOut();

        }

    }

}
