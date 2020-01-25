
package cseprojecttwo;

import java.util.Scanner;


public class CSEProjectTwo {

  
    public static void main(String[] args) {
        Scanner input = new Scanner(System.in);
        System.out.println("Welcome to Arithmetic’s Exercise Program");
        System.out.println("—————————————————————");
        System.out.println("You have 60 seconds to answer as many questions as possible.\n"
                + "You will get 5 seconds bonus if you answer 5 questions in a row.\n"
                + "Use java arithmetic precedence rules!\n"
                + "Press ‘q’ to quit or any key to start…");
        String quickQuit = input.next();
        int level = 1;
        do {
            long startTime = System.currentTimeMillis();

            int numberofCorrect = 0;
            int numberofFalse = 0;
            int numberofQuestion = 1;
            int Point = 0;
            int rowParameter = 0;
            int rowCounter = 0;
            int totaltime = 60;
            boolean checks = false;
            while ((System.currentTimeMillis() - startTime) / 1000 < 60 && !quickQuit.equals("q")) {

                if (level == 1) {
                    checks = generateQuestionforLevel1(numberofQuestion, numberofCorrect, numberofFalse, Point);
                    numberofQuestion++;

                    if (checks == true) {
                        numberofCorrect++;
                        Point++;
                        rowParameter++;
                    } else {
                        numberofFalse++;
                        rowParameter = 0;
                    }
                }
                if (level == 2) {
                    checks = generateQuestionforLevel2(numberofQuestion, numberofCorrect, numberofFalse, Point);
                    numberofQuestion++;

                    if (checks == true) {
                        numberofCorrect++;
                        Point++;
                        rowParameter++;
                    } else {
                        numberofFalse++;
                        rowParameter = 0;
                    }
                }
                if (rowParameter % 3 == 0 && rowParameter != 0) {
                    StarPattern(rowCounter);
                    rowCounter++;
                }
                if (rowParameter % 5 == 0 && rowParameter != 0) {
                    System.out.println("You've done five correct on a row! You wonned 5 sec.");
                    startTime += 5000;
                    totaltime += 5;
                }

            }
            if (numberofCorrect > 20){
                System.out.println("Uww , you are FLASH!");
            }
            else if (numberofCorrect > 15){
                System.out.println("Fast like a plane!");
            }
            else if  (numberofCorrect > 10){
                System.out.println("You know you can do better than this.");
            }
            else if (numberofCorrect > 5){
                System.out.println("You are as fast as a turtle! ");
            }
            else 
                System.out.println("My friend you should take math lessons!");
            System.out.println("TIME IS UP! ");
            System.out.println("Correct answers : " + numberofCorrect);
            System.out.println("Total questions : " + numberofQuestion);
            System.out.println("Total time : " + totaltime);
            if (Point > 15 && level == 1) {
                System.out.println("You can go to Level 2 !");
                System.out.println("if you want to be level 2 press 'two', if you want stay level 1 press 'one' , if you want exit press 'q' ");
                quickQuit = input.next();
                if (quickQuit.equals("two")) {
                    level++;
                }
            } else if (level == 1) {
                System.out.println("Your point is not enought to go to Level 2 , sorry !");
            }

            System.out.println("if you want to play again press 'p' , if you want to exit press 'q' ");
            quickQuit = input.next();
            if (quickQuit.equals("p")) {
                startTime += 60000;
            }
        } while (quickQuit.equalsIgnoreCase("p"));
        System.out.println("Thank you for playing! Goodbye.");
    }
    public static boolean generateQuestionforLevel1 (int qNumber , int qCorrect , int qFalse , int qPoint){
        System.out.print("Q" + qNumber + "-)        ");
        int firstValue = (int) (Math.random() * 20) + 1;
        int secondValue = (int) (Math.random() * 20) + 1;
        int operator = (int) (Math.random() * 4) + 1;
        boolean check = false;
        switch (operator) {
            case 1:
                check = SumforLevel1(firstValue, secondValue);
                break;
            case 2:
                check = MinusforLevel1(firstValue, secondValue);
                break;
            case 3:
                check = MultiplicationforLevel1(firstValue, secondValue);
                break;
            case 4:
                check = DivisionforLevel1(firstValue, secondValue);
                break;
        }
        return check;
    }
     public static boolean generateQuestionforLevel2(int qNumber , int qCorrect , int qFalse , int qPoint){
        System.out.print("Q" + qNumber + "-)        ");
        int firstValue = (int) (Math.random() * 20) + 1;
        int secondValue = (int) (Math.random() * 20) + 1;
        int thirdValue = (int) (Math.random() * 20) + 1;
        int operator = (int) (Math.random() * 4) + 1;
        boolean check = false;
        switch (operator) {
            case 1:
                check = SumforLevel2(firstValue, secondValue, thirdValue);
                break;
            case 2:
                check = MinusforLevel2(firstValue, secondValue, thirdValue);
                break;
            case 3:
                check = MultiplicationforLevel2(firstValue, secondValue, thirdValue);
                break;
            case 4:
                check = DivisionforLevel2(firstValue, secondValue, thirdValue);
                break;
        }
        return check;
    }
    public static boolean SumforLevel1 (int firstV,int secondV){
        boolean check = false;
        Scanner questionscanner = new Scanner(System.in);
        System.out.print(firstV + " + " + secondV + " = ");
        int answer = questionscanner.nextInt();
        if (answer == (firstV + secondV)) {
            System.out.println("YOUR ANSWER İS CORRECT");
            check = true;
        } else {
            System.out.println("YOUR ANSWER İS WRONG");

        }
        return check;
    }
    public static boolean SumforLevel2 (int firstV,int secondV,int thirdV){
        boolean check = false;
        Scanner questionscanner = new Scanner(System.in);
        int secondoperator = (int) (Math.random() * 4) + 1;
        int answer = 0;
        switch (secondoperator) {
            case 1:
                System.out.println(firstV + "+" + secondV + "+" + thirdV + " = ");
                answer = questionscanner.nextInt();
                if (answer == (firstV + secondV + thirdV)) {
                    System.out.println("YOUR ANSWER İS CORRECT");
                    check = true;
                } else {
                    System.out.println("YOUR ANSWER İS WRONG");

                }
                break;
            case 2:
                System.out.println(firstV + "+" + secondV + "-" + thirdV + " = ");
                answer = questionscanner.nextInt();
                if (answer == (firstV + secondV - thirdV)) {
                    System.out.println("YOUR ANSWER İS CORRECT");
                    check = true;
                } else {
                    System.out.println("YOUR ANSWER İS WRONG");

                }
                break;
            case 3:
                System.out.println(firstV + "+" + secondV + "x" + thirdV + " = ");
                answer = questionscanner.nextInt();
                if (answer == (firstV + secondV * thirdV)) {
                    System.out.println("YOUR ANSWER İS CORRECT");
                    check = true;
                } else {
                    System.out.println("YOUR ANSWER İS WRONG");

                }
                break;
            case 4:
                System.out.println(firstV + "+" + secondV + "/" + thirdV + " = ");
                answer = questionscanner.nextInt();
                if (answer == (firstV + secondV / thirdV)) {
                    System.out.println("YOUR ANSWER İS CORRECT");
                    check = true;
                } else {
                    System.out.println("YOUR ANSWER İS WRONG");

                }
                break;

        }
        return check;
    }
   public static boolean MinusforLevel1 (int firstV,int secondV){
        boolean check = false;
        Scanner questionscanner = new Scanner(System.in);
        System.out.print(firstV + " - " + secondV + " = ");
        int answer = questionscanner.nextInt();
        if (answer == (firstV - secondV)) {
            System.out.println("YOUR ANSWER İS CORRECT");
            check = true;
        } else {
            System.out.println("YOUR ANSWER İS WRONG");

        }
        return check;
    }
   public static boolean MinusforLevel2 (int firstV,int secondV,int thirdV){
        boolean check = false;
        Scanner questionscanner = new Scanner(System.in);
        int secondoperator = (int) (Math.random() * 4) + 1;
        int answer = 0;
        switch (secondoperator) {
            case 1:
                System.out.println(firstV + "-" + secondV + "+" + thirdV + " = ");
                answer = questionscanner.nextInt();
                if (answer == (firstV - secondV + thirdV)) {
                    System.out.println("YOUR ANSWER İS CORRECT");
                    check = true;
                } else {
                    System.out.println("YOUR ANSWER İS WRONG");

                }
                break;
            case 2:
                System.out.println(firstV + "-" + secondV + "-" + thirdV + " = ");
                answer = questionscanner.nextInt();
                if (answer == (firstV - secondV - thirdV)) {
                    System.out.println("YOUR ANSWER İS CORRECT");
                    check = true;
                } else {
                    System.out.println("YOUR ANSWER İS WRONG");

                }
                break;
            case 3:
                System.out.println(firstV + "-" + secondV + "x" + thirdV + " = ");
                answer = questionscanner.nextInt();
                if (answer == (firstV - secondV * thirdV)) {
                    System.out.println("YOUR ANSWER İS CORRECT");
                    check = true;
                } else {
                    System.out.println("YOUR ANSWER İS WRONG");

                }
                break;
            case 4:
                System.out.println(firstV + "-" + secondV + "/" + thirdV + " = ");
                answer = questionscanner.nextInt();
                if (answer == (firstV - secondV / thirdV)) {
                    System.out.println("YOUR ANSWER İS CORRECT");
                    check = true;
                } else {
                    System.out.println("YOUR ANSWER İS WRONG");

                }
                break;
        }
        return check;
    }
    public static boolean MultiplicationforLevel1 (int firstV,int secondV){
        boolean check = false;
        Scanner questionscanner = new Scanner(System.in);
        System.out.print(firstV + " x " + secondV + " = ");
        int answer = questionscanner.nextInt();
        if (answer == (firstV * secondV)) {
            System.out.println("YOUR ANSWER İS CORRECT");
            check = true;
        } else {
            System.out.println("YOUR ANSWER İS WRONG");

        }
        return check;
    }
   public static boolean MultiplicationforLevel2 (int firstV,int secondV,int thirdV){
        boolean check = false;
        Scanner questionscanner = new Scanner(System.in);
        int secondoperator = (int) (Math.random() * 4) + 1;
        int answer = 0;
        switch (secondoperator) {
            case 1:
                System.out.println(firstV + "x" + secondV + "+" + thirdV + " = ");
                answer = questionscanner.nextInt();
                if (answer == (firstV * secondV + thirdV)) {
                    System.out.println("YOUR ANSWER İS CORRECT");
                    check = true;
                } else {
                    System.out.println("YOUR ANSWER İS WRONG");

                }
                break;
            case 2:
                System.out.println(firstV + "x" + secondV + "-" + thirdV + " = ");
                answer = questionscanner.nextInt();
                if (answer == (firstV * secondV - thirdV)) {
                    System.out.println("YOUR ANSWER İS CORRECT");
                    check = true;
                } else {
                    System.out.println("YOUR ANSWER İS WRONG");

                }
                break;
            case 3:
                System.out.println(firstV + "x" + secondV + "x" + thirdV + " = ");
                answer = questionscanner.nextInt();
                if (answer == (firstV * secondV * thirdV)) {
                    System.out.println("YOUR ANSWER İS CORRECT");
                    check = true;
                } else {
                    System.out.println("YOUR ANSWER İS WRONG");

                }
                break;
            case 4:
                System.out.println(firstV + "x" + secondV + "/" + thirdV + " = ");
                answer = questionscanner.nextInt();
                if (answer == (firstV * secondV / thirdV)) {
                    System.out.println("YOUR ANSWER İS CORRECT");
                    check = true;
                } else {
                    System.out.println("YOUR ANSWER İS WRONG");

                }
                break;

        }
        return check;
    }
    public static boolean DivisionforLevel1 (int firstV,int secondV){
        boolean check = false;
        Scanner questionscanner = new Scanner(System.in);
        System.out.print(firstV + " + " + secondV + " = ");
        int answer = questionscanner.nextInt();
        if (answer == (firstV + secondV)) {
            System.out.println("YOUR ANSWER İS CORRECT");
            check = true;
        } else {
            System.out.println("YOUR ANSWER İS WRONG");

        }
        return check;
    }
    public static boolean DivisionforLevel2 (int firstV,int secondV,int thirdV){
        boolean check = false;
        Scanner questionscanner = new Scanner(System.in);
        int secondoperator = (int) (Math.random() * 4) + 1;
        int answer = 0;
        switch (secondoperator) {
            case 1:
                System.out.println(firstV + "/" + secondV + "+" + thirdV + " = ");
                answer = questionscanner.nextInt();
                if (answer == (firstV / secondV + thirdV)) {
                    System.out.println("YOUR ANSWER İS CORRECT");
                    check = true;
                } else {
                    System.out.println("YOUR ANSWER İS WRONG");

                }
                break;
            case 2:
                System.out.println(firstV + "/" + secondV + "-" + thirdV + " = ");
                answer = questionscanner.nextInt();
                if (answer == (firstV / secondV - thirdV)) {
                    System.out.println("YOUR ANSWER İS CORRECT");
                    check = true;
                } else {
                    System.out.println("YOUR ANSWER İS WRONG");

                }
                break;
            case 3:
                System.out.println(firstV + "/" + secondV + "x" + thirdV + " = ");
                answer = questionscanner.nextInt();
                if (answer == (firstV / secondV * thirdV)) {
                    System.out.println("YOUR ANSWER İS CORRECT");
                    check = true;
                } else {
                    System.out.println("YOUR ANSWER İS WRONG");

                }
                break;
            case 4:
                System.out.println(firstV + "/" + secondV + "/" + thirdV + " = ");
                answer = questionscanner.nextInt();
                if (answer == (firstV / secondV / thirdV)) {
                    System.out.println("YOUR ANSWER İS CORRECT");
                    check = true;
                } else {
                    System.out.println("YOUR ANSWER İS WRONG");

                }
                break;

        }
        return check;
    }
    public static int rowParameters (boolean x){
        int row = 0;
        if (x == true) {
            row++;
            return row;
        } else {
            row = 0;
            return row;
        }
    }
    public static void StarPattern(int rowCounters) {
        int rowCounter = rowCounters;
        int N = 3 * (rowCounter + 1);
        int last = 0;
        for (int y = 1; y <= N; y++) {

            for (int x = N; x > y; x--) {
                System.out.print(" ");
            }
            for (int repeat = 1; repeat <= N; repeat++) {
                for (int x = 1; x <= (2 * y - 1); x++) {
                    System.out.print("*");
                    last = (2 * y - 1);
                }
                for (int x = N; x > y; x--) {
                    System.out.print("  ");
                }
            }
            System.out.println("");

        }
        for (int s = 1; s <= last; s++) {
            System.out.print("*");
        }

        for (int s = 1; s <= (N / 2 - 1) * last; s++) {
            System.out.print(" ");
        }
        String message1 = "good ";
        String message2 = " very good ";
        String message3 = "    excellent    ";
        String message4 = "you are rock!  ";

        switch (rowCounter) {
            case 0:
                System.out.print(message1);
                break;
            case 1:
                System.out.print(message2);
                break;
            case 2:
                System.out.print(message3);
                break;
            default:
                for (int space = 1; space < (last - 13) / 2; space++) {
                    System.out.print(" ");
                }
                System.out.print(message4);
                for (int space = 1; space < (last - 13) / 2; space++) {
                    System.out.print(" ");
                }
        }
        if (N % 2 == 0) {
            for (int s = 1; s <= (N / 2 - 2) * last; s++) {
                System.out.print(" ");
            }
        } else {
            for (int s = 1; s <= (N / 2 - 1) * last; s++) {
                System.out.print(" ");
            }
        }
        for (int s = 1; s <= last; s++) {
            System.out.print("*");
        }
        System.out.println("");
    }
}
