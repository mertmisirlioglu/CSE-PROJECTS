/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package cse202.project1.messagetransferlıne;

import java.io.DataInputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Random;
import java.util.Scanner;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author Mert
 */
public class Student {
    String name;
    String message;
    Student next;
    Student previous;
    int order;

    public Student() {
    try {
            this.name = pickaRandomName();
        } catch (IOException ex) {
            Logger.getLogger(Student.class.getName()).log(Level.SEVERE, null, ex);
        }
        this.next = null;
        this.previous = null;
    }

    public boolean isHubStudent(){
        if (this.name.charAt(this.name.length()-1) == '*') {
            return true;
        } else {
            return false;
        }
    }
    public Student copy(){
        Student a = new Student();
        a.name = this.name;
        a.order = this.order;
        a.message = this.message;
        return a;
    }
    public String pickaRandomName() throws FileNotFoundException, IOException {
        File namelist = new File("names.txt"); 
        FileInputStream read = new FileInputStream(namelist);
        DataInputStream read2 = new DataInputStream(read);

        Random random = new Random();

        int number = random.nextInt(100); //namelist contains 100 names. 
        String choosenName = read2.readLine();

        for (int i = 0; i < 100; i++) { 
            if (i == number) {
                read2.close();
                read.close();
                return choosenName;
            }
            choosenName = read2.readLine();
        }
        return choosenName;
    }

    @Override
    public String toString() {
        return name; //To change body of generated methods, choose Tools | Templates.
    }
    
    
}
