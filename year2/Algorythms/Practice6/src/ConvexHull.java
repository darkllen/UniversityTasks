import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Scanner;

public class ConvexHull {
    //Graham alg
    public static void main(String[] args) throws FileNotFoundException {
        StdDraw.setXscale(0, 32768);
        StdDraw.setYscale(0, 32768);
        String dir = "C:\\Users\\yanki\\OneDrive\\Documents\\GitHub\\UniversityTasks\\year2\\Algorythms\\Practice6\\src\\Data\\";
       // String name = "grid6x6.txt";
       // String name = "horizontal100.txt";
       // String name = "input6.txt";
       // String name = "input8.txt";
       // String name = "input40.txt";
       // String name = "input50.txt";
       // String name = "input56.txt";
       // String name = "input100.txt";
        String name = "input400.txt";
       // String name = "rs1423.txt";
        Scanner scanner = new Scanner(new File(dir + name));
        ArrayList<Point> points = new ArrayList<>();
        int n = scanner.nextInt();
        for (int i = 0; i < n; ++i) {
            points.add(new Point(scanner.nextInt(), scanner.nextInt()));
        }
        points.forEach(Point::draw);
        Point min = points.get(0);
        Point start = min;
        for(int i = 0; i< points.size();i++){
            if (points.get(i).getY()<= min.getY()) min = points.get(i);
        }

        points.remove(min);
        points.sort(min.POLAR_OREDER);
        points.add(0, min);

        ArrayList<Point> conn = new ArrayList();
        conn.add(min);
        conn.add(points.get(1));
        for (int i = 2; i<points.size(); i++){
            if (conn.size()>1)
            while (Point.ccw(conn.get(conn.size() - 2), conn.get(conn.size() - 1), points.get(i)) >= 0){
                if (conn.size()<=2) break;
                conn.remove(conn.size()-1);
            }
            conn.add(points.get(i));
        }


        for (int i = 0; i< conn.size()-1; i++){
            conn.get(i).drawTo(conn.get(i+1));
        }
        conn.get(conn.size()-1).drawTo(conn.get(0));
    }
}
