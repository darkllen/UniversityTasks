import acm.program.ConsoleProgram;

public class Main extends ConsoleProgram
{
    public void run(){
        this.setSize(600,500);
        //this.setFont(Font.getFont("SansSerif-22"));
       // while (true){
       /*     int i = readInt("Input tusk number:" );
            switch (i){
                case 0:
                    return;
                case 1:
                    //   this.getConsole().removeAll();
                    println("Написати программу, що робить перевірку, чи є значення символьної змінної, що введена з клавіатури: \n" +
                            "цифрою від '0' до '9';\n" +
                            "малою латинською літерою;\n" +
                            "латинською літерою (великою чи малою)");
                    task1();
                    break;
                case 2:
                    //  removeAll();
                    println("У розкладі рейсів літаків дні тижня позначаються номерами від 1 до 7. Припустимо, що в програмі дні тижня подаються enum типом Weekd. Написати програму, що у відповідь на введення номера дня виводить текстове подання дня тижня.");
                    task2();
                    break;
                case 3:
                    //removeAll();
                    println("За понеділком іде вівторок тощо, а за неділею – понеділок. Написати функцію обчислення за днем тижня (типу Weekd) наступного за ним дня.");
                    task3();
                    break;
                case 4:
                    //removeAll();
                    println("Написати \"найпростіший калькулятор\", що отримує на вхід 2 числа і операцію над ними після чого повертає результат обчислень.");
                    task4();
                    break;
                case 5:
                    //removeAll();
                    println("Написати процедуру обчислення за цілим N>3 таких натуральних A і B, що 5A-2B=N, причому A+B мінімально.");
                    task5();
                    break;
                default:
                    //removeAll();
                    break;
            }*/
        }}

 //   }
 /*   public void task1(){
        println();
        String str = readLine("Input a char: ");
        while (str.length()!=1){
            str = readLine("Input a char: ");
        }
        char ch = str.charAt(0);
        if (ch>=48 && ch<=57){
            println(ch +" is a number");
        }else if (ch>=65 && ch<=90){
            println(ch + " is a big letter");
        }else if (ch>=97 && ch<=122){
            println(ch + "  is a small letter");
        }else println(ch +" isn't a letter or a number");

    }
    public void task2(){
        println();
        int i = readInt("Input day number (1 to 7): ");
        while (i<1 || i>7){
            i = readInt("Input day number (1 to 7): ");
        }
        switch (i){
            case 1:
                println(Weekd.Monday);
                break;
            case 2:
                println(Weekd.Tuesday);
                break;
            case 3:
                println(Weekd.Wednesday);
                break;
            case 4:
                println(Weekd.Thursday);
                break;
            case 5:
                println(Weekd.Friday);
                break;
            case 6:
                println(Weekd.Saturday);
                break;
            case 7:
                println(Weekd.Sunday);
                break;
        }
    }
    public void task3(){
        println();
        int i = readInt("Input day number (1 to 7): ");
        while (i<1 || i>7){
            i = readInt("Input day number (1 to 7): ");
        }
        switch (i){
            case 7:
                println("Next day is "+ Weekd.Monday);
                break;
            case 1:
                println("Next day is "+ Weekd.Tuesday);
                break;
            case 2:
                println("Next day is "+Weekd.Wednesday);
                break;
            case 3:
                println("Next day is "+Weekd.Thursday);
                break;
            case 4:
                println("Next day is "+Weekd.Friday);
                break;
            case 5:
                println("Next day is "+Weekd.Saturday);
                break;
            case 6:
                println("Next day is "+Weekd.Sunday);
                break;
        }
    }
    public void task4(){
        println();
        String str = readLine("Input your expression: ");
        try{
            if (str.contains("+")){
                String s = str.substring(0,str.indexOf('+'));
                String a = str.substring(str.indexOf('+')+1,str.length());
                double res = Double.parseDouble(s)+Double.parseDouble(a);
                print(res);
            }else if (str.contains("*")){
                String s = str.substring(0,str.indexOf('*'));
                String a = str.substring(str.indexOf('*')+1,str.length());
                double res = Double.parseDouble(s)*Double.parseDouble(a);
                print(res);
            } else      if (str.contains("-")){
                String s = str.substring(0,str.indexOf('-'));
                String a = str.substring(str.indexOf('-')+1,str.length());
                double res =Double.parseDouble(s)-Double.parseDouble(a);
                print(res);
            } else      if (str.contains("/")){
                String s = str.substring(0,str.indexOf('/'));
                String a = str.substring(str.indexOf('/')+1,str.length());
                double res = Double.parseDouble(s)/Double.parseDouble(a);
                print(res);
            }
        }catch (Exception e){
            return;
        }

    }
    public void task5(){
        println();
        double i = readInt("Input N: ");
        while (i<=3){
            i = readInt("Input N: ");
        }
        double b = 1;
        while (true){
            int a = (int) ((i + 2*b)/5);
            double c = (i + 2*b)/5;
            System.out.println(a);
            System.out.println(c);
            if (c==a){
                println("A min = "+a + "; B min = " + (int)b);
                return;
            }
            b++;
        }
    }

    enum Weekd{Monday,Tuesday,Wednesday,Thursday,Friday,Saturday,Sunday};
}*/
