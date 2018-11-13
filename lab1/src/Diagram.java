import acm.graphics.GLabel;
import acm.graphics.GLine;
import acm.graphics.GRect;
import acm.program.GraphicsProgram;

//Ihor Yankin
//paint a diagram

public class Diagram extends GraphicsProgram {

    public static final int WIDTH = 1200;
    public static final int HEIGHT = 500;
    public static final int FONT_SIZE = 10;
    public static final int HEIGHT_DISTANCE = 50;
    public static final int WIDTH_DISTANCE = 20;
    public static final String TEXT = "Program";
    public static final String TEXT1 = "GraphicsProgram";
    public static final String TEXT2 = "ConsoleProgram";
    public static final String TEXT3 = "DialogProgram";
    public void run(){
        this.setSize(WIDTH,HEIGHT);
        int centreW = WIDTH/2;
        int centreH = HEIGHT/2;
        int height = 2*FONT_SIZE;

        int maxLength = findMaxLength(TEXT, TEXT1, TEXT2, TEXT3);

        double s;
        if (maxLength%2==0)
            s = maxLength / 2+1;
        else
            s =maxLength / 2+2;



        double w = ((WIDTH-WIDTH_DISTANCE*2)-WIDTH*0.2)/3;

        int f = (int) (w/s);

        add4RectsWithText(centreW,centreH,f, TEXT, TEXT1, TEXT2, TEXT3);
    }

    //add 4 Rectangles within text and lines from main to other
    private void add4RectsWithText(int x, int y, int fontSize, String text1, String text2, String text3, String text4){
        int maxLength = findMaxLength(text1,text2,text3,text4);


        double s;
        if (maxLength%2==0)
            s = maxLength / 2+1;
        else
            s =maxLength / 2+2;


        GRect rect = new GRect(x-fontSize*s/2, y-fontSize-HEIGHT_DISTANCE/2,fontSize*s,fontSize);
        GLabel label = new GLabel(text1, 0,0);
        label.setFont("SeinsSherif-"+fontSize);
        label.setLocation(rect.getX()+(rect.getWidth()-label.getWidth())/2,rect.getY()+fontSize-0.125*fontSize);

        GRect rect1 = new GRect(x-fontSize*s/2, y-fontSize - HEIGHT_DISTANCE/2+fontSize+HEIGHT_DISTANCE,fontSize*s,fontSize);
        GLabel label1 = new GLabel(text2, 0, 0 );
        label1.setFont("SeinsSherif-"+fontSize);
        label1.setLocation(rect1.getX()+(rect1.getWidth()-label1.getWidth())/2,rect1.getY()+fontSize-0.125*fontSize);

        GRect rect2 = new GRect(x-fontSize*s/2-rect.getWidth()-WIDTH_DISTANCE, y-fontSize - HEIGHT_DISTANCE/2+fontSize+HEIGHT_DISTANCE,fontSize*s,fontSize);
        GLabel label2 = new GLabel(text3, 0, 0 );
        label2.setFont("SeinsSherif-"+fontSize);
        label2.setLocation(rect2.getX()+(rect2.getWidth()-label2.getWidth())/2,rect2.getY()+fontSize-0.125*fontSize);

        GRect rect3 = new GRect(x-fontSize*s/2+rect.getWidth()+WIDTH_DISTANCE, y-fontSize - HEIGHT_DISTANCE/2+fontSize+HEIGHT_DISTANCE,fontSize*s,fontSize);
        GLabel label3 = new GLabel(text3, 0, 0 );
        label3.setFont("SeinsSherif-"+fontSize);
        label3.setLocation(rect3.getX()+(rect3.getWidth()-label3.getWidth())/2,rect3.getY()+fontSize-0.125*fontSize);

        add(rect1);
        add(label1);

        add(rect);
        add(label);

        add(rect2);
        add(label2);

        add(rect3);
        add(label3);

        GLine line1 = new GLine(rect.getX()+rect.getWidth()/2,rect.getY()+rect.getHeight(),rect1.getX()+rect1.getWidth()/2,rect1.getY());
        GLine line2 = new GLine(rect.getX()+rect.getWidth()/2,rect.getY()+rect.getHeight(),rect2.getX()+rect2.getWidth()/2,rect2.getY());
        GLine line3 = new GLine(rect.getX()+rect.getWidth()/2,rect.getY()+rect.getHeight(),rect3.getX()+rect3.getWidth()/2,rect3.getY());

        add(line1);
        add(line2);
        add(line3);
            }

            //findMax
     private int findMaxLength(String text1, String text2, String text3, String text4){
        int a = Math.max(text1.length(), text2.length());
        int b = Math.max(text3.length(), text4.length());
        return Math.max(a, b);

     }
}
