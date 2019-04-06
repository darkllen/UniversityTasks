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
        while (word.contains("*")){
            int a = Character.getNumericValue(word.charAt(word.indexOf("*")-1));
            int b = Character.getNumericValue(word.charAt(word.indexOf("*")+1));
            word =word.substring(0,word.indexOf("*")-1) +(a*b) + word.substring(word.indexOf("*")+2);
        }
        return evalLeft(word);
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
       res = ER();

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

    private int ER() throws SyntaxError{
        int res = 0;
        if(next=='('){
                next=input.nextChar();
                res = ER();
                match(')');
                return res;
        }
        if (next == '1'){
            next=input.nextChar();
            return 1;
        } else if (next=='2'){
            next=input.nextChar();
            return 2;
        }
        return 0;
    }



    private int SL() throws SyntaxError{
        int res = 0;
        res = TL();
        return  AL(res);
    }

    private int AL(int s) throws SyntaxError{
        int res = s;
        if(next=='+'){
            next=input.nextChar();
           res = TL() + s;
            return    AL(res);
        } else if (next=='*'){
            next=input.nextChar();
            res = TL() * s;
            return  AL(res);
        }else if (next=='-'){
            next=input.nextChar();
            res = s- TL();
            return  AL(res);
        }
        return res;
    }

    private int TL() throws SyntaxError{
        int res = 0;
        if(next=='('){
            next=input.nextChar();
            res = SL();
            match(')');
            return res;
        } else if (next == '1'){
            next=input.nextChar();
            return 1;
        } else if (next=='2'){
            next=input.nextChar();
            return 2;
        }
        return res;
    }

    private void match(char c) throws SyntaxError{
        if(next==c) next=input.nextChar();
        else throw new SyntaxError("Expecting " + c + ", found " + next);
    }

}
