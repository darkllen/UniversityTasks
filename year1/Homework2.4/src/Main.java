
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.geom.*;
import java.util.ArrayList;
import java.util.Collections;

class Main extends JFrame{

    ArrayList<Rectangle> rectangles = new ArrayList<>();
    Point point = new Point(-1,-1);
    Rectangle rectangle1;
    Rectangle rectangle2;
    int countCurrentRect=0;

    /**
     * create static menu and add all listeners
     */
    public Main(){
        JPanel panel=new JPanel();
        panel.setLayout(null);

        Label rect1 = new Label();
        rect1.setBounds(500,20,400,20);

        Label rect2 = new Label();
        rect2.setBounds(500,50,400,20);

        JTextField moveX = new JTextField(15);
        moveX.setBounds(500,80,50,20);
        Label move1 = new Label();
        move1.setText("x");
        move1.setBounds(490,80,10,15);

        JTextField moveY = new JTextField(15);
        moveY.setBounds(500,100,50,20);
        Label move2 = new Label();
        move2.setText("y");
        move2.setBounds(490,100,10,15);

        JButton buttonMove =new JButton("move rectangle");
        buttonMove.setBounds(560,90,150,20);
        buttonMove.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                for (int i = 0;i<rectangles.size();i++){
                    if (rectangles.get(i)==rectangle1){
                        rectangles.get(i).setX1(rectangles.get(i).getX1()+Integer.parseInt(moveX.getText()));
                        rectangles.get(i).setX2(rectangles.get(i).getX2()+Integer.parseInt(moveX.getText()));
                        rectangles.get(i).setY1(rectangles.get(i).getY1()+Integer.parseInt(moveY.getText()));
                        rectangles.get(i).setY2(rectangles.get(i).getY2()+Integer.parseInt(moveY.getText()));
                    }

                }
                repaint();
            }
        });

        JButton buttonCross =new JButton("cross rectangles");
        buttonCross.setBounds(690,90,150,20);
        buttonCross.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {

                ArrayList<Integer> x = new ArrayList<>();
                ArrayList<Integer> y = new ArrayList<>();
                for (int i = rectangle1.getX1();i<=rectangle1.getX2();i++){
                    if (i>=rectangle2.getX1()&&i<=rectangle2.getX2()&&rectangle1.getY1()>=rectangle2.getY1()&&rectangle1.getY1()<=rectangle2.getY2()){
                        x.add(i);
                    }
                    if (i>=rectangle2.getX1()&&i<=rectangle2.getX2()&&rectangle1.getY2()>=rectangle2.getY1()&&rectangle1.getY2()<=rectangle2.getY2()){
                        x.add(i);
                    }
                }
                for (int i = rectangle1.getY1();i<=rectangle1.getY2();i++){
                    if (i>=rectangle2.getY1()&&i<=rectangle2.getY2()&&rectangle1.getX1()>=rectangle2.getX1()&&rectangle1.getX1()<=rectangle2.getX2()){
                        y.add(i);
                    }
                    if (i>=rectangle2.getY1()&&i<=rectangle2.getY2()&&rectangle1.getX2()>=rectangle2.getX1()&&rectangle1.getX2()<=rectangle2.getX2()){
                        y.add(i);
                    }
                }
                for (int i = rectangle2.getX1();i<=rectangle2.getX2();i++){
                    if (i>=rectangle1.getX1()&&i<=rectangle1.getX2()&&rectangle2.getY1()>=rectangle1.getY1()&&rectangle2.getY1()<=rectangle1.getY2()){
                        x.add(i);
                    }
                    if (i>=rectangle1.getX1()&&i<=rectangle1.getX2()&&rectangle2.getY2()>=rectangle1.getY1()&&rectangle2.getY2()<=rectangle1.getY2()){
                        x.add(i);
                    }
                }
                for (int i = rectangle2.getY1();i<=rectangle2.getY2();i++){
                    if (i>=rectangle1.getY1()&&i<=rectangle1.getY2()&&rectangle2.getX1()>=rectangle1.getX1()&&rectangle2.getX1()<=rectangle1.getX2()){
                        y.add(i);
                    }
                    if (i>=rectangle1.getY1()&&i<=rectangle1.getY2()&&rectangle2.getX2()>=rectangle1.getX1()&&rectangle2.getX2()<=rectangle1.getX2()){
                        y.add(i);
                    }
                }
                Collections.sort(x);
                Collections.sort(y);

                rectangles.remove(rectangle1);
                rectangles.remove(rectangle2);

                rectangle1 = null;
                rectangle2 = null;

                System.out.println(x.get(0));
                System.out.println(y.get(0));
                System.out.println(x.get(x.size()-1));
                System.out.println(y.get(y.size()-1));
                rectangles.add(new Rectangle(x.get(0),y.get(0),x.get(x.size()-1),y.get(y.size()-1)));
                repaint();

                rectangle1=null;
                rectangle2=null;
                panel.remove(move1);
                panel.remove(move2);
                panel.remove(moveX);
                panel.remove(moveY);
                panel.remove(buttonMove);
                panel.remove(buttonCross);
                panel.remove(rect1);
                panel.remove(rect2);
            }
        });

        JButton buttonSum =new JButton("sum rectangles");
        buttonSum.setBounds(530,90,150,20);
        buttonSum.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (rectangle1==rectangle2) return;
                int x1=0;
                int y1=0;
                int x2=0;
                int y2=0;
                if (rectangle1.getX1()<rectangle2.getX1()){
                    x1 = rectangle1.getX1();
                }else x1=rectangle2.getX1();
                if (rectangle1.getX2()>rectangle2.getX2()){
                    x2 = rectangle1.getX2();
                }else x2=rectangle2.getX2();
                if (rectangle1.getY1()<rectangle2.getY1()){
                    y1 = rectangle1.getY1();
                }else y1=rectangle2.getY1();
                if (rectangle1.getY2()>rectangle2.getY2()){
                    y2 = rectangle1.getY2();
                }else y2=rectangle2.getY2();
                for (int i = 0;i<rectangles.size();i++){
                    System.out.println(rectangles.get(i).toString());
                    if (rectangles.get(i)==rectangle1){
                        rectangles.remove(i);
                    }
                    if (rectangles.get(i)==rectangle2){
                        rectangles.remove(i);
                    }
                }
                rectangle1=null;
                rectangle2=null;
                System.out.println(new Rectangle(x1,y1,x2,y2).toString());
                rectangles.add(new Rectangle(x1,y1,x2,y2));
                repaint();

                rectangle1=null;
                rectangle2=null;
                panel.remove(move1);
                panel.remove(move2);
                panel.remove(moveX);
                panel.remove(moveY);
                panel.remove(buttonMove);
                panel.remove(buttonSum);
                panel.remove(buttonCross);
                panel.remove(rect1);
                panel.remove(rect2);
            }
        });

        panel.addMouseListener(new MouseListener() {
            @Override
            public void mouseClicked(MouseEvent e) {
                Point point = new Point(e.getX()+8,e.getY()-169);
                int c = 0;
                for (int i = 0; i<rectangles.size();i++){
                    if (point.getX()>=rectangles.get(i).getX1()&&point.getX()<=rectangles.get(i).getX2()&&point.getY()>=rectangles.get(i).getY1()&&point.getY()<=rectangles.get(i).getY2()){
                        c++;
                        if (countCurrentRect%2==0){
                                rectangle1 = rectangles.get(i);
                                    rect1.setText("rectangle1: " +rectangles.get(i).toString());
                                    panel.add(rect1);
                                    if (rectangle2==null){
                                        panel.remove(buttonSum);
                                        panel.remove(buttonCross);
                                        panel.add(move1);
                                        panel.add(move2);
                                        panel.add(moveX);
                                        panel.add(moveY);
                                        panel.add(buttonMove);
                                    }
                        }else {
                                rectangle2 = rectangles.get(i);
                                rect2.setText("rectangle2: " + rectangles.get(i).toString());
                                panel.add(rect2);

                                panel.remove(move1);
                                panel.remove(move2);
                                panel.remove(moveX);
                                panel.remove(moveY);
                                panel.remove(buttonMove);
                                panel.add(buttonSum);
                                panel.add(buttonCross);
                        }
                        countCurrentRect++;

                    }

                }
                if (c==0){
                    countCurrentRect=0;
                    rectangle1=null;
                    rectangle2=null;
                    panel.remove(move1);
                    panel.remove(move2);
                    panel.remove(moveX);
                    panel.remove(moveY);
                    panel.remove(buttonMove);
                    panel.remove(buttonSum);
                    panel.remove(buttonCross);
                    panel.remove(rect1);
                    panel.remove(rect2);
                }
            }

            @Override
            public void mousePressed(MouseEvent e) {

            }

            @Override
            public void mouseReleased(MouseEvent e) {

            }

            @Override
            public void mouseEntered(MouseEvent e) {

            }

            @Override
            public void mouseExited(MouseEvent e) {

            }
        });

        getContentPane().add(panel);
        setSize(1000,1000);


        JTextField x1 = new JTextField(15);
        x1.setBounds(30,10,50,20);
        Label label1 = new Label();
        label1.setText("x1");
        label1.setBounds(5,10,15,15);

        JTextField y1 = new JTextField(15);
        y1.setBounds(30,35,50,20);
        Label label2 = new Label();
        label2.setText("y1");
        label2.setBounds(5,35,15,15);

        JTextField x2 = new JTextField(15);
        x2.setBounds(30,65,50,20);
        Label label3 = new Label();
        label3.setText("x2");
        label3.setBounds(5,65,15,15);

        JTextField y2 = new JTextField(15);
        y2.setBounds(30,90,50,20);
        Label label4 = new Label();
        label4.setText("y2");
        label4.setBounds(5,90,15,15);

        JTextField pointX = new JTextField(15);
        pointX.setBounds(300,10,50,20);
        Label pointXL = new Label();
        pointXL.setText("x");
        pointXL.setBounds(290,10,15,15);

        JTextField pointY = new JTextField(15);
        pointY.setBounds(300,35,50,20);
        Label pointYL = new Label();
        pointYL.setText("y");
        pointYL.setBounds(290,35,15,15);



        panel.add(x1);
        panel.add(label1);
        panel.add(y1);
        panel.add(label2);

        panel.add(x2);
        panel.add(label3);
        panel.add(y2);
        panel.add(label4);

        panel.add(pointX);
        panel.add(pointXL);
        panel.add(pointY);
        panel.add(pointYL);


        JButton button =new JButton("draw new rectangle");
        button.setBounds(100,50,150,20);
        button.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (Integer.parseInt(x1.getText())>0&&Integer.parseInt(y1.getText())>0&&Integer.parseInt(x2.getText())>0&&Integer.parseInt(y2.getText())>0){
               rectangles.add(new Rectangle(Integer.parseInt(x1.getText()),Integer.parseInt(y1.getText()),Integer.parseInt(x2.getText()),Integer.parseInt(y2.getText())));
               repaint();}
            }
        });
        panel.add(button);

        JButton buttonClear =new JButton("Clear");
        buttonClear.setBounds(100,20,150,20);
        buttonClear.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                rectangles = new ArrayList<Rectangle>();
                point = new Point(-1,-1);
            repaint();
                countCurrentRect=0;
                rectangle1=null;
                rectangle2=null;
                panel.remove(move1);
                panel.remove(move2);
                panel.remove(moveX);
                panel.remove(moveY);
                panel.remove(buttonMove);
                panel.remove(buttonSum);
                panel.remove(buttonCross);
                panel.remove(rect1);
                panel.remove(rect2);
            }
        });
        panel.add(buttonClear);

        JButton buttonPoint =new JButton("Create point");
        buttonPoint.setBounds(290,70,120,20);
        buttonPoint.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                point = new Point(Integer.parseInt(pointX.getText()),Integer.parseInt(pointY.getText()));
                repaint();
            }
        });
        panel.add(buttonPoint);

        JButton buttonCheck =new JButton("Check point");
        buttonCheck.setBounds(290,100,120,20);
        buttonCheck.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                repaint();
                int count = 0;
                for (int i = 0; i<rectangles.size();i++){
                    if (point.getX()>=rectangles.get(i).getX1()&&point.getX()<=rectangles.get(i).getX2()&&point.getY()>=rectangles.get(i).getY1()&&point.getY()<=rectangles.get(i).getY2()){
                        count++;
                    }

                }
                if (count>0)
                    JOptionPane.showMessageDialog(null,  "point is in " + count + " rectangles");
                else  JOptionPane.showMessageDialog(null,  "point don`t cross any rectangle");
            }
        });
        panel.add(buttonCheck);

    }

    /**
     * paint all rectangles and point
     * @param g
     */
    public void paint(Graphics g) {
        super.paint(g);  // fixes the immediate problem.
        Graphics2D g2 = (Graphics2D) g;
        Line2D bord = new Line2D.Float(0, 200, 1000, 200);
        g2.draw(bord);
        if (point.getX()!=-1){
            g2.drawOval(point.getX()-2,point.getY()-2+200,4,4);
        }

        for (int i = 0; i<rectangles.size();i++){
            int x1 = rectangles.get(i).getX1();
            int x2 = rectangles.get(i).getX2();
            int y1 = rectangles.get(i).getY1()+200;
            int y2 = rectangles.get(i).getY2()+200;
            Line2D lin1 = new Line2D.Float(x1, y1, x2, y1);
            Line2D lin2 = new Line2D.Float(x1, y2, x2, y2);
            Line2D lin3 = new Line2D.Float(x1, y1, x1, y2);
            Line2D lin4 = new Line2D.Float(x2, y1, x2, y2);

            g2.draw(lin1);
            g2.draw(lin2);
            g2.draw(lin3);
            g2.draw(lin4);


        }
    }

    public static void main(String []args){
        Main s=new Main();
        s.setVisible(true);
    }


}