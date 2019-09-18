import acm.program.ConsoleProgram;
/*
Написати програму, що зчитує з клавіатури стрічку і повертає (кожен пункт оформити у вигляді методу)

кількість слів (в слові можуть зустрічатися: тільки літери; тільки символи; літери+символи+цифри; літери+символи; літери+цифри; символи(окрім крапки та коми)+цифри; якщо відділені пропусками символи цифри, або цифри та кома чи крапка, то даний ланцюжок є числом)
кількість чисел
стрічку в якій прибрані усі символи крім літер
стрічку в якій множинні проміжки перетворені в один проміжок ("А   пр" ="А пр")
стрічку яка містить лише слова, що починаються з великої літери ("А   пр" ="А")
 */
public class HomeworkMain extends ConsoleProgram {
    public void run(){
        this.setSize(600,500);
        this.setFont(("SansSerif-22"));
        String str = readLine("Input your string: ");
        wordsNumber(str);
        deleteSymbols(str);
        deleteSmallWords(str);
        deleteSpaces(str);
        while(!str.matches("")){
            str = readLine("Input your string: ");
            wordsNumber(str);
            deleteSymbols(str);
            deleteSmallWords(str);
            deleteSpaces(str);
        }
    }


    /**
     *output number of words and numbers
     * @param str
     */
    public void wordsNumber(String str){
        int count = 0;
        int wCount = 0;
        while (str.contains(" ")){
            wCount++;
           String s = str.substring(0,str.indexOf(" "));

           str = str.substring(str.indexOf(" ")+1,str.length());
            if (s.equals(" ")|| s.length()==0)continue;
            int countY = 0;
           for (int i = 0; i<s.length();i++){
               if (s.charAt(i)<=47||s.charAt(i)>=58){
                   if(s.charAt(i)!=44&&s.charAt(i)!=46){
                       count++;
                       break;
                   } else countY++;

               }
           }
           if (countY>1){
               count++;
           }
        }
        int countY = 0;
        if (str.equals(" ")|| str.length()==0){

        }else{
            wCount++;
        for (int i = 0; i<str.length();i++){
            if (str.charAt(i)<=47||str.charAt(i)>=58){
                if(str.charAt(i)!=44&&str.charAt(i)!=46){
                    count += 1;
                    break;
                }else countY++;
            }
        }
        if (countY>1){
            count++;
        }

    } println("Words number: " +count);
        println("Numbers number: " +(wCount-count));
    }

    /**
     *output only words starts with big letter
     * @param str
     */
    public void deleteSmallWords(String str){
        String[] strin = str.split(" ");
        String res = "";
        for (int i = 0;i<strin.length;i++){
            if (strin[i].length()>0){
                if (strin[i].charAt(0)>=65&&strin[i].charAt(0)<=90){
                    res = res + strin[i] + " ";
                }
            }

        }
        println("Line with words from big letter: " + res);
    }

    /**
     * output line with only one space beetween words and numbers
     * @param str
     */
    public void deleteSpaces(String str){
        String[] strin = str.split(" ");
        String res = "";
        for (int i = 0;i<strin.length;i++){
            if (strin[i].length()>0){
                res = res + strin[i] + " ";
            }

        }
        println("Line without useless spaces:" +res);
    }

    /**
     * output only letters
     * @param str
     */
    public void deleteSymbols(String str){
        str = str.replaceAll("\\.","");
        int num = str.length();
        for (int i = 0; i<num;i++){
            if ((str.charAt(i)>=65&&str.charAt(i)<=90)||(str.charAt(i)>=97&&str.charAt(i)<=122)){

            }else {
              // if (str.charAt(i)==32) continue;
                str = str.replaceFirst(String.valueOf(str.charAt(i)),"");
                num--;
                i--;
            }
        }
        println("Line without any symbols: " +str);
    }

}
