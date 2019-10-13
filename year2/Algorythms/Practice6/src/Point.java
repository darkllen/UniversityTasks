import java.util.Comparator;
import java.util.Objects;

public class Point implements Comparable<Point> {

    public final Comparator<Point> POLAR_OREDER = new PolarComp(this);

    private final int x;
    private final int y;

    public Point(int x, int y) {
        this.x = x;
        this.y = y;
    }

    public int getY() {
        return y;
    }

    public int getX() {
        return x;
    }

    public void draw() {
        StdDraw.filledCircle(x,y, 100);
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

    public static int ccw(Point a, Point b, Point c){
        int res = (b.x-a.x)*(c.y-a.y)-(b.y-a.y)*(c.x-a.x);
        if (res>0) return -1;
        if (res<0) return 1;
        return 0;

    }

    private static class PolarComp implements Comparator<Point>{
        Point zero;
        PolarComp(Point point) {
            zero = point;
        }
        public int compare(Point p, Point q){
            if (p.y>zero.y && q.y<zero.y) return -1;
            if (p.y<zero.y && q.y>zero.y) return 1;
            return ccw(zero, p, q);
        }
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Point point = (Point) o;
        return x == point.x &&
                y == point.y;
    }

    @Override
    public int hashCode() {
        return Objects.hash(x, y);
    }

    public String toString() {
        /* DO NOT MODIFY */
        return "(" + x + ", " + y + ")";
    }
}