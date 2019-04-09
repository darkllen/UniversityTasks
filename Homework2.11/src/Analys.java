import Output.Word;

import java.io.*;
import java.nio.charset.Charset;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.nio.file.StandardOpenOption;
import java.util.HashMap;
import java.util.List;

public abstract class Analys {

    /**
     *
     * @param file to analyse
     * @param words
     * @return list with words
     * @throws IOException
     */
    public static List<Word> fileAnalys(File file, HashMap words) throws IOException {
        BufferedReader r = new BufferedReader(new InputStreamReader(new FileInputStream(file), Charset.forName("cp1251")));
        StreamTokenizer tokenizer = new StreamTokenizer(r); Analys.tokenizerInit(tokenizer);

        while (tokenizer.nextToken()!=StreamTokenizer.TT_EOF){
            if (Analys.checkForWords(tokenizer)){
                if (words.get(tokenizer.sval.toLowerCase())!=null){
                    ((Word)words.get(tokenizer.sval.toLowerCase())).setNewValue(file.getName());
                }

                else {
                    Word word = new Word(tokenizer.sval.toLowerCase());
                    word.setNewValue(file.getName());
                    words.put(tokenizer.sval.toLowerCase(),word);
                }

            }
        }
        return null;
        }




    private static boolean checkForWords( StreamTokenizer tokenizer){
        return  tokenizer.ttype==StreamTokenizer.TT_WORD && (tokenizer.sval.replaceAll("[a-zA-Zа-яА-Я]","").length()!=tokenizer.sval.length());

    }
    private static void tokenizerInit( StreamTokenizer tokenizer){
        tokenizer.whitespaceChars(' ',' ');
        tokenizer.whitespaceChars('!','!');
        tokenizer.whitespaceChars(',',',');
        tokenizer.whitespaceChars('.','.');
        tokenizer.whitespaceChars('?','?');
        tokenizer.whitespaceChars(';',';');
        tokenizer.whitespaceChars(':',':');
        tokenizer.whitespaceChars('-','-');
        tokenizer.whitespaceChars('"','"');
        tokenizer.whitespaceChars('\'','\'');
        tokenizer.whitespaceChars('(','(');
        tokenizer.whitespaceChars(')',')');
        tokenizer.whitespaceChars('»','»');
        tokenizer.whitespaceChars('.','.');
        tokenizer.whitespaceChars('«','«');
        tokenizer.whitespaceChars('„','„');
        tokenizer.whitespaceChars('“','“');
        tokenizer.whitespaceChars('–','–');

    }

    public static long writeToFile(String str, int wordsNum, long collectionSize) throws IOException {
        System.out.println("ccc");
        File file = new File("avc.txt");
        FileWriter writer = new FileWriter(file);
        writer.write(wordsNum + " words" + System.getProperty("line.separator") + "Collection size : " + collectionSize + " bytes" + System.getProperty("line.separator") + System.getProperty("line.separator") + str);
        writer.close();
        writeToFileSize("File size : " +file.length() + " bytes",file.getPath());
        return file.length();
    }

    private static void writeToFileSize(String text, String filePath) throws IOException {
        Files.write(Paths.get(filePath), text.getBytes(), StandardOpenOption.APPEND);
    }
    }

