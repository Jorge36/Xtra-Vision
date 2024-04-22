/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ApplicationClasses.Payment;

import ApplicationClasses.User.Customer;
import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.Properties;
import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.PasswordAuthentication;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;

/**
 *
 * @author
 */
public class Payment {
    
    private int idPayment;
    private LocalDate paymentDate;
    private BigDecimal amount;
    private String description;
    private Customer customer;
    
    public void issueReceipt() throws MessagingException {
        
        // Add recipient
        String to = this.customer.getEmail();

       // Add sender
       String from = "xtravisionxpress@gmail.com";
       final String username = "xtravisionxpress@gmail.com";//your Gmail username 
       final String password = "xtravisionxpress2021";//your Gmail password

       String host = "smtp.gmail.com";

       Properties props = new Properties();
        props.put("mail.smtp.auth", "true");
        props.put("mail.smtp.starttls.enable", "true"); 
        props.put("mail.smtp.ssl.trust", "smtp.gmail.com");
        props.put("mail.smtp.host", host);
        props.put("mail.smtp.port", "587");

        // Get the Session object
        Session session = Session.getInstance(props,
        new javax.mail.Authenticator() {
            @Override
            protected PasswordAuthentication getPasswordAuthentication() {
            return new PasswordAuthentication(username, password);
        }
        });

        // Create a default MimeMessage object
        Message message = new MimeMessage(session);

        message.setFrom(new InternetAddress(from));

        message.setRecipients(Message.RecipientType.TO,
        InternetAddress.parse(to));

        // Set Subject
        message.setSubject("Receipt from Xtra-Vision Rental");

        // Put the content of your message
        message.setText(this.description);

        // Send message
        Transport.send(message);

    }
    
    public void pay(paymentMethod pMethod) {
        
        if (pMethod instanceof Card) {
            // get IdCustomer (version encrypted) from customer attribute and idMethodPayment from database
            // send IdCustomer, and if idMethodPayment is not null, send it to the class Card.
            pMethod.pay(amount);
            // if idMethodPayment was null, get it from card class
            // then save idCustomer, idMethodPayment in the database, we could use them to charge a
            // customers when they are late returning a disc
        }    
        
    }
        
    public void openUnlimitedAccount(paymentMethod pMethod) {
        
        if (pMethod instanceof Card) {
        
            // get IdCustomer (version encrypted) from customer attribute and idMethodPayment from database
            // send IdCustomer, and if idMethodPayment is not null, send it to the class Card.
            // set attribute open plan in card to true
            pMethod.pay(amount);
            // if idMethodPayment was null, get it from card class
            // then save idCustomer, idMethodPayment in the database, we could use them to charge a
            // customers
            
        }
    }
    
    public void closeUnlimitedAccount(paymentMethod pMethod) {
        
        if (pMethod instanceof Card) {
        
            // get IdCustomer (version encrypted) from customer attribute and idMethodPayment from database
            // send IdCustomer, and if idMethodPayment is not null, send it to the class Card.
            // set attribute close plan in card to true
            pMethod.pay(amount);
            // if idMethodPayment was null, get it from card class
            // then save idCustomer, idMethodPayment in the database, we could use them to charge a
            // customers  
        }
        
    }

    public Payment(LocalDate paymentDate, BigDecimal amount, String description, Customer customer) {
        this.paymentDate = paymentDate;
        this.amount = amount;
        this.description = description;
        this.customer = customer;
    }
    
    
    
    
    
    
}
