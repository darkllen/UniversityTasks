public class ParserG {
    Letter input; char next;

    public boolean analys(String word){
        input = new Letter(word);
        try{
            next = input.nextChar();
            S();
            match('$');
        } catch(SyntaxError ex){
        return false; }
        return true;
    }

    private void S() throws SyntaxError{
        if(next=='a') {
            next=input.nextChar();
            match('b');
            if (next=='b'){
                next=input.nextChar();
            }else {
             A();
             B();
             match('d');
            }
        } else throw new SyntaxError("");
    }

    private void A() throws SyntaxError{
        if(next=='a'){
            next=input.nextChar();
            A();
            match('b');
        } else if (next=='d'){
            next=input.nextChar();
        } else throw new SyntaxError("");
    }


    private void B() throws SyntaxError{
        if(next=='b'){
            next=input.nextChar();
            C();
        } else throw new SyntaxError("");
    }

    private void C() throws SyntaxError{
         if (next=='c'){
             next=input.nextChar();
             A();
             C();
        }
    }

        private void match(char c) throws SyntaxError{
            if(next==c) next=input.nextChar();
            else throw new SyntaxError("Expecting " + c + ", found " + next);
        }

    }


