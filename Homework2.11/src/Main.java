import Output.MainFrame;
import javax.swing.*;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;

public class Main {
    public static void main(String[] args) throws IOException {
        int collectionSize = 0;
        int wordsNumber = 0;

        JFileChooser fileChooser = new JFileChooser();
        fileChooser.setMultiSelectionEnabled(true);
        fileChooser.setCurrentDirectory(new File("C:\\Users\\yanki\\OneDrive"));
        if (fileChooser.showDialog(null, "Choose your file") == JFileChooser.APPROVE_OPTION) {
            File[] files = fileChooser.getSelectedFiles();

            HashMap myList = new HashMap();

            for (File f: files) {
                collectionSize += f.length();
                Analys.fileAnalys(f,myList);
            }
            wordsNumber = myList.size();

            ArrayList arrayList = new ArrayList();
            arrayList.addAll(myList.values());

            long size =  Analys.writeToFile(myList.values().toString().replaceAll(", ", ""), wordsNumber, collectionSize);
            new MainFrame(arrayList, wordsNumber, collectionSize, size);
        }
    }
}

