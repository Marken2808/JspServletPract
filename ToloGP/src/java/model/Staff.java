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
    private boolean isApproved;
    
    public Staff(){}


    public Staff(int sID, String uUsername, String uPassword) {
        super(uUsername, uPassword);
        this.sID = sID;
    }    
    

    public int getsID() {
        return sID;
    }

    public void setsID(int sID) {
        this.sID = sID;
    }

    public boolean isIsApproved() {
        return isApproved;
    }

    public void setIsApproved(boolean isApproved) {
        this.isApproved = isApproved;
    }
    
    
    
}
