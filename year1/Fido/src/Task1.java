public class Task1 {
    public static void main(String[] args) {
        int m = 20;
        int n = 5;
        System.out.println(countWays(m,n));
    }
    private static long countWays(int m, int n){
        if (m==1 || n==1)
            return 1;
        return countWays(m,n-1)+ countWays(m-1,n);
    }
}
