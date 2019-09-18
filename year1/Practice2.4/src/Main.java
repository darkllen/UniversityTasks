import dao.Connect;
import dao.Database;

import java.io.UnsupportedEncodingException;
import java.sql.SQLException;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) throws SQLException, UnsupportedEncodingException {
        Connect connect = new Connect();
        Database database = new Database(connect.connect());
        Main main = new Main();

        while (true){
            System.out.print("Input 1 for get last string, 0 for input new: ");
            String i = getString();
            if (i.equals("0")){
                System.out.print("Input string to encode: ");
                String str = getString();
                System.out.print("Input encode pass: ");
                String enc = getString();
                database.updateLastStr(encode(str,enc));
                byte[] bytes = database.getStr();
                String encode = "";
                for (int m = 0;m<bytes.length;m++){
                    encode+=bytes[m];
                }
                System.out.println("encode phrase: "+encode);
            } else {
                byte[] bytes = database.getStr();
                String encode = "";
                for (int m = 0;m<bytes.length;m++){
                    encode+=bytes[m];
                }
                System.out.println("encode phrase: "+encode);
                System.out.print("Input encode pass: ");
                String enc = getString();
                String dec= decode(bytes,enc);
                System.out.println("decode phrase: " + dec);
            }
        }

    }

    private static String getString() {
        String s =new Scanner(System.in).nextLine();
        while (s.length()==0){
            s = new Scanner(System.in).nextLine();
        }
        return s ;
    }

    /**
     * encode string by xor
     * @param pText
     * @param pKey
     * @return encode bytes of bew string
     */
    public static byte[] encode(String pText, String pKey) {
        byte[] txt = pText.getBytes();
        byte[] key = pKey.getBytes();
        byte[] res = new byte[pText.length()];

        for (int i = 0; i < txt.length; i++) {
            res[i] = (byte) (txt[i] ^ key[i % key.length]);
        }

        return res;
    }

    /**
     * decode bytes of string with xor
     * @param pText
     * @param pKey
     * @return decoding string
     */
    public static String decode(byte[] pText, String pKey) {
        byte[] res = new byte[pText.length];
        byte[] key = pKey.getBytes();

        for (int i = 0; i < pText.length; i++) {
            res[i] = (byte) (pText[i] ^ key[i % key.length]);
        }

        return new String(res);
    }
}
