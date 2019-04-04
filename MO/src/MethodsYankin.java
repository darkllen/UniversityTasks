import java.math.BigDecimal;
import java.util.ArrayList;

public class MethodsYankin {

    public static boolean onlyOdd(String str){
        if (str.replaceAll("[^ab]","").length()!=str.length())return false;
        if (str.replaceAll("a","").length()%2==str.length()%2 || str.replaceAll("b","").length()%2==str.length()%2) return false;
        return true;
    }

    public static boolean isiden(String str){
        return str.matches("[a-zA-Z]\\w*");
    }

    public static int cntIden(String str){
        String[] strings = str.split("\\W");
        int count = strings.length;
        for (String s:strings) {
            if (s.length()==0)count--;
            else if (s.replaceAll("[0-9_]","").length()==0)count--;
        }
        return count;
    }

       public static int distIden(String str){
           String[] strings = str.split("\\W");
           ArrayList<String> unique = new ArrayList<>();
           int count = strings.length;
           for (String s:strings) {
               if (s.length()==0)count--;
               else if (s.replaceAll("[0-9_]","").length()==0){
                   s = "";
                   count--;
               } else if (!unique.contains(s)){
                   while (s.replaceAll("[0-9_]","").charAt(0)!=s.charAt(0))s = s.substring(1);

                   int eq = 0;
                   for (String un:unique) {
                       if (un.equals(s)) eq++;
                   }
                   if (eq==0)
                   unique.add(s);
               }
           }
           return unique.size();
       }

       public static Double sumDouble(String str){
        String[] strings = str.split(",");
        BigDecimal sum = new BigDecimal(0);
           for (String s:strings) {
               try {
                   while (s.startsWith(" "))s = s.substring(1);
                   while (s.endsWith(" "))s = s.substring(0,s.length()-1);
                   sum = sum.add(new BigDecimal(s));
               }catch (NumberFormatException e){
                   return null;
               }
           }
           return sum.doubleValue();
       }
}
