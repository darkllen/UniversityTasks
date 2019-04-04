import javax.swing.*;
import java.io.File;
import java.io.IOException;
import java.util.Comparator;
import java.util.List;

public class Main {
    public static void main(String[] args) throws IOException {
        int collectionSize = 0;
        int wordsNumber = 0;

        JFileChooser fileChooser = new JFileChooser();
        fileChooser.setMultiSelectionEnabled(true);
        if (fileChooser.showDialog(null, "Choose your file") == JFileChooser.APPROVE_OPTION) {
            File[] files = fileChooser.getSelectedFiles();

            List<Word> myList = new MyWordList();

            for (File f: files) {
                collectionSize += f.length();
                Analys.fileAnalys(f,myList);
            }

            wordsNumber = myList.size();

            myList.sort(Comparator.comparing(Word::getName));

            Analys.writeToFile(myList.toString(), wordsNumber, collectionSize);
        }
    }
}

