import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Main {
    public static void main(String[] args) {
        System.out.println(cntFromab(""));
        System.out.println(cntFromab("aaaa"));
        System.out.println(cntFromab("abc"));
        System.out.println(Double.parseDouble(".1"));

    }

    public static boolean isFromab(String str){
        String regex = "[ab]+";
        Pattern pattern = Pattern.compile(regex);
        Matcher matcher = pattern.matcher(str);
        return matcher.matches();
    }


    public static int cntFromab (String str){
        String regex = "[^a^b]+";
        Pattern pattern = Pattern.compile(regex);
        Matcher matcher = pattern.matcher(str);

        int count = 0;
        while (matcher.find()){
            count++;
        }

        return count;

    }

    public static String repFromab(String str){
        String regex = "[ab]+";
        Pattern pattern = Pattern.compile(regex);
        Matcher matcher = pattern.matcher(str);

        return matcher.replaceAll("+");
    }
}
