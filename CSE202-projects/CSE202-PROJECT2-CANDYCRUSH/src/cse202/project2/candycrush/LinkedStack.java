/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package cse202.project2.candycrush;

/**
 *
 * @author Mert
 */
public class LinkedStack {
    Candy top;
    int size;

    public LinkedStack() {
    }

    public void push(Candy a) {
        if (top == null) {
            top = a;
        } else {
            a.next = top;
            top = a;
        }
        size++;
    }

    public Candy pop() {
        if (isEmpty()) {
            throw new java.util.NoSuchElementException();
        }
        if (top.next == null) {
            Candy tmp = top;
            top = null;
            size--;
            return tmp;
        } else {
            size--;
            Candy tmp = top;
            if (top.next != null) {
                top = top.next;
            } else {
                top = null;
            }

            return tmp;
        }

    }

    public boolean isEmpty() {
        return size == 0;
    }
    public LinkedStack reverseStack(){
        LinkedStack ls = new LinkedStack();
        Candy tmp = this.top;
        while (tmp != null) {            
            Candy tmp2 = new Candy(tmp.color, tmp.shape, tmp.symbol);
            ls.push(tmp2);
            tmp = tmp.next;
        }
        return ls;
    }
    @Override
    public String toString() {
        
        Candy tmp = reverseStack().top;
        String k = "";
        while (tmp != null) {
            k += tmp.toString() + "<<";
            tmp = tmp.next;
        }
        return k;
    }

}
