import java.io.*;
import java.util.Random;
public class Main {

    public static void main(String[] args) throws IOException {
       generateNewFile(10);
        BufferedReader br = new BufferedReader(new InputStreamReader(new FileInputStream("1.txt")));
        int n = Integer.parseInt(br.readLine());
        Pupil pupils[] = new Pupil[n];
        for (int i = 0; i< n; i++){
            String[] l = br.readLine().split(" ");
            pupils[i] = new Pupil(Integer.parseInt(l[0]), Double.parseDouble(l[1]), l[2], Boolean.parseBoolean(l[3]));
        }
        Sorts.mergeSort(pupils, pupils.length, new Pupil.CompareByAge());
    }


    public static void generateNewFile(int length) throws IOException {
        BufferedWriter writer = new BufferedWriter(new FileWriter("1.txt"));
        writer.write(String.valueOf(length));
        writer.newLine();
        for (int i = 0; i<length; i++){
            Random random = new Random();
            int age = random.nextInt(11)+7;
            double ascore = random.nextDouble()*100;
            byte[] array = new byte[10]; // length is bounded by 10
            new Random().nextBytes(array);
            String name =generateRandomString(10);
            boolean hasSc = random.nextBoolean();

            writer.write(String.valueOf(age) + " " + ascore + " " + name + " " + hasSc);
            writer.newLine();
        }
        writer.flush();
    }
    public static String generateRandomString(int length) {
        Random random = new Random();
        String CHAR_LOWER = "abcdefghijklmnopqrstuvwxyz";
        String CHAR_UPPER = CHAR_LOWER.toUpperCase();
        String NUMBER = "0123456789";
        String DATA_FOR_RANDOM_STRING = CHAR_LOWER + CHAR_UPPER + NUMBER;

        if (length < 1) throw new IllegalArgumentException();

        StringBuilder sb = new StringBuilder(length);
        for (int i = 0; i < length; i++) {

            // 0-62 (exclusive), random returns 0-61
            int rndCharAt = random.nextInt(DATA_FOR_RANDOM_STRING.length());
            char rndChar = DATA_FOR_RANDOM_STRING.charAt(rndCharAt);

            // debug

            sb.append(rndChar);

        }

        return sb.toString();

    }
}
