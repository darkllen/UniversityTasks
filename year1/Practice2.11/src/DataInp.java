import java.util.Scanner;

public abstract class DataInp {
    public static String getName(){
        Scanner sc = new Scanner(System.in);
        String name = sc.nextLine();
        name = name.replaceAll("[^a-zA-Zа-яА-Я]","");
        while (name.length()==0){
            name = sc.nextLine();
            name = name.replaceAll("[^a-zA-Zа-яА-Я]","");
        }
        return name;
    }

    public static int getInt(){
        while (true) {
            Scanner scanner = new Scanner(System.in);
            while (true) {
                try {
                    int res = scanner.nextInt();
                    if (res>=0 && res<=100)
                    return res;
                    else   System.out.print("Wrong output, try again: ");
                } catch (Exception e) {
                    System.out.print("Wrong output, try again: ");
                    break;
                }
            }
        }
    }
}
