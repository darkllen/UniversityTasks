import acm.graphics.GCompound;
import acm.graphics.GLabel;
import acm.graphics.GOval;
import acm.graphics.GRect;
import acm.program.GraphicsProgram;
import acm.util.RandomGenerator;

import java.awt.event.HierarchyBoundsListener;
import java.awt.event.HierarchyEvent;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.util.ArrayList;

public class Game extends GraphicsProgram {
    String num1 = "";
    int num = -1;
    int num2 = -1;
    int sum1= 0;
    int sum2 = 0;

    private static final int N = 5;
    private static final int HEIGTH = 500;
    private static final int WIDHT = 1000;
    public void run() {
        this.setSize(WIDHT,HEIGTH);

        for(int i=0;i<N;i++) {
            int sizeX = (int) (Math.random()*this.getWidth());
            int sizeY = (int) (Math.random()*this.getHeight());
            int ableX = this.getWidth()-sizeX;
            int ableY = this.getHeight()-sizeY;
            int x = (int) (Math.random()*ableX);
            int y = (int) (Math.random()*ableY);
            GRect rect = new GRect(x, y, sizeX, sizeY);

            add(rect);
        }
    }

    private class ListenChanges implements HierarchyBoundsListener{

        @Override
        public void ancestorMoved(HierarchyEvent e) {

        }

        @Override
        public void ancestorResized(HierarchyEvent e) {

        }
    }
    private int updateW(){
        return this.getWidth();
    }
    private int updateH(){
        return this.getHeight();
    }
    private void clearAll(){
        this.removeAll();
    }

    private class Dice {
        private int num = 6;
        public GRect gRect = new GRect(0,0,0,0);
        public GCompound compound = new GCompound();
        public Dice(int num1, int num2){
            gRect.setSize(num1,num1);
            int w = (int) (gRect.getWidth()-gRect.getWidth()/100*10);
         compound.add(gRect);
           GLabel label = ACMMethods.writeText(String.valueOf(num2),ACMMethods.getCentreX((int) gRect.getWidth()),ACMMethods.getCentreX((int) gRect.getWidth()),20);
           compound.add(label);

        }

    }
    private class ListenKeybord implements KeyListener{

        @Override
        public void keyTyped(KeyEvent e) {

        }

        @Override
        public void keyPressed(KeyEvent e) {
            println(e.getKeyCode());
            if (e.getKeyCode()>47&&e.getKeyCode()<58){
                clearAll();
                num1+=String.valueOf(e.getKeyChar());
                GLabel label = ACMMethods.writeText(num1,ACMMethods.getCentreX(updateW()),ACMMethods.getCentreY(updateH()),26);
                add(label);
            }
            if (e.getKeyCode()==90){
                returnToStart();
            }
            if (e.getKeyCode()==10){
                if (num==-1) {
                    num = Integer.parseInt(num1);
                    num1 = "";
                    clearAll();
                    GLabel label = ACMMethods.writeText("Введите количество граней",ACMMethods.getCentreX(updateW()),ACMMethods.getCentreY(updateH()),26);
                    add(label);
                }
                else {
                    num2 = Integer.parseInt(num1);
                    clearAll();
                    GLabel label = ACMMethods.writeText("Press space for next throw, press x for ending, z for new game.", ACMMethods.getCentreX(updateW()), ACMMethods.getCentreY(updateH())-updateH()/100*30, 26);
                    add(label);
                    GLabel label1 = ACMMethods.writeText("Player 1",ACMMethods.getCentreX(updateW()/2),ACMMethods.getCentreY(updateH()),26);
                    add(label1);
                    GLabel label2 = ACMMethods.writeText("Player 2",ACMMethods.getCentreX(updateW())+ACMMethods.getCentreX(updateW()/2),ACMMethods.getCentreY(updateH()),26);
                    add(label2);
                    GLabel label3 = ACMMethods.writeText("0",ACMMethods.getCentreX(updateW()/2),ACMMethods.getCentreY(updateH())+updateH()/5,26);
                    add(label3);
                    GLabel label4 = ACMMethods.writeText("0",ACMMethods.getCentreX(updateW())+ACMMethods.getCentreX(updateW()/2),ACMMethods.getCentreY(updateH())+updateH()/5,26);
                    add(label4);
                }

            }

            RandomGenerator generator = RandomGenerator.getInstance();
            if (e.getKeyCode()==32) {
                ArrayList arrayList = new ArrayList(); ArrayList arrayList2 = new ArrayList();
                if (num !=0 && num2!=0){
                    for (int i = 0; i < num; i++) {
                      //  sum1 += generator.nextInt(1, num2);
                 int n = (int) (Math.random()*num2);
                        sum1+= n;
                        sum1++;
                       clearAll();
                        if (num>7){
                            Dice dice = new Dice((updateW()/2-40)/num,n+1);
                            dice.compound.setLocation(i*(updateW()/2-40)/num,ACMMethods.getCentreY(updateH())+updateH()/100*30);
                            arrayList.add(dice.compound);
                        } else {
                            Dice dice = new Dice(50,n+1);
                            dice.compound.setLocation(i*52,ACMMethods.getCentreY(updateH())+updateH()/100*30);
                            arrayList.add(dice.compound);
                        }

                    }
                    for (int i = 0; i < num; i++) {
                      //  sum2 += generator.nextInt(1, num2);
                        int n = (int) (Math.random()*num2);
                        sum2+= n;
                        sum2++;
                        if (num>7){
                            Dice dice = new Dice((updateW()/2-40)/num,n+1);
                            dice.compound.setLocation(updateW()/2+i*(updateW()/2-40)/num,ACMMethods.getCentreY(updateH())+updateH()/100*30);
                            arrayList2.add(dice.compound);
                        } else {
                            Dice dice = new Dice(50,n+1);
                            dice.compound.setLocation(updateW()/2+i*52,ACMMethods.getCentreY(updateH())+updateH()/100*30);
                            arrayList2.add(dice.compound);
                        }

                    }
                    drawSum(sum1, sum2, arrayList, arrayList2);
                }
            }
            if (e.getKeyCode()==88){
                clearAll();
                if (sum1>sum2){
                    GLabel label = ACMMethods.writeText("Player 1 is the winner",ACMMethods.getCentreX(updateW()),ACMMethods.getCentreY(updateH()),26);
                    add(label);
                }else if (sum2>sum1){
                    GLabel label = ACMMethods.writeText("Player 2 is the winner",ACMMethods.getCentreX(updateW()),ACMMethods.getCentreY(updateH()),26);
                    add(label);
                } else {
                    GLabel label = ACMMethods.writeText("Draw",ACMMethods.getCentreX(updateW()),ACMMethods.getCentreY(updateH()),26);
                    add(label);
                }
            }
        }

        @Override
        public void keyReleased(KeyEvent e) {

        }
    }
    private void drawSum(int sum1, int sum2, ArrayList<GCompound> arrayList, ArrayList<GCompound> arrayList2){

        GLabel label = ACMMethods.writeText("Press space for next throw, press x for ending.", ACMMethods.getCentreX(updateW()), ACMMethods.getCentreY(updateH())-updateH()/100*30, 26);
        add(label);
        GLabel label1 = ACMMethods.writeText("Player 1",ACMMethods.getCentreX(updateW()/2),ACMMethods.getCentreY(updateH()),26);
        add(label1);
        GLabel label2 = ACMMethods.writeText("Player 2",ACMMethods.getCentreX(updateW())+ACMMethods.getCentreX(updateW()/2),ACMMethods.getCentreY(updateH()),26);
        add(label2);
        GLabel label3 = ACMMethods.writeText(String.valueOf(sum1),ACMMethods.getCentreX(updateW()/2),ACMMethods.getCentreY(updateH())+updateH()/5,26);
        add(label3);
        GLabel label4 = ACMMethods.writeText(String.valueOf(sum2),ACMMethods.getCentreX(updateW())+ACMMethods.getCentreX(updateW()/2),ACMMethods.getCentreY(updateH())+updateH()/5,26);
        add(label4);
        for (int i = 0; i<arrayList.size();i++){
            add(arrayList.get(i));
        }
        for (int i = 0; i<arrayList2.size();i++){
            add(arrayList2.get(i));
        }
    }
    private void returnToStart(){
        num1 = "";
        num = -1;
         num2 = -1;
         sum1= 0;
         sum2 = 0;
         clearAll();
        GLabel label = ACMMethods.writeText("Введите число кубиков",ACMMethods.getCentreX(this.getWidth()),ACMMethods.getCentreY(this.getHeight()),26);
        add(label);
    }

}
