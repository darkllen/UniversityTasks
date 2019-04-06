public class Parser {
    Letter input; char next;

    int pairs = 0;
    int count = 0;
    int max = 0;

    public Parser(){}
    public boolean analys(String word){
        input = new Letter(word);
        try{ next = input.nextChar(); S(); match('$');
        } catch(SyntaxError ex){ System.out.println(ex.getMessage());
        pairs = 0;
        max = -1;
            System.out.println("pairs " +pairs);
            System.out.println("max " +max);
        return false; }
        System.out.println("pairs " +pairs);
        System.out.println("max " +max);
        return true;
    }

    private  void count(){
        count++;
        if (count>max) max = count;
    }

    private void S() throws SyntaxError{
        if(next=='(') {count(); next=input.nextChar(); S(); match(')'); count--; pairs++; S();}



//else match('');
}
  /*  private void A() throws SyntaxError{
        if(next=='b'){ next=input.nextChar(); match('a'); A(); S();
        } else match('a');
    }*/
        private void match(char c) throws SyntaxError{
            if(next==c) next=input.nextChar();
            else throw new SyntaxError("Expecting " + c + ", found " + next);
        }

    }
