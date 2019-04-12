import java.util.stream.IntStream;

public class Main {
    public static void main(String[] args) {
        int[] u = {-2,-4,0,1,5,2,6,9,4};
        IntStream stream = IntStream.of(u);
       // System.out.println(StreamHome.cntDistIden("aaa"));
        StreamHome.allIdens("___as_d a32sd&*32fa12s").forEach(System.out::println);
    }
}
