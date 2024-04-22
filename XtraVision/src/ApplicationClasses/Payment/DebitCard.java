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
public class DebitCard extends Card {

    @Override
    protected void executeTransaction(BigDecimal amount) {
        
        if (!openPlan && !closePlan) {
        
            // call payment card service
            // if idCustomer is null
            //      payService.execute(this.amount, "EUR", this.pan, this.cvv, this.expirationDate, this.nameOnCard)
            // if idCustomer is not null AND idMethodPayment is null {
            //      idMethodPayment = payService.VAULT(idCustomer, this.pan, this.cvv, this.expirationDate, this.nameOnCard)  
            //      payService.execute(this.amount, "EUR", idCustomer,  idMethodPayment)
            // } else if idCustomer and idMethodPayment are not null
            //      payService.execute(this.amount, "EUR", idCustomer,  idMethodPayment)
            
            
        } else if (openPlan && closePlan) {
            
            // ERROR
            
        } else if (openPlan) {
            
            // call payment card service
            // if idCustomer is null
            //      createAgreement(this.pan, this.cvv, this.expirationDate, this.nameOnCard, IdPlan)
            // if idCustomer is not null AND idMethodPayment is null {
            //      idMethodPayment = payService.VAULT(idCustomer, this.pan, this.cvv, this.expirationDate, this.nameOnCard)    
            //      createAgreement(idCustomer, idMethodPayment, IdPlan)
            // } else if idCustomer and idMethodPayment are not null
            //      createAgreement(idCustomer, idMethodPayment, IdPlan)
                        
        } else if (closePlan) {

            // call payment card service
            // if idCustomer is null
            //      cancelAgreement(this.pan, this.cvv, this.expirationDate, this.nameOnCard, IdPlan)
            // if idCustomer is not null AND idMethodPayment is null { 
            //      idMethodPayment = payService.VAULT(idCustomer, this.pan, this.cvv, this.expirationDate, this.nameOnCard)
            //      cancelAgreement(idCustomer, idMethodPayment, IdPlan)
            // } else if idCustomer and idMethodPayment are not null
            //      cancelAgreement(idCustomer, idMethodPayment, IdPlan)
                        

        }
    }

    @Override
    public void pay(BigDecimal amount) {
        executeTransaction(amount);
    }

    public DebitCard(String pan, String nameOnCard, LocalDate expirationDate, int pin, int cvv) {
        super(pan, nameOnCard, expirationDate, pin, cvv);
    }

    
    
    
    
    
    
    
}
