import java.io.File;
import java.io.IOException;

public class Main {
    public static void main(String[] args) throws IOException {
        int n = 9;
        Labyrint.generate(n);

        StdDraw.setCanvasSize(1400,700);
        StdDraw.setXscale(0, 2000*n);
        StdDraw.setYscale(0, 1000*n);

        Labyrint labyrint = new Labyrint(new File("2.txt"));
        labyrint.draw(0);
        labyrint.draw(n*1001);

        Graph g = labyrint.createGraph();
        DepthFirstPaths pathsDFS = new DepthFirstPaths(g, labyrint.start, labyrint.end);
        BreadthFirstPaths pathsBFS = new BreadthFirstPaths(g, labyrint.start, labyrint.end, n*1001);

        labyrint.drawPath(pathsDFS.pathTo(labyrint.end),0);
        labyrint.drawPath(pathsBFS.pathTo(labyrint.end), n*1001);
    }

}
