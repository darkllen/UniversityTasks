import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;

public class Outcast {
    // конструктор приймає об’єкт WordNet
    WordNet net;
    public Outcast(WordNet wordnet){
        net = wordnet;
    }
    // маючи масив WordNet іменників, повернути «ізгоя»
    public String outcast(String[] nouns){
        int[] l = new int[nouns.length];
        for (int i = 0; i < nouns.length; i++) {
            for (int i1 = 0; i1 < nouns.length; i1++) {
                l[i] += net.distance(nouns[i],nouns[i1]);
            }
        }
        int max = l[0];
        String m = nouns[0];
        for (int i = 1; i < l.length; i++) {
            if(max<l[i]){
                max = l[i];
                m = nouns[i];
            }
        }
        return m;
    }
    public static void main(String[] args) throws IOException {
        WordNet wordNet = new WordNet("synsets.txt", "hypernyms.txt");
        Outcast outcast = new Outcast(wordNet);
        String[] s= {"water", "bed","milk"};
        System.out.println(outcast.outcast(s));
    }

}
