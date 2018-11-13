
import acm.graphics.GRect;
import acm.program.GraphicsProgram;
//Ihor Yankin

/*
Напишіть програму що малює піраміду. Піраміда складається з горизонтальних рядів цеглин. В кожному наступному ряду кількість цеглин зменшується на один. Нижче наведено приклад:
        Піраміда має розташовуватися по центру горизонталі вікна. Мають бути використані наступні константи:
*/
public class Pyramid extends GraphicsProgram {

    public static final int BRICK_WIDTH = 30;
    public static final int BRICK_HEIGHT = 24;
    public static final int BRICKS_IN_BASE =50;
    public static final int WIDTH = 1000;
    public static final int HEIGHT = 500;

    public void run(){
        this.setSize(WIDTH,HEIGHT);

        int br = BRICK_WIDTH;
        int  height= BRICK_HEIGHT;
        if(br==0)return;
        if(height==0)return;

        for(int i = BRICKS_IN_BASE; i>0;i--){

            int centre = WIDTH/2;
            int startWidth = centre-(i/2)*BRICK_WIDTH-(i%2*BRICK_WIDTH/2);
            int startHeight = HEIGHT-((BRICKS_IN_BASE+1)*BRICK_HEIGHT)-2;

            for(int j =0;j<i;j++){
                addBrick(startWidth+j*BRICK_WIDTH,startHeight+i*BRICK_HEIGHT);
            }
        }
    }
        //add new brick with start coordinate (x,y)
    private void addBrick(int x, int y){
        GRect rect = new GRect(x,y,BRICK_WIDTH,BRICK_HEIGHT);
        add(rect);
    }
}
