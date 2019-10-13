import java.io.File;
import java.io.FileNotFoundException;
import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Scanner;

public class ConvexHull {
    public static void main(String[] args) throws FileNotFoundException {
        StdDraw.setXscale(0, 32768);
        StdDraw.setYscale(0, 32768);
        String dir = "C:\\Users\\yanki\\OneDrive\\Documents\\GitHub\\UniversityTasks\\year2\\Algorythms\\Practice6\\src\\Data\\";
        String name = "input40.txt";
        Scanner scanner = new Scanner(new File(dir + name));
        ArrayList<Point> points = new ArrayList<>();
        int n = scanner.nextInt();
        for (int i = 0; i < n; ++i) {
            points.add(new Point(scanner.nextInt(), scanner.nextInt()));
        }
        points.forEach(Point::draw);
        Point min = points.get(0);
        Point start = new Point(min.getX(), min.getY());
        for(int i = 0; i< points.size();i++){
            if (points.get(i).getY()<= min.getY()) min = points.get(i);
        }

        points.remove(min);
        points.sort(min.POLAR_OREDER);

        Point curr = points.get(0);
        ArrayList<Point> conn = new ArrayList();
        conn.add(min);
        conn.add(curr);
        int countM= -1;
        int countC= 0;
        for(int i = 1; i< points.size(); i++){
            if (Point.ccw(min, curr, points.get(i))<=0) {
                conn.add(points.get(i));
                min = curr;
                curr = points.get(i);
                countM++;
                countC++;
            }
            else{
                conn.remove(conn.size()-1);
                min = points.get(countM);
                countM--;
                curr = points.get(countC);
                countC--;
                i++;
            }

        }
      /*  for(int i = 1; i< points.size()-1; i++){
         if (Point.ccw(min, curr, points.get(i))<0) {
             conn.add(points.get(i));
             min = curr;
             curr = points.get(i);
         }
         else{
             conn.remove(conn.size()-1);
             conn.add(points.get(i));
             curr=min;
             min  = points.get(i-1);
             i++;
         }

        }*/
        for (int i = 0; i< conn.size()-1; i++){
            conn.get(i).drawTo(conn.get(i+1));
        }
       // conn.get(conn.size()-1).drawTo(start);
    }
}
