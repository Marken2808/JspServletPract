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
public class User implements java.io.Serializable {
    private int uID;
    private String uUsername;
    private String uPassword;
    private String uRole;    //Admin, Patient, Staff
    private String uName;
    private String uAddress;
    private String uPhone;

    public User() {}

    public User(String uUsername, String uPassword) {
        this.uUsername = uUsername;
        this.uPassword = uPassword;
    }

    public User(int uID, String uUsername, String uPassword, String uRole, String uName, String uAddress, String uPhone) {
        this.uID = uID;
        this.uUsername = uUsername;
        this.uPassword = uPassword;
        this.uRole = uRole;
        this.uName = uName;
        this.uAddress = uAddress;
        this.uPhone = uPhone;
    }

    public int getuID() {
        return uID;
    }

    public void setuID(int uID) {
        this.uID = uID;
    }

    public String getuUsername() {
        return uUsername;
    }

    public void setuUsername(String uUsername) {
        this.uUsername = uUsername;
    }

    public String getuPassword() {
        return uPassword;
    }

    public void setuPassword(String uPassword) {
        this.uPassword = uPassword;
    }

    public String getuRole() {
        return uRole;
    }

    public void setuRole(String uRole) {
        this.uRole = uRole;
    }

    public String getuName() {
        return uName;
    }

    public void setuName(String uName) {
        this.uName = uName;
    }

    public String getuAddress() {
        return uAddress;
    }

    public void setuAddress(String uAddress) {
        this.uAddress = uAddress;
    }

    public String getuPhone() {
        return uPhone;
    }

    public void setuPhone(String uPhone) {
        this.uPhone = uPhone;
    }

    @Override
    public String toString() {
        return "User{" + "uID=" + uID + ", uUsername=" + uUsername + ", uPassword=" + uPassword + ", uRole=" + uRole + ", uName=" + uName + ", uAddress=" + uAddress + ", uPhone=" + uPhone + '}';
    }
    
    
    

    
}
