import acm.graphics.GOval;
import acm.graphics.GRect;
import acm.program.GraphicsProgram;

import java.awt.event.HierarchyBoundsListener;
import java.awt.event.HierarchyEvent;

public class Robot extends GraphicsProgram {
    public static final int  HEAD_WIDTH = 200;
    public static final int  HEAD_HEIGHT = 200;
    public static int  EYE_RADIUS = 10;
    public static int  MOUTH_WIDTH = 50;
    public static int MOUTH_HEIGHT = 30;
    public void run(){
        if (EYE_RADIUS>(HEAD_HEIGHT/4)||EYE_RADIUS>(HEAD_WIDTH/4)){
            EYE_RADIUS=Math.min(HEAD_HEIGHT,HEAD_WIDTH)/4;
        }
        if (MOUTH_WIDTH>HEAD_WIDTH/100*90){
            MOUTH_WIDTH=HEAD_WIDTH/100*90;
        }
        if (MOUTH_HEIGHT>HEAD_HEIGHT/100*10){
            MOUTH_HEIGHT=HEAD_HEIGHT/100*10;
        }
        drawMouse(drawEyes(drawHead()));
        this.getGCanvas().addHierarchyBoundsListener(newCanvasListener());
    }

    private HierarchyBoundsListener newCanvasListener() {
        HierarchyBoundsListener listener1 = new HierarchyBoundsListener(){
            @Override
            public void ancestorMoved(HierarchyEvent e) {
                drawHead();
                drawMouse(drawEyes(drawHead()));
            }

            @Override
            public void ancestorResized(HierarchyEvent e) {
                drawHead();
                drawMouse(drawEyes(drawHead()));
            }
        };
        return listener1;
    }

    private GRect drawHead(){
        this.getGCanvas().removeAll();
        int x = this.getWidth()/2;
        int y = this.getHeight()/2;
        GRect rect = new GRect(x-(HEAD_WIDTH/2), y-(HEAD_HEIGHT/2),HEAD_WIDTH,HEAD_HEIGHT);
        add(rect);
        return rect;
    }
    private GRect drawEyes(GRect rect){
        int centrX = (int) (rect.getWidth()/4)-EYE_RADIUS;
        int centrY = (int) (rect.getHeight()/4)-EYE_RADIUS;
        GOval oval = new GOval(rect.getX()+centrX, rect.getY()+centrY, EYE_RADIUS, EYE_RADIUS);
        add(oval);

        int centrX2 = (int) (rect.getWidth()/4);
        int centrY2 = (int) (rect.getHeight()/4)-EYE_RADIUS;
        GOval oval2 = new GOval(rect.getX()+rect.getWidth()/2+centrX2, rect.getY()+centrY2, EYE_RADIUS, EYE_RADIUS);
        add(oval2);
        return rect;
    }
    private void drawMouse(GRect rect){
        int y = (int) (rect.getY()+rect.getHeight()-rect.getHeight()/100*15);
        int x = (int) (rect.getX()+rect.getWidth()/2)-MOUTH_WIDTH/2;
        GRect rect1 = new GRect(x,y, MOUTH_WIDTH, MOUTH_HEIGHT);
        add(rect1);
    }

}
