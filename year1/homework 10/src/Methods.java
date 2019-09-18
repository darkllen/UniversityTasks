import acm.io.IOConsole;


public class Methods {

    public void Task1(Main console, String name, String surName){
        for (int i = 1; i<=10; i++){
            console.println(i + ". "+name + " " + surName);
        }

    }
    public void Task2(Main console) {
        for (int i = 1; i < 11; i++) {
            console.println(i + "^2 = " +i*i);
        }
    }
    public void Task3(Main console) {
        for (int i = 1; i < 10; i+=2) {
            console.println(i + "^2 = " +i*i);
        }
    }
    public void Task4(Main console, int n) {
        int sum = 0;
        for (int i = 1; i <= n; sum+=i ,i++) {
            if (i==n){
                console.print(i);
            }else
            console.print(i + "+");
        }
        console.print("= " + sum);
    }
    public void Task5(Main console, int n) {
        int sum = 0;
        for (int i = 1; i <= n; i++) {
            if (i%2==1){
                sum+=i;
                n++;

                continue;
            }
            if (i==n){
                console.print(i-1);
            }else
                console.print((i-1) + "+");
        }
        console.print("= " + sum);
    }
    public void Task6(Main console, int n) {
        double sum = 0;
        for (double i = 1; i <= n; sum+=1/i ,i++) {
            if (i==n){
                console.print("1/"+i);
            }else
                console.print("1/"+i + "+");
        }
        console.print("= " + sum);
    }
    public void Task7(Main console) {
        for (int i = 0; i <= 10; i++) {
            console.println("2^"+i + " = " +(int)Math.pow(2,i));
        }
    }
    public void Task8(Main console, int n) {
        int prev = 1;
        for (int i = 1; i <=n; i++) {
            prev=i*prev;
        }
        console.println(n+"! = " + prev);
    }
    public void Task9(Main console) {
        for (double i = -2; i <=2; i+=0.5) {
            console.println("y = -2.4*"+i+"^2+5*"+i+"-3 = " + (-2.4*i*i+5*i-3));
        }
    }
    public void Task10(Main console) {
        double sum =0;
        for (double i = 0; i <10; i++) {
            int n = ((int)((Math.random()*10)))+1;
            sum+=n;
            console.println(((int)(i+1)) +". " + n);
        }
        console.println("sum = " + sum);
        console.println("average = " + sum/10);
    }
    public void Task11(Main console, double n) {
        double sum =0;
        for (double i = 1; i <10; i++) {
            sum+=n;
            console.println((i*100) +"г. коштують " + sum + " грн.");
        }
        console.println((1) +"кг. коштує " + (sum+n) + " грн.");
    }
    public void Task12(Main console) {
        int count=0;
        for (int i = 1; i <=10; i++) {
            if (Math.random()>0.5){
                int a = (int) (Math.random()*99);
                int possible = 100-a;
                int b = (int) (Math.random()*possible);
                console.print(i+". "+a + "+" +b+ " = ");
                if (console.readInt()==a+b){
                    count++;
                    console.println("Right");
                }else{
                    console.println("Wrong");
                }
            }else {
                int a = (int) (Math.random()*100);
                int b = (int) (Math.random()*a);
                console.print(i+". "+a + "-" +b+ " = ");
                if (console.readInt()==a-b){
                    count++;
                    console.println("Right");
                }else{
                    console.println("Wrong");
                }
            }
        }
        console.println();
        console.println("Your score - " + count);
        if (count>9){
            console.println("Perfect");
        }else if (count>7){
            console.println("Well");
        }else if (count>5){
            console.println("so-so");
        }else console.println("Awful");
    }
    public void Task13(Main console) {

        while (true){
            int ch = 0;
            int sum = 0;
            String nums = console.readLine("please, input numbers in one line with spaces: ");
            String[] arr = nums.split(" ");
            for (int i = 0; i <arr.length; i++) {
                if (Integer.parseInt(arr[i])<=0){
                    console.println("Input line only with positive numbers");
                    ch=1;
                    break;
                }
            }
            if (ch==1)continue;
            for (int i = 0; i <arr.length; i++) {
                if (i == arr.length - 1) {
                    console.print(arr[i]);
                } else {
                    console.print(arr[i] + "+");
                }
                sum += Integer.parseInt(arr[i]);
            }
            double av = ((double)sum/((double)arr.length));
            console.println("= " + sum);
            console.println("average = " + av);
            break;
        }



    }
    public boolean Task14(Main console) {
        while (true){
            int ch = 0;
            String nums = console.readLine("please, input numbers in one line with spaces: ");
            String[] arr = nums.split(" ");
            int max = 0;
            for (int i = 0; i <arr.length; i++) {
                if (Integer.parseInt(arr[i])<=0){
                    console.println("Input line only with positive numbers");
                    ch=1;
                    break;
                }
            }
            if (ch==1)continue;
            for (int i = 0; i <arr.length; i++) {
                if (Integer.parseInt(arr[i])<=0){
                    return false;
                }
                if (Integer.parseInt(arr[i])>max)max=Integer.parseInt(arr[i]);
            }
            console.println("Max number = " + max);
            return true;
        }

    }

    public boolean Task15(Main console, int n) {

        for (int i = 2; i<n; i++) {
            if (n%i==0){
                return false;
            }
        }
        return true;
    }
    public void Task16(Main console) {
        int guessNum = (int) (Math.random()*100)+1;
        int num = 0;

        for (int i = 1; i<=7; i++) {
            console.print("Try "+i+": ");
             num = console.readInt();
            if (num<1||num>100){
                console.println("Input number must be from 1 to 100");
                i--;
            }else
            if (num>guessNum){
                console.println("Less");
            }else if (num<guessNum){
                console.println("More");
            }else {
                console.println("Right      " + i + " attemps");
                return;
            }
        }
        if (num!=guessNum){
            console.println("You lose");
        }

    }


}
