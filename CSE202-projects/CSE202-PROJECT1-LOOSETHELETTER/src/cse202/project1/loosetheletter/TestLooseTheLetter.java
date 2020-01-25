/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package cse202.project1.loosetheletter;

import java.util.Random;

/**
 *
 * @author Mert
 */
public class TestLooseTheLetter {

    public static void main(String[] args) {
        int N = 1000;  //N= 100 tooks under 1 seconds , N = 1000 tooks 30 seconds for names.txt
        //N = 1000 tooks nearly 2 minute , N = 100 tooks 7 seconds for names2.txt. According to my computer.
        CircularLinkedList cl = new CircularLinkedList();
        cl.insertNPeople(N);

        Random random = new Random();
        int k = 0;
        int cnt = cl.size;
        while (cl.size > 1) {
            
                k = random.nextInt(cl.size) + 1;
                People tmp = cl.indicateWho(k);
                cl.looseLetter(tmp);
                System.out.print("K is : " + k);
                System.out.print("  // Choosen is : " + tmp.original);
                String org = tmp.name;
                tmp.name = "<" + tmp.name + ">";

                if (cl.size == cnt) {
                    System.out.println("  // " + cl);
                } else {
                    System.out.println("  // Good bye " + tmp.original + ".  //  New List is : "+ cl);
                    cnt--;
                }

                tmp.name = org;
           
        }

        System.out.println("--------------------------------------");
        System.out.println("AND THEEEE WINNNERRR IS : " + cl.head.original);
        System.out.println("--------------------------------------");

    }
}
