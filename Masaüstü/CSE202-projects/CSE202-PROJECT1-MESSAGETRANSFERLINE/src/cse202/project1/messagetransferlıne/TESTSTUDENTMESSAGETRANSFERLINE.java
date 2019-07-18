/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package cse202.project1.messagetransferlıne;

import java.util.Scanner;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author Mert
 */
public class TESTSTUDENTMESSAGETRANSFERLINE {

    public static void main(String[] args) {
        Scanner a = new Scanner(System.in);

        DoubleLinkList dl = new DoubleLinkList();
        System.out.println("Enter the number of hubs p: ");
        
        int p = a.nextInt();
        
        dl.insertNStudent(40);
        dl.selectHubStudents(p);
        System.out.println("…Creating a linked list of 40 students ," + p + " are hubs");
        System.out.println("Enter the number of students to pass the message k : ");
        
        int k = a.nextInt();
        
        System.out.println("---Displaying the student message train,  hubs have star*:");
        System.out.println(dl);
        System.out.println("How many Times you want to transfer message ?");
        
        int replay = a.nextInt();
        int count = 0;

        while (count != replay) {
            System.out.print("Enter the message: ");
            String message = a.next();
            System.out.print("Enter the source student: ");
            String name = a.next();
            
            
            try {
                
                DoubleLinkList d2;
                d2 = dl.MessagePasser(message, k, name);
                System.out.print(name + " passes message '" + message + "' to forward : ");
                printTheReceivers(d2);
            } catch (NameMismatch ex) {
                System.out.println("Name mismatch ! Try again.");
            }
            
           count++; 

        }

    }

    public static void printTheReceivers(DoubleLinkList dl) {
        Student tmp = dl.first.next;
        while (tmp != null) {
            if (tmp != dl.last) {
                System.out.print(tmp.name + ",");
            } else {
                System.out.print(tmp.name);
            }
            tmp = tmp.next;
        }
        System.out.println();
    }
}
