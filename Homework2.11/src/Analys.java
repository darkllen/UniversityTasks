

import java.io.*;
import java.nio.charset.Charset;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.nio.file.StandardOpenOption;
import java.util.List;

public abstract class Analys {


    public static List<Word> fileAnalys( File file, List<Word> words) throws IOException {
        BufferedReader r = new BufferedReader(new InputStreamReader(new FileInputStream(file), Charset.forName("cp1251")));
        StreamTokenizer tokenizer = new StreamTokenizer(r); Analys.tokenizerInit(tokenizer);

        while (tokenizer.nextToken()!=StreamTokenizer.TT_EOF){
            if (Analys.checkForWords(tokenizer)){
                if (words.contains(tokenizer.sval)){
                    ((MyWordList)words).getWord(tokenizer.sval).setNewValue(file.getName());
                }
                else {
                    Word word = new Word(tokenizer.sval);
                    word.setNewValue(file.getName());
                    words.add(word);
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
    }

    public static void writeToFile(String str, int wordsNum, long collectionSize) throws IOException {
        File file = new File("avc.txt");
        FileWriter writer = new FileWriter(file);
        writer.write(wordsNum + " words" + System.getProperty("line.separator") + "Collection size : " + collectionSize + " bytes" + System.getProperty("line.separator") + System.getProperty("line.separator") + str);
        writer.close();
        writeToFileSize("File size : " +file.length() + " bytes",file.getPath());
    }

    private static void writeToFileSize(String text, String filePath) throws IOException {
        Files.write(Paths.get(filePath), text.getBytes(), StandardOpenOption.APPEND);
    }
    }

