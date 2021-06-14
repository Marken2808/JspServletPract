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
public class Patient extends User{
 
    private int patientID;
    private String patientUsername;
    private String patientPassword;
    private String patientName;
    private String patientAddress;
    private String patientType;

    public Patient() {
    }

    public Patient(int patientID, String patientName, String patientAddress, String patientType, String patientUsername) {
        this.patientID = patientID;
        this.patientUsername = patientUsername;
        this.patientName = patientName;
        this.patientAddress = patientAddress;
        this.patientType = patientType;
    }
        

    public Patient(String patientName, String patientAddress) {
        this.patientName = patientName;
        this.patientAddress = patientAddress;
    }

    public Patient(String patientName, String patientAddress, String userName, String userPass) {
        super(userName, userPass);
        this.patientUsername = userName;
        this.patientPassword = userPass;
        this.patientName = patientName;
        this.patientAddress = patientAddress;
    }

    public int getPatientID() {
        return patientID;
    }

    public void setPatientID(int patientID) {
        this.patientID = patientID;
    }
  

    public String getPatientName() {
        return patientName;
    }

    public void setPatientName(String patientName) {
        this.patientName = patientName;
    }

    public String getPatientAddress() {
        return patientAddress;
    }

    public void setPatientAddress(String patientAddress) {
        this.patientAddress = patientAddress;
    }

    public String getPatientType() {
        return patientType;
    }

    public void setPatientType(String patientType) {
        this.patientType = patientType;
    }
    
    public String getPatientUsername() {
        return patientUsername;
    }
    
    public void setPatientUsername(String patientUsername) {
        this.patientUsername = patientUsername;
    }

    public String getPatientPassword() {
        return patientPassword;
    }


    public void setPatientPassword(String patientPassword) {
        this.patientPassword = patientPassword;
    }

    
    
    
}
