import java.io.*;
import java.util.ArrayList;

 abstract class FileAnalyse {

     private static void  countWords(FileWord fileWord) throws IOException {
        int count = 0;
        StreamTokenizer tokenizer = new StreamTokenizer(new FileInputStream(fileWord.getPath()));
        tokenizer.whitespaceChars(' ',' ');
        tokenizer.whitespaceChars('!','!');
        tokenizer.whitespaceChars(',',',');
        tokenizer.whitespaceChars('.','.');
        tokenizer.whitespaceChars('?','?');
         tokenizer.whitespaceChars(';',';');
         tokenizer.whitespaceChars(':',':');
         tokenizer.whitespaceChars('-','-');

        while (tokenizer.nextToken()!=StreamTokenizer.TT_EOF){
            if (tokenizer.ttype==StreamTokenizer.TT_WORD){
            if (tokenizer.sval.replaceAll("[a-zA-Zа-яА-Я]","").length()!=tokenizer.sval.length())
            count++;}
        }
        fileWord.setWordsNumber(count);
    }

    private static void  countUniqueWords(FileWord fileWord) throws IOException {
        ArrayList<String> strings = new ArrayList<>();
        ArrayList<Word> words = new ArrayList<>();
        int uniqueCount = 0;
        StreamTokenizer tokenizer = new StreamTokenizer(new FileInputStream(fileWord.getPath()));
        tokenizer.whitespaceChars(' ',' ');
        tokenizer.whitespaceChars('!','!');
        tokenizer.whitespaceChars(',',',');
        tokenizer.whitespaceChars('.','.');
        tokenizer.whitespaceChars('?','?');
        tokenizer.whitespaceChars(';',';');
        tokenizer.whitespaceChars(':',':');
        tokenizer.whitespaceChars('-','-');

        while (tokenizer.nextToken()!=StreamTokenizer.TT_EOF){
            if (tokenizer.ttype==StreamTokenizer.TT_WORD){
                if (tokenizer.sval.replaceAll("[a-zA-Zа-яА-Я]","").length()!=tokenizer.sval.length()){
                    if (strings.contains(tokenizer.sval)){
                        for (Word w:words) {
                            if(w.getWord().equals(tokenizer.sval))w.incrNumber();
                        }
                    }else {
                        strings.add(tokenizer.sval);
                        words.add(new Word(tokenizer.sval,1));
                        uniqueCount++;
                    }
                }

            }


        }
        fileWord.setWordsUniqueNumber(uniqueCount);
        fileWord.setWords(words);
    }

    static void analyse(FileWord fileWord) throws IOException {
         countWords(fileWord);
         countUniqueWords(fileWord);
    }
}
