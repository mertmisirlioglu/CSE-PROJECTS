/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package cse202.project1.messagetransferlıne;

import java.util.ArrayList;
import java.util.Random;

/**
 *
 * @author Mert
 */
public class DoubleLinkList {

    Student first;
    Student last;
    int size;
    

    public void insertNStudent(int N) {
        int cnt = 0;
        while (cnt < N) {
            Student a = new Student();
            if (first == null && last == null) {
                first = a;
                last = a;
                first.order = 1;
                first.next = a;
                last.previous = a;
                size++;
            } else {
                a.order = ++size;
                last.next = a;
                a.previous = last;
                last = a;

            }
            cnt++;
        }
    }
    public boolean setDirection(Student a , int k){
        Random random = new Random();
        boolean rndm = random.nextBoolean();
        if (rndm) {
            if ((a.order + k) <= this.last.order) {
                return true;
            } else {
                return false;
            }
    
        } else {
            if ((a.order - k) >= this.first.order) {
                return false;
            } else {
                return true;
            }
        }
        
        
    }
    public DoubleLinkList MessagePasser(String message, int k, String source) throws NameMismatch {
        DoubleLinkList ml = new DoubleLinkList();
        Student tmp = findByname(source);
        if (tmp == null) {
            throw new NameMismatch();
        }
         boolean direction = true;
         if (tmp == this.first) {
            direction = true;
        } else if(tmp == this.last){
            direction = false;
        }else{
            direction = setDirection(tmp, k);
        }
        
         
            ml.insertLast(tmp.copy());

        
         
        int count = 0;
        tmp.message = message;
        Student tmpprev = tmp.previous;
        Student tmpnext = tmp.next;
        while (count < k) {
            if (direction) {
                if (!tmpnext.isHubStudent()) {
                    
                    tmpnext.message = tmp.message;
                    ml.insertLast(tmpnext.copy());
                    tmpnext = tmpnext.next;

                } else {
                    boolean check = checkHubStudent(tmp.next, message);
                    if (check) {
                        DoubleLinkList temporary = MessagePasser(message, k, tmpnext.name);
                        ml.last.next = temporary.first;
                        temporary.first.previous = ml.last;
                        ml.last = temporary.last;
                        return ml;
                    } else {
                        tmpnext.message = tmp.message;
                        ml.insertLast(tmpnext.copy());
                        tmpnext = tmpnext.next;
                    }
                    
                }
                count++;
                
                
            } else {
                if (!tmpprev.isHubStudent()) {
                     tmpprev.message = tmp.message;
                     ml.insertLast(tmpprev.copy());
                     tmpprev = tmpprev.previous;
                } else {
                     boolean check = checkHubStudent(tmp, message);
                    if (check) {
                        DoubleLinkList temporary = MessagePasser(message, k, tmpprev.name);
                        ml.last.next = temporary.first;
                        temporary.first.previous = ml.last;
                        ml.last = temporary.last;
                        return ml;
                    } else {
                        tmpprev.message = tmp.message;
                        ml.insertLast(tmpprev.copy());
                        tmpprev = tmpprev.previous;
                    }                   
                }
               
                count++;
            }
            
        }   
        return ml;
    }

    public Student insertLast(Student n1) {

        if (first == null && last == null) {
            first = n1;
            first.previous = null;
            first.next = last;
            last = n1;
            last.previous = first;
            last.next = null;
        } else {
            Student tmp = last;
            last.next = n1;
            n1.previous = last;
            last = n1;
            last.next = null;
            last.previous = tmp;
        }
        size++;
        return n1;
    }

    public boolean checkHubStudent(Student a, String message) {
        int count = 0;
        StringBuilder change = new StringBuilder(a.name);
        StringBuilder change2 = new StringBuilder(message);

        for (int i = 0; i < change.length(); i++) {
            for (int j = 0; j < change2.length(); j++) {
                if (change.charAt(i) == change2.charAt(j)) {
                    count++;
                    change.setCharAt(i, ' '); // I'm doing this for I dont do this some letter will count twice or more.
                    change2.setCharAt(j, ' ');
                }
            }
        }
        if (count >= 2) {
            return true;
        } else {
            return false;
        }
    }

    public Student findByname(String name) {
        Student tmp = first;
        String namehub = name + "*";
        while (tmp != null) {
            if (name.equals(tmp.name) || namehub.equals(tmp.name)) {
                return tmp;
            }
            tmp = tmp.next;
        }
        return null;
    }

    public Student findByOrder(int Order) {
        Student tmp = first;

        while (tmp != null) {
            if (tmp.order == Order) {
                return tmp;
            } else {
                tmp = tmp.next;
            }
        }
        return null;
    }

    public void selectHubStudents(int M) {
        Random random = new Random();
        Student tmp = first;
        int k = 0;
        int cnt = 0;
        while (cnt < M) {
            k = random.nextInt(this.size)+1;
            tmp = findByOrder(k);
            if (tmp.name.charAt(tmp.name.length() - 1) != '*') {
                tmp.name += "*";
                cnt++;
            }

        }

    }

    @Override
    public String toString() {
        String k = "";
        Student tmp = first;
        while (tmp != null) {
            k += tmp.name + "---";
            tmp = tmp.next;
        }
        return k;
    }

}
