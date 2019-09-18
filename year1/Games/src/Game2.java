import acm.graphics.*;
import acm.program.GraphicsProgram;

import java.awt.*;
import java.awt.event.MouseEvent;

public class Game2 extends GraphicsProgram {

    GCompound plain = new GCompound();
    int num = 10;
    GImage image = new GImage("pict.gif");
    x
    public void run(){
       image.scale(2,2);
        this.setSize((int)image.getWidth(),(int)image.getHeight());
        add(image);
        pause(200);

        createPlain();
        aims();
        addMouseListeners();
        while (true) {
            while (plain.getX() + plain.getWidth() < this.getWidth()) {
                move(1);
                checkForCollision();
                pause(10);
            }
            while (plain.getX() > 0) {
                move(-1);
                checkForCollision();
                pause(10);
            }
        }

    }

    /**
     * check for collision and destroy objects
     */
    public void checkForCollision(){
        int u = num;
        while (true){
            u++;
            try{

                if (this.getElementAt(this.getElement(u).getX(),this.getElement(u).getY()+1+this.getElement(u).getHeight()) instanceof GRect||this.getElementAt(this.getElement(u).getX()+this.getElement(u).getWidth(),this.getElement(u).getY()+1+this.getElement(u).getHeight()) instanceof GRect||this.getElementAt(this.getElement(u).getX()+this.getElement(u).getWidth()/2,this.getElement(u).getY()+1+this.getElement(u).getHeight())instanceof GRect) {
               System.out.println("true");
                  if (this.getElementAt(this.getElement(u).getX(),this.getElement(u).getY()+1+this.getElement(u).getHeight()) instanceof GRect){
                      remove(this.getElementAt(this.getElement(u).getX(),this.getElement(u).getY()+1+this.getElement(u).getHeight()));
                      System.out.println(1);
                      num--;
                  }else if(this.getElementAt(this.getElement(u).getX()+this.getElement(u).getWidth(),this.getElement(u).getY()+1+this.getElement(u).getHeight()) instanceof GRect){
                        remove(this.getElementAt(this.getElement(u).getX()+this.getElement(u).getWidth(),this.getElement(u).getY()+1+this.getElement(u).getHeight()));
                        System.out.println(2);
                        num--;
                  }/*else if (this.getElementAt(this.getElement(u).getX()+this.getElement(u).getWidth()/2,this.getElement(u).getY()+1+this.getElement(u).getHeight()) instanceof GRect){
                        remove(this.getElementAt(this.getElement(u).getX()+this.getElement(u).getWidth()/2,this.getElement(u).getY()+1+this.getElement(u).getHeight()));
                        System.out.println(3);
                        num--;
                  }*/

                    remove(this.getElement(u-1));
                    u--;
                    break;
                }

            }catch (Exception e){
                return;
            }


        }
    }

    /**
     *
     * @param event mouse clicked event
     */
    public void mouseClicked(MouseEvent event){
        createBullet();
    }

    /**
     * Bullet creation under plain
     */
    public void createBullet(){
        BUllet bUllet = new BUllet(this.getElement(1).getX()+this.getElement(1).getWidth()/2,this.getElement(1).getY()+this.getElement(1).getHeight()-50,10,10);
        add(bUllet);
    }

    /**
     *
     * @return first wing of the plain
     */
    private GPolygon createTriangle1(){
        GPolygon polygon= new GPolygon();
        polygon.addVertex(75,10);
        polygon.addVertex(160,-30);
        polygon.addVertex(155,20);
        polygon.setFilled(true);
        return polygon;
    }

    /**
     *
     * @return second wing of the plain
     */
    private GPolygon createTriangle2(){
        GPolygon polygon= new GPolygon();
        polygon.addVertex(75,+20);
        polygon.addVertex(55,+90);
        polygon.addVertex(145,+25);
        polygon.setFilled(true);
        return polygon;
    }

    /**
     * plain creation
     */
    private void createPlain() {
        GOval oval = new GOval(0,0, 200,60);
        oval.setFilled(true);
        oval.setColor(Color.GREEN);
        plain.add(oval);

        GPolygon polygon1 = createTriangle1();
        GPolygon polygon2 = createTriangle2();

        plain.add(polygon1);
        plain.add(polygon2);
        oval.sendToFront();
        oval.sendForward();
        polygon2.sendForward();
        plain.setLocation(0,100);

        add(plain);
    }

    /**
     * create num aims
     */
    private void aims(){
        for (int i = 1;i<=num;i++){
            int w = (int) (Math.random()*90+10);
            int h = (int) (Math.random()*90+10);
            Aim rect = new Aim(0,this.getHeight()-h-2,w,h);
            add(rect);
        }
    }
    /**
     *
     * @param i plane speed
     *          move all objects
     */
    private void move(int i){

        plain.move(i,0);
        int u = 0;
        for (int j =0; j<num+2;j++,u++){
            if (this.getElement(j) instanceof  Aim){
                this.getElement(j).move(((Aim) this.getElement(j)).speed,0);
                if (this.getElement(j).getX()+this.getElement(j).getWidth()>this.getWidth())((Aim) this.getElement(j)).speed*=-1;
                if (this.getElement(j).getX()<0)((Aim) this.getElement(j)).speed*=-1;
            }

        }
        u--;

        while (true) {
            u++;
            try {
                if (this.getElement(u) instanceof BUllet) {
                        this.getElement(u).move(((BUllet) this.getElement(u)).speedX,((BUllet) this.getElement(u)).speedY);
                }
            }catch (Exception e){
                return;
            }
        }

    }
}