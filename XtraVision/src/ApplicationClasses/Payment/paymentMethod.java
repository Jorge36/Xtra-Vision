/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ApplicationClasses.Payment;

import java.math.BigDecimal;

/**
 *
 * @author
 */
public interface paymentMethod {
    
    
    public void pay(BigDecimal amount);
    
}
