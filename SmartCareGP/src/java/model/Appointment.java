/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package model;

import java.sql.Date;
import java.sql.Time;

/**
 *
 * @author Marken Tuan Nguyen
 */
public class Appointment {
    private int AID;
    private int StaffID;
    private int PatientID;
    private Date ADate;
    private Time ATime;
    private int ALength;

    public Appointment(int AID, int StaffID, int PatientID, Date ADate, Time ATime, int ALength) {
        this.AID = AID;
        this.StaffID = StaffID;
        this.PatientID = PatientID;
        this.ADate = ADate;
        this.ATime = ATime;
        this.ALength = ALength;
    }
    
    public int getAID() {
        return AID;
    }

    public void setAID(int AID) {
        this.AID = AID;
    }

    public int getStaffID() {
        return StaffID;
    }

    public void setStaffID(int StaffID) {
        this.StaffID = StaffID;
    }

    public int getPatientID() {
        return PatientID;
    }

    public void setPatientID(int PatientID) {
        this.PatientID = PatientID;
    }

    public Date getADate() {
        return ADate;
    }

    public void setADate(Date ADate) {
        this.ADate = ADate;
    }

    public Time getATime() {
        return ATime;
    }

    public void setATime(Time ATime) {
        this.ATime = ATime;
    }

    public int getALength() {
        return ALength;
    }

    public void setALength(int ALength) {
        this.ALength = ALength;
    }
    
    
    
}
