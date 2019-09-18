import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

public class FrameMain extends JFrame {
    int maxNum = 0;
    ArrayList<Sample> samples = new ArrayList<>();
    private JPanel panel1;
    private JTextField textField1;
    private JTextField textField2;
    private JTextField textField3;
    private JButton createSampleButton;
    private JButton resultButton;
    private JLabel result;
    private JButton prevButton;
    private JButton nextButton;
    private JLabel leftSide;
    private JLabel rightSide;
    private JLabel action;
    private JLabel equal;
    private JPanel content;

    public FrameMain() throws HeadlessException {
        final int[] count = {0};
        setSize(500,300);
        setResizable(false);
        setContentPane(panel1);
        setVisible(true);

        /**
         * method to create samples
         */
        createSampleButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                samples = new ArrayList<>();
                if (leftSide!=null){
                content.remove(leftSide);
                content.remove(rightSide);
                content.remove(action);
                content.remove(textField3);
                content.remove(equal);}
                maxNum = Integer.parseInt(textField1.getText());
                if (Integer.parseInt(textField2.getText())<1) return;
                if (maxNum <=1) return;
                for (int i = 0;i<Integer.parseInt(textField2.getText());i++){
                    samples.add(i,new Sample(maxNum));
                }
                leftSide =new JLabel();
                leftSide.setBounds(10,20,50,100);
                leftSide.setText(String.valueOf(samples.get(0).getLeftSide()));
                rightSide =new JLabel();
                rightSide.setBounds(110,20,50,100);
                rightSide.setText(String.valueOf(samples.get(0).getRightSide()));

                action = new JLabel();
                action.setBounds(60,20,50,100);
                action.setText(samples.get(0).getAction());

                equal = new JLabel("=");
                equal.setBounds(170,20,50,100);

                textField3 = new JTextField();
                textField3.setBounds(210,55,50,30);

                content.add(leftSide);
                content.add(rightSide);
                content.add(action);
                content.add(equal);
                content.add(textField3);
                textField1.setText("");
                textField2.setText("");
                repaint();
            }
        });

        /**
         * method to move beetween samples
         */
        nextButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                content.remove(leftSide);
                content.remove(rightSide);
                content.remove(action);
                content.remove(textField3);
                content.remove(equal);
                if (textField3.getText().length()>0) {
                    try{
                    samples.get(count[0]).setResult(Integer.parseInt(textField3.getText()));}
                    catch (Exception e1){
                        samples.get(count[0]).setResult(-999);
                        textField3.setText("");
                    }
                }
                count[0]++;
                if (count[0]<samples.size()){
                leftSide.setText(String.valueOf(samples.get(count[0]).getLeftSide()));
                    leftSide.setBounds(10,20,50,100);

                    rightSide.setText(String.valueOf(samples.get(count[0]).getRightSide()));
                    rightSide.setBounds(110,20,50,100);

                    action.setText(String.valueOf(samples.get(count[0]).getAction()));
                    action.setBounds(60,20,50,100);
                    equal.setBounds(170,20,50,100);
                    textField3.setBounds(210,55,50,30);



                    if (samples.get(count[0]).getResult()!=-999){
                        textField3.setText(String.valueOf(samples.get(count[0]).getResult()));} else textField3.setText("");
                    content.add(leftSide);
                    content.add(rightSide);
                    content.add(action);
                    content.add(equal);
                    content.add(textField3);
                repaint();
                }else count[0]--;
            }
        });

        /**
         * method for move beetween samples
         */
        prevButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                content.remove(leftSide);
                content.remove(rightSide);
                content.remove(action);
                content.remove(textField3);
                content.remove(equal);
                if (textField3.getText().length() > 0) {
                    try{
                    samples.get(count[0]).setResult(Integer.parseInt(textField3.getText()));}
                    catch (Exception e1){
                        samples.get(count[0]).setResult(-999);
                        textField3.setText("");
                    }
                }
                count[0]--;
                if (count[0]>=0){
                    leftSide.setText(String.valueOf(samples.get(count[0]).getLeftSide()));
                    leftSide.setBounds(10,20,50,100);

                    rightSide.setText(String.valueOf(samples.get(count[0]).getRightSide()));
                    rightSide.setBounds(110,20,50,100);

                    action.setText(String.valueOf(samples.get(count[0]).getAction()));
                    action.setBounds(60,20,50,100);
                    equal.setBounds(170,20,50,100);
                    textField3.setBounds(210,55,50,30);

                    if (samples.get(count[0]).getResult()!=-999){
                    textField3.setText(String.valueOf(samples.get(count[0]).getResult()));} else textField3.setText("");
                    content.add(leftSide);
                    content.add(rightSide);
                    content.add(action);
                    content.add(equal);
                    content.add(textField3);
                    repaint();
                } else count[0]++;
            }
        });

        /**
         * method for submit result
         */
        resultButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (textField3.getText().length()>0){
                    try{
                        samples.get(count[0]).setResult(Integer.parseInt(textField3.getText()));
                    } catch (Exception e1){
                        samples.get(count[0]).setResult(-999);
                        textField3.setText("");
                    }

                }

                content.remove(leftSide);
                content.remove(rightSide);
                content.remove(action);
                content.remove(textField3);
                content.remove(equal);

                int count = 0;
                for (int i = 0;i<samples.size();i++){
                    if (samples.get(i).getResultReal()==samples.get(i).getResult())count++;
                }
                action.setText(count+" from " + samples.size()+" is right");
                action.setSize(100,100);
                samples = new ArrayList<>();

                content.add(action);
                repaint();
            }
        });
    }
}
