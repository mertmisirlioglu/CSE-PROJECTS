/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package cse202.project2.candycrush;

import java.util.Scanner;

/**
 *
 * @author Mert
 */
public class CSE202PROJECT2CANDYCRUSH {
    
    static ArrayQueue list;
    static LinkedStack stack1;
    static LinkedStack stack2;
    static LinkedStack stack3;
    static int point;
    
    public static void main(String[] args) {
        list = new ArrayQueue(40);
        stack1 = new LinkedStack();
        stack2 = new LinkedStack();
        stack3 = new LinkedStack();
        
        int maxTurnLimit = list.size;
        int currentTurn = 1;
        
        long gametimeStart = System.currentTimeMillis();
        long gametimeEnd = System.currentTimeMillis();
        long elapsedGameTime = 0;
        
        long turntimeStart = 0;
        long turntimeEnd = 0;
        long elapsedTurnTime = 0;
        
        Scanner input = new Scanner(System.in);
        
        System.out.println("Welcome to the Candy Crush Stack Game !");
        System.out.println("You can make two operation: \n1-You can push and pop."
                + " (Ex: pp 1 2 Description : pop Candy from Stack 1 and push to Stack 2)"
                + "\n2- You can crush candies. (Ex: cr 1  Description : crush candies in stack 1)\n"
                + "You can play maximum " + maxTurnLimit + " turn and you have 900 seconds.");
        
        while ((currentTurn <= maxTurnLimit || elapsedGameTime <= 900) && !list.isEmpty()) {
            System.out.println("Turn " + currentTurn + " is start.");
            stack1.push(list.dequeue());
            System.out.println("Next five candy is : " + list.printnextFiveCandy());
            
            System.out.println("Stack 1 : " + stack1);
            System.out.println("Stack 2 : " + stack2);
            System.out.println("Stack 3 : " + stack3);
            
            System.out.println("Current point : " + point);
            System.out.println("Remaining time : " + ((900 - elapsedGameTime) / 60) + " minute " + ((900 - elapsedGameTime) % 60) + " seconds");
            System.out.println("You have 30 seconds to make pop and push.");
            System.out.println("if you want to end turn press q");
            turntimeStart = System.currentTimeMillis();
            while (elapsedTurnTime <= 30) {
                
                String a = input.nextLine();
                if (a.equals("q")) {
                    break;
                }                
                proccesString(a);
                
                turntimeEnd = System.currentTimeMillis();
                elapsedTurnTime = (turntimeEnd - turntimeStart) / 1000;
                System.out.println("Remaining time : " + (30 - elapsedTurnTime) + " seconds");
            }
            System.out.println("Turn " + currentTurn + " is end.");
            currentTurn++;
            elapsedTurnTime = 0;
            
            gametimeEnd = System.currentTimeMillis();
            elapsedGameTime = (gametimeEnd - gametimeStart) / 1000;
        }
        System.out.println("Game is end. You lose " + notCrushedCandies() + " points cause of not crushed candies. Your current point is : " + point);
    }
    
    public static void proccesString(String a) {
        if (a.length() <= 3) { //for solving StringIndexOutOfBoundsException
            System.out.println("Wrong operation quest.");
        } else {
            if (a.charAt(0) == 'p' && a.charAt(1) == 'p' && isCorrect(a.charAt(3)) && isCorrect(a.charAt(5))) {               
                LinkedStack stck1 = stackSelector(a.charAt(3));
                LinkedStack stck2 = stackSelector(a.charAt(5));                
                popPushOperation(stck1, stck2);                                                                
            } else if (a.charAt(0) == 'c' && a.charAt(1) == 'r' && isCorrect(a.charAt(3))) {
                LinkedStack stck = stackSelector(a.charAt(3));
                crushCandy(stck);
            } else {
                System.out.println("Wrong operation quest.");
            }
        }
    }
    
    public static LinkedStack stackSelector(char x) {
        LinkedStack stck = null;
        switch (x) {
            case '1':
                stck = stack1;
                break;
            case '2':
                stck = stack2;
                break;
            case '3':
                stck = stack3;
                break;
            default:
                break;
        }
        return stck;
    }
    public static boolean isCorrect(char x){
        return x == '1' || x == '2' || x == '3';
    }
    public static void popPushOperation(LinkedStack x, LinkedStack y) {
        if (!x.isEmpty()) {
            Candy tmp = x.pop();
            Candy nCandy = new Candy(tmp.color, tmp.shape, tmp.symbol);
            y.push(nCandy);
        }
        else {
            System.out.println("You cannot do this operation.");
        }
        
    }
    
    public static void crushCandy(LinkedStack a) {
        try {
            Candy tmp = a.pop();
            Candy tmp2 = a.pop();
            
            boolean changetmp = true;
            int bothSamecount = 0;
            int onlyoneSamecount = 0;
            while ((tmp.color.equals(tmp2.color) || tmp.shape.equals(tmp2.shape))) {
                if (tmp.color.equals(tmp2.color) && tmp.shape.equals(tmp2.shape)) {
                    bothSamecount++;
                } else {
                    onlyoneSamecount++;
                }
                
                if (changetmp && !a.isEmpty()) {
                    tmp = a.pop();
                    if (!tmp.color.equals(tmp2.color) && !tmp.shape.equals(tmp2.shape)) {                        
                        a.push(tmp);
                        break;
                    }
                    changetmp = false;
                } else if (!changetmp && !a.isEmpty()) {
                    tmp2 = a.pop();
                    if (!tmp.color.equals(tmp2.color) && !tmp.shape.equals(tmp2.shape)) {                        
                        a.push(tmp2);
                        break;
                    }
                    changetmp = true;
                } else {
                    break;
                }
            }            
            int pointOld = point;
            if (bothSamecount > 0) {
                point += Math.pow(2, bothSamecount + 2);
            }
            if (onlyoneSamecount > 0) {
                point += Math.pow(2, onlyoneSamecount);
            }            
            System.out.println("You gain " + (point - pointOld) + " point(s).");            
        } catch (Exception e) {
            System.out.println("You cannot crush this stack");
        }
        
    }
    
    public static int notCrushedCandies() {
        int minus = (stack1.size + stack2.size + stack3.size) * 2;
        point -= minus;
        return minus;
    }
}
