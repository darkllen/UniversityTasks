import java.util.ArrayList;
import java.util.StringTokenizer;

public class Main {
    int countMoves=2;
    static ArrayList<Move> moves = new ArrayList<>();
    public static void main(String[] args) {
        Move move = new Move(0,1,"(a*.b*)*.a.a.b.b.a");
        moves.add(move);
        int count = 0;
        for (int i =0;i<moves.size();i++){
            if (check1(moves.get(i))){
            ArrayList<Move> moves1 =  movesOr(moves.get(i));
            moves.remove(i);
           moves.addAll(moves1);
           count=0;
            }else count++;

           if (check(moves)) i=-1+count;
        }
System.out.println(moves);

        ArrayList<Condition> conditions = new ArrayList<>();

        while (moves.size()!=0){
            int a = moves.get(0).start;
            ArrayList<Move> moves1 = new ArrayList<>();
            for (int i = 0; i<moves.size();i++){
                if (moves.get(i).start==a){
                    moves1.add(moves.get(i));
                    moves.remove(i);
                    i=-1;
                }
            }
            Condition condition = new Condition(moves1,a);
            conditions.add(condition);
        }
        System.out.println(conditions);
    }

    public static int next(){
        int max = 1;
        for (Move m:moves) {
            if (m.end>max)max=m.end;
        }
        return max;
    }
    public static boolean check(ArrayList<Move> moves){
        for (Move m:moves) {
            if (m.lexem.contains("|")||m.lexem.contains(".")||m.lexem.contains("*"))return true;

        }return false;
    }
    public static boolean check1(Move moves){
            if (moves.lexem.contains("|")||moves.lexem.contains(".")||moves.lexem.contains("*"))return true;
        return false;
    }
  /*  public static ArrayList<String> dividebySkob(String reg){
        ArrayList<String> lexems = new ArrayList<>();
        while (reg.contains("(")){
            if (reg.startsWith("(")){
                int count1 = 0;
                for (int i = 0; i<reg.length();i++){
                    if (reg.charAt(i)=='('){
                        count1++;
                    }
                    if (reg.charAt(i)==')'){
                        count1--;
                    }
                    if (count1==0){
                        lexems.add(reg.substring(1,i));
                        reg = reg.substring(i+1);
                    }
                }
            }else {
                lexems.add(reg.substring(0,reg.indexOf("(")));
                reg = reg.substring(reg.indexOf("("));
            }
        }
        System.out.println("d");
        return lexems;
    }*/

    public static ArrayList<Move> movesOr(Move move){
        int countLex = 0;
       String str = new String(move.lexem);
       ArrayList<Move> moves = new ArrayList<>();
       int count =0;
       for (int i = 0;i<str.length();i++){
           if (str.charAt(i)=='('){
               count++;
           }
           if (str.charAt(i)==')'){
               count--;
           }
           if (count==0 && str.charAt(i)=='|'){
               countLex++;
               String lex = str.substring(0,i);
               if (lex.startsWith("(")&&lex.endsWith(")"))lex = lex.substring(1,lex.length()-1);
               moves.add(new Move(move.start,move.end,lex));
               str = str.substring(i+1);
               i=-1;
           }
       }

        if (moves.size()==0){
            count = 0;
            str = new String(move.lexem);
            for (int i = 0;i<str.length();i++){
                if (str.charAt(i)=='('){
                    count++;
                }
                if (str.charAt(i)==')'){
                    count--;
                }
                if (count==0 && str.charAt(i)=='.'){
                    countLex++;
                    String lex = str.substring(0,i);
                    if (lex.startsWith("(")&&lex.endsWith(")"))lex = lex.substring(1,lex.length()-1);
                    moves.add(new Move(move.start,next()+1,lex));
                    str = str.substring(i+1);
                    if (str.startsWith("(")&&str.endsWith(")")&&countLex!=0)str= str.substring(1,str.length()-1);
                    moves.add(new Move(next()+1,move.end,str));
                }
            }

            if (countLex==0){
                count = 0;
                str = new String(move.lexem);
                for (int i = 0;i<str.length();i++){
                    if (str.charAt(i)=='('){
                        count++;
                    }
                    if (str.charAt(i)==')'){
                        count--;
                    }
                    if (count==0 && str.charAt(i)=='*'){
                        countLex++;
                        String lex = str.substring(0,i);
                        if (lex.startsWith("(")&&lex.endsWith(")"))lex = lex.substring(1,lex.length()-1);
                        moves.add(new Move(move.start,next()+1,"e"));
                        moves.add(new Move(next()+1,move.end,"e"));
                        moves.add(new Move(next()+1,next()+1,lex));
                    }
                }
            }

        }else{
            if (str.startsWith("(")&&str.endsWith(")")&&countLex!=0)str= str.substring(1,str.length()-1);
            moves.add(new Move(move.start,move.end,str));
        }


       return moves;
    }

/*    public static String makeLexems(String lex){
        int count = 0;
        for (int i =0;i<lex.length();i++){
            if (lex.charAt(i)=='('){
                count++;
            }else
            if (lex.charAt(i)==')'){
                count--;
            }else
            if (count!=0){
                lex = lex.substring(0,i)+ " " +lex.substring(i+1);
            }
        }
        return lex.replaceAll(" ","");

    }*/
}
