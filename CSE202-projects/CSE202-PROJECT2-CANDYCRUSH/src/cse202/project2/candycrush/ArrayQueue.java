package cse202.project2.candycrush;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.util.Random;
import java.util.logging.Level;
import java.util.logging.Logger;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
/**
 *
 * @author Mert
 */
public class ArrayQueue {  
    Candy[] q;
    int size;
    int first;
    int last;

    public ArrayQueue(int capacity) {
        this.q = new Candy[capacity];
        createRandomCandy(capacity);
    }

    public boolean isFull() {
        if (size == q.length) {
            return true;
        }
        return false;
    }

    public boolean isEmpty() {
        if (size == 0) {
            return true;
        }
        return false;
    }

    public void enqueue(Candy a) {
        if (size == q.length) {
            resize();
        }
        if (last == q.length) {
            last = 0;
        }
        q[last] = a;
        last++;
        size++;
    }

    public void createRandomCandy(int q) {
        try {
            Candy[] candy = candyArray();
            Random rnd = new Random();
            int k = 0;
            for (int i = 0; i < q; i++) {
                k = rnd.nextInt(candy.length); // choosen randomly
                Candy nCndy = new Candy(candy[k].color, candy[k].shape, candy[k].symbol);
                this.enqueue(nCndy);
            }
        } catch (IOException ex) {
            Logger.getLogger(ArrayQueue.class.getName()).log(Level.SEVERE, null, ex);
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(ArrayQueue.class.getName()).log(Level.SEVERE, null, ex);
        }

    }

    public Candy[] candyArray() throws FileNotFoundException, IOException, ClassNotFoundException {
        Candy[] candy = new Candy[16]; // objects number of Candys.txt

        FileInputStream fi = new FileInputStream(new File("Candys.txt"));
        ObjectInputStream oi = new ObjectInputStream(fi);

        for (int i = 0; i < candy.length; i++) {
            candy[i] = (Candy) oi.readObject();
        }
        return candy;

    }

    public Candy dequeue() {
        if (isEmpty()) {
            throw new java.util.NoSuchElementException();
        }
        Candy tmp = q[first];
        first++;
        if (first == q.length) {
            first = 0;
        }
        size--;
        return tmp;
    }

    public String printnextFiveCandy() {
        String k = "";
        for (int i = 0; i < 5; i++) {
            if (i != size) {
                k += q[(first + i) % q.length] + " >>";
            } else {
                break;
            }

        }
        return k;
    }

    public void resize() {
        Candy[] nArray = new Candy[q.length * 2];
        for (int i = 0; i < q.length; i++) {
            nArray[i] = q[i];
        }
        this.q = nArray;
    }

    @Override
    public String toString() {
        String k = "";
        for (int i = 0; i < size; i++) {
            k += q[(first + i) % q.length] + " >>";
        }
        return k;
    }

}
