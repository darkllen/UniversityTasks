import acm.program.ConsoleProgram;

import java.io.*;

public class Main extends ConsoleProgram {
   private static final String GLOBAL_PATH = "C:\\Users\\yanki\\IdeaProjects\\UniversityTasks\\UniversityTasks\\Practice13\\";

    public void run(){
        this.setSize(600,400);
        this.setFont("SansSherif-24");
        while (true){

            int choice = readInt("Input task number (1 to 5): ");
            this.getConsole().clear();
            switch (choice){
                case 1: {
                    try {println("Написати програму, що замінює підстрічку в файлі заданою стрічкою");
                        findPhraseInFile(readLine("Input file name: "),readLine("Input phrase: "),readLine("Input new phrase: "));
                    } catch (IOException e) {
                        println("Input was incorrect");
                    }
                    println();
                    break;
                }
                case 2:{
                    try {
                        println("Написати програму, що копіює зміст тектового файлу в інший з розширення \".bak\". Назва файлу вводиться користувачем.");
                   copyFile(readLine("Input file name: "),readLine("Input new file name: "));
                    } catch (Exception e) {
                        println("Input was incorrect");
                    }
                    println();
                    break;
                }
                case 3:{
                    try {
                        println("Написати програму, що відкриває файл на читання і формує два інших файли. Перший файл формується з непарних стрічок початкового файлу, а інший з парних.");
                        copyFileInTwo(readLine("Input file name: "));
                    } catch (IOException e) {
                        println("Input was incorrect");
                    }
                    println();
                    break;
                }
                case 4:{
                    try {
                        println("Здійснити шифрування файлу використовуючи зсув символів.");
                   secureFile(readLine("Input file name: "), readInt("Input key number: "));
                    } catch (IOException e) {
                        println("Input was incorrect");
                    }
                    println();
                    break;
                }
                case 5:{
                    try {
                        println("Здійснити розшифрування файлу зашифрованого вище");
                        unSecureFile(readLine("Input file name: "), readInt("Input key number: "));
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
     * Написати програму, що читає текстову інформацію з файлу і виводить на екран
     * @param filename
     * @return
     * @throws IOException
     */
    public String fileOutput(String filename) throws IOException {
        File file = new File(GLOBAL_PATH + filename);
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
     * Написати програму, що замінює підстрічку в файлі заданою стрічкою
     * @param filename
     * @param phrase
     * @param phraseInstead
     * @return
     * @throws IOException
     */
    public String findPhraseInFile(String filename, String phrase, String phraseInstead) throws IOException {
        String fileS = fileOutput(filename);
        int count = 0;
        System.out.println(fileS);
        FileWriter fr = new FileWriter(GLOBAL_PATH +filename);
        BufferedWriter bf = new BufferedWriter(fr);
        int ind = 0;
        String r = "";
        while (fileS.indexOf(phrase)!=-1){
            System.out.println(fileS);

          r += fileS.substring(0,fileS.indexOf(phrase));
          r +=phraseInstead;
            fileS= fileS.substring(fileS.indexOf(phrase)+phrase.length(),fileS.length());
            count++;
        }
        r+=fileS;
        String[] ops = r.split("\n");
        for (int i = 0;i<ops.length;i++){
            bf.write(ops[i]);
            bf.newLine();
        }
        bf.close();
        return  "phrase \""+phrase+"\" was found in this file " + count + " times";
    }

    /**
     * Написати програму, що копіює зміст тектового файлу в інший з розширення ".bak". Назва файлу вводиться користувачем.
     * @param filename
     * @param newFileName
     * @throws IOException
     */
    public void copyFile(String filename, String newFileName) throws IOException {
        String fileS = fileOutput(filename);
        String[] ops = fileS.split("\n");
        File file = new File(GLOBAL_PATH + newFileName +".bak");
        FileWriter fr = new FileWriter(file);
        BufferedWriter bf = new BufferedWriter(fr);

        for (int i = 0;i<ops.length;i++){
            bf.write(ops[i]);
            bf.newLine();
        }
        bf.close();
    }

    /**
     * Написати програму, що відкриває файл на читання і формує два інших файли. Перший файл формується з непарних стрічок початкового файлу, а інший з парних.
     * @param filename
     * @throws IOException
     */
    public void copyFileInTwo(String filename) throws IOException {
        File file = new File(GLOBAL_PATH + filename);
        FileReader fileReader = new FileReader(file);
        BufferedReader bf = new BufferedReader(fileReader);
        int count = 0;
        FileWriter wr1= new FileWriter(GLOBAL_PATH+"1.txt");
        FileWriter wr2 = new FileWriter(GLOBAL_PATH+"2.txt");
        BufferedWriter bf1 = new BufferedWriter(wr1);
        BufferedWriter bf2 = new BufferedWriter(wr2);
        String line = bf.readLine();
        while (line!=null){

            if (count%2==0){
                bf1.write(line);
                bf1.newLine();
            }else {
                bf2.write(line);
                bf2.newLine();
            }
            line = bf.readLine();
            count++;
        }
        bf1.close();
        bf2.close();
    }

    /**
     * Здійснити шифрування файлу використовуючи зсув символів.
     * @param filename
     * @param key
     * @throws IOException
     */
    public void secureFile(String filename, int key) throws IOException {
        String fl = fileOutput(filename);
        FileWriter wr1= new FileWriter(GLOBAL_PATH+filename);
        BufferedWriter bf1 = new BufferedWriter(wr1);
        String[] ops = fl.split("\n");
        for (int i = 0;i<ops.length;i++){
          bf1.write(encrypt(ops[i],key));
            bf1.newLine();
        }
        bf1.close();
    }

    /**
     * Здійснити розшифрування файлу зашифрованого вище
     * @param filename
     * @param key
     * @throws IOException
     */
    public void unSecureFile(String filename, int key) throws IOException {
        String fl = fileOutput(filename);
        FileWriter wr1= new FileWriter(GLOBAL_PATH+filename);
        BufferedWriter bf1 = new BufferedWriter(wr1);
        String[] ops = fl.split("\n");
        for (int i = 0;i<ops.length;i++){
            bf1.write(encrypt(ops[i],26-key%26));
            bf1.newLine();
        }
        bf1.close();
    }

    /**
     * encrypt string with a number key
     * @param input
     * @param num
     * @return
     */
    private static String encrypt(String input, int num) throws IOException {
        if (num<0){
            throw new IOException();
        }
        String res = "";
        int len = input.length();
       for (int i=0;i<len;i++){
           if (Character.isLetter(input.charAt(i))){
               if (input.charAt(i)>='a'){
                   char ch = (char) (input.charAt(i)+(num%26));
                   if (ch>'z'){
                       ch = (char) (ch-'z'-1+'a');
                   }
                    res+=ch;
               }else{
                   char ch = (char) ((input.charAt(i)+(num%26)));
                   if (ch>'Z'){
                       ch = (char) (ch-'Z'-1+'A');
                   }
                   res+=ch;
               }
           }else res+=input.charAt(i);
       }
        return res;
    }

}
