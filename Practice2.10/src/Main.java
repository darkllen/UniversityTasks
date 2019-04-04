import javax.swing.*;

import java.awt.*;
import java.io.*;
import java.nio.charset.Charset;

public class Main {
    public static void main(String[] args) throws IOException {
        JFileChooser fileChooser = new JFileChooser();
        if (fileChooser.showDialog(null,"Choose your file")==JFileChooser.APPROVE_OPTION){
            File file = fileChooser.getSelectedFile();
            Desktop desktop = Desktop.getDesktop();
            desktop.open(file);
            BufferedReader reader = new BufferedReader(new InputStreamReader(new FileInputStream(file.getPath()),Charset.forName("cp1251")));
            String line = reader.readLine();
            while (line !=null){
                System.out.println(line);
                line = reader.readLine();
            }
        }

    }
}
