/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package cse202.project1.loosetheletter;

import java.io.DataInputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Random;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author Mert
 */
public class People {

    
    String name;
    String original;
    int order;
    People next;
    
    

    public People() {
        try {
            this.name = pickaRandomName();
        } catch (IOException ex) {
            Logger.getLogger(People.class.getName()).log(Level.SEVERE, null, ex);
        }
        this.next = null;
        this.original = this.name;
    }

    public String pickaRandomName() throws FileNotFoundException, IOException {
        File namelist = new File("names.txt"); // *1
        FileInputStream read = new FileInputStream(namelist);
        DataInputStream read2 = new DataInputStream(read);

        Random random = new Random();

        int number = random.nextInt(37); //namelist contains 37 names. *2
    //I found another name list have many non-Turkisch names.It contains 4945 different names. All of these names are four letter. If you want to try 
    // *1 = names2.txt , *2 = random.nextInt(4945); , *3 i < 4944 
        String choosenName = read2.readLine();

        for (int i = 0; i < 36; i++) { // *3
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
