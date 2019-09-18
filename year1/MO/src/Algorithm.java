import java.util.ArrayList;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Algorithm {
    ArrayList<Rule> base = new ArrayList<>();

    public Algorithm(String desc) {
        try {
            String[] strings = desc.split(";");

            for (String s:strings) {
                Rule rule = new Rule();

                if (s.replaceAll("[^a-zA-Z0-9#\\->.\\s]","").length()!=s.length()){
                    base = null;
                    return;
                }

                if (!s.contains("->")){
                    base=null;
                    return;
                }
                if(s.length()-s.replaceAll("[->]","").length()>2) {
                    base=null;
                    return;
                }

                if(s.length()-s.replaceAll("[.]","").length()>1) {
                    base=null;
                    return;
                }

                while (s.startsWith(" "))s=s.substring(1);
                while (s.endsWith(" "))s=s.substring(0,s.length()-1);
                while (s.substring(0,s.indexOf('-')).endsWith(" "))
                    s = s.substring(0,s.indexOf('-')-1)+s.substring(s.indexOf('-'));



                rule.left = Pattern.compile(s.substring(0,s.indexOf('-')));
                if (s.contains(".")){
                    rule.end=true;

                    if (s.endsWith(".")){
                        if (s.contains(" ")) {
                            base = null;
                            return;
                        }
                        rule.right = "";
                        base.add(rule);
                        continue;
                    }
                    while (s.substring(s.indexOf('.')+1).startsWith(" "))
                        s=s.substring(0,s.indexOf('.')+1)+s.substring(s.indexOf('.')+2);
                    if (s.contains(" ")) {
                        base = null;
                        return;
                    }
                    rule.right = s.substring(s.indexOf('.')+1);
                }
                else {
                    rule.end=false;
                    if (s.endsWith(">")){
                        if (s.contains(" ")) {
                            base = null;
                            return;
                        }
                        rule.right = "";
                        base.add(rule);
                        continue;
                    }
                    while (s.substring(s.indexOf('>')+1).startsWith(" "))
                        s=s.substring(0,s.indexOf('>')+1)+s.substring(s.indexOf('>')+2);
                    if (s.contains(" ")) {
                        base = null;
                        return;
                    }
                    rule.right = s.substring(s.indexOf('>')+1);
                }
                base.add(rule);
            }
        } catch (Exception e){
            base = null;
        }

    }

    public String eval(String input, int cnt){
        int count = 0;

        while (true){
            int i =0;
            for (Rule r:base) {
                Matcher matcher = r.left.matcher(input);
               if (matcher.find()){
                    i++;
                    count++;
                    input =  matcher.replaceFirst(r.right);
                    if (count>=cnt) return "undefined";
                    if (r.end) return input;
                    break;
               }


            }
            if (i==0) return input;
        }
    }

    public boolean isGood(){
        if (base==null) return false;
        return true;
    }

    private class Rule{
        Pattern left;
        String right;
        boolean end;
    }
}
