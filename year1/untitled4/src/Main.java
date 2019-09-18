import javax.swing.*;

public class Main extends JFrame{

    public Main(){

        super();

        this.setSize(300, 200);

        this.getContentPane().setLayout(null);

    }

    public static void main(String[] args) {

        Main mf = new Main();

        JButton mb = new JButton();

        mb.setBounds(103, 110, 71, 27);

        mb.setText("OK");

        mb.add(mf);

        mf.setVisible(true);

    }

}