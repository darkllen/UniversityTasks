public class Main {
    public static void main(String[] args) {
         Expr parserG = new Expr();
        System.out.println( parserG.evalPrior("10+((((2+3*3))+2*2)*2)"));
    }
}
