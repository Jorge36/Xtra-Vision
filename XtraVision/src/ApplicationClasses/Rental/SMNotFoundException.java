/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ApplicationClasses.Rental;

import Model.*;

/**
 * This class is used to send errors from database to the controller
 * Database throws an error, this one is caught. Then an instance of
 * this class is created witht error message. Then DAO methods throws
 * this exception
 * @author
 */
public class SMNotFoundException extends Exception {

    /**
     * Creates a new instance of <code>DBException</code> without detail
     * message.
     */
    public SMNotFoundException() {
    }

    /**
     * Constructs an instance of <code>DBException</code> with the specified
     * detail message.
     *
     * @param msg the detail message.
     */
    public SMNotFoundException(String msg) {
        super(msg);
    }
}
