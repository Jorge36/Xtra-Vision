/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ApplicationClasses.User;

import java.time.DateTimeException;
import java.time.LocalDate;
import java.time.Year;

/**
 *
 * @author
 */
public class Customer {
    
    
    private String idCustomer; // we should use an algorithm to hide or encrypt this the pan number because we must not save card details
    private Boolean unlimitedAccount;
    private String email;
    private LocalDate endUnlimitedAccountDate;
    private String name;

    public Customer(String idCustomer, Boolean unlimitedAccount, String email, LocalDate endUnlimitedAccountdate, String name) throws DateTooLongNoUnlimitedAccountException, CustomerNoUnlimitedAccount {
        this.idCustomer = idCustomer;
        this.unlimitedAccount = unlimitedAccount;
        this.email = email;
        this.name = name;
        this.endUnlimitedAccountDate = endUnlimitedAccountdate;
        
        if (!this.unlimitedAccount && this.endUnlimitedAccountDate != null && this.endUnlimitedAccountDate.getYear() == Year.MAX_VALUE) 
                throw new DateTooLongNoUnlimitedAccountException("This customer does not have an unlimited Account, the date can not be in the future. Its year format can not be Year.MAX_VALUE or +999,999,999");
       
        if (this.unlimitedAccount && (this.endUnlimitedAccountDate == null || this.endUnlimitedAccountDate.getYear() != Year.MAX_VALUE))
                throw new CustomerNoUnlimitedAccount("This customer does not have an unlimited account. End unlimited account date must have the format for Year equal to Year.MAX_VALUE or +999,999,999");
        
    }

    public Customer(String idCustomer, String name) {
        this.idCustomer = idCustomer;
        this.unlimitedAccount = false;
        this.email = "";
        this.name = name;
    }
    

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getIdCustomer() {
        return idCustomer;
    }

    public boolean getUnlimitedAccount() {
        return unlimitedAccount;
    }
    
    // this date could have a date in the future (YEAR.MAX) and 
    // when the account is closed, the date is set by fixed date 
    public LocalDate getEndUnlimitedAccountdate() {
        
        return this.endUnlimitedAccountDate;
    }
    
    // we get the end date of the subscription period in the current month or next month. When the account is closed
    // endunlimitedAccountDate and this method are gonna have same date when the account is closed
    public LocalDate getEndDateOfSubscriptionPeriod() {
        
        if (this.endUnlimitedAccountDate == null)
            return null;

        LocalDate dateAux;
        
        if (this.unlimitedAccount && this.endUnlimitedAccountDate.getYear() == Year.MAX_VALUE) // user has unlimited account... 
            if (LocalDate.now().getDayOfMonth() > this.endUnlimitedAccountDate.getDayOfMonth()) { // current day is greater than the day of unlimited date 

                try { // create the date with the day number, and add one month, the method plusMonth solves problem like 31 January, return 28 February
                    dateAux = LocalDate.of(LocalDate.now().getYear(), LocalDate.now().getMonth(), this.endUnlimitedAccountDate.getDayOfMonth());
                    return dateAux.plusMonths(1);
                } catch (DateTimeException ex1) { }
            
                // if the previous one does not exist, we add one month to the current one and change the date...
                dateAux = LocalDate.now().plusMonths(1);
                return LocalDate.of(dateAux.getYear(), dateAux.getMonth(), this.endUnlimitedAccountDate.getDayOfMonth());
            
            } else {
                
                try { // create the date with the day number
                    return LocalDate.of(LocalDate.now().getYear(), LocalDate.now().getMonth(), this.endUnlimitedAccountDate.getDayOfMonth());
                } catch (DateTimeException ex1) { 
            
                // if the previous one does not exist, we add the last day for this month
                return LocalDate.of(LocalDate.now().getYear(), LocalDate.now().getMonth(), LocalDate.now().getMonth().maxLength());

                }                
                                
            }    
        else 
            // user does not have unlimited account 
            return this.endUnlimitedAccountDate;  
        
    }

    public String getName() {
        return name;
    }

    public void closeAccount() throws CustomerNoUnlimitedAccount {
        
        if (this.unlimitedAccount == false)
            throw new CustomerNoUnlimitedAccount("Customer does not have an unlimited account");
                
        if (LocalDate.now().getDayOfMonth() >= this.endUnlimitedAccountDate.getDayOfMonth()) { // close account increment one month
            
            try {
                // search same day in the next month
                LocalDate dateAux = LocalDate.of(LocalDate.now().getYear(), LocalDate.now().getMonth(), this.endUnlimitedAccountDate.getDayOfMonth());
                this.endUnlimitedAccountDate = dateAux.plusMonths(1);
                this.unlimitedAccount = false;
                return;
            } catch (DateTimeException ex1) {}
        
            try {
                // if that day does not exist in the current month, so try in the next month
                LocalDate dateAux = LocalDate.now().plusMonths(1);
                this.endUnlimitedAccountDate = LocalDate.of(dateAux.getYear(), dateAux.getMonth(), this.endUnlimitedAccountDate.getDayOfMonth());

            } catch (DateTimeException ex1) {};

            // return the max day in the next month
            LocalDate dateAux = LocalDate.now().plusMonths(1);
            this.endUnlimitedAccountDate = LocalDate.of(dateAux.getYear(), dateAux.getMonth(), dateAux.getMonth().maxLength());
        
            
        } else {
            
            try {
                // current date and we add the da
                this.endUnlimitedAccountDate = LocalDate.of(LocalDate.now().getYear(), LocalDate.now().getMonth(), this.endUnlimitedAccountDate.getDayOfMonth());
            } catch (DateTimeException ex1) {};
            
            this.endUnlimitedAccountDate = LocalDate.of(LocalDate.now().getYear(), LocalDate.now().getMonth(), LocalDate.now().getMonth().maxLength());

            
        }
        
    }
    
    // Open an account today
    public void openAccount() {
        
        this.unlimitedAccount = true;
        this.endUnlimitedAccountDate = LocalDate.of(Year.MAX_VALUE, LocalDate.now().getMonth(), LocalDate.now().getDayOfMonth());

    }
    
}
