/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ApplicationClasses.Rental;

import ApplicationClasses.MaintainSMList.Machine;
import ApplicationClasses.MaintainSMList.StorageMedium;
import ApplicationClasses.User.Customer;
import Model.RentStatus;
import Model.StatusSM;
import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.LocalTime;
import java.util.List;
import static java.time.temporal.ChronoUnit.DAYS;
import java.util.LinkedList;

/**
 *
 * @author
 */
public class Rental {
    
    private int id;
    private LocalDate startDate;
    private LocalDate returnDate; // the date which is scheduled to return the movie...
    private BigDecimal totalCost;
    private List<Detail> details;
    private List<Offer> offers;
    private Machine machine;
    private Customer customer; 

    
    public Rental(int id, LocalDate startDate, LocalDate returnDate, BigDecimal totalCost, Machine machine, Customer customer) {
        this.id = id;
        this.startDate = startDate;
        this.returnDate = returnDate;
        this.totalCost = totalCost;
        this.machine = machine;
        this.customer = customer;
        this.offers = null;
        this.details = new LinkedList<>();        
    }

    public Rental(LocalDate startDate, List<Offer> offers, Machine machine, List<StorageMedium> sms) {
        this.startDate = startDate;
        this.machine = machine;
        this.offers = offers;
        this.returnDate = startDate;
        this.customer = null;
        this.details = new LinkedList<>();
        this.totalCost = BigDecimal.ZERO;
        
        // create details
        int detailPos = 1;
        for (StorageMedium sm: sms) {
            // add pos, rented status, price from the movie/videogame etc and SM
            details.add(new Detail(detailPos, RentStatus.rented, sm.getBrand().getPrice(), sm));
            detailPos++;
            
        }
        
        // calculate return date automatically
        LocalDate returnDateAux;
        
        if (offers.isEmpty())
            this.returnDate = this.returnDate.plusDays(1);
        
        for (Offer o: this.offers) {
            
            // loop through offers, we set the greatest date
            returnDateAux = o.getReturnDate(startDate);
            
            if (returnDateAux.isAfter(returnDate)) {
                
                returnDate = returnDateAux;
                
            }
            
            // calculate final price for the item/details depending of the offers
            // we set the lowest price
            for (int i = 1; i <= o.getQuantity() && i <= details.size(); i++) {
                
                if (details.get(i - 1).getFinalPrice().compareTo(o.getPrice()) == 1)                
                    details.get(i - 1).setFinalPrice(o.getPrice());
                
            }
            
        }
                
        calculateTotalCost();
                
    }
        
    public BigDecimal calculateOverdueCharges(LocalDate dateReturned, StorageMedium sm) throws SMNotFoundException {
               
        for (Detail d: details) { // loop through details
            
            if (d.getSm().getElectronicTag().equals(sm.getElectronicTag())) { // search for that SM

                if (dateReturned.isAfter(this.returnDate)) { // rental is delayed.. checking
                    
                    if (DAYS.between(this.returnDate, dateReturned) == 10 && LocalTime.now().isAfter(LocalTime.parse("20:00:00"))) 
                        return new BigDecimal(15);   // sold        
                    else if (DAYS.between(this.returnDate, dateReturned) >= 1 && DAYS.between(dateReturned, this.returnDate) <= 10) 
                        if (LocalTime.now().isAfter(LocalTime.parse("20:00:00")))
                            return new BigDecimal(1.5 * DAYS.between(this.returnDate, dateReturned) + 1); // returned late  
                        else return new BigDecimal(1.5 * (DAYS.between(this.returnDate, dateReturned))); // returned late  
                    else if (DAYS.between(this.returnDate, dateReturned) > 10) 
                        return new BigDecimal(15); // sold
                        
                } else if (dateReturned.isEqual(this.returnDate)) {
                        if (LocalTime.now().isAfter(LocalTime.parse("20:00:00")))
                            return new BigDecimal(1.5);
                        else return BigDecimal.ZERO;
                } else {
                    
                    return BigDecimal.ZERO;
                    
                }                    
               
            }

        }
        
        throw new SMNotFoundException("The StorageMedium does not belong to this rental");
            
                
    }    
        
    // set attributes before saving in the database, we should check for null 
    public void processReturn(StorageMedium sm, LocalDate dateReturned, Machine machine) throws SMNotFoundException {
        
                
        for (Detail d: details) { // loop through details
            
            if (d.getSm().getElectronicTag().equals(sm.getElectronicTag())) { // search for that SM
                
                d.setMachine(machine); // set the machine where the disc was returned
                
                if (d.getLateFee() == null) 
                    d.setLateFee(calculateOverdueCharges(dateReturned, sm));
                    
                d.setDateReturned(dateReturned); // set date returned
                d.getSm().setMachine(machine); // set the machine in the storage medium, where this storage medium is now
                
                long days = calculateDaysOverdue(dateReturned, sm); // calculate days overdue
                
                if (days == 0) { // returned on time
                    d.setStatus(RentStatus.returned);
                    d.getSm().setStatus(StatusSM.availableToRent);
                    
                } else if ((days > 0 && days < 10) || (days == 10 && (!LocalTime.now().isAfter(LocalTime.parse("20:00:00"))))) { // returned late
                    d.setStatus(RentStatus.returnedLate);
                    d.getSm().setStatus(StatusSM.availableToRent);
                } else { // sold
                    d.setStatus(RentStatus.notReturned);
                    d.getSm().setStatus(StatusSM.sold);                    
                    
                }
                
                return;

            }

        }
        
        throw new SMNotFoundException("The StorageMedium does not belong to this rental");
        
        
    }
    
    public long calculateDaysOverdue(LocalDate dateReturned, StorageMedium sm) throws SMNotFoundException {
        
        for (Detail d: details) { // loop through the details
            
            if (d.getSm().getElectronicTag().equals(sm.getElectronicTag())) { // search for the storage medium
                
                // returned after
                if (dateReturned.isAfter(this.returnDate)) { // rental is delayed.. checking
                
                    
                    if (LocalTime.now().isAfter(LocalTime.parse("20:00:00"))) // days after 20 hr , one day more
                        return (DAYS.between(this.returnDate, dateReturned) + 1);
                    else // days before 20 hr  one day less
                        return DAYS.between(this.returnDate, dateReturned);   
                    
                } else if (dateReturned.isEqual(this.returnDate) && (LocalTime.now().isAfter(LocalTime.parse("20:00:00"))))
                        return 1;
                else {
                    
                    return 0; // return 0 no delays
                    
                }
                    
                
            }

        }
        
        throw new SMNotFoundException("The StorageMedium does not belong to this rental");
    }
    
    private void calculateTotalCost() {
        // loop through details to calculaye the total price
        for (Detail d: details) 
            totalCost = totalCost.add(d.getFinalPrice());
        
    }

    public void setReturnDate(LocalDate returnDate) {
        this.returnDate = returnDate;
    }

    public void setCustomer(Customer customer) {
        this.customer = customer;
    }
    
    public LocalDate getReturnDate() {
        return returnDate;
    }

    public BigDecimal getTotalCost() {
        return totalCost;
    }

    public int getId() {
        return id;
    }

    public Customer getCustomer() {
        return customer;
    }

    public Machine getMachine() {
        return machine;
    }

    public LocalDate getStartDate() {
        return startDate;
    }

    public Detail getDetail(StorageMedium sm) {
        
        for (Detail d: details) {
            
            if (d.getSm().getElectronicTag().equals(sm.getElectronicTag())) {
               
                return d;
                
            }
            
        }
        
        return null;
    }

    public List<Detail> getDetails() {
        return details;
    }

    
    
    public void addDetail(Detail detail) {
        
        details.add(detail);
        
    }
    
    @Override
    public String toString() {
        return "Rental{" + "startDate=" + startDate + ", returnDate=" + returnDate + ", totalCost=" + totalCost + System.lineSeparator() + "details=" + System.lineSeparator() + details + '}';
    }

    public String toStringReturn() {
        return "Rental{" + "id=" + id + ", startDate=" + startDate + ", returnDate=" + returnDate + ", totalCost=" + totalCost + "}";
    }
    
    public class Detail {
    
        private int id;
        private RentStatus status; // status of the rental
        private LocalDate dateReturned; // the date the tem was returned
        private BigDecimal price;
        private BigDecimal lateFee;
        private BigDecimal finalPrice;
        private StorageMedium sm;
        private Machine machine;

        public void setDateReturned(LocalDate dateReturned) {
            this.dateReturned = dateReturned;
        }

        public Detail(int id, RentStatus status, BigDecimal price, StorageMedium sm) {
            this.id = id;
            this.status = status;
            this.price = price;
            this.finalPrice = price;
            this.sm = sm;
            this.dateReturned = null;
            this.lateFee = null;
            this.machine = null;
        }

        public Detail(int id, RentStatus status, LocalDate dateReturned, BigDecimal price, BigDecimal lateFee, BigDecimal finalPrice, StorageMedium sm, Machine machine) {
            this.id = id;
            this.status = status;
            this.dateReturned = dateReturned;
            this.price = price;
            this.lateFee = lateFee;
            this.finalPrice = finalPrice;
            this.sm = sm;
            this.machine = machine;
        }
        
        
        
        public void setFinalPrice(BigDecimal finalPrice) {
            this.finalPrice = finalPrice;
        }

        public BigDecimal getFinalPrice() {
            return finalPrice;
        }

        @Override
        public String toString() {
            return "Detail{" + "id=" + id + ", finalPrice=" + finalPrice + '}';
        }

        public String toStringReturn() {
            return "Detail{" + "id=" + id + ", lateFee=" + lateFee + ", date returned=" + dateReturned + ", " + sm.toString() + "}" + System.lineSeparator();
        }        
        
        public RentStatus getStatus() {
            return status;
        }

        public int getId() {
            return id;
        }

        public LocalDate getDateReturned() {
            return dateReturned;
        }

        public BigDecimal getPrice() {
            return price;
        }

        public BigDecimal getLateFee() {
            return lateFee;
        }

        public StorageMedium getSm() {
            return sm;
        }

        public Machine getMachine() {
            return machine;
        }

        public void setMachine(Machine machine) {
            this.machine = machine;
        }

        public void setStatus(RentStatus status) {
            this.status = status;
        }

        public void setLateFee(BigDecimal lateFee) {
            this.lateFee = lateFee;
        }
        
        
        
    }
    
    
    
}
