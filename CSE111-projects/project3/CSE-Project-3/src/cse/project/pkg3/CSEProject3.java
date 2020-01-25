/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package cse.project.pkg3;


import java.util.Scanner;

/**
 *
 * @author Mert
 */
public class CSEProject3 {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        System.out.println("Welcome to the cseAliens simulation!\n"
                            + "————————————————————- ");
        Scanner input = new Scanner(System.in);
        System.out.println("Enter the size of cseAlien population: ");
        int N = input.nextInt();
        System.out.println("Simulating cseAlien species… ");
        char[][] Aliens = cseAlien(N);
        System.out.println("——————————————————————————-- ");

        for (int c = 0; c < Aliens.length; c++) {
            System.out.print("ID :" + (c + 1) + ", " + genderFinder(Aliens, c) + "," + " Health: "
                    + (healthCounter(Aliens, c)));

            System.out.println();

        }
        System.out.println("——————————————————————————-- \ncseAlien population is generated! ");

        int control = 0;

        while (control != 99) {
            System.out.println("Choose an options:\n"
                    + "(1)Mate two cseAliens\n"
                    + "(2)Randomly mate a set of cseAliens\n"
                    + "(3)Show statistics\n"
                    + "(4)Show the best mate\n"
                    + "(5)Only one random mate\n"
                    + "(99)Exit to simulation\n"
                    + "Enter an option: ");
            control = input.nextInt();
            switch (control) {
                case 1:
                    System.out.println("Mating two cseAliens\n"
                            + "————————————————————- ");
                    System.out.println("Enter ID of first cseAlien:");
                    int firstA = input.nextInt();
                    System.out.println("Enter ID of second cseAlien");
                    int secondA = input.nextInt();
                    matetwocseAliens(firstA, secondA, Aliens);
                    break;
                case 2:
                    System.out.println("Simulating Random cseAlien Reproduction\n"
                            + "——————————————————————————————————————— ");
                    System.out.println("Enter number of cseAlien pairs to reproduce: ");
                    int number = input.nextInt();
                    randommateAliens(number, Aliens);
                    break;
                case 3:
                    System.out.println("cseAlien Population Statistics\n"
                            + "——————————————————————————————");
                    System.out.println("MALE population = " + genderCounter(Aliens) + "%");
                    System.out.println("FEMALE population = " + (100 - genderCounter(Aliens)) + "%");
                    System.out.println("Enter an health threshold [between … and …]: ");
                    int threshold1 = input.nextInt();
                    int threshold2 = input.nextInt();
                    System.out.println(healthStats(threshold1, threshold2, Aliens) + "% of cseAlien population have an health more than " + threshold1
                            + " and less than " + threshold2);
                    maxminHealth(Aliens);
                    break;
                case 4:
                    System.out.println("Show the best mate\n"
                            + "——————————————————————————————");
                    bestMate(Aliens);
                    break;
                case 5:
                    System.out.println("Simulating the one is constant and the other one is randomly mate a set of cseAliens\n"
                            + "——————————————————————————————");
                    System.out.println("Enter ID of constant cseAlien : ");
                    int constant = input.nextInt();
                    System.out.println("Enter number of cseAlien pairs to reproduce: ");
                    int count = input.nextInt();
                    halfRandomcseAliens(constant,count,Aliens);
                    break;
            }
        }
        System.out.println("Hope to see you again!\n"
                + "————————————————————- ");
    }
    public static char[][] cseAlien(int N) {
        char[][] x = new char[N][128];
        for (int i = 0; i < N; i++) {
            for (int j = 0; j < x[i].length; j++) {
                x[i][j] = randomChar(j);
            }
        }
        return x;
    }
    public static char randomChar(int N) {
        char random = (char) ((int) (Math.random() * 3) + (int) ('A'));
        return random;
    }
    public static String genderFinder(char[][] x, int c) {
        String a = "";
        if (x[c][127] == ('A')) {
            a = "Male";
        } else {
            a = "Female";
        }
        return a;
    }
    public static int healthCounter(char[][] x, int c) {
        int count = 0;
        for (int i = c; i <= c; i++) {
            for (int j = 0; j < x[i].length; j++) {
                if (x[i][j] == 'B' && j != 127) {
                    if (x[i][j + 1] == 'A' && j + 1 != 127) {
                        if (x[i][j + 2] == 'C') {
                            count++;
                        }
                    }
                }
            }
        }
        return count;
    }
    public static void matetwocseAliens(int x, int y, char[][] z) {
        if (genderFinder(z, x - 1).equals(genderFinder(z, y - 1))) {
            matetwocseAliensmessage(x, y, z);
            System.out.println(" no mate");
        } else if (!genderFinder(z, x - 1).equals(genderFinder(z, y - 1))) {
            matetwocseAliensmessage(x, y, z);
            System.out.print("Offspring chance " + reproduction(x, y, z) + "%.");
            if (reproductionResult(x, y, z) == 1) {
                System.out.println(" They have 1 offspring :) ");
            } else {
                System.out.println(" no OffSpring ");
            }
        }

    }
    public static void randommateAliens(int x, char[][] z) {
        for (int i = 0; i < x; i++) {
            int n1 = (int) (Math.random() * z.length) + 1;

            int n2 = (int) (Math.random() * z.length) + 1;
            matetwocseAliens(n1, n2, z);
        }
    }
    public static void matetwocseAliensmessage(int x, int y, char[][] z) {
        System.out.print("cseAlien " + x + "(" + genderFinder(z, x - 1) + ") and " + y + "("
                + genderFinder(z, y - 1) + ") Mate :  ");
    }
    public static int reproduction(int x, int y, char[][] z) {
        int r1 = healthCounter(z, x - 1) + healthCounter(z, y - 1);
        int result = ((r1 * 100) / 20);
        return result;

    }
    public static int reproductionResult(int x, int y, char[][] z) {
        int chance = reproduction(x, y, z);
        int random = (int) (Math.random() * 99) + 1;
        if (random <= chance) {
            return 1;
        } else {
            return 0;
        }
    }
    public static double genderCounter(char[][] z) {
        int male = 0;
        int total = 0;
        double percent = 0;
        for (int i = 0; i < z.length; i++) {
            if (genderFinder(z, i).equals("Male")) {
                male++;
                total++;
            } else {
                total++;
            }
        }
        percent = (male * 100) / total;
        return percent;
    }
    public static double healthStats(int x, int y, char[][] z) {
        int count = 0;
        int total = 0;
        double percent = 0;
        for (int i = 0; i < z.length; i++) {
            if (healthCounter(z, i) > x && healthCounter(z, i) < y) {
                count++;
                total++;
            } else {
                total++;
            }
        }
        percent = (count * 100) / total;
        return percent;
    }
    public static void maxminHealth(char[][] z) {
        int max = 0;
        int min = 0;
        for (int i = 0; i < z.length; i++) {
            if (healthCounter(z, i) < min) {
                min = healthCounter(z, i);
            } else if (healthCounter(z, i) > max) {
                max = healthCounter(z, i);
            }
        }
        System.out.println("Max health is : " + max);
        System.out.println("Min health is : " + min);
    }
    public static void bestMate(char[][] z) {
        int max = 0;
        int alien1 = 0;
        int alien2 = 0;
        for (int i = 0; i < z.length / 2; i++) {
            for (int j = 0; j < z.length; j++) {
                if (!genderFinder(z, i).equals(genderFinder(z, j))) {
                    int b = reproduction((i + 1), (j + 1), z);
                    if (b >= max) {
                        max = b;
                        alien1 = i;
                        alien2 = j;
                    }
                }
            }
        }
        if (max != 0) {
            System.out.println("The best mate of all in the cseAliens : Alien " + (alien1 + 1) + "(" + genderFinder(z, alien1) + ") and Alien "
                    + (alien2 + 1) + "(" + genderFinder(z, alien2) + ")" + " = " + max + "%");
        } else {
            System.out.println("There is no match!");
        }
    }
    public static void halfRandomcseAliens(int x, int y, char[][] z) {
        for (int i = 0; i < y; i++) {
            int b = (int) (Math.random() * z.length) + 1;
            matetwocseAliens(x, b, z);
        }
    }
}