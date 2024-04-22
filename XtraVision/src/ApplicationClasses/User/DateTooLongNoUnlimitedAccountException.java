/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ApplicationClasses.User;

import ApplicationClasses.Rental.*;
import Model.*;

/**
 * This class is used to send errors from database to the controller
 * Database throws an error, this one is caught. Then an instance of
 * this class is created witht error message. Then DAO methods throws
 * this exception
 * @author
 */
public class DateTooLongNoUnlimitedAccountException extends Exception {

    /**
     * Creates a new instance of <code>DBException</code> without detail
     * message.
     */
    public DateTooLongNoUnlimitedAccountException() {
    }

    /**
     * Constructs an instance of <code>DBException</code> with the specified
     * detail message.
     *
     * @param msg the detail message.
     */
    public DateTooLongNoUnlimitedAccountException(String msg) {
        super(msg);
    }
}
