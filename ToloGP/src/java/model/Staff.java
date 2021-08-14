/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package model;

/**
 *
 * @author Marken Tuan Nguyen
 */
public class Staff extends User{
    
    private int sID;
    private String sName;
    private String sAddress;
    private String sPhone;
    private boolean isApproved;
    
    public Staff(){}

    public Staff(int sID, String sName, String sAddress, String sPhone) {
        this.sID = sID;
        this.sName = sName;
        this.sAddress = sAddress;
        this.sPhone = sPhone;
    }

    public Staff(int sID, String sName, String sAddress, String sPhone, String uUsername, String uPassword) {
        super(uUsername, uPassword);
        this.sID = sID;
        this.sName = sName;
        this.sAddress = sAddress;
        this.sPhone = sPhone;
    }

    public Staff(String sName, String sAddress, String sPhone) {
        this.sName = sName;
        this.sAddress = sAddress;
        this.sPhone = sPhone;
    }
    
    

    public int getsID() {
        return sID;
    }

    public void setsID(int sID) {
        this.sID = sID;
    }

    public String getsName() {
        return sName;
    }

    public void setsName(String sName) {
        this.sName = sName;
    }

    public String getsAddress() {
        return sAddress;
    }

    public void setsAddress(String sAddress) {
        this.sAddress = sAddress;
    }

    public String getsPhone() {
        return sPhone;
    }

    public void setsPhone(String sPhone) {
        this.sPhone = sPhone;
    }

    public boolean isIsApproved() {
        return isApproved;
    }

    public void setIsApproved(boolean isApproved) {
        this.isApproved = isApproved;
    }
    
    
    
}
