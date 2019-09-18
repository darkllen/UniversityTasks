package Kurenkova;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Exercises {

    //1.Побудувати метод static boolean onlyOdd(String str),
// котрий повертає значення true тоді і тільки тоді,
// коли рядок str містить лише символи a і b,
// при цьому і один і другий символ в непарній кількості.

 public    static boolean onlyOdd(String str) {
        if (!str.matches("[ab]+")) return false;
        int a = 0, b = 0;
        for (String subStr : str.split("[^a]+")) a += subStr.length();//Check number of "a"
        if (a % 2 == 0) return false;
        for (String subStr : str.split("[^b]+")) b += subStr.length();//Check number of "b"
        if (b % 2 == 0) return false;
        return true;
    }

    //2.Ідентифікатор – це неперервна послідовність символів,
// що може містити букви латинського алфавіту, цифри та символ підкреслення (_) і починається з букви.
// Побудувати наступні статичні  методи:
//  - static boolean isIden(String str) – перевіряє, що str є ідентифікатор.
//  - static int cntIden(String str) – підраховує скільки ідентифікаторів є в рядку str .
//  - static int distIden(String str) - підраховує скільки різних ідентифікаторів є в рядку str.
  public   static boolean isIden(String str) {
        return str.matches("[A-Za-z]\\w*");
    }

  public   static int cntIden(String str) {
        if (str.isEmpty()) return 0;//Checks whether str is empty word
        String[] array = str.split("(\\W[^A-Za-z]*)");
        if (array.length > 1) if (array[0].isEmpty()) return array.length - 1;
        return array.length;
    }

   public static int distIden(String str) {
        if (str.isEmpty()) return 0;//Checks whether str is empty word
        String[] array = str.split("(\\W[^A-Za-z]*)");
        int id = 1;//Number of unique identifiers
        for (int j = 0; j < array.length; j++) {//for loop for array length
            for (int i = j + 1; i < array.length; i++) {//for loop for array length - j, starting from element j
                if (array[j].equals(array[i]))
                    break; //if element j is equal to one of the elements in the rest of the massive, break (miss it)
                if (i + 1 == array.length)
                    id++;//if for loop comes to the end, that means that no coincidences were found and element j is unique
            }
        }
        if (array.length > 1) if (array[0].isEmpty())
            return id - 1;//If the first word == empty word, number of unique identifiers is decreased by 1,
        // because empty word is not an identifier
        if (array.length < 1) return 0;
        return id;
    }

    //3.Дійсне число, можливо зі знаком, має вид ddd.ddddd, +dd.ddd  або -dddd.dddd.
// Рядок str містить послідовність дійсних чисел, що розділяються символом кома (,).
// Дозволяється вживати символи проміжку в довільному місці крім числа.
// Побудувати метод static Double sumDouble(String str),
// котрий в рядку str розпізнає дійсні числа і повертає їх суму.
// Якщо рядок str синтаксично некоректний, то повертається null.  [\+-]?\d+\.?\d*
    static Double sumDouble(String str) {
        Double a = 0.0;
        if (str.isEmpty()) return null;//Checks whether str is empty word
        String[] array = str.split("[,]");
        for (int i = 0; i < array.length; i++) {
            if (array[i] != null && !array[i].isEmpty()) {
                if (parseDouble(array[i]) == null) return null;
                else a += parseDouble(array[i]);
            }
        }
        return a;
    }

    static private Double parseDouble(String s) {
        Double r = null;
        String reg = "\\s*([+-])?(\\d+\\.?\\d*)\\s*";
        Pattern ip = Pattern.compile(reg);
        Matcher im = ip.matcher(s);
        if (im.matches()) {
            r = Double.parseDouble(im.group(2));
            String sign = im.group(1);
            if ((sign != null) && (sign.charAt(0) == '-')) r *= (-1);
        }
        return r;
    }

//Побудувати клас Algorithm, що реалізує модель обчислень – Нормальні алгоритми Маркова (НАМ). Клас містить:
//Внутрішній клас Rule (представлення одної підстановки) з полями
//-Pattern left – ліва частина підстановки,
//-String right – права частина підстановки,
//-boolean end – ознака заключної підстановки.
//Поле ArrayList <Rule> base – задає список підстановок.
//Конструктор Algorithm (String desc) котрий за рядком desc, що задає список підстановок, будує список base, якщо рядок синтаксично вірний, або покладає base рівним null в іншому випадку.
//Рядок desc – це список підстановок, що розділяються символом ‘;’. Кожна підстановка це два слова, ліва і права частини підстановки, між якими  знаходиться одне з слів “->” або “->.”. Ліва і права частина підстановки – слова, в тому числі порожні,  що містять символи латинського алфавіту, десяткові цифри і символ ‘#’. В довільному місці крім слів дозволяється вживати проміжки.
//Метод String eval(String input, int cnt), що виконує алгоритм на слові input і повертає результат. Якщо виконавши cnt підстановок, алгоритм не зупинився, то вважаємо результат “undefined”
//Метод boolean isGood() повертає значення true, якщо екземпляр класу задає коректний алгоритм (base != null) і false в іншому випадку (base==null).

    public static void main(String[] args) {
        //Testing exercise 1
        System.out.println("Testing onlyOdd():");
        System.out.println(onlyOdd("bba"));
        System.out.println(onlyOdd("ab"));
        System.out.println(onlyOdd(""));
        System.out.println(onlyOdd("abbbabab"));
        System.out.println(onlyOdd("ab aabbab a b"));
        //Testing exercise 2
        System.out.println("Testing isIden():");
        System.out.println(isIden("Some_Input0986"));
        System.out.println(isIden("_wrongIdentifier_555"));
        System.out.println(isIden(""));
        System.out.println(isIden("457Some text"));
        System.out.println(isIden("475Another_test745"));
        System.out.println("Testing cntIden() and distIden():");
        System.out.println(cntIden("aaaaab !@# Asd 54aaaaab ASD @$Asd 123asdfgae asd ^afghe asdfghrte ghtr do do do do"));//14
        System.out.println(distIden("aaaaab !@# Asd 54aaaaab ASD @$Asd 123asdfgae asd ^afghe asdfghrte ghtr do do do do"));//9
        System.out.println(cntIden("_wrongIdentifier_555 _wrongIdentifier_555 Some_Input0986, Some_Input0984t,    Some_Input0986,^^%Some_Input0986"));//6
        System.out.println(distIden("_wrongIdentifier_555 _wrongIdentifier_555 Some_Input0986, Some_Input0984t,    Some_Input0986,^^%Some_Input0986"));//4
        //Testing exercise 3
        System.out.println("Testing sumDouble():");
        System.out.println(sumDouble("1.1, -0.4,  0,   +0.3, 2, 7."));//3.0
        System.out.println(sumDouble(""));//null
        System.out.println(sumDouble("1.1, -0.3tfdg,  ,  +0.3, 2.2"));//wrong syntax -> null
        System.out.println(sumDouble("1.1, 0,  ,  +0.3, 2.2"));//wrong syntax -> null
        //Testing exercise 4
        Algorithm add3 = new Algorithm("b->a;1->_");//f(x, y)=y+3; (x,y) -> 1..x..1#1..y..1
        System.out.println("Result: " + add3.eval("111", 30));//6=111111
        Algorithm div3 = new Algorithm("a#->1a; a->.;111->#; 1-> ;;  ->a;");//f(x)=x/3; x->1..x..1
        System.out.println("Result: " + div3.eval("1111111", 30));//2=11
        Algorithm undefined = new Algorithm("->   ");//f()=undefined
        System.out.println("Result: " + undefined.eval("a", 30));//undefined
    }
}