import java.awt.List;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.*;
import java.util.regex.Pattern;
import java.util.stream.IntStream;
import java.util.stream.Stream;

public class StreamHome {
    public static int sumOdd(IntStream is, int m) {
        return is.filter(x -> x <= m).filter(x -> x % 2 == 0).filter(x -> x > 0).sum();
    }


    private static boolean isPrime(int n) {
        if (n == 1) return false;
        for (int i = 2; i * i <= n; i++) {
            if (n % i == 0) return false;
        }
        return true;
    }

    private static int lessPrime(int n) {
        for (int i = n; i > 1; i--) {
            if (n % i == 0 && isPrime(i)) return i;
        }
        return 1;
    }

    public static IntStream bigPrimeDiv(int n) {
        if (n < 2) n = 2;
        IntStream stream = IntStream.iterate(n, x -> x + 1);
        return stream.map(StreamHome::lessPrime);
    }

    public static OptionalInt maxPrime(IntStream is, int n) {
        return is.filter(x -> x > n).filter(StreamHome::isPrime).max();
    }

    public static Stream<String> allIdens(String word) {
        Stream<String> stringStream = Pattern.compile("\\W").splitAsStream(word);
        return stringStream.map(x -> x.replaceFirst("[\\d_]*", "")).filter(x -> !x.isEmpty());
    }

    public static Optional<String> minIden(String file) {
        try {
            Stream<String> stringStream = Files.lines(Paths.get(file));
            stringStream = stringStream.flatMap(x -> allIdens(x));
            return stringStream.sorted().findFirst();
        } catch (IOException e) {
            e.printStackTrace();
            return Optional.empty();
        }
    }

    public static long cntDistIden(String file) {
        try {
            Stream<String> stringStream = Files.lines(Paths.get(file));
            stringStream = stringStream.map(x -> x.replaceFirst("//.*", "")).
                    flatMap(StreamHome::allIdens).distinct();
            return stringStream.count();
        } catch (IOException e) {
            e.printStackTrace();
            return 0;
        }
    }

    public static String[] allTypesAuthor(Stream<BookA> library, String aut) {
      return   library.filter(x ->Arrays.asList(x).contains(aut)).flatMap(x->Stream.of(x.getTypes())).distinct().toArray(String[]::new);
    }

}
