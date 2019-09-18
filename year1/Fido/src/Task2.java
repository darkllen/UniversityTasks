import java.util.Scanner;

public class Task2 {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        String input = sc.nextLine();
        input = input.toLowerCase();
        int num = sc.nextInt();
       String res = encrypt(input,num);
        System.out.println(res);
        res = decrypt(res,num);
        System.out.println(res);
        }

        private static String encrypt(String input, int num){
            char[] chars = input.toCharArray();
            for (int i = 0;i<chars.length;i++) {
                if (chars[i] > 96 && chars[i] < 123) {
                    chars[i] = (char) (chars[i] + num);
                    if (chars[i] > 122) {
                        int n = chars[i] - 122;
                        n=n%26;
                        if (n==0){
                            n=26;
                        }
                        chars[i] = (char) (96 + n);
                    }
                } else if ((chars[i] > 1071 && chars[i] < 1104)) {
                    chars[i] = (char) (chars[i] + num);
                    if (chars[i] > 1103) {
                        int n = chars[i] - 1103;
                        n=n%32;
                        chars[i] = (char) (1071 + n);
                    }
                }
            }
            String res="";
            for (char i:chars) {
                res+=i;
            }
            return res;
        }

        private static String decrypt(String input, int num){
            char[] chars = input.toCharArray();
            for (int i = 0;i<chars.length;i++) {
                if (chars[i] > 96 && chars[i] < 123) {
                    chars[i] = (char) (chars[i] - num);
                    if (chars[i] < 97) {
                        int n =96 - chars[i];
                        n=n%26;
                        if (n==0){
                            n=26;
                        }
                        chars[i] = (char) (96 + n);
                    }
                } else if ((chars[i] > 1071 && chars[i] < 1104)) {
                    chars[i] = (char) (chars[i] - num);
                    if (chars[i] < 1072) {
                        int n =1071- chars[i];
                        n=n%32;
                        if (n==0)
                            n=32;
                        chars[i] = (char) (1071 + n);
                    }
                }
            }
            String res="";
            for (char i:chars) {
                res+=i;
            }
            return res;
        }



    }
