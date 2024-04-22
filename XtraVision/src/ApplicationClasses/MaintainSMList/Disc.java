/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ApplicationClasses.MaintainSMList;

import Model.StatusSM;
import Model.TypeDisc;

/**
 *
 * @author 
 */
public class Disc extends StorageMedium {
    
    private TypeDisc typeDisc;

    public Disc(TypeDisc typeDisc, String electronicTag, StatusSM status, Machine machine) {
        super(electronicTag, status, machine);
        this.typeDisc = typeDisc;
    }

    public Disc(String electronicTag, StatusSM status, Machine machine) {
        super(electronicTag, status, machine);
    }

    public void setTypeDisc(TypeDisc typeDisc) {
        this.typeDisc = typeDisc;
    }

    public TypeDisc getTypeDisc() {
        return typeDisc;
    }

    

    

}
