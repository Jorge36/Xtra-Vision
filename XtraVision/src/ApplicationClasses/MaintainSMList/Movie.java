/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ApplicationClasses.MaintainSMList;

import Model.Category;
import java.math.BigDecimal;
import java.time.Year;
import java.util.List;

/**
 *
 * @author 
 */
public class Movie extends Brand {

    public Movie(String title, String description, String linkVideo, Category type, String genre, Year year, int idBrand, List<StorageMedium> sms) {
        super(title, description, linkVideo, type, genre, year, idBrand, sms);
    }

    @Override
    public BigDecimal getPrice() {
        return new BigDecimal("2.99");
    }

    @Override
    public String toString() {
        if (sms != null)
            return "Movie{" + "title=" + title + ", description=" + description + ", linkVideo=" + linkVideo + ", type=" + type + ", genre=" + genre + ", year=" + year + ", idBrand=" + idBrand + ", quantity=" + sms.size() + '}';
        else
            return "Movie{" + "title=" + title + ", description=" + description + ", linkVideo=" + linkVideo + ", type=" + type + ", genre=" + genre + ", year=" + year + ", idBrand=" + idBrand + ", quantity=" + 0 + '}';
    }
    
    @Override
    public String toShortString() {
        
        if (sms != null)
            return "Movie{" + "title=" + title + ", type=" + type + ", genre=" + genre + ", year=" + year + ", quantity=" + sms.size() + '}';
        else
            return "Movie{" + "title=" + title + ", type=" + type + ", genre=" + genre + ", year=" + year + ", quantity=" + 0 + '}';
            
    }    
    
}
