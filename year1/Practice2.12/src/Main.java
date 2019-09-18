import java.util.Scanner;

class Factorial{

    public static int getFactorial(int num) throws FactorialException{

        int result=1;
        if(num<0) throw new FactorialException("The number is less than 1", num);

        if (num==0) return result;
        for(int i=1; i<=num;i++){

            result*=i;
        }
        return result;
    }
}

class Inp{
     static Integer getInt(){
        Scanner scanner = new Scanner(System.in);
         int a = 0;
         try {
             String s = scanner.nextLine();
             try{
                 a = Integer.parseInt(s);
             }catch (NumberFormatException e){
                 throw new IntException("input is string", s);
             }

            if (Character.getNumericValue(s.charAt(s.length()-1))%2!=0)throw new IntException("input not divide by 2",s);

            return a;
        } catch (IntException e){
            System.out.println(e.getMessage());
            return 1;
        }
    }
}

class FactorialException extends Exception{
    private int number;
    public int getNumber(){return number;}
    public FactorialException(String message, int num){
        super(message);
        number=num;
    }
}
class IntException extends NumberFormatException{
    private String string;
    public String getS(){return string;}
    public IntException(String message, String s){
        super(message);
        string=s;
    }
}

public class Main{
    public static void main(String[] args){

        try{
            int result = Factorial.getFactorial(Inp.getInt());
            System.out.println(result);
        }
        catch(FactorialException ex){
            System.out.println(ex.getMessage());
        }
    }
}