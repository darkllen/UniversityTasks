import javax.swing.*;
import java.awt.event.*;
import java.io.FileWriter;
import java.io.IOException;

public class FileName extends JDialog {
    private JPanel contentPane;
    private JButton buttonOK;
    private JButton buttonCancel;
    private JTextField textField1;

    public FileName(double start, double end, double step, double sizeKoef, double mouseX, double mouseY, int centreX, int centreY) {

        setLocation(centreX,centreY);
        setVisible(true);
        setSize(400,100);
        setResizable(false);
        setContentPane(contentPane);
        setModal(true);
        getRootPane().setDefaultButton(buttonOK);

        buttonOK.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                onOK(start, end, step,sizeKoef,mouseX,mouseY);
            }
        });
        buttonCancel.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                onCancel();
            }
        });
        setDefaultCloseOperation(DO_NOTHING_ON_CLOSE);
        addWindowListener(new WindowAdapter() {
            public void windowClosing(WindowEvent e) {
                onCancel();
            }
        });
        contentPane.registerKeyboardAction(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                onCancel();
            }
        }, KeyStroke.getKeyStroke(KeyEvent.VK_ESCAPE, 0), JComponent.WHEN_ANCESTOR_OF_FOCUSED_COMPONENT);
    }

    /**
     *
     * @param start start a parameter
     * @param end end a parameter
     * @param step step of a for each iteration
     * @param sizeKoef zoom
     * @param mouseX mouse change to the centre by x coordinate
     * @param mouseY mouse change to the centre by y coordinate
     */
    private void onOK(double start, double end, double step, double sizeKoef, double mouseX, double mouseY) {
        try{
        FileWriter writer = new FileWriter(textField1.getText());
        writer.write(start + " " + end + " " + step + " " + sizeKoef + " " + mouseX + " " + mouseY);
        writer.close();}
        catch (IOException e){}
        dispose();
    }
    private void onCancel() { dispose(); }

}
