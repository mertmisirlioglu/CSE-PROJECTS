/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package cse202.project1.messagetransferlıne;

/**
 *
 * @author Mert
 */
public class NameMismatch extends Exception {

    /**
     * Creates a new instance of <code>NameMismatch</code> without detail
     * message.
     */
    public NameMismatch() {
    }

    /**
     * Constructs an instance of <code>NameMismatch</code> with the specified
     * detail message.
     *
     * @param msg the detail message.
     */
    public NameMismatch(String msg) {
        super(msg);
    }
}
