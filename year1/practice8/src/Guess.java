import acm.graphics.GLabel;
import acm.program.GraphicsProgram;

import java.awt.event.HierarchyBoundsListener;
import java.awt.event.HierarchyEvent;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

public class Guess extends GraphicsProgram {

    private int randNum = (int) ((Math.random()*100)+1);

    public void run(){
     this.setSize(700,600);
        ListenChanges listenChanges = new ListenChanges();
        ListenKeybord listenKeybord = new ListenKeybord();
        this.getGCanvas().addKeyListener(listenKeybord);
        this.getGCanvas().addHierarchyBoundsListener(listenChanges);
    GLabel label = ACMMethods.writeText("Try to guess the number",ACMMethods.getCentreX(this.getWidth()),ACMMethods.getCentreY(this.getHeight()),26);
    add(label);
    }

    private class ListenChanges implements HierarchyBoundsListener {

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

    private class ListenKeybord implements KeyListener {
        String num1 = "";
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
            if (e.getKeyCode()==10){
                if (Integer.parseInt(num1)<=100 && Integer.parseInt(num1)!=0 ){
                    if (randNum>Integer.parseInt(num1)) {
                        clearAll();
                        GLabel label = ACMMethods.writeText("More",ACMMethods.getCentreX(updateW()),ACMMethods.getCentreY(updateH()),26);
                        add(label);
                        num1="";
                    }
                    else if (randNum<Integer.parseInt(num1)){
                        clearAll();
                        GLabel label = ACMMethods.writeText("Less",ACMMethods.getCentreX(updateW()),ACMMethods.getCentreY(updateH()),26);
                        add(label);
                        num1 = "";
                    } else{
                        clearAll();
                        GLabel label = ACMMethods.writeText("Right",ACMMethods.getCentreX(updateW()),ACMMethods.getCentreY(updateH()),26);
                        add(label);
                        num1="" ;
                        randNum = (int) ((Math.random()*100)+1);
                    }

                }else{
                    clearAll();
                    GLabel label = ACMMethods.writeText("Input number must be less than 100 and more than 0",ACMMethods.getCentreX(updateW()),ACMMethods.getCentreY(updateH()),26);
                    add(label);
                    num1="" ;
                }

            }


        }
        @Override
        public void keyReleased(KeyEvent e) {

        }
    }
}
