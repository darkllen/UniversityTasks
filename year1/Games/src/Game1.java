import acm.graphics.*;
import acm.program.GraphicsProgram;

import java.awt.*;
import java.awt.event.MouseEvent;

public class Game1 extends GraphicsProgram {
    GObject current;
    boolean playWithComputer = true;
    int firstTurn = (int) (Math.random()*2);
    int gameMode = 0;

    public void run(){
        this.setSize(700,700);
        pause(10);
        chooseMode();
        addMouseListeners();
    }

    /**
     * make choice between game modes
     */
    public void chooseMode(){
        add(ACMMethods.writeText("Player VS Player",this.getWidth()/2, (int) (getHeight()/2.3),25));
        add(ACMMethods.writeText("Player VS Computer",getWidth()/2, (int) (getHeight()/1.7),25));

    }

    /**
     * create game place
     */
    public void createPlace(){

        int width = this.getWidth()/3-1;
        int height  = this.getHeight()/3-1;
        for (int i=0;i<3;i++){
            for (int j = 0; j < 3; j++) {
                GRect rect = new GRect(j*width, i*height,width,height);
                add(rect);
            }
        }
    }
    int count = 0;

    /**
     *
     * @param event mouse click action
     *
     */

    public void mouseClicked(MouseEvent event){
        try{
            GLabel label = (GLabel) getElementAt(event.getX(),event.getY());
            if (label.getLabel().matches("Player VS Player")){
                removeAll();
                playWithComputer=false;
                createPlace();
            }else if (label.getLabel().matches("Player VS Computer")){
                removeAll();
                playWithComputer=true;
                createPlace();
                computerTurn();
                count++;

            }else{
                removeAll();
                count=0;
                chooseMode();
            }
        }catch (Exception e){

            try{
                GRect rect = (GRect) getElementAt(event.getX(), event.getY());
                if (playWithComputer==false) {
                    if (checkWinner() != 0 || count>8) return;

                    current = getElementAt(event.getX(), event.getY());
                    if (count % 2 == firstTurn) {
                        drawX(current);
                    } else {
                        draw0(current);
                    }
                    count++;
                    if (checkWinner() != 0) {
                        gameResult(checkWinner());
                        System.out.println(checkWinner());
                        return;
                    }
                    if (count > 8) {
                        add( ACMMethods.writeText("Draw",this.getWidth()/2, this.getHeight()/2,28));
                        return;
                    }
                }else {
                    if (checkWinner() != 0||count>8) return;

                    current = getElementAt(event.getX(), event.getY());
                    drawX(current);
                    if (checkWinner() != 0) {
                        gameResult(checkWinner());
                        System.out.println(checkWinner());
                        return;
                    }
                    computerTurn();

                    count++;
                    count++;
                    if (checkWinner() != 0) {
                        gameResult(checkWinner());
                        System.out.println(checkWinner());
                        return;
                    }
                    if (count > 8) {
                        add( ACMMethods.writeText("Draw",this.getWidth()/2, this.getHeight()/2,28));
                        return;
                    }
                }
            }catch (Exception a){

            }

        }
        System.out.println(getElementAt(event.getX(),event.getY()));




    }

    /**
     *
     * @param a int for winner define (1 - X, else - O)
     */
    public void gameResult(int a){
        if (a==1){
           add( ACMMethods.writeText("Player X wins",this.getWidth()/2, this.getHeight()/2,25));
        }else{
            add(ACMMethods.writeText("Player O wins",this.getWidth()/2, this.getHeight()/2,25));
        }

    }

    /**
     * make random turn for O
     */
    public void computerTurn(){
        int x = (int) (Math.random()*this.getWidth());
        int y = (int) (Math.random()*this.getHeight());
        int count =0;
        while (true && count!=300){
            count++;
            try{
               X rect = (X) getElementAt(x,y);
                x = (int) (Math.random()*this.getWidth());
                y = (int) (Math.random()*this.getHeight());
                System.out.println("true1");
            }catch (Exception e){
                try{
                    O rect = (O) getElementAt(x,y);
                    x = (int) (Math.random()*this.getWidth());
                    y = (int) (Math.random()*this.getHeight());
                    System.out.println("true");
                }catch (Exception a){
                    draw0( getElementAt(x,y));
                    return;
                }

            }
        }
    }


    /**
     *
     * @param object draw X in object
     */
    public void drawX(GObject object){
        GRect rect = new GRect(0,0,object.getWidth(),object.getHeight());
        GLine line = new GLine(0,0, object.getWidth(), object.getHeight());
        GLine line2 = new GLine(0,object.getHeight(),object.getWidth(),0);
        X x =new X();
        x.add(rect);
        x.add(line);
        x.add(line2);
        x.setLocation(object.getX(),object.getY());

        add(x);
    }

    /**
     *
     * @param object draw O in object
     */
    public void draw0(GObject object){
        GOval oval = new GOval(0, 0,object.getWidth(), object.getHeight());
        O o = new O();
        GRect rect = new GRect(0,0,object.getWidth(),object.getHeight());

        o.add(oval);
        o.add(rect);
        o.setLocation(object.getX(),object.getY());
        add(o);
    }

    /**
     *
     * @return 1 for X winner, 2 for O winner, else return O
     */
    public int checkWinner(){
        int width = this.getWidth()/3-1;
        int height  = this.getHeight()/3-1;
        int j = 0;
                  try{
                          O x = (O) getElementAt(width*j+width/2,height*j+height/2);
                          try {
                              O x1 = (O) getElementAt(width*(j+1)+width/2,height*j+height/2);
                              O x2 = (O) getElementAt(width*(j+2)+width/2,height*j+height/2);
                              x.setColor(Color.RED);
                              x1.setColor(Color.RED);
                              x2.setColor(Color.RED);
                              return 2;
                          }catch (Exception e){

                          }
                          try {
                              O x1 = (O) getElementAt(width*(j)+width/2,height*(j+1)+height/2);
                              O x2 = (O) getElementAt(width*(j)+width/2,height*(j+2)+height/2);
                              x.setColor(Color.RED);
                              x1.setColor(Color.RED);
                              x2.setColor(Color.RED);
                              return 2;

                          }catch (Exception e){
                          }
                      try {
                          O x1 = (O) getElementAt(width*(j+1)+width/2,height*(j+1)+height/2);
                          O x2 = (O) getElementAt(width*(j+2)+width/2,height*(j+2)+height/2);
                          x.setColor(Color.RED);
                          x1.setColor(Color.RED);
                          x2.setColor(Color.RED);
                          return 2;
                      }catch (Exception e){
                      }

                  }catch (Exception e){
                  }
                  try{
                      O x = (O) getElementAt(width*(j+1)+width/2,height*(j+1)+height/2);
                      try {
                          O x1 = (O) getElementAt(width*(j+1)+width/2,height*j+height/2);
                          O x2 = (O) getElementAt(width*(j+1)+width/2,height*(j+2)+height/2);
                          x.setColor(Color.RED);
                          x1.setColor(Color.RED);
                          x2.setColor(Color.RED);
                          return 2;
                      }catch (Exception e){

                      }
                      try {
                          O x1 = (O) getElementAt(width*(j)+width/2,height*(j+1)+height/2);
                          O x2 = (O) getElementAt(width*(j+2)+width/2,height*(j+1)+height/2);
                          x.setColor(Color.RED);
                          x1.setColor(Color.RED);
                          x2.setColor(Color.RED);
                          return 2;
                      }catch (Exception e){
                      }
                      try {
                          O x1 = (O) getElementAt(width*(j+2)+width/2,height*(j)+height/2);
                          O x2 = (O) getElementAt(width*(j)+width/2,height*(j+2)+height/2);
                          x.setColor(Color.RED);
                          x1.setColor(Color.RED);
                          x2.setColor(Color.RED);
                          return 2;
                      }catch (Exception e){
                      }
                  }catch (Exception e){

                  }
                  try{
                      O x = (O) getElementAt(width*(j+2)+width/2,height*(j+2)+height/2);
                      try {
                          O x1 = (O) getElementAt(width*(j+2)+width/2,height*(j)+height/2);
                          O x2 = (O) getElementAt(width*(j+2)+width/2,height*(j+1)+height/2);
                          x.setColor(Color.RED);
                          x1.setColor(Color.RED);
                          x2.setColor(Color.RED);
                          return 2;
                      }catch (Exception e){
                      }
                      try {
                          O x1 = (O) getElementAt(width*(j)+width/2,height*(j+2)+height/2);
                          O x2 = (O) getElementAt(width*(j+1)+width/2,height*(j+2)+height/2);
                          x.setColor(Color.RED);
                          x1.setColor(Color.RED);
                          x2.setColor(Color.RED);
                          return 2;
                      }catch (Exception e){
                      }
                  }catch (Exception e){

                  }
                    try{
                        X x = (X) getElementAt(width*j+width/2,height*j+height/2);
                        try {
                            X x1 = (X) getElementAt(width*(j+1)+width/2,height*j+height/2);
                            X x2 = (X) getElementAt(width*(j+2)+width/2,height*j+height/2);
                            x.setColor(Color.RED);
                            x1.setColor(Color.RED);
                            x2.setColor(Color.RED);
                            return 1;
                        }catch (Exception e){

                        }
                        try {
                            X x1 = (X) getElementAt(width*(j)+width/2,height*(j+1)+height/2);
                            X x2 = (X) getElementAt(width*(j)+width/2,height*(j+2)+height/2);
                            x.setColor(Color.RED);
                            x1.setColor(Color.RED);
                            x2.setColor(Color.RED);
                            return 1;

                        }catch (Exception e){
                        }
                        try {
                            X x1 = (X) getElementAt(width*(j+1)+width/2,height*(j+1)+height/2);
                            X x2 = (X) getElementAt(width*(j+2)+width/2,height*(j+2)+height/2);
                            x.setColor(Color.RED);
                            x1.setColor(Color.RED);
                            x2.setColor(Color.RED);
                            return 1;
                        }catch (Exception e){
                        }

                    }catch (Exception e){
                    }
                    try{
                        X x = (X) getElementAt(width*(j+1)+width/2,height*(j+1)+height/2);
                        try {
                            X x1 = (X) getElementAt(width*(j+1)+width/2,height*j+height/2);
                            X x2 = (X) getElementAt(width*(j+1)+width/2,height*(j+2)+height/2);
                            x.setColor(Color.RED);
                            x1.setColor(Color.RED);
                            x2.setColor(Color.RED);
                            return 1;
                        }catch (Exception e){

                        }
                        try {
                            X x1 = (X) getElementAt(width*(j)+width/2,height*(j+1)+height/2);
                            X x2 = (X) getElementAt(width*(j+2)+width/2,height*(j+1)+height/2);
                            x.setColor(Color.RED);
                            x1.setColor(Color.RED);
                            x2.setColor(Color.RED);
                            return 1;
                        }catch (Exception e){
                        }
                        try {
                            X x1 = (X) getElementAt(width*(j+2)+width/2,height*(j)+height/2);
                            X x2 = (X) getElementAt(width*(j)+width/2,height*(j+2)+height/2);
                            x.setColor(Color.RED);
                            x1.setColor(Color.RED);
                            x2.setColor(Color.RED);
                            return 1;
                        }catch (Exception e){
                        }
                    }catch (Exception e){

                    }
                    try{
                        X x = (X) getElementAt(width*(j+2)+width/2,height*(j+2)+height/2);
                        try {
                            X x1 = (X) getElementAt(width*(j+2)+width/2,height*(j)+height/2);
                            X x2 = (X) getElementAt(width*(j+2)+width/2,height*(j+1)+height/2);
                            x.setColor(Color.RED);
                            x1.setColor(Color.RED);
                            x2.setColor(Color.RED);
                            return 1;
                        }catch (Exception e){
                        }
                        try {
                            X x1 = (X) getElementAt(width*(j)+width/2,height*(j+2)+height/2);
                            X x2 = (X) getElementAt(width*(j+1)+width/2,height*(j+2)+height/2);
                            x.setColor(Color.RED);
                            x1.setColor(Color.RED);
                            x2.setColor(Color.RED);
                            return 1;
                        }catch (Exception e){
                        }
                    }catch (Exception e){

                    }
            return 0;

    }
}
