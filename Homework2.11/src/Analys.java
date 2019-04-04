import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import java.io.*;
import java.util.List;

public abstract class Analys {


    public static List<Word> fileAnalys(@NotNull File file, List<Word> words) throws IOException {
        StreamTokenizer tokenizer = new StreamTokenizer(new FileInputStream(file.getPath()));
        Analys.tokenizerInit(tokenizer);

        while (tokenizer.nextToken()!=StreamTokenizer.TT_EOF){
            if (Analys.checkForWords(tokenizer)){

            }
            }

        return null;
        }




    private static boolean checkForWords(@NotNull StreamTokenizer tokenizer){
        return  tokenizer.ttype==StreamTokenizer.TT_WORD && (tokenizer.sval.replaceAll("[a-zA-Zа-яА-Я]","").length()!=tokenizer.sval.length());

    }
    private static void tokenizerInit(@NotNull StreamTokenizer tokenizer){
        tokenizer.whitespaceChars(' ',' ');
        tokenizer.whitespaceChars('!','!');
        tokenizer.whitespaceChars(',',',');
        tokenizer.whitespaceChars('.','.');
        tokenizer.whitespaceChars('?','?');
        tokenizer.whitespaceChars(';',';');
        tokenizer.whitespaceChars(':',':');
        tokenizer.whitespaceChars('-','-');
    }
    }

