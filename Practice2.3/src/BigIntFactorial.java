import java.math.BigInteger;
import java.util.ArrayList;

public class BigIntFactorial {
    public BigIntFactorial() {
        for (BigInteger b:countedFactorialRecords
             ) {
            b=(BigInteger.valueOf(0));
        }
        countedFactorialRecords[0]= BigInteger.valueOf(1);
        countedFactorialRecords[1]= BigInteger.valueOf(1);
        numberOfCountedFactorial=1;
    }

    private static int numberOfCountedFactorial=0;
    private static BigInteger[] countedFactorialRecords = new BigInteger[21];

    /**
     *
     * @param n
     * @return factorial of n
     */
    public static BigInteger bigFactorial(int n){
        if (n<0) return null;
        BigInteger res = new BigInteger("1");
        int i = 1;
        while (n>=i){
           res = res.multiply(BigInteger.valueOf(i));
            i++;
        }
        return res;
    }

    /**
     *
     * @param n
     * @return factorial of n and record all cash for next fast counts
     */
    public BigInteger cashFactorial(int n){
        if (n<0) return null;
        if (n<=numberOfCountedFactorial)return countedFactorialRecords[n];

        for (int i =numberOfCountedFactorial;i<n;i++){
            if (numberOfCountedFactorial!=0){
                countedFactorialRecords[i+1] = countedFactorialRecords[i].multiply(BigInteger.valueOf((i+1)));
            }
        }
        return countedFactorialRecords[n];
    }

    public void sysOut(){
        for (BigInteger bi:countedFactorialRecords
             ) {
            try{
                System.out.println(bi.toString());
            }catch (NullPointerException e){
                System.out.println(0);
            }

        }
    }
}
