import jdk.nashorn.internal.ir.debug.JSONWriter;
import jdk.nashorn.internal.parser.JSONParser;

import javax.print.attribute.standard.Copies;
import javax.swing.*;
import java.awt.*;
import java.io.*;
import java.math.BigInteger;
import java.nio.file.CopyOption;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;

public class A {
    public static void main(String[] args) throws IOException {
        System.out.println(new A().fact(50000));
/*        JFileChooser fileChooser = new JFileChooser();
        fileChooser.showDialog(null, "Sss");
        File file = fileChooser.getSelectedFile();
        System.out.println(file.getAbsolutePath());
        String d = "Sdd";
        Files.copy(new ByteArrayInputStream(d.getBytes()),Paths.get("ccc.txt"), StandardCopyOption.REPLACE_EXISTING);
        Files.copy(Paths.get("aaa.txt"),Paths.get("bbb.txt"), StandardCopyOption.REPLACE_EXISTING);*/
   //     Main main = new Main();
     //   Butt butt = new Butt();
    }

    BigInteger fact(int n)
    {
        BigInteger cache = BigInteger.valueOf(1);
        if (n==0||n==1) return BigInteger.valueOf(1);
        for (int i =2; i<=n; i++){
           cache =  cache.multiply(new BigInteger(String.valueOf(i)));
        }
        return cache;
    }
}
