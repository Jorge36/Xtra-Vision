/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ApplicationClasses.MaintainSMList;

import Model.StatusSM;

/**
 *
 * @author 
 */
public abstract class StorageMedium {
    
    protected String electronicTag;
    protected StatusSM status;
    protected Brand brand;
    protected Machine machine;

    public StorageMedium(String electronicTag, StatusSM status, Machine machine) {
        this.electronicTag = electronicTag;
        this.status = status;
        this.machine = machine;
    }

    public StatusSM getStatus() {
        return status;
    }

    public Brand getBrand() {
        return brand;
    }

    public String getElectronicTag() {
        return electronicTag;
    }

    public void setStatus(StatusSM status) {
        this.status = status;
    }

    public void setMachine(Machine machine) {
        this.machine = machine;
    }

    public void setBrand(Brand brand) {
        this.brand = brand;
    }

    public Machine getMachine() {
        return machine;
    }

    @Override
    public String toString() {
        return "StorageMedium{" + "electronicTag=" + electronicTag + ", title=" + brand.getTitle() + '}';
    }
    
    
    
    
    
    
    
}
