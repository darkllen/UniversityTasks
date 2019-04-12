import java.util.Comparator;
import java.util.Optional;
import java.util.OptionalInt;
import java.util.function.IntPredicate;
import java.util.regex.Pattern;
import java.util.stream.IntStream;
import java.util.stream.Stream;

public class Main {
    public static void main(String[] args) {
         String[] ia = {"aa", "bb"};


         Main.onlyAbBeginA("baab ba sd a").forEach(System.out::println);
    }

    static public int sumA(int[] ia){

        return IntStream.of(ia).sum();
    }


    private static int fact(int a){
        return a==0?1:a*fact(a-1);
    }

    private static IntStream factS(){
        IntStream intStream = IntStream.iterate(1,x->x+1);
        return intStream.map(x->fact(x));
    }

    public static OptionalInt maxPos(IntStream is){
        return is.filter(x->x>0).filter(x->x%2==0).max();
    }

    public static boolean isIn(String[] as, String s){
        return Stream.of(as).anyMatch(x->x.equals(s));
    }

    public static Stream<String> onlyAb(String word){
        return Pattern.compile("[^ab]+").splitAsStream(word).filter(x->!x.isEmpty());
    }

    public static Stream<String> onlyAbBeginA(String word){
        return Pattern.compile("[^ab]+").splitAsStream(word).map(x->x.replaceFirst("b*","")).filter(x->!x.isEmpty());
    }
    public static  Optional<String> minAb (String word){
        return onlyAbBeginA(word).sorted(Comparator.comparing(String::length)).findFirst();
    }

    public static long sizeFile(String file){
        return 0;
    }




}
