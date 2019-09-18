package Kurenkova;

import java.util.ArrayList;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
//Побудувати клас Algorithm, що реалізує модель обчислень – Нормальні алгоритми Маркова (НАМ). Клас містить:
//Внутрішній клас Rule (представлення одної підстановки) з полями
//-Pattern left – ліва частина підстановки,
//-String right – права частина підстановки,
//-boolean end – ознака заключної підстановки.
//Поле ArrayList <Rule> base – задає список підстановок.
//Конструктор Algorithm (String desc) котрий за рядком desc, що задає список підстановок, будує список base,
// якщо рядок синтаксично вірний, або покладає base рівним null в іншому випадку.
//Рядок desc – це список підстановок, що розділяються символом ‘;’.
// Кожна підстановка це два слова, ліва і права частини підстановки, між якими  знаходиться одне з слів “->” або “->.”.
// Ліва і права частина підстановки – слова, в тому числі порожні,  що містять символи латинського алфавіту, десяткові цифри і символ ‘#’.
// В довільному місці крім слів дозволяється вживати проміжки.
//Метод String eval(String input, int cnt), що виконує алгоритм на слові input і повертає результат. Якщо виконавши cnt підстановок, алгоритм не зупинився, то вважаємо результат “undefined”
//Метод boolean isGood() повертає значення true, якщо екземпляр класу задає коректний алгоритм (base != null) і false в іншому випадку (base==null).

public class Algorithm {
    ArrayList<Rule> base = new ArrayList<Rule>(); //задає список підстановок.

    class Rule {
        Pattern left; //лва частина підстановки
        String right; //права частина підстановки,
        boolean end = false; //ознака заключної підстановки.

        @Override
        public String toString() {
            return "Rule: " + left + "->" + right + " end - " + end;
        }
    }

    Algorithm(String desc) {
        addToBase(desc);
    }

    boolean isGood() {
        if (base != null) return true;
        return false;
    }

    private void addToBase(String desc) {
        if (desc.isEmpty()) base = null;//Checks whether desc is an empty word
        String[] temp = desc.split("[;]");
        for (int i = 0; i < temp.length; i++) {
            if (temp[i] == null) {
                base = null;
                System.out.println("Wrong algorithm");
                break;
            }
            if (!temp[i].isEmpty()) {
                if (convertToRule(temp[i]) == null) {
                    base = null;
                    System.out.println("Wrong algorithm");
                    break;
                } else base.add(convertToRule(temp[i]));
            }
        }
    }

    private Rule convertToRule(String desc) {
        Rule rule = new Rule();
        String reg = "\\s*([\\w#]*)(->\\.?)([\\w#]*)\\s*";
        Pattern ip = Pattern.compile(reg);
        Matcher im = ip.matcher(desc);
        if (im.matches()) {
            rule.left = Pattern.compile(im.group(1));
            String end = im.group(2);
            if (end.equals("->.")) rule.end = true;
            rule.right = im.group(3);
            return rule;
        }
        return null;
    }

    String eval(String input, int cnt) {
        if (isGood()) {
            String temp = input;
            for (int i = 0; i < base.size(); i++) {
                Matcher mch = base.get(i).left.matcher(temp);
                boolean isFound = mch.find();
                if (isFound) {
                    temp = mch.replaceFirst(base.get(i).right);
                    if (base.get(i).end) break;
                    if (cnt <= 0) return "undefined";
                    i = -1;//moves to the start of the for loop, start of base (ArrayList)
                    cnt--;
                }
            }
            return temp;
        } else return "Coundn't convert because of a wrong algorithm";
    }
}
