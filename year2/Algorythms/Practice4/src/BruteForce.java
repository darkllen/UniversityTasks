import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Scanner;

public class BruteForce {
    public static void main(String[] args) throws FileNotFoundException {
        StdDraw.setXscale(0, 32768);
        StdDraw.setYscale(0, 32768);
        String dir = "C:\\Users\\yanki\\OneDrive\\Documents\\GitHub\\UniversityTasks\\year2\\Algorythms\\Practice4\\src\\Data\\";
        String name= "grid6x6.txt";
        Scanner scanner = new Scanner(new File(dir + name));
        ArrayList<Point> points = new ArrayList<>();
        int n = scanner.nextInt();
        for (int i = 0; i< n; ++i){
            points.add(new Point(scanner.nextInt(),scanner.nextInt()));
        }

        points.forEach(x->x.draw());

        for (int i = 0; i< n; i++){
            for (int j = 0; j<n; j++){
                for (int l = 0; l<n; l++){
                    for (int y = 0; y<n; y++){
                        double grad1 = points.get(i).slopeTo(points.get(j));
                        double grad2 = points.get(i).slopeTo(points.get(l));
                        double grad3 = points.get(i).slopeTo(points.get(y));
                        if (grad1==grad2 && grad2==grad3 && isUnique(i,j,l,y)){
                            points.get(i).drawTo(points.get(j));
                            points.get(i).drawTo(points.get(l));
                            points.get(i).drawTo(points.get(y));
                        }
                    }
                }
            }
        }
    }

    public static boolean isUnique(int i, int j, int l, int y){
        return  (i!=j && i!=l&& i!=y && j!=l && j!=y && l!=y);
    }
}
