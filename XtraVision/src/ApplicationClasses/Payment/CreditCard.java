/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ApplicationClasses.Payment;

import java.math.BigDecimal;
import java.time.LocalDate;

/**
 *
 * @author
 */
public class CreditCard extends Card {

    @Override
    protected void executeTransaction(BigDecimal amount) {
        // Not implemented

    }

    @Override
    public void pay(BigDecimal amount) {
        executeTransaction(amount);
    }

    public CreditCard(String pan, String nameOnCard, LocalDate expirationDate, int pin, int cvv) {
        super(pan, nameOnCard, expirationDate, pin, cvv);
    }

    
    
    
    
    
}
