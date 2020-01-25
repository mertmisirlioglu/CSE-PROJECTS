/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package cse202.project1.loosetheletter;

/**
 *
 * @author Mert
 */
public class CircularLinkedList {
    People head;
    People tail;
    int count;
    int size = 0;
    
    public void insertNPeople(int N) {
        count = N - 1;
        int cnt = 0;
        while (cnt < N) {
            People a = new People();
            if (head == null) {
                head = a;
                tail = a;
                a.next = head;
                head.order = count;
                tail.order = count;
            } else {
                People tmp = head;
                a.next = tmp;
                head = a;
                tail.next = head;
                a.order = count--;

            }
            size++;
            cnt++;
        }
        tail.order = N;
    }

    public People findByOrder(int Order){
        People tmp = head.next;
        if (this.head.order == Order) {
            return head;
        }
        while (tmp != head) {            
            if (tmp.order == Order) {
                return tmp;
            }else{
                tmp = tmp.next;
            }         
        }
        return null;
    }
    public People indicateWho(int k){
        People tmp = findByOrder(k);
        return tmp;
    }
    public People looseLetter(People a){
        
        
        if (a.name.length() != 1) {
            StringBuffer sBuffer = new StringBuffer();
            sBuffer.append(a.name);
            sBuffer.deleteCharAt((a.name.length()-1));
            a.name = sBuffer.toString();
        } else {
            changeOrder(a.next);
            deletePeople(a);
            
        }
        
        
        return a;
    }
    
    public void deletePeople(People a){
              
        if (a.order == 1) 
        {
            head = head.next;
            tail.next = head;
            size--; 
            return ;
        }
        if (a.order == size) 
        {
            People s = head;
            People t = head;
            while (s != tail)
            {
                t = s;
                s = s.next;
            }
            tail = t;
            tail.next = head;
            size --;
            return;
        }
         
        People ptr = head;
        a.order = a.order - 1 ;
        for (int i = 1; i < size - 1; i++) 
        {
            if (i == a.order) 
            {
                People tmp = ptr.next;
                tmp = tmp.next;
                ptr.next = tmp;
                break;
            }
            ptr = ptr.next;
        }
        size-- ;
    }
        public void changeOrder(People a){
            while (a != head ) {                
                a.order -= 1;
                a = a.next;
            }
        }
    
    
    @Override
    public String toString() {
        People tmp = head.next;
        String k = head + "---";
        while (tmp != head) {            
            k+= tmp.name + "---";
            tmp = tmp.next;
        }
    return k;
    }
    
}
