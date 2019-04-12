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

    public Integer evalPrior(String word) {
        if (!test(word)) return null;
        int r = 0;
        input = new Letter(word);
        try {
            next = input.nextChar();
            r = EP();
            match('$');
        } catch (SyntaxError ex) {
            return null;
        }
        return r;
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



    private int EP() throws SyntaxError{  int r = TP(); return AP(r); }
    private int AP(int op) throws SyntaxError{  int r=op;
        if(next=='+' ){next=input.nextChar();  r = op+TP();   r = AP(r); }
        else if (next=='-'){
            next=input.nextChar();  r = op-TP();   r = AP(r);
        }
        return r;
    }
    private int TP() throws SyntaxError{
        String q = FN();
        if (q.startsWith("0")&&q.length()>1) throw new SyntaxError("");
        int r = Integer.parseInt(q);
        return BP(r); }
    private int BP(int op) throws SyntaxError{ int r=op;
        if(next=='*' ){next=input.nextChar();
            String q = FN();
            if (q.startsWith("0")&&q.length()>1) throw new SyntaxError("");
            r = op*Integer.parseInt(q);
        r = BP(r); }
        return r;
    }

    private String FN() throws SyntaxError{
        String  res = "";
        if(next=='('){
            next=input.nextChar();
            res = String.valueOf(EP());
            match(')');
            return res;
        } else if (next == '1'){
            next=input.nextChar();
            res="1";
            res+=FN();
            return res;
        } else if (next=='2'){
            next=input.nextChar();
            res="2";
            res+=FN();
            return res;}
        else if (next=='3'){
            next=input.nextChar();
            res="3";
            res+=FN();
            return res;
        }else if (next=='4'){
            next=input.nextChar();
            res="4";
            res+=FN();
            return res;
        }else if (next=='5'){
            next=input.nextChar();
            res="5";
            res+=FN();
            return res;
        }else if (next=='6'){
            next=input.nextChar();
            res="6";
            res+=FN();
            return res;
        }else if (next=='7'){
            next=input.nextChar();
            res="7";
            res+=FN();
            return res;
        }else if (next=='8'){
            next=input.nextChar();
            res="8";
            res+=FN();
            return res;
        }else if (next=='9'){
            next=input.nextChar();
            res="9";
            res+=FN();
            return res;
        }else if (next=='0'){
            next=input.nextChar();
            res="0";
            res+=FN();
            return res;
        }
        return res;
    }



    private int SR() throws SyntaxError{
        int res = 0;
        res = TR();
        return AR(res);
    }

    private int TR() throws SyntaxError{
        int res = 0;
        String q = FR();
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

    private String FR() throws SyntaxError{
        String  res = "";
        if(next=='('){
            next=input.nextChar();
            res = String.valueOf(SR());
            match(')');
            return res;
        } else if (next == '1'){
            next=input.nextChar();
            res="1";
            res+=FR();
            return res;
        } else if (next=='2'){
            next=input.nextChar();
            res="2";
            res+=FR();
            return res;}
        else if (next=='3'){
            next=input.nextChar();
            res="3";
            res+=FR();
            return res;
        }else if (next=='4'){
            next=input.nextChar();
            res="4";
            res+=FR();
            return res;
        }else if (next=='5'){
            next=input.nextChar();
            res="5";
            res+=FR();
            return res;
        }else if (next=='6'){
            next=input.nextChar();
            res="6";
            res+=FR();
            return res;
        }else if (next=='7'){
            next=input.nextChar();
            res="7";
            res+=FR();
            return res;
        }else if (next=='8'){
            next=input.nextChar();
            res="8";
            res+=FR();
            return res;
        }else if (next=='9'){
            next=input.nextChar();
            res="9";
            res+=FR();
            return res;
        }else if (next=='0'){
            next=input.nextChar();
            res="0";
            res+=FR();
            return res;
        }
        return res;
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
