/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ApplicationClasses.MaintainSMList;

import java.time.LocalDate;

/**
 *
 * @author
 */
public class Machine {
    
    private String eircode;
    private String address;
    private String town;
    private String county;
    private String description;
    private String country;
    
    public void openReturnSlot() {
        
        // call the library hardware
        
    }
    
    public void prepareCardReader() {
        
        // call the library hardware
        
        
    }
    
    public void releaseCardReader() {
        
        // call the library hardware
        
    }
    
    
    public String scanSM() {
        
        // call the library hardware
        
        return null; // this function should return a id to search the storage medium
        
    }
    
    public void dispenseDisc(String electronicTag) {
        
        // call the library hardware
        
    }
    
    public boolean VerifyCardAndPin(String pan, String nameOnCard, int cvv, LocalDate expirationDate, int pin) {
    
        // call the library hardware
        return true;
    
    }

    public String getEircode() {
        return eircode;
    }
    
    

    public Machine(String eircode, String address, String town, String county, String description, String country) {
        this.eircode = eircode;
        this.address = address;
        this.town = town;
        this.county = county;
        this.description = description;
        this.country = country;
    }

    public Machine(String eircode) {
        this.eircode = eircode;
    }

    @Override
    public String toString() {
        return "Machine{" + "eircode=" + eircode + ", address=" + address + ", town=" + town + ", county=" + county + ", description=" + description + ", country=" + country + '}';
    }
    
}
