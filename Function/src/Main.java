import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.geom.Line2D;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.math.BigDecimal;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.text.DecimalFormat;

@SuppressWarnings("ComparatorResultComparison")
public class Main extends JFrame {
    private JTextField textField1;
    private JTextField textField2;
    private JButton buildButton;
    private JPanel mainPanel;
    private JPanel functionPanel;
    private JButton buildFromSaveButton;
    private JButton saveFunctionButton;
    private JTextField textField3;
    private JButton SaveAsImageButton;
    private JPanel buttonsPanel;
    private JPanel inpPanel;
    private JPanel allPanel;
    private JButton button1;
    private JButton button2;
    private JPanel sizePanel;
    private JLabel sizeKoefLabel;
    private JButton getToCenterButton;
    private JButton defaultSizeButton;

    private DecimalFormat format = new DecimalFormat("#.###");
    private double step = 1;
    private double xMouse = 0;
    private double yMouse = 0;
    private double sizeKoef = 1;


    private Main(String title) throws HeadlessException {

        super(title);
        setContentPane(mainPanel);
        setVisible(true);
        setSize(720,600);
        setMinimumSize(new Dimension(720,400));
        setDefaultCloseOperation(DISPOSE_ON_CLOSE);

        addMouseListener(new MouseListener() {
            double xClicked = 0;
            double yClicked = 0;
            @Override
            public void mouseClicked(MouseEvent e) {}

            @Override
            public void mousePressed(MouseEvent e) {
                xClicked = e.getX();
                yClicked = e.getY();
            }

            @Override
            public void mouseReleased(MouseEvent e) {
                mouseClicked(e);
                xMouse += e.getX() -xClicked;
                yMouse += (e.getY() -yClicked);
                repaint();
            }

            @Override
            public void mouseEntered(MouseEvent e) {}

            @Override
            public void mouseExited(MouseEvent e) {}
        });
        buildButton.addActionListener(e -> repaint());
        SaveAsImageButton.addActionListener(e -> {
            try
            {
                if (!textField1.getText().equals("")&&!textField2.getText().equals("")){
                    repaint();
                    BufferedImage awtImage = new Robot().createScreenCapture(new Rectangle(getX()+10,getY()+inpPanel.getHeight()+buildButton.getHeight()+5,getWidth()-15,getHeight()-inpPanel.getHeight()-buildButton.getHeight()-10-sizePanel.getHeight()));
                    new ImageName(awtImage, getX()+getWidth()/2, getY()+getHeight()/2);}
            }
            catch(AWTException e1)
            {
                e1.printStackTrace();
            }
        });
        saveFunctionButton.addActionListener(e -> {
                if (textField3.getText().equals(""))textField3.setText("1");
                new FileName(Double.parseDouble(textField1.getText()), Double.parseDouble(textField2.getText()), Double.parseDouble(textField3.getText()),sizeKoef,xMouse,yMouse, getX()+getWidth()/2, getY()+getHeight()/2);
            });
        buildFromSaveButton.addActionListener(e -> {
            JFileChooser fileChooser = new JFileChooser();
            fileChooser.showDialog(null, "Ok");
            try {
              Object[] arr =  Files.lines(Paths.get(fileChooser.getSelectedFile().toURI())).toArray();
              String[] strings = ((String) arr[0]).split(" ");
              textField1.setText(String.valueOf(Double.parseDouble(strings[0])));
              textField2.setText(String.valueOf(Double.parseDouble(strings[1])));
              textField3.setText(String.valueOf(Double.parseDouble(strings[2])));
              sizeKoef = Double.parseDouble(strings[3]);
              sizeKoefLabel.setText(format.format(sizeKoef*100)+"%");
              xMouse = Double.parseDouble(strings[4]);
              yMouse = Double.parseDouble(strings[5]);
              repaint();
            } catch (IOException ex) {
                ex.printStackTrace();
            }
        });
        button1.addActionListener(e -> {
            if (sizeKoef<500) sizeKoef*=1.35;
            sizeKoefLabel.setText(format.format(sizeKoef*100)+"%");
            repaint();
        });
        button2.addActionListener(e -> {
            if (sizeKoef>0.00184) sizeKoef/=1.35;
            sizeKoefLabel.setText(format.format(sizeKoef*100)+"%");
            repaint();
        });
        defaultSizeButton.addActionListener(e -> {
            sizeKoef = 1;
            sizeKoefLabel.setText(format.format(sizeKoef*100)+"%");
            repaint();
        });
        getToCenterButton.addActionListener(e -> {
            xMouse = 0;
            yMouse = 0;
            repaint();
        });
    }

    @SuppressWarnings("IntegerDivisionInFloatingPointContext")
    public void paint(Graphics g) {
        int xSize = this.getWidth();
        double oXLineYConstant = (functionPanel.getY()+functionPanel.getHeight()/2+buildButton.getHeight()+yMouse);
        int ordinatesRangeConstant = 100000;
        int xCentre = xSize/2;
        int yCentre = functionPanel.getY()+functionPanel.getHeight()/2+buildButton.getHeight();
        int numNums = 1;
        double koef;

        super.paint(g);
        Graphics2D g2 = (Graphics2D) g;
        g2.setColor(Color.RED);

        Line2D oX = new Line2D.Double(-ordinatesRangeConstant+xMouse,oXLineYConstant, (xSize+xMouse+ordinatesRangeConstant), oXLineYConstant);
        Line2D oY = new Line2D.Double(xSize/2+xMouse, yMouse-ordinatesRangeConstant, xSize/2+xMouse, (ordinatesRangeConstant+yMouse));
        g2.draw(oX);
        g2.draw(oY);

        if (functionPanel.getHeight()-buildButton.getHeight()>getWidth()) koef = getWidth()/2;
        else koef = (functionPanel.getHeight()-buildButton.getHeight())/2;

        g.setFont(new Font(g.getFont().getName(), Font.BOLD, 14));

        if(sizeKoef>=1) {
            double a = 7*numNums/2*0.5*numNums;
            numNums = (int) sizeKoef;
            for (int i = 0; i<=7*numNums;i++){
                if (-a+i*0.5/numNums==0)continue;
                if (i==7*numNums){
                    Line2D lineX1 = new Line2D.Double((-a+i*(0.5/numNums))*koef*sizeKoef+xCentre+xMouse,yCentre+yMouse+7,(-a+i*(0.5/numNums))*koef*sizeKoef+xCentre+xMouse+8,yCentre+yMouse);
                    Line2D lineX2 = new Line2D.Double((-a+i*(0.5/numNums))*koef*sizeKoef+xCentre+xMouse,yCentre+yMouse-7,(-a+i*(0.5/numNums))*koef*sizeKoef+xCentre+xMouse+8,yCentre+yMouse);
                    Line2D lineY1 = new Line2D.Double(xCentre+xMouse,(-a+i*(0.5/numNums))*koef*sizeKoef*-1+yCentre+yMouse,xCentre+xMouse+7,(-a+i*(0.5/numNums))*koef*sizeKoef*-1+yCentre+yMouse+8);
                    Line2D lineY2 = new Line2D.Double(xCentre+xMouse,(-a+i*(0.5/numNums))*koef*sizeKoef*-1+yCentre+yMouse,xCentre+xMouse-7,(-a+i*(0.5/numNums))*koef*sizeKoef*-1+yCentre+yMouse+8);
                    g2.draw(lineX1);
                    g2.draw(lineX2);
                    g2.drawString("x",(int)((-a+i*(0.5/numNums))*koef*sizeKoef+xCentre+xMouse), (int)(yCentre+yMouse+20));
                    g2.draw(lineY1);
                    g2.draw(lineY2);
                    g2.drawString("y",(int)(xCentre+xMouse-15),(int)((-a+i*(0.5/numNums))*koef*sizeKoef*-1+yCentre+yMouse+5));
                    break;
                }

                Line2D line = new Line2D.Double((-a+i*(0.5/numNums))*koef*sizeKoef+xCentre+xMouse,yCentre-5+yMouse,(-a+i*(0.5/numNums))*koef*sizeKoef+xCentre+xMouse,yCentre+5+yMouse);
                Line2D line2 = new Line2D.Double(xCentre-5+xMouse,(-a+i*0.5/numNums)*koef*sizeKoef+yCentre+yMouse,xCentre+5+xMouse,(-a+i*0.5/numNums)*koef*sizeKoef+yCentre+yMouse);
                g2.drawString(format.format(BigDecimal.valueOf(i).multiply(BigDecimal.valueOf(0.5/numNums)).add(BigDecimal.valueOf(-a)).doubleValue()), (int)((-a+i*0.5/numNums)*koef*sizeKoef+xCentre+xMouse), (int)(yCentre-5+yMouse));
                g2.drawString(format.format(BigDecimal.valueOf(i).multiply(BigDecimal.valueOf(0.5/numNums)).add(BigDecimal.valueOf(-a)).multiply(BigDecimal.valueOf(-1)).doubleValue()) , (int)(xCentre+5+xMouse), (int)((-a+i*0.5/numNums)*koef*sizeKoef+yCentre+yMouse));
                g2.draw(line2);
                g2.draw(line);
            }
        } else {
            numNums =  (int)(1/sizeKoef);
            double a = 7/2*0.5*numNums;
            for (int i = 0; i<=7;i++){
                if (i==3)continue;
                if (i==7){
                    Line2D lineX1 = new Line2D.Double((-a+i*(0.5*numNums))*koef*sizeKoef+xCentre+xMouse,yCentre+yMouse+7,(-a+i*(0.5*numNums))*koef*sizeKoef+xCentre+xMouse+8,yCentre+yMouse);
                    Line2D lineX2 = new Line2D.Double((-a+i*(0.5*numNums))*koef*sizeKoef+xCentre+xMouse,yCentre+yMouse-7,(-a+i*(0.5*numNums))*koef*sizeKoef+xCentre+xMouse+8,yCentre+yMouse);
                    Line2D lineY1 = new Line2D.Double(xCentre+xMouse,(-a+i*(0.5*numNums))*koef*sizeKoef*-1+yCentre+yMouse,xCentre+xMouse+7,(-a+i*(0.5*numNums))*koef*sizeKoef*-1+yCentre+yMouse+8);
                    Line2D lineY2 = new Line2D.Double(xCentre+xMouse,(-a+i*(0.5*numNums))*koef*sizeKoef*-1+yCentre+yMouse,xCentre+xMouse-7,(-a+i*(0.5*numNums))*koef*sizeKoef*-1+yCentre+yMouse+8);
                    g2.draw(lineX1);
                    g2.draw(lineX2);
                    g2.drawString("x",(int)((-a+i*(0.5*numNums))*koef*sizeKoef+xCentre+xMouse), (int)(yCentre+yMouse+20));
                    g2.draw(lineY1);
                    g2.draw(lineY2);
                    g2.drawString("y",(int)(xCentre+xMouse-15),(int)((-a+i*(0.5*numNums))*koef*sizeKoef*-1+yCentre+yMouse+5));
                    break;
                }
                Line2D line = new Line2D.Double((-a+i*0.5*numNums)*koef*sizeKoef+xCentre+xMouse,yCentre-5+yMouse,(-a+i*0.5*numNums)*koef*sizeKoef+xCentre+xMouse,yCentre+5+yMouse);
                Line2D line2 = new Line2D.Double(xCentre-5+xMouse,(-a+i*0.5*numNums)*koef*sizeKoef+yCentre+yMouse,xCentre+5+xMouse,(-a+i*0.5*numNums)*koef*sizeKoef+yCentre+yMouse);
                g2.drawString(BigDecimal.valueOf(i).multiply(BigDecimal.valueOf(0.5*numNums)).add(BigDecimal.valueOf(-a)).toString(), (int)((-a+i*0.5*numNums)*koef*sizeKoef+xCentre+xMouse), (int)(yCentre-5+yMouse));
                g2.drawString(BigDecimal.valueOf(i).multiply(BigDecimal.valueOf(0.5*numNums)).add(BigDecimal.valueOf(-a)).multiply(BigDecimal.valueOf(-1)).toString(),  (int)(xCentre+5+xMouse),(int)((-a+i*0.5*numNums)*koef*sizeKoef+yCentre+yMouse));
                g2.draw(line2);
                g2.draw(line);
            }
        }
        g2.drawString("0", (int)(5+xCentre+xMouse),(int)(-5+yCentre+yMouse));
        g2.setColor(Color.BLACK);

        try{
            double r = Math.sin(Double.parseDouble(textField1.getText())*0.99);
            double xPrev = r*Math.cos(Double.parseDouble(textField1.getText()))*koef*sizeKoef;
            double yPrev = r*Math.sin(Double.parseDouble(textField1.getText()))*koef*sizeKoef;
            try{
                step = Double.parseDouble(textField3.getText());
                if (step<=0){
                    step=1;
                    textField3.setText("1");
                }
            }catch (Exception e){
                e.printStackTrace();
            }

            for (BigDecimal i = BigDecimal.valueOf(Double.parseDouble(textField1.getText())+step); i.compareTo(BigDecimal.valueOf(Double.parseDouble(textField2.getText())+1.0))==-1; i=i.add(BigDecimal.valueOf(step))){
                r = Math.sin(i.multiply(BigDecimal.valueOf(0.99)).doubleValue());
                double x = r*Math.cos(i.doubleValue())*koef*sizeKoef;
                double y = r*Math.sin(i.doubleValue())*koef*sizeKoef;
                Line2D line2D = new Line2D.Double(xPrev+xCentre+xMouse,yPrev+yCentre+yMouse, x+xCentre+xMouse,y+yCentre+yMouse);
                g2.draw(line2D);
                xPrev = x;
                yPrev = y;
            }
        }catch (Exception ew){
            ew.printStackTrace();
        }
        allPanel.repaint();
        sizePanel.repaint();
    }

    public static void main(String[] args) {
        new Main("Function R(a) = sin(0.99a)");
    }
}
