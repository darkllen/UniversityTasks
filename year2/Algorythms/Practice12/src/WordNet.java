import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.concurrent.atomic.AtomicBoolean;
import java.util.concurrent.atomic.AtomicInteger;

public class WordNet {
    Digraph digraph;
    ArrayList<Synset> syn = new ArrayList<>();
    public WordNet(String synsets, String hypernyms) throws IOException {
        BufferedReader br = new BufferedReader(new FileReader(synsets));
        while (br.ready()){
            String[] s = br.readLine().split(",");
            syn.add(new Synset(Integer.parseInt(s[0]),s[1],s[2]));
        }
        digraph = new Digraph(syn.size());
        br = new BufferedReader(new FileReader(hypernyms));
        while (br.ready()){
            String[] s = br.readLine().split(",");
            for (int i = 1; i < s.length; i++) {
                digraph.addEdge(Integer.parseInt(s[0]),Integer.parseInt(s[i]));
            }
        }
    System.out.println("S");
    }
    // множина іменників, що повертається як ітератор (без дублікатів)
    public Iterable<String> nouns(){
        ArrayList<String> arr = new ArrayList<>();
        syn.forEach(x->arr.add(x.name));
        return  arr;
    }
    // чи є слово серед WordNet іменників?
    public boolean isNoun(String word){
        AtomicBoolean b = new AtomicBoolean(false);
        nouns().forEach(x->{
            if (x.equals(word))b.set(true);
        });
        return b.get();
    }
    // відстань між nounA і nounB
    public int getIdByNoun(String n){
        AtomicInteger i = new AtomicInteger(-1);
        syn.forEach(x->{
            if (x.name.equals(n)){
                i.set(x.id);
                return;
            }
        });
        return i.get();
    }
    public int distance(String nounA, String nounB){

        return 0;
    }
    // синсет що є спільним предком nounA і nounB
// в найкоршому шляху до предка
    public String sap(String nounA, String nounB){
        return null;
    }
    // тестування
    public static void main(String[] args) throws IOException {
        WordNet wordNet = new WordNet("synsets.txt", "hypernyms.txt");
        System.out.println(wordNet.isNoun("sunset"));
        System.out.println(wordNet.distance("alloy metal", "18-karat_gold"));
    }

    class Synset{
        int id;
        String name;
        String definition;

        public Synset(int id, String name, String definition) {
            this.id = id;
            this.name = name;
            this.definition = definition;
        }
    }



}
