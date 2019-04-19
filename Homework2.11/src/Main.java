import Output.MainFrame;
import javax.swing.*;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.IllegalFormatException;

public class Main {
    public static void main(String[] args) throws IOException {
        int collectionSize = 0;
        int wordsNumber = 0;

        JFileChooser fileChooser = new JFileChooser();
        fileChooser.setMultiSelectionEnabled(true);
        fileChooser.setCurrentDirectory(new File("C:\\Users\\yanki\\OneDrive"));
        if (fileChooser.showDialog(null, "Choose your file") == JFileChooser.APPROVE_OPTION) {
            File[] files = fileChooser.getSelectedFiles();
try{

    if (files.length<10)throw new Exception("less files " + files.length);
}catch(Exception e){
    System.out.println(e.getMessage());
    return;
}
            HashMap myList = new HashMap();

            for (File f: files) {
                try {
                    if (!f.getName().contains("txt")) throw new Exception("impossible not txt type " + f.getName());
                    if (!f.canRead()) throw new Exception("impossible to read " + f.getName());
                    if (f.length()==0) throw new Exception("no information in " + f.getName());
                    if (f.length()<=140000) throw new Exception("too small size " + f.length());
                    collectionSize += f.length();
                    Analys.fileAnalys(f,myList);
                } catch (Exception e){
                    System.out.println(e.getMessage());
                }
            }
            wordsNumber = myList.size();
            ArrayList arrayList = new ArrayList();
            arrayList.addAll(myList.values());
            try{
                if (arrayList.size()==0) throw new Exception("no words to record");
                long size =  Analys.writeToFile(myList.values().toString().replaceAll(", ", ""), wordsNumber, collectionSize);
                new MainFrame(arrayList, wordsNumber, collectionSize, size);
            }catch (Exception e){
                System.out.println(e.getMessage());
            }



        }
    }
}

