import acm.io.IOConsole;
import acm.program.ConsoleProgram;

import java.util.ArrayList;

public class Main extends ConsoleProgram {
    public void run(){
        this.setSize(1400, 800);
        this.setFont("SansSerif-23");
        Methods methods = new Methods();
        String s = "";
        int n = 0;
        while (true){
           this.getConsole().clear();
            int choice = readInt("Input task number from 1 to 16: ");
            println();
                    if (choice==0)choice=-1;
            try{
                switch (choice){
                    case 0:
                        return;
                    case 1:
                        println("print name and surname 10 times:");
                        String name = readLine("Input name: ");
                        String surName = readLine("Input surname: ");
                        println();
                        methods.Task1(this, name, surName);
                        break;
                    case 2:
                        println("Output squares of first 10 positive numbers: ");
                        println();
                        methods.Task2(this);
                        break;
                    case 3:
                        println("Output squares of first 5 positive unpaired numbers: ");
                        println();
                        methods.Task3(this);
                        break;
                    case 4:
                        println("Calculate sum of n first positive numbers");
                        n = readInt("Input n: ");
                        while (n<=0){
                            println("Input positive number");
                            n=readInt();
                        }
                        println();
                        methods.Task4(this, n);
                        break;
                    case 5:
                        println("Calculate sum of {1, 3, 5... n}");
                        n = readInt("Input n: ");
                        while (n<=0){
                            println("Input positive number");
                            n=readInt();
                        }
                        println();
                        methods.Task5(this, n);
                        break;
                    case 6:
                        println("Calculate sum of {1, 1/2, 1/3...1/n}");
                        n = readInt("Input n: ");
                        while (n<=0){
                            println("Input positive number");
                            n=readInt();
                        }
                        println();
                        methods.Task6(this, n);
                        break;
                    case 7:
                        println("Output pow(2,n)");
                        println();
                        methods.Task7(this);
                        break;
                    case 8:
                        println("Calculate n!");
                        n = readInt("Input n: ");
                        while (n<=0){
                            println("Input positive number");
                            n=readInt();
                        }
                        println();
                        methods.Task8(this,n);
                        break;
                    case 9:
                        println("Calculate y=-2.4x^2+5x-3");
                        println();
                        methods.Task9(this);
                        break;
                    case 10:
                        println("Generate 10 numbers from 1 to 10 and output sum and average");
                        println();
                        methods.Task10(this);
                        break;
                    case 11:
                        println("Output cost of apples, where 100g. cost n");
                        n = readInt("Input n: ");
                        while (n<=0){
                            println("Input positive number");
                            n=readInt();
                        }
                        println();
                        methods.Task11(this, n);
                        break;
                    case 12:
                        println("Math check of +/- from 1 to 100");
                        println();
                        methods.Task12(this);
                        break;
                    case 13:
                        println("Calculate sum and average of next numbers");
                        println();
                        methods.Task13(this);
                        break;
                    case 14:
                        println("Output max number");
                        println();
                        methods.Task14(this);
                        break;
                    case 15:
                        println("Check n for simple number");
                        n = readInt("Input n: ");
                        while (n<=0){
                            println("Input positive number");
                            n=readInt();
                        }
                        println();
                       if (methods.Task15(this, n))println("n is simple");else println("n isn't  simple");
                        break;
                    case 16:
                        println("Guess game. You have 7 attempts");
                        println();
                        methods.Task16(this);
                        break;
                    default:
                        s = "Input right number: ";
                        println(s);
                }
            } finally {
                if (s.matches("")){
                    println();
                    choice = readInt("input 0 for stop, any num for continue: ");
                    if (choice==0)return;
                }
                         else {
                    s="";
                }
            }

        }


    }
}
