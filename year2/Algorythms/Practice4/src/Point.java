import java.util.Comparator;

public class Point implements Comparable<Point> {

    public final Comparator<Point> SLOPE_ORDER = new SOrder(this);

    private final int x;
    private final int y;

    public Point(int x, int y) {
        this.x = x;
        this.y = y;
    }

    public void draw() {
        StdDraw.filledCircle(x,y, 250);
    }

    public void drawTo(Point that) {
        StdDraw.line(this.x, this.y, that.x, that.y);
    }

    public double slopeTo(Point that) {
        if (this.x == that.x && this.y==that.y)return Integer.MIN_VALUE;
        if (this.x==that.x) return Integer.MAX_VALUE;
        return ((double) that.y-this.y)/((double) that.x-this.x);
    }

    public int compareTo(Point that) {
        if ( this.y<that.y || (this.y==that.y && this.x<that.x))return -1;
        if (this.y==that.y && this.x==that.x) return 0;
        return 1;
    }

    private static class SOrder implements Comparator<Point>{
        Point zero;
        SOrder(Point point) {
            zero = point;
        }
        public int compare(Point p, Point q){
            if (zero.slopeTo(p)<zero.slopeTo(q)) return -1;
            if (zero.slopeTo(p)>zero.slopeTo(q)) return 1;
            if (zero.slopeTo(p)==zero.slopeTo(q)){
                if (p.compareTo(q)==1) return  1;
                if (p.compareTo(q)==-1) return -1;
            }
            return 0;
        }
    }

    public String toString() {
        /* DO NOT MODIFY */
        return "(" + x + ", " + y + ")";
    }
}