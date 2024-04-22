/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ApplicationClasses.Rental;

import java.math.BigDecimal;
import java.time.LocalDate;

/**
 *
 * @author 
 */
public class Offer {
    
    protected final int id; // id
    protected final BigDecimal price; // price for each disc/movie
    protected final BigDecimal totalPrice;
    protected final double days; // For how many days can a customer rent a movie?
    protected final int quantity; // quantity of discs/movies
    protected final String description;

    public int getId() {
        return id;
    }

    public LocalDate getReturnDate(LocalDate date) {
        
        if (days == Double.POSITIVE_INFINITY)
            return LocalDate.MAX;
        else 
            return date.plusDays((long) days);
    }

    public BigDecimal getTotalPrice() {
        return totalPrice;
    }
    
    

    public BigDecimal getPrice() {
        return price;
    }
        
    public int getQuantity() {
        return quantity;
    }

    public Offer(int id, BigDecimal price, BigDecimal totalPrice, double days, int quantity, String description) { // days >= 1
        this.id = id;
        this.price = price;
        this.totalPrice = totalPrice;
        this.days = days;
        this.quantity = quantity;
        this.description = description;
    }

    public String getDescription() {
        return this.description;
    }

    @Override
    public String toString() {
        return this.id + ". " + this.description;
    }
}
