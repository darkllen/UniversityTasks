class Brackets {
    Letter input; char next;

   private int squareDepth = 0;
   private int maxSquareDepth = 0;

   private int roundDepth = 0;
   private int maxRoundDepth = 0;

    public int depthSquare(String word){
        if (analys(word)){
           return maxSquareDepth;
        }
        return -1;
    }
    public int depthRound(String word){
        if (analys(word)){
            return maxRoundDepth;
        }
        return -1;
    }

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
        if(next=='(') {
            roundDepth++;
            if (roundDepth>maxRoundDepth)maxRoundDepth=roundDepth;
            next=input.nextChar();
            S();
            match(')');
            roundDepth--;
            S();
        }else if (next=='['){
            squareDepth++;
            if (squareDepth>maxSquareDepth)maxSquareDepth=squareDepth;
            next=input.nextChar();
            S();
            match(']');
            squareDepth--;
            S();
        }else if (next=='{'){
            next=input.nextChar();
            S();
            match('}');
            S();
        }
    }

    private void match(char c) throws SyntaxError{
        if(next==c) next=input.nextChar();
        else throw new SyntaxError("Expecting " + c + ", found " + next);
    }

}

