import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.math.BigInteger;

public class Main {
    public static void main(String[] args) throws IOException {

        BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(System.in));
        bufferedReader.readLine();
        BigInteger a = new BigInteger(bufferedReader.readLine());
        BigInteger b = new BigInteger(bufferedReader.readLine());

        while (!a.equals(b)){
            if(a.compareTo(b)>0){
                a = a.divide(BigInteger.valueOf(2));
            }
            else{
                b = b.divide(BigInteger.valueOf(2));
            }
        }

        System.out.println(a);
    }

}
