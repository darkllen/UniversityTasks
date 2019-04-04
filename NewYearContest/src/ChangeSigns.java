import java.util.Scanner;

public class ChangeSigns {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int n = Integer.parseInt(sc.nextLine());
        int[] arr = new int[n];

        String[] lines = sc.nextLine().split(" ");
        for (int i = 0; i<n;i++){
            arr[i]= Integer.parseInt(lines[i]);
        }
        int k = Integer.parseInt(sc.nextLine());
        for (int i = 0; i<k;i++){
            String[] lns = sc.nextLine().split(" ");
            if (Integer.parseInt(lns[0])==1){
                int y = 1;
                long sun = 0;
                for (int u = Integer.parseInt(lns[1]);u<=Integer.parseInt(lns[2]);u++){
                    sun+=(arr[u-1]*y);
                    y*=-1;
                }
                System.out.println(sun);
            } else{
                arr[Integer.parseInt(lns[1])-1]=arr[Integer.parseInt(lns[2])-1];
            }
        }
    }
}
