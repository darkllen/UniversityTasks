import acm.graphics.*;
import acm.program.GraphicsProgram;
import java.awt.event.*;
import java.util.ArrayDeque;

public class Towers extends GraphicsProgram {

    private int numberOfBlocksInt = 0;
    private ArrayDeque<GRect>[] arrayDeques = new ArrayDeque[3];
    private double placeForTowersHeight =0;
    private static int count= 0;
    private int count2=0;
    private ArrayDeque<GRect>[][] solut = new ArrayDeque[31000][3];

    public void run(){
        this.setSize(500,700);
        arrayDeques[0] = new ArrayDeque<GRect>();
        arrayDeques[1] = new ArrayDeque<GRect>();
        arrayDeques[2] = new ArrayDeque<GRect>();
        this.getGCanvas().addHierarchyBoundsListener(newCanvasListener());
        this.getGCanvas().addKeyListener(newKeyListener());

    }

    private  ArrayDeque<GRect>[][] solution(int h, int a, int b, int c,int i,    ArrayDeque<GRect>[][] sol) throws InterruptedException {

            if (h==1){
                moveBlock(a,b);

                sol[count][0]=(arrayDeques[0]).clone();
                sol[count][1]=(arrayDeques[1]).clone();
                sol[count][2]=(arrayDeques[2]).clone();
                count++;

                return sol;
            }
            solution(h-1,a,c,b, i+1, sol);
            moveBlock(a,b);

        sol[count][0]=(arrayDeques[0]).clone();
        sol[count][1]=(arrayDeques[1]).clone();
        sol[count][2]=(arrayDeques[2]).clone();
        count++;
      return solution(h-1,c,b,a, i+1,sol);
    }

    private HierarchyBoundsListener newCanvasListener(){
        HierarchyBoundsListener listener1 = new HierarchyBoundsListener() {
            @Override
            public void ancestorMoved(HierarchyEvent e) {
                createTowers();
                if (arrayDeques[0].size()!=0){

                    createArrayOfBlocks(arrayDeques[0].size(), hUpdate(),wUpdate(),0);
                }
                if (arrayDeques[1].size()!=0){

                    createArrayOfBlocks(arrayDeques[1].size(), hUpdate(),wUpdate(),1);
                }
                if (arrayDeques[2].size()!=0){

                    createArrayOfBlocks(arrayDeques[2].size(), hUpdate(),wUpdate(),2);

                }
                addBlocksToTower();
            }

            @Override
            public void ancestorResized(HierarchyEvent e) {
                createTowers();
                if (arrayDeques[0].size()!=0){

                    createArrayOfBlocks(arrayDeques[0].size(), hUpdate(),wUpdate(),0);
                }
                if (arrayDeques[1].size()!=0){

                    createArrayOfBlocks(arrayDeques[1].size(), hUpdate(),wUpdate(),1);
                }
                if (arrayDeques[2].size()!=0){

                    createArrayOfBlocks(arrayDeques[2].size(), hUpdate(),wUpdate(),2);

                }
                addBlocksToTower();

            }
        };
    return listener1;
    }

    private int hUpdate(){
        return this.getHeight();
    }
    private int wUpdate(){
        return this.getWidth();
    }

    private void moveStep(ArrayDeque<GRect>[][] sol) {
        arrayDeques[0] = sol[count2][0].clone();
        arrayDeques[1] = sol[count2][1].clone();
        arrayDeques[2] = sol[count2][2].clone();
        addBlocksToTower();

        count2++;
    }

    private KeyListener newKeyListener(){

        final int[] count = {0};
        final String[] numberofBlocks = {new String()};

        KeyListener listener = new KeyListener() {
            int j = 0;
            int towN = 0;
            @Override
            public void keyTyped(KeyEvent e) {}
            @Override
            public void keyPressed(KeyEvent e) {
                if (e.getKeyCode()>=48&&e.getKeyCode()<=58 && j==0){
                    GLabel label = new GLabel(Character.toString(e.getKeyChar()),150+count[0]*10,hUpdate()-hUpdate()/100*10);
                    count[0]++;

                    add(label);
                }

                if (e.getKeyCode()>=48&&e.getKeyCode()<=58 && j==1){
                    GLabel label = new GLabel(Character.toString(e.getKeyChar()),140+count[0]*10,hUpdate()-hUpdate()/20);
                    count[0]++;
if (j!=0){
    towN=Integer.parseInt(Character.toString(e.getKeyChar()))  ;
}
                    add(label);
                }

                if (e.getKeyCode()==10){
                        if (j!=0){
                            createTowers();

                            arrayDeques[0].clear();
                            arrayDeques[1].clear();
                            arrayDeques[2].clear();
                            Towers.count=0;
                            count2=0;

                            createArrayOfBlocks(Integer.parseInt(numberofBlocks[0]), hUpdate(),wUpdate(),0);


                            addBlocksToTower();
                            numberOfBlocksInt= Integer.parseInt(numberofBlocks[0]);
                            numberofBlocks[0]="";
                            j=-1;
                        }
                    j++;
                } else if (e.getKeyCode()==32){
                    ArrayDeque<GRect>[][] sol = new ArrayDeque[(int) Math.pow(2,(arrayDeques[0].size()))-1][3];

                    for (int i =0;i<Math.pow(2,(arrayDeques[0].size())-1);i++){
                        sol[i][0] = new ArrayDeque<>();
                        sol[i][1] = new ArrayDeque<>();
                        sol[i][2] = new ArrayDeque<>();
                    }
                   try {

if (towN==2){
    solut   =   solution(arrayDeques[0].size(),0,1,2, 0, sol);

}
                       if (towN==3){
                           solut   =   solution(arrayDeques[0].size(),0,2,1, 0, sol);

                       }

                    } catch (InterruptedException e1) {
                        e1.printStackTrace();
                    }

                                  }
                else if(e.getKeyCode()==88){
                    moveStep(solut);
                }
                else    if (e.getKeyCode()>=48&&e.getKeyCode()<=58){
                    if (j==0)
                    numberofBlocks[0] +=Character.toString(e.getKeyChar());

                }

            }
            @Override
            public void keyReleased(KeyEvent e) {}
        };
        return listener;
    }

    private void createArrayOfBlocks (int blocksNum, int height, int width, int tow){
                arrayDeques[tow] = new ArrayDeque<GRect>();
            int blockHeight = height/100*2;

            int blockWidthStart = width/100*30;
            int blockWidthEnd = width/100*5;

            double step = (double)25/blocksNum;

        for (int i = 0; i<blocksNum; i++){
            GRect rect = new GRect(0, 0, blockWidthEnd+width/100*i*step,  blockHeight);
            arrayDeques[tow].addLast(rect);
            add(rect);
        }
    }
    private void createTowers(){
        this.getGCanvas().removeAll();
        GLabel label = new GLabel("Input Blocks Number",10,hUpdate()-hUpdate()/100*10);
        add(label);
        GLabel label1 = new GLabel("Input Tower Number",10,hUpdate()-hUpdate()/20);
        add(label1);

        int height = this.getHeight();
        int width = this.getWidth();

        int placeForTowersHeight = height-2*(height/10); //80%
        int placeForTowersWidth = width-2*(width/100*24); //60%

        GRect rect1 = new GRect(width-placeForTowersWidth-(width/100*24),height/10,  width/100*2,height/100*80);
        add(rect1);

        GRect rect2 = new GRect(width-(width/100*26),height/10,  width/100*2,height/100*80);
        add(rect2);

        int xStartForLast = width - ((width-placeForTowersWidth-(width/100*24)+ width/100*2));
        int xEndForLast = width - (width-(width/100*26));

        GRect rect3 = new GRect(((xStartForLast+xEndForLast)/2)-width/99,height/10, width/100*2, height/100*80);
        add(rect3);

    }

    private void addBlocksToTower(){
        int width = this.getWidth();
        int placeForTowersWidth = width-2*(width/100*24); //60%
        int centreX = width-placeForTowersWidth-(width/100*23);
        int centreX1 = width-(width/100*25);

        int xEndForLast = width - (width-(width/100*26));
        int xStartForLast = width - ((width-placeForTowersWidth-(width/100*24)+ width/100*2));

        int centreX2 = ((xStartForLast+xEndForLast)/2);

        int height = this.getHeight();
        if (arrayDeques[0].size()>0){
             placeForTowersHeight = height-height/100*20+arrayDeques[0].getFirst().getHeight()*2; //
        }
        int n = height/10+height/100*80;


        int size1 = arrayDeques[0].size();
        int size2 = arrayDeques[1].size();
        int size3 = arrayDeques[2].size();

       for (int i =0;i<size1;i++){
            println(placeForTowersHeight-i*arrayDeques[0].getFirst().getHeight());
           arrayDeques[0].getFirst().setLocation(centreX-(arrayDeques[0].getFirst().getWidth()/2),n-(size1-i)*arrayDeques[0].getFirst().getHeight());
            add(arrayDeques[0].getFirst());
           arrayDeques[0].addLast(  arrayDeques[0].getFirst());
           arrayDeques[0].removeFirst();
        }
        for (int i =0;i<size3;i++){
            println(placeForTowersHeight-i*arrayDeques[2].getFirst().getHeight());
            arrayDeques[2].getFirst().setLocation(centreX1-(arrayDeques[2].getFirst().getWidth()/2),n-(size3-i)*arrayDeques[2].getFirst().getHeight());
            add(arrayDeques[2].getFirst());
            arrayDeques[2].addLast(  arrayDeques[2].getFirst());
            arrayDeques[2].removeFirst();
        }
        for (int i =0;i<size2;i++){
            println(placeForTowersHeight-i*arrayDeques[1].getFirst().getHeight());
            arrayDeques[1].getFirst().setLocation(centreX2-(arrayDeques[1].getFirst().getWidth()/2),n-(size2-i)*arrayDeques[1].getFirst().getHeight());
            add(arrayDeques[1].getFirst());
            arrayDeques[1].addLast(  arrayDeques[1].getFirst());
            arrayDeques[1].removeFirst();
        }

    }

    private void moveBlock(int tower1, int tower2){
            arrayDeques[tower2].addFirst(arrayDeques[tower1].getFirst());
            arrayDeques[tower1].removeFirst();
    }
}