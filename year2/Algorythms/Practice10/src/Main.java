import java.io.*;

public class Main {
    public static void main(String[] args) throws IOException {
        SearchDictionary dictionary = new SearchDictionary();
        BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(new FileInputStream("//C:/Users/yanki/OneDrive/Documents/GitHub/UniversityTasks/year2/Algorythms/Practice10/src/3.txt")));
        while (bufferedReader.ready()){
            String[] l = bufferedReader.readLine().replaceAll("[^a-zA-Zа-яА-Яё ]", " ").split(" ");
            for (int i = 0; i < l.length; i++) {
                if (l[i].length()>0)dictionary.addWord(l[i]);
            }
        }
        dictionary.delWord("как");
        dictionary.addWord("какогог");

        Iterable<String> i = dictionary.query("кол*");
        i.forEach(System.out::println);

    }
}
