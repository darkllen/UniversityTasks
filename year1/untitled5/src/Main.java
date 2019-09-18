import javax.swing.*;
import java.awt.*;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.stream.Stream;


public class Main extends JFrame {
    private JTextArea textArea1;
    private JPanel panel1;

    public Main() throws HeadlessException, IOException {
        Stream<String> str = Files.lines(Paths.get("aaa.txt"));
        str.forEach(x->textArea1.append(x + "\n"));
        this.setVisible(true);
        this.setContentPane(panel1);
    }
}
