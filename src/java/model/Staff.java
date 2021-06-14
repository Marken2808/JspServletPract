/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package model;

/**
 *
 * @author ESD20
 */
public class Staff extends User{
    private int staffID;
    private String staffUsername;
    private String staffPassword;
    private String staffName;
    private String staffAddress;
    private boolean staffApproved;
//    private String staffID;
    

    public Staff() {
    }

    public Staff(int staffID, String staffName, String staffAddress, boolean staffApproved, String staffUsername) {
        this.staffID = staffID;
        this.staffUsername = staffUsername;
        this.staffName = staffName;
        this.staffAddress = staffAddress;
        this.staffApproved = staffApproved;
    }

    

    public Staff(String staffName, String staffAddress) {
        this.staffName = staffName;
        this.staffAddress = staffAddress;
    }

    public Staff(String staffName, String staffAddress, String userName, String userPass) {
        super(userName, userPass);
        this.staffUsername = userName;
        this.staffPassword = userPass;
        this.staffName = staffName;
        this.staffAddress = staffAddress;
    }  

    public int getStaffID() {
        return staffID;
    }

    public void setStaffID(int staffID) {
        this.staffID = staffID;
    }
    
    

    public String getStaffName() {
        return staffName;
    }

    public void setStaffName(String staffName) {
        this.staffName = staffName;
    }

    public String getStaffAddress() {
        return staffAddress;
    }

    public void setStaffAddress(String staffAddress) {
        this.staffAddress = staffAddress;
    }

    public String getStaffUsername() {
        return staffUsername;
    }

    public String getStaffPassword() {
        return staffPassword;
    }

//    public void setStaffID(String staffID) {
//        this.staffID = staffID;
//    }
//
//    public String getStaffID() {
//        return staffID;
//    }
    
}
