/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package model;

import database.DBbean;
import java.sql.Connection;
import java.util.ArrayList;
import java.sql.Date;  

/**
 *
 * @author Eli
 */
public class StaffListOfPrescriptions {

    private Connection conn;
    public String patientTable;
    private String medicationTable;
    private String prescriptionTable;

    private ArrayList<String> patientData;
    private ArrayList<String> medicationData;
    private ArrayList<String> prescriptionData;

    private String patientHTML;
    private String medicationHTML;
    private String refillsHTML;
    private String successHTML;

    public StaffListOfPrescriptions(Connection conn, String patientTable , String medicationTable, String prescriptionTable) {
        this.conn = conn;
        this.patientTable = patientTable;
        this.medicationTable = medicationTable;
        this.prescriptionTable = prescriptionTable;

    }

    public void dbSelect() {
        DBbean dao = new DBbean();

        dao.getConnection(conn);
        patientData = dao.selectAllPatientNameID();

        medicationData = dao.selectAllMedName();
        
//        remove associated infomation
//        prescriptionData = dao.selectAllPrescriptions();

    }

    public void createHTML() {

        patientHTML = "<label for='patientName'>Patient Name:</label><br>";

        patientHTML = patientHTML + "<select name='patient'>";

        for (int i = 0; i < patientData.size(); i += 2) {
            System.out.println("test");
            System.out.println(patientData.get(i));
            System.out.println(patientData.get(i + 1));
            patientHTML = patientHTML + "<option value='";
            patientHTML = patientHTML + patientData.get(i);
            patientHTML = patientHTML + "'>";
            patientHTML = patientHTML + patientData.get(i + 1);
            patientHTML = patientHTML + " ID:";
            patientHTML = patientHTML + patientData.get(i);
        }
        patientHTML = patientHTML + "</select>";

        medicationHTML = "<br><label for='medicationName'>Medication Name:</label><br>";

        medicationHTML = medicationHTML + "<select name='medication'>";

        for (int i = 0; i < medicationData.size(); i += 2) {

            medicationHTML = medicationHTML + "<option value='";
            medicationHTML = medicationHTML + medicationData.get(i);
            medicationHTML = medicationHTML + "'>";
            medicationHTML = medicationHTML + medicationData.get(i);

        }

        medicationHTML = medicationHTML + "</select>";

//        allowed number of refills is currently limited to just 5 for all types of medication
        refillsHTML = "<br><label for='numberOfRefills'>Number of Refills:</label><br>";
        refillsHTML = refillsHTML + "<input type='number' name='refills' min='1' max='5' value = '1' >";

        System.out.println("reffils" + refillsHTML);
        System.out.println(medicationData);
        System.out.println(medicationHTML);
    }

    public String dbInsert(String patient, String medication, String refillsString) {

//        refills is converted to integer for db
        

        long systemDate=System.currentTimeMillis();  
        Date date = new Date(systemDate);  

        DBbean dao = new DBbean();

        dao.getConnection(conn);
        
        
        System.out.println("patient"+patient);
        System.out.println("medication"+medication);
        System.out.println("date"+date);
        System.out.println("refillsString"+refillsString);
        
        boolean sucsses = dao.insertPrescription(patient, medication,date,refillsString);

        if (sucsses) {
            return "<p>Patient ID:" + patient + "prescription for " + medication + " was sucsessful<p>";
        } else {
            return "<p>Patient ID:" + patient + "prescription for " + medication + " FAILED as it already exists</p>";
        }

    }

    public String getPatientHTML() {
        return patientHTML;
    }

    public String getMedicationHTML() {
        return medicationHTML;
    }

    public String getRefillsHTML() {
        return refillsHTML;
    }

}
