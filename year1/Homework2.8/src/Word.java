public class Word implements Comparable<Word>{
    private String word;
    private int number;

     Word(String word, int number) {
        this.word = word;
        this.number = number;
    }

     String getWord() {
        return word;
    }

    public void setWord(String word) {
        this.word = word;
    }

    public int getNumber() {
        return number;
    }

    public void setNumber(int number) {
        this.number = number;
    }

    void incrNumber(){
        number++;
    }

    @Override
    public int compareTo(Word o) {
        return this.word.compareTo(o.word);
    }

    @Override
    public String toString() {
        return  word + " - " + number;
    }
}
