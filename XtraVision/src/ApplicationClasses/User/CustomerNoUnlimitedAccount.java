/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ApplicationClasses.User;

/**
 *
 * @author
 */
public class CustomerNoUnlimitedAccount extends Exception {

    /**
     * Creates a new instance of <code>CustomerNoUnlimitedAccount</code> without
     * detail message.
     */
    public CustomerNoUnlimitedAccount() {
    }

    /**
     * Constructs an instance of <code>CustomerNoUnlimitedAccount</code> with
     * the specified detail message.
     *
     * @param msg the detail message.
     */
    public CustomerNoUnlimitedAccount(String msg) {
        super(msg);
    }
}
