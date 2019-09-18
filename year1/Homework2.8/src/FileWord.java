import java.util.ArrayList;

public class FileWord {
    private String path;
    private int wordsNumber;
    private int wordsUniqueNumber;
    private ArrayList<Word> words;

     FileWord(String path) {
        this.path = path;
    }

     String getPath() {
        return path;
    }

    public void setPath(String path) { this.path = path; }

    public int getWordsNumber() {
        return wordsNumber;
    }

    void setWordsNumber(int wordsNumber) {
        this.wordsNumber = wordsNumber;
    }

    public int getWordsUniqueNumber() {
        return wordsUniqueNumber;
    }

    void setWordsUniqueNumber(int wordsUniqueNumber) {
        this.wordsUniqueNumber = wordsUniqueNumber;
    }

    public ArrayList<Word> getWords() {
        return words;
    }

    void setWords(ArrayList<Word> words) {
        this.words = words;
    }

    @Override
    public String toString() {
        String res= "";
        for (Word w:words
             ) {
            res = res + w.toString()+"\n";
        }
        return "File - " + path + "\n"+
                "Words number - " + wordsNumber +"\n"+
                "Unique words number -" + wordsUniqueNumber + "\n"+res;
    }
}
