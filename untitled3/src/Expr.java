import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Expr {
    Letter input; char next;

    public Integer evalLeft(String word){
        int res = 0;
        input = new Letter(word);
        try{
            next = input.nextChar();
            if (next=='$') throw  new SyntaxError("");
            res =SL();
            match('$');
            return res;
        } catch(SyntaxError ex){
            }
        return null;
    }

    public Integer evalRigth(String word){
        //one more way to do is just to reverse
       // return evalLeft(new StringBuilder(word).reverse().toString());
        int res = 0;
        input = new Letter(word);
        try{
            next = input.nextChar();
            if (next=='$') throw  new SyntaxError("");
            res = SR();
            match('$');
            return res;
        } catch(SyntaxError ex){
            return null; }
    }

    public Integer evalPrior(String word){
        if (!test(word))return null;
        int tenNum = 0;
        int tenNum2 = 0;
        String regexp = "(\\((\\d*[+\\-*]?\\d*)*\\))";
        Pattern pattern = Pattern.compile(regexp);
        while (word.replaceAll(regexp,"").length()!=word.length()){
            Matcher matcher = pattern.matcher(word);
            while (matcher.find(tenNum)){
                tenNum++;
            }
            tenNum2=tenNum;
            while (word.charAt(tenNum2)!=')'){
                tenNum2++;
            }
            String temp = word.substring(tenNum,tenNum2);

            int y = 1;
            int y2 = 1;
            while (temp.contains("*")){
                try {
                    while (Character.getNumericValue(temp.charAt(temp.indexOf("*")-y))>=0&&Character.getNumericValue(temp.charAt(temp.indexOf("*")-y))<=9){
                        y++;
                    }
                    y--;
                }catch (IndexOutOfBoundsException e){
                    y--;
                }
                int a = Integer.parseInt(temp.substring(temp.indexOf("*")-y,temp.indexOf("*")));
                try {
                    while (Character.getNumericValue(temp.charAt(temp.indexOf("*")+y2))>=0&&Character.getNumericValue(temp.charAt(temp.indexOf("*")+y2))<=9){
                        y2++;
                    }
                } catch (IndexOutOfBoundsException e){

                }

                int b = Integer.parseInt(temp.substring(temp.indexOf("*")+1,temp.indexOf("*")+y2));

                temp =temp.substring(0,temp.indexOf("*")-y) +a*b + temp.substring(temp.indexOf("*")+y2);

                y=1;
                y2=1;
            }
            word = word.substring(0,tenNum-1) + evalLeft(temp) + word.substring(tenNum2+1);
            tenNum=0;
        }
        return evalLeft(word);
                /*while (word.contains("*")){
            while (Character.getNumericValue(word.charAt(word.indexOf("*")-tenNum))>=0&&Character.getNumericValue(word.charAt(word.indexOf("*")-tenNum))<=9){
                tenNum++;
            }
            tenNum--;
            int a = Integer.parseInt(word.substring(word.indexOf("*")-tenNum,word.indexOf("*")));
            try {
                while (Character.getNumericValue(word.charAt(word.indexOf("*")+tenNum2))>=0&&Character.getNumericValue(word.charAt(word.indexOf("*")+tenNum2))<=9){
                    tenNum2++;
                }
            } catch (IndexOutOfBoundsException e){

            }

            int b = Integer.parseInt(word.substring(word.indexOf("*")+1,word.indexOf("*")+tenNum2));

            word =word.substring(0,word.indexOf("*")-tenNum) +"(" +a + "m" + b + ")" + word.substring(word.indexOf("*")+tenNum2);

            tenNum=1;
            tenNum2=1;
        }
        word = word.replaceAll("m","*");
        word = word.replaceAll("\\(\\(","(");
        word = word.replaceAll("\\)\\)",")");*/

    }



    public boolean test(String word){
        input = new Letter(word);
        try{
            next = input.nextChar();
            if (next=='$') throw  new SyntaxError("");
            SL();
            match('$');
        } catch(SyntaxError ex){
            return false; }
        return true;
    }


    private int SR() throws SyntaxError{
        int res = 0;
        res = TR();
        return AR(res);
    }

    private int TR() throws SyntaxError{
        int res = 0;
        String q = TL();
        if (q.startsWith("0")&&q.length()>1) throw new SyntaxError("");
        res = Integer.parseInt(q);

        return  BR(res);
    }

    private int AR(int s) throws SyntaxError{
        int res = s;
        if(next=='+'){
            next=input.nextChar();
            res = TR() + s;
            return  AR(res);
        }
        if(next=='-'){
            next=input.nextChar();
            res =s - TR();
            return  AR(res);
        }
        return res;
    }

    private int BR(int s) throws SyntaxError{
        int res = s;
        if(next=='*'){
            next=input.nextChar();
            res = TR() * s;
            return  BR(res);
        }
        return  AR(res);
    }


    private int SL() throws SyntaxError{
        int res = 0;
        String q = TL();
        if (q.startsWith("0")&&q.length()>1) throw new SyntaxError("");
        res = Integer.parseInt(q);
        return  AL(res);
    }

    private int AL(int s) throws SyntaxError{
        int res = s;
        if(next=='+'){
            next=input.nextChar();
            String q = TL();
            if (q.startsWith("0")&&q.length()>1) throw new SyntaxError("");
           res = Integer.parseInt(q) + s;
            return    AL(res);
        } else if (next=='*'){
            next=input.nextChar();
            String q = TL();
            if (q.startsWith("0")&&q.length()>1) throw new SyntaxError("");
            res =  Integer.parseInt(q) * s;
            return  AL(res);
        }else if (next=='-'){
            next=input.nextChar();
            String q = TL();
            if (q.startsWith("0")&&q.length()>1) throw new SyntaxError("");
            res = s-  Integer.parseInt(q);
            return  AL(res);
        }
        return res;
    }


    private String TL() throws SyntaxError{
        String  res = "";
        if(next=='('){
            next=input.nextChar();
            res = String.valueOf(SL());
            match(')');
            return res;
        } else if (next == '1'){
            next=input.nextChar();
            res="1";
            res+=TL();
            return res;
        } else if (next=='2'){
            next=input.nextChar();
            res="2";
            res+=TL();
            return res;}
        else if (next=='3'){
            next=input.nextChar();
            res="3";
            res+=TL();
            return res;
        }else if (next=='4'){
            next=input.nextChar();
            res="4";
            res+=TL();
            return res;
        }else if (next=='5'){
            next=input.nextChar();
            res="5";
            res+=TL();
            return res;
        }else if (next=='6'){
            next=input.nextChar();
            res="6";
            res+=TL();
            return res;
        }else if (next=='7'){
            next=input.nextChar();
            res="7";
            res+=TL();
            return res;
        }else if (next=='8'){
            next=input.nextChar();
            res="8";
            res+=TL();
            return res;
        }else if (next=='9'){
            next=input.nextChar();
            res="9";
            res+=TL();
            return res;
        }else if (next=='0'){
            next=input.nextChar();
            res="0";
            res+=TL();
            return res;
        }
        return res;
    }

    private void match(char c) throws SyntaxError{
        if(next==c) next=input.nextChar();
        else throw new SyntaxError("Expecting " + c + ", found " + next);
    }

}
