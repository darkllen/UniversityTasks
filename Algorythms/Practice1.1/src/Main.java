import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        do{
            int startI = sc.nextInt();
            int startJ = sc.nextInt();
            int i;
            int j;
            if (startI>startJ){
                i = startJ;
                j = startI;
            }else {
                i = startI;
                j = startJ;
            }
            int max = 0;
            for (int k = i; k <= j; k++) {
                int count = 0;
                int l = k;
                while (l != 1) {
                    if (l % 2 == 0) {
                        l /= 2;
                    } else {
                        l = 3 * l + 1;
                    }
                    count++;
                }
                count++;
                if (max < count) max = count;
            }
            System.out.println(startI + " " + startJ + " " + max);
        }
        while (sc.hasNext()) ;
    }
}
