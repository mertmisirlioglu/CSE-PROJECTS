/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package cse202.project2.candycrush;

import java.io.Serializable;

/**
 *
 * @author Mert
 */
public class Candy implements Serializable{
    String color;
    String shape;
    char symbol;
    
    Candy next;

    public Candy(String color , String shape, char symbol) {       
        this.color = color;
        this.shape = shape;
        this.symbol = symbol;
        this.next = null;
    }

    @Override
    public String toString() {
        if (color.equals("Red")) {
            return ConsoleColors.RED + (symbol + " " + color + " " + shape)
                    + ConsoleColors.RESET;

        } else if (color.equals("Blue")) {
            return ConsoleColors.BLUE + (symbol + " " + color + " " + shape)
                    + ConsoleColors.RESET;
        } else {
            return (symbol + " " + color + " " + shape);
        }

    }

    class ConsoleColors {
    // Reset
    public static final String RESET = "\033[0m";  // Text Reset

    // Regular Colors   
    public static final String RED = "\033[0;31m";     // RED
    public static final String BLUE = "\033[0;34m";    // BLUE
   
    }
}
