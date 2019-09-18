import acm.program.ConsoleProgram;

import java.io.*;

public class Main extends ConsoleProgram {
    private static final String GLOBAL_PATH = "C:\\Users\\yanki\\IdeaProjects\\UniversityTasks\\UniversityTasks\\Practice13\\";

    public void run(){
        this.setSize(600,400);
        this.setFont("SansSherif-24");
        while (true){

            int choice = readInt("Input task number (1 to 4): ");
            this.getConsole().clear();
            switch (choice){
                case 1: {
                    println("Великі числа при друку прийнято розділяти комами. Так наприклад число один мільйон при друку має виглядати - 1,000,000. Напишіть програму, що на вхід приймає число (саме число, INT), а на вихід виводить число в наведеному вище форматі. Числа мають зчитуватися до тих пір, поки користувач не введе 0. String.valueOf використовувати не можна.");
               separateInt(readInt("Input int: "));
                    println();
                    break;
                }
                case 2:{
                    try {
                        println("Написати клас який реалізує метод, що видаляє заданий символ з стрічки і повертає результат: \n" +
                                "public String removeAllOccurences(String str, char ch);");
                        println(removeAllOccurences(readLine("Input your String: "), stringToChar(readLine("Input your char: "))));
                    } catch (Exception e) {
                        println("Input was incorrect");
                    }
                    println();
                    break;
                }
                case 3:{
                    try {
                        println("Написати програму, що читає текстову інформацію з файлу і виводить на екран");
                        println(fileOutput(readLine("Input file name: ")));
                    } catch (IOException e) {
                        println("Input was incorrect");
                    }
                    println();
                    break;
                }
                case 4:{
                    try {
                        println("Написати програму, що шукає в тексті файлу задану фразу і виводить інформацію про її наявність");
                        println(findPhraseInFile(readLine("Input file name: "),readLine("Input phrase: ")));
                    } catch (IOException e) {
                        println("Input was incorrect");
                    }
                    println();
                    break;
                }
                case 0:return;

            }
        }
    }

    /**
     *Великі числа при друку прийнято розділяти комами. Так наприклад число один мільйон при друку має виглядати - 1,000,000. Напишіть програму, що на вхід приймає число (саме число, INT), а на вихід виводить число в наведеному вище форматі. Числа мають зчитуватися до тих пір, поки користувач не введе 0. String.valueOf використовувати не можна.
     * @param number
     * @return
     */
    public String separateInt(int number){
        String res = "";
        while (number!=0){
            int count = 0;
            while (number%10!=number){
                res=number%10+res;
                number/=10;
                count++;
                if (number%10<0){
                    res = res.replace("-", "");
                }
                if (count%3==0){
                    res=","+res;
                }

            }
            res=number%10+res;
            count++;
            println(res);
            res = "";
            number=readInt("Input your int: ");
        }
return null;
    }

    /**
     * Написати клас який реалізує метод, що видаляє заданий символ з стрічки і повертає результат:
     * @param str
     * @param ch
     * @return
     */
    public String  removeAllOccurences(String str, char ch){
        if (ch=='.'){
            return str.replaceAll("\\.","");
        } else
        return str.replaceAll(String.valueOf(ch),"");
    }

    /**
     * convert stringt\ToChar;
     * @param string
     * @return
     * @throws Exception
     */
    public char stringToChar(String string) throws Exception {
        if (string.length()==1){
            return string.charAt(0);
        } else throw new Exception();

    }

    /**
     * Написати програму, що читає текстову інформацію з файлу і виводить на екран
     * @param filename
     * @return
     * @throws IOException
     */
    public String fileOutput(String filename) throws IOException {
        String globalPath = GLOBAL_PATH;
        File file = new File(globalPath + filename);
       FileReader fileReader = new FileReader(file);
        BufferedReader bf = new BufferedReader(fileReader);
        String res = "";
        String line = bf.readLine();
        while (line!=null){
            res+=line + "\n";
            line = bf.readLine();
        }
        return res;
    }

    /**
     * Написати програму, що шукає в тексті файлу задану фразу і виводить інформацію про її наявність
     * @param filename
     * @param phrase
     * @return
     * @throws IOException
     */
    public String findPhraseInFile(String filename, String phrase) throws IOException {
        String fileS = fileOutput(filename);
        int count = 0;
        int lastIndex = -1;
        System.out.println(fileS);
        while (fileS.indexOf(phrase, lastIndex+1)!=-1){
            System.out.println(fileS);
            System.out.println(lastIndex);
            lastIndex =fileS.indexOf(phrase, lastIndex+1);
            count++;
        }
       return  "phrase \""+phrase+"\" was found in this file " + count + " times";
    }
}
