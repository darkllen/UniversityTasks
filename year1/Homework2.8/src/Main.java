import java.io.IOException;

public class Main {
    private static final String PATH ="C:\\Users\\yanki\\Desktop\\";
    public static void main(String[] args) throws IOException {


        FileWord fileWord = new FileWord(PATH+"bbb.txt");

        FileAnalyse.analyse(fileWord);

       System.out.println(fileWord);
    }
}
