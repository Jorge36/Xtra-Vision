/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ApplicationClasses.Rental;

import java.math.BigDecimal;

/**
 *
 * @author
 */
public class FreeOffer extends Offer {
    
    private String code;

    public FreeOffer(String code, int id, BigDecimal price, BigDecimal totalPrice, double days, int quantity, String description) {
        super(id, price, totalPrice, days, quantity, description);
        this.code = code;
    }

    
}
