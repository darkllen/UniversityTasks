import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class FraimMain extends JFrame {
    private JTextField textField1;
    private JPanel panel1;
    private JButton signInButton;
    private JPasswordField passwordField1;
    private static final String LOGIN = "admin";
    private static final String PASSWORD = "admin";

    public FraimMain() throws HeadlessException {
        setSize(500,300);
        setResizable(false);
        setContentPane(panel1);
        setVisible(true);
        signInButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (textField1.getText().equals(LOGIN)&& checkPass(passwordField1.getPassword())){
                    DialogYes dialogYes = new DialogYes();
                    dialogYes.pack();
                    dialogYes.setVisible(true);
                }else {
                    DialogNo dialogYes = new DialogNo();
                    dialogYes.pack();
                    dialogYes.setVisible(true);
                }
            }

        });
    }

    /**
     * check pass for correct
     * @param pass
     * @return
     */
    public boolean checkPass(char[] pass){
        if (pass.length!=PASSWORD.length()) return false;
        for(int i = 0;i<pass.length;i++){
            if (pass[i]!=PASSWORD.getBytes()[i]){
                return false;
            }
        }
        return true;
    }
}
