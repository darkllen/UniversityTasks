import java.awt.*;
import java.io.*;
import java.util.Random;
import java.util.Scanner;

public class Labyrint {
    int size = 1000;
    byte[][] arr;
    int m;
    int n;

    int start;
    int end;

    public Labyrint(File file) throws FileNotFoundException {
        Scanner scanner = new Scanner(file);
         m = scanner.nextInt();
         n = scanner.nextInt();
        arr = new byte[m][n];
        for(int i = 0; i< m; i++){
            for( int j = 0; j< n; j++){
                arr[i][j] = scanner.nextByte();
                if(arr[i][j]==2){
                    start = i*n+j;
                }
                if(arr[i][j]==3){
                    end = i*n+j;
                }
            }
        }
    }

    public void drawPath(Iterable<Integer> path, int extraSpace){
        StdDraw.setPenColor(Color.RED);
        path.forEach(x->{
            int i = x/n;
            int j = x-i*n;
            StdDraw.filledSquare(size/2+j*size +extraSpace, size/2+i*size, size/2);
        });

        int i = end/n;
        int j = end-i*n;
        StdDraw.setPenColor(Color.BLUE);
        StdDraw.filledSquare(size/2+j*size+extraSpace, size/2+i*size, size/2);

         i = start/n;
         j = start-i*n;
        StdDraw.setPenColor(Color.GREEN);
        StdDraw.filledSquare(size/2+j*size+extraSpace, size/2+i*size, size/2);
        StdDraw.setPenColor(Color.BLACK);

    }

    public Graph createGraph(){
        Graph g = new Graph(m*n);
        for(int i = 0; i< m; i++){
            for( int j = 0; j< n; j++){
                int curr = i*n+j;
                if(i>0){
                    if(arr[i-1][j]!=1 && arr[i][j]!=1)g.addEdge(curr, curr-n);
                }
                if(i<m-1){
                    if(arr[i+1][j]!=1 && arr[i][j]!=1)g.addEdge(curr, curr+n);
                }
                if(j>0){
                    if(arr[i][j-1]!=1 && arr[i][j]!=1)g.addEdge(curr, curr-1);
                }
                if(j<n-1){
                    if(arr[i][j+1]!=1 && arr[i][j]!=1)g.addEdge(curr, curr+1);
                }
            }
        }
        return g;
    }

    public void draw(int extraSpace){
        for(int i = 0; i<m; i++){
            for(int j = 0; j<n; j++){
                if(arr[i][j]==1)
                StdDraw.filledSquare(size/2+j*size +extraSpace, size/2+i*size, size/2);
                if(arr[i][j]==2){
                    StdDraw.setPenColor(Color.GREEN);
                    StdDraw.filledSquare(size/2+j*size+extraSpace, size/2+i*size, size/2);
                    StdDraw.setPenColor(Color.BLACK);
                }
                if(arr[i][j]==3){
                    StdDraw.setPenColor(Color.BLUE);
                    StdDraw.filledSquare(size/2+j*size+extraSpace, size/2+i*size, size/2);
                    StdDraw.setPenColor(Color.BLACK);
                }

            }
        }
        StdDraw.show();

    }


    static int wall = 1;
    static int pass = 0;
    static boolean deadend(int x, int y, int[][] maze, int height, int width){
        int a = 0;

        if(x != 1){
            if(maze[y][x-2] == pass)
                a+=1;
        }
        else a+=1;

        if(y != 1){
            if(maze[y-2][x] == pass)
                a+=1;
        }
        else a+=1;

        if(x != width-2){
            if(maze[y][x+2] == pass)
                a+=1;
        }
        else a+=1;

        if(y != height-2){
            if(maze[y+2][x] == pass)
                a+=1;
        }
        else a+=1;

        if(a == 4)
            return true;
        else
            return false;
    }
    static void mazemake(int[][] maze, int height, int width){
        int x, y, c, a;
        boolean b;

        for(int i = 0; i < height; i++) // Массив заполняется землей-ноликами
            for(int j = 0; j < width; j++)
                maze[i][j] = wall;

        x = 3; y = 3; a = 0; // Точка приземления крота и счетчик
        while(a < 10000){ // Да, простите, костыль, иначе есть как, но лень
            maze[y][x] = pass; a++;
            while(true){ // Бесконечный цикл, который прерывается только тупиком
                c = new Random().nextInt(4); // Напоминаю, что крот прорывает
                switch(c){  // по две клетки в одном направлении за прыжок
                    case 0: if(y != 1)
                        if(maze[y-2][x] == wall){ // Вверх
                            maze[y-1][x] = pass;
                            maze[y-2][x] = pass;
                            y-=2;
                        }
                    case 1: if(y != height-2)
                        if(maze[y+2][x] == wall){ // Вниз
                            maze[y+1][x] = pass;
                            maze[y+2][x] = pass;
                            y+=2;
                        }
                    case 2: if(x != 1)
                        if(maze[y][x-2] == wall){ // Налево
                            maze[y][x-1] = pass;
                            maze[y][x-2] = pass;
                            x-=2;
                        }
                    case 3: if(x != width-2)
                        if(maze[y][x+2] == wall){ // Направо
                            maze[y][x+1] = pass;
                            maze[y][x+2] = pass;
                            x+=2;
                        }
                }
                if(deadend(x,y,maze,height,width))
                    break;
            }

            if(deadend(x,y,maze,height,width)) // Вытаскиваем крота из тупика
                do{
                    x = 2*(new Random().nextInt(((width-1)/2)))+1;
                    y = 2*(new Random().nextInt(((height-1)/2)))+1;
                }
                while(maze[y][x] != pass);
        } // На этом и все.
    }


    public static void generate(int n) throws IOException {
        int[][] maze = new int[n][n];
        mazemake(maze,n,n);

        int l = new Random().nextInt(n);
        int k = new Random().nextInt(n);
        while (maze[l][k]!=0){
            l = new Random().nextInt(n);
            k = new Random().nextInt(n);
        }
        maze[l][k] = 2;
        while (maze[l][k]!=0){
            l = new Random().nextInt(n);
            k = new Random().nextInt(n);
        }
        maze[l][k] = 3;
        File file = new File("2.txt");
        FileWriter writer = new FileWriter(file);
        writer.write(n+ " " + n+" \n");
        for(int i = 0; i< n; i++){
            for(int j = 0; j<n;j++){
                writer.write(maze[i][j] + " ");
            }
            writer.write("\n");
        }
        writer.flush();
        writer.close();
    }

}
