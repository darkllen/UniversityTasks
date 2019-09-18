import acm.program.ConsoleProgram;

import java.io.*;

public class Main extends ConsoleProgram {

    private static final String GLOBAL_PATH = "C:\\Users\\yanki\\IdeaProjects\\UniversityTasks\\UniversityTasks\\Practice13\\";

    public void run(){
        this.setSize(600,400);
        this.setFont("SansSherif-24");
        while (true){
            try {println("Написати програму, що замінює слово в файлі заданою стрічкою");
                println(findPhraseInFile(readLine("Input file name: "),readLine("Input word: "), readLine("Input new Word: ")));
                println();
            } catch (IOException e) {
                println("Input was incorrect");
            }
        }
    }

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

    public String findPhraseInFile(String filename, String phrase, String newPfrase) throws IOException {
        String fileS = fileOutput(filename);
        int count = 0;
        System.out.println(fileS);
        FileWriter fr = new FileWriter(GLOBAL_PATH +filename);
        BufferedWriter bf = new BufferedWriter(fr);
        String[] ops = fileS.split("\n");
        for (int i = 0;i<ops.length;i++){
            if (!ops[i].startsWith(" ")){
                ops[i] = " " + ops[i];
            }
            if (!ops[i].endsWith(" ")){
                ops[i] = ops[i] + " ";
            }
            while (ops[i].indexOf(   " " + phrase+ " ")!=-1){
                System.out.println(ops[i]);
                ops[i]= ops[i].replaceFirst(" "+phrase+" "," "+newPfrase+" ");
                count++;
            }
            bf.write(ops[i]);
            bf.newLine();
        }
        bf.close();
        return  "word \""+phrase+"\" was found in this file " + count + " times";
    }


}
