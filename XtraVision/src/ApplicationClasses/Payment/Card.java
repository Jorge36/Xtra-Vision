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
public abstract class Card implements paymentMethod {
    
    protected String pan;
    protected String nameOnCard;
    protected LocalDate expirationDate;
    protected int pin;
    protected int cvv;
    protected String idMethodPayment; // to pay with it, if we dont have this id, we have to send all the card details
    protected String idCustomer; // to send it along with the idMethodPayment, to make the payment quicker
    protected String idPlan = "123456789SADREA"; 
    protected boolean openPlan;
    protected boolean closePlan;
            
    
    protected abstract void executeTransaction(BigDecimal amount);

    public Card(String pan, String nameOnCard, LocalDate expirationDate, int pin, int cvv) {
        this.pan = pan;
        this.nameOnCard = nameOnCard;
        this.expirationDate = expirationDate;
        this.pin = pin;
        this.cvv = cvv;
        this.openPlan = false;
        this.closePlan = false;
    }

    public String getPan() {
        return pan;
    }

    public String getNameOnCard() {
        return nameOnCard;
    }

    public void setIdMethodPayment(String idMethodPayment) {
        this.idMethodPayment = idMethodPayment;
    }

    public void setIdCustomer(String idCustomer) {
        this.idCustomer = idCustomer;
    }

    public String getIdMethodPayment() {
        return idMethodPayment;
    }

    public String getIdCustomer() {
        return idCustomer;
    }

    public String getIdPlan() {
        return idPlan;
    }

    public void setIdPlan(String idPlan) {
        this.idPlan = idPlan;
    }
    
    
    
}
