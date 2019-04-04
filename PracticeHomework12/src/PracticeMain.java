import acm.program.ConsoleProgram;


public class PracticeMain extends ConsoleProgram {
    public void run(){
        this.setSize(600,500);
        this.setFont(("SansSerif-22"));
        while (true){
            int i = readInt("Input tusk number:" );
            switch (i){
                case 0:
                    return;
                case 1:
                //   removeAll();
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
                  //  removeAll();
                    println("За понеділком іде вівторок тощо, а за неділею – понеділок. Написати функцію обчислення за днем тижня (типу Weekd) наступного за ним дня.");
                    task3();
                    break;
                case 4:
                  //  removeAll();
                    println("Написати \"найпростіший калькулятор\", що отримує на вхід 2 числа і операцію над ними після чого повертає результат обчислень.");
                    task4();
                    break;
                case 5:
                  //  removeAll();
                    println("Написати процедуру обчислення за цілим N>3 таких натуральних A і B, що 5A-2B=N, причому A+B мінімально.");
                    task5();
                    break;
                default:
                  //  removeAll();
                    break;
            }
        }

    }

    /**
     * Написати программу, що робить перевірку, чи є значення символьної змінної, що введена з клавіатури:
     * цифрою від '0' до '9';
     * малою латинською літерою;
     * латинською літерою (великою чи малою)
     */
    public void task1(){
        println();
            String str = readLine("Input a char: ");
            while (str.length()!=1){
                str = readLine("Input a char: ");
            }
            char ch = str.charAt(0);
            if (ch>=48 && ch<=57){
                println(ch +" is a number");
                println(ch +" isn't a small letter");
                println(ch +" isn't a big letter");
            }else if (ch>=65 && ch<=90){
                println(ch +" isn't a number");
                println(ch +" isn't a small letter");
                println(ch + " is a big letter");
            }else if (ch>=97 && ch<=122){
                println(ch +" isn't a number");
                println(ch + "  is a small letter");
                println(ch + " isn't a big letter");
            }else println(ch +" isn't a letter or a number");

    }

    /**
     * У розкладі рейсів літаків дні тижня позначаються номерами від 1 до 7.
     * Припустимо, що в програмі дні тижня подаються enum типом Weekd.
     * Написати програму, що у відповідь на введення номера дня виводить текстове подання дня тижня.
     */
    public void task2(){
        println();
       int i = readInt("Input day number (1 to 7): ");
       while (i<1 || i>7){
           i = readInt("Input day number (1 to 7): ");
       }
       switch (i){
           case 1:
               println(Weekd.monday);
               break;
           case 2:
               println(Weekd.tuesday);
               break;
           case 3:
               println(Weekd.wednesday);
               break;
           case 4:
               println(Weekd.thursday);
               break;
           case 5:
               println(Weekd.friday);
               break;
           case 6:
               println(Weekd.saturday);
               break;
           case 7:
               println(Weekd.sunday);
               break;
       }
    }

    /**
     * За понеділком іде вівторок тощо, а за неділею – понеділок.
     * Написати функцію обчислення за днем тижня (типу Weekd) наступного за ним дня.
     */
    public void task3(){
        println();
        String i = readLine("Input day number (1 to 7) or day: ");
        try{
           int y = Integer.parseInt(i);
            switch (y){
                case 7:
                    println("Next day is "+ Weekd.monday);
                    break;
                case 1:
                    println("Next day is "+ Weekd.tuesday);
                    break;
                case 2:
                    println("Next day is "+Weekd.wednesday);
                    break;
                case 3:
                    println("Next day is "+Weekd.thursday);
                    break;
                case 4:
                    println("Next day is "+Weekd.friday);
                    break;
                case 5:
                    println("Next day is "+Weekd.saturday);
                    break;
                case 6:
                    println("Next day is "+Weekd.sunday);
                    break;

                    default:
                        println("Input isn`t correct");
                        return;
            }
        }catch (Exception e){
          i = i.toLowerCase();
          switch (i){
              case "sunday":
                  println("Next day is "+ Weekd.monday);
                  break;
              case "monday":
                  println("Next day is "+ Weekd.tuesday);
                  break;
              case "tuesday":
                  println("Next day is "+Weekd.wednesday);
                  break;
              case "wednesday":
                  println("Next day is "+Weekd.thursday);
                  break;
              case "thursday":
                  println("Next day is "+Weekd.friday);
                  break;
              case "friday":
                  println("Next day is "+Weekd.saturday);
                  break;
              case "saturday":
                  println("Next day is "+Weekd.sunday);
                  break;

              default:
                  println("Input isn`t correct");
                  return;
          }
        }

    }

    /**
     * Написати "найпростіший калькулятор",
     * що отримує на вхід 2 числа і операцію над ними після чого повертає результат обчислень.
     */
    public void task4(){
        println();
        String str = readLine("Input your expression: ");
        try{
            if (str.contains("+")){
                String s = str.substring(0,str.indexOf('+'));
                String a = str.substring(str.indexOf('+')+1,str.length());
                double res = Double.parseDouble(s)+Double.parseDouble(a);
                println(res);
            }else if (str.contains("*")){
                String s = str.substring(0,str.indexOf('*'));
                String a = str.substring(str.indexOf('*')+1,str.length());
                double res = Double.parseDouble(s)*Double.parseDouble(a);
                println(res);
            } else     if (str.contains("-")){
                String s = str.substring(0,str.indexOf('-'));
                String a = str.substring(str.indexOf('-')+1,str.length());
                double res =Double.parseDouble(s)-Double.parseDouble(a);
                println(res);
            } else      if (str.contains("/")){
                String s = str.substring(0,str.indexOf('/'));
                String a = str.substring(str.indexOf('/')+1,str.length());
                if (a.matches("0")){
                    println("impossible statement");
                    return;
                }
                double res = Double.parseDouble(s)/Double.parseDouble(a);
                println(res);
            }
        }catch (Exception e){
            println("impossible statement");
            return;
        }

    }

    /**
     * Написати процедуру обчислення за цілим N>3 таких натуральних A і B,
     * що 5A-2B=N, причому A+B мінімально.
     */
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

    enum Weekd{monday,tuesday,wednesday,thursday,friday,saturday,sunday};
}
