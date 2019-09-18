import acm.graphics.GOval;
import acm.program.GraphicsProgram;

import java.awt.*;
//Ihor Yankin

//Ви маєте написати програму, що малює зображення "мішень для лучника". Приклад:
//
//
//
//Мішень має розташовуватися по центру вікна. Мають бути використані константи:
//
//
//
//ширира світу;
//висота світу;
//кількість кругів.
//
public class Target extends GraphicsProgram {
    public static final int WIDTH = 500;
    public static final int HEIGHT = 500;
    public static final int CIRCLES_NUMBER = 15;
    public static final int RADIUS_INCREASE = 5;

    public void run(){
        this.setSize(WIDTH,HEIGHT);
        int centreW = WIDTH/2;
        int centreH = HEIGHT/2;
        for(int i =CIRCLES_NUMBER;i>0;i--){
            if (i%2==1)
             addCircle(centreW,centreH,RADIUS_INCREASE*i,true);
            else
                addCircle(centreW,centreH,RADIUS_INCREASE*i,false);
        }
    }
//add new circle that starts at x,y
    private void addCircle(int x, int y, int radius, boolean color){
        int startW = x-radius;
        int startH = y-radius;
        GOval oval = new GOval(startW,startH,2*radius, 2*radius);
            oval.setFilled(true);
            if (color==true)
            oval.setFillColor(Color.RED);
            else oval.setFillColor(Color.WHITE);
        add(oval);
    }
}
