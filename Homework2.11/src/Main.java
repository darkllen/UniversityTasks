import javax.swing.*;
import java.io.File;
import java.util.List;

public class Main {
    public static void main(String[] args) {
  /*      JFileChooser fileChooser = new JFileChooser();
        fileChooser.setMultiSelectionEnabled(true);
        if (fileChooser.showDialog(null,"Choose your file")==JFileChooser.APPROVE_OPTION){
            File[] files = fileChooser.getSelectedFiles();
            */
            List myList = new MyList();
            Word word = new Word();
            word.setName("a");
            Word word1 = new Word();
            word1.setName("v");
            myList.add(word);
            myList.add(word1);
            Word word2 = new Word();
            word2.setName("c");
            System.out.println(myList.contains(word2));
        }
    }

