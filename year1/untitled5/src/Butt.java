import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class Butt extends JFrame {
    private JButton button1;
    private JPanel asd;
    private JButton button3;
    private JButton button2;
    private JButton button4;
    private JButton button5;
    private JButton button6;
    private JTextField textField1;

    public Butt() throws HeadlessException {
        this.setContentPane(asd);
        this.setVisible(true);
        button1.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                button1.setVisible(false);
                button1.setEnabled(false);
                System.out.println("ssss");
            }
        });
    }
}
