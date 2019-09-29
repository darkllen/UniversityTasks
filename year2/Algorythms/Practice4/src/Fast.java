import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Scanner;

public class Fast {
    public static void main(String[] args) throws FileNotFoundException {
        StdDraw.setXscale(0, 32768);
        StdDraw.setYscale(0, 32768);
        String dir = "C:\\Users\\yanki\\OneDrive\\Documents\\GitHub\\UniversityTasks\\year2\\Algorythms\\Practice4\\src\\Data\\";
        String name= "rs1423.txt";
        Scanner scanner = new Scanner(new File(dir + name));
        ArrayList<Point> points = new ArrayList<>();
        int n = scanner.nextInt();
        for (int i = 0; i< n; ++i){
            points.add(new Point(scanner.nextInt(),scanner.nextInt()));
        }

        points.forEach(x->x.draw());

        ArrayList<Point> copy = (ArrayList<Point>) points.clone();
        for (int i = 0; i< n; i++){
            copy.sort(points.get(i).SLOPE_ORDER);
            for (int j = 1; j<n-1;j++){
                    int count = 0;
                    int curr = j;
                    while (copy.get(0).slopeTo(copy.get(j))==copy.get(0).slopeTo(copy.get(j+1))){
                        count++;
                        if (j<n-2)
                        ++j;
                        else break;
                }
                    if (count<2) j = curr;
                    else {
                        copy.get(0).drawTo(copy.get(j));
                    }
            }
        }
    }
}
