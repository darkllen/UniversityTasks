import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner in = new Scanner(System.in);
        int N = in.nextInt();
        int[][] blocks = new int[N][N];
        for (int i = 0; i < N; i++)
            for (int j = 0; j < N; j++)
                blocks[i][j] = in.nextInt();
        Board initial = new Board(blocks);

        // розв’язати
        Solver solver = new Solver(initial);

        // надрукувати рішення
        if (!solver.isSolvable())
            System.out.println("Дошка не має розв’язку");
        else {
            System.out.println("Мінімальна кількість кроків = " + solver.moves());
            for (Board board : solver.solution())
                System.out.println(board);
        }
    }

}
