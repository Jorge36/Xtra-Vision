/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ApplicationClasses.MaintainSMList;

import Model.Category;
import java.math.BigDecimal;
import java.time.Year;
import java.util.LinkedList;
import java.util.List;

/**
 *
 * @author
 */
public abstract class Brand {
    
    protected String title;
    protected String description;
    protected String linkVideo;
    protected Category type;
    protected String genre;
    protected Year year;
    protected int idBrand;
    protected List<StorageMedium> sms;
    
    public Brand(String title, String description, String linkVideo, Category type, String genre, Year year, int idBrand, List<StorageMedium> sms) {
        this.title = title;
        this.description = description;
        this.linkVideo = linkVideo;
        this.type = type;
        this.genre = genre;
        this.year = year;
        this.idBrand = idBrand;
        if (sms == null)
           this.sms = new LinkedList<>();
        else this.sms = sms;
    }

    @Override
    public String toString() {
        if (sms != null)
            return "Brand{" + "title=" + title + ", description=" + description + ", linkVideo=" + linkVideo + ", type=" + type + ", genre=" + genre + ", year=" + year + ", idBrand=" + idBrand + ", quantity=" + sms.size() + '}';
        else
            return "Brand{" + "title=" + title + ", description=" + description + ", linkVideo=" + linkVideo + ", type=" + type + ", genre=" + genre + ", year=" + year + ", idBrand=" + idBrand + ", quantity=" + 0 + '}';
    }
    
    public String toShortString() {
        
        if (sms != null)
            return "Brand{" + "title=" + title + ", type=" + type + ", genre=" + genre + ", year=" + year + ", quantity=" + sms.size() + '}';
        else
            return "Brand{" + "title=" + title + ", type=" + type + ", genre=" + genre + ", year=" + year + ", quantity=" + 0 + '}';
            
    }
    
    public StorageMedium getAvailableStorageMedium() {
        
        if (sms != null && sms.size() > 0)
            return sms.get(0);
        else return null;
        
    }   
    
    public int quantityOfStorageMedium() {
        
        if (sms != null && sms.size() > 0)
            return sms.size();
        else return 0;
        
    }
   
    public abstract BigDecimal getPrice();

    public int getIdBrand() {
        return idBrand;
    }
        
    public void addSM(StorageMedium sm) {
        
        sms.add(sm);
        
    }   

    public void setSms(List<StorageMedium> sms) {
        this.sms = sms;
    }

    public String getTitle() {
        return title;
    }
    
    
    
}
