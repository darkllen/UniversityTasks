
public class Rectangle {
    private int x1;
    private int x2;
    private int y1;
    private int y2;
    private boolean crossWithPoint=false;

    public Rectangle(int x1, int y1, int x2, int y2) {
        if (x1>x2){
            int temp = x1;
            x1 = x2;
            x2 = temp;
        }
        if (y1>y2){
            int temp = y1;
            y1=y2;
            y2 = temp;
        }
        this.x1 = x1;
        this.x2 = x2;
        this.y1 = y1;
        this.y2 = y2;
    }

    public boolean isCrossWithPoint() {
        return crossWithPoint;
    }

    public void setCrossWithPoint(boolean crossWithPoint) {
        this.crossWithPoint = crossWithPoint;
    }

    public int getX1() {
        return x1;
    }

    public void setX1(int x1) {
        this.x1 = x1;
    }

    public int getX2() {
        return x2;
    }

    public void setX2(int x2) {
        this.x2 = x2;
    }

    public int getY1() {
        return y1;
    }

    public void setY1(int y1) {
        this.y1 = y1;
    }

    public int getY2() {
        return y2;
    }

    public void setY2(int y2) {
        this.y2 = y2;
    }

    @Override
    public String toString() {
        return
                "xStart=" + x1 +
                ", width=" + (x2-x1) +
                ", yStart=" + y1 +
                ", heigth=" + (y2-y1);
    }
}
