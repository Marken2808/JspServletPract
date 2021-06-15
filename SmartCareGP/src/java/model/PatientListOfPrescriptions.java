/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package model;

import database.DBbean;
import java.sql.Connection;

import java.util.ArrayList;

import java.time.*;
import java.time.format.DateTimeFormatter;
import java.util.*;

/**
 *
 * @author Eli
 */
public class PatientListOfPrescriptions {

    private Connection conn;

    private String prescriptionTable;
    private String medicationTable;

    private ArrayList<String> precriptionData;
    private ArrayList<String> medicationData;

    private ArrayList<Integer> refillsAllowed;

    private Patient patient;

    private String patientHTML;

    public PatientListOfPrescriptions(Connection conn, String medicationTable, String prescriptionTable, Patient patient) {
        this.conn = conn;
        this.medicationTable = medicationTable;
        this.prescriptionTable = prescriptionTable;
        this.patient = patient;

    }

    public void dbSelect() {
        DBbean dao = new DBbean();

        dao.getConnection(conn);

        precriptionData = dao.selectPatientPrescriptions(patient.getPatientID());

        System.out.println("Prescription data" + precriptionData);

        ArrayList<String> patientMedicationNames = new ArrayList<String>();

        for (int i = 0; i < precriptionData.size(); i += 5) {

            patientMedicationNames.add((String) precriptionData.get(i));

        }

        String medicationLength;
        medicationData = new ArrayList<String>();

        for (int i = 0; i < patientMedicationNames.size(); i++) {

            medicationLength = (String) dao.selectMedQuantity(patientMedicationNames.get(i));

            medicationData.add(medicationLength);
            System.out.println("medicationLength" + medicationLength);

        }

    }

    public void determinRefill() {

        int reffilLength;

        LocalDate lastRefillDate = null;

        DateTimeFormatter datetimeformatter = DateTimeFormatter.ofPattern("yyyy-mm-dd");

        LocalDate currentDate = LocalDate.now();

        String lastRefillDateString = "";

        Period diffference;

        refillsAllowed = new ArrayList<Integer>();

        for (int i = 0; i < medicationData.size(); i++) {

            reffilLength = Integer.parseInt(medicationData.get(i));

            lastRefillDateString = precriptionData.get(i * 5 + 2);

            lastRefillDate = LocalDate.parse(lastRefillDateString);

            diffference = Period.between(lastRefillDate, currentDate);

//            If the length of time the medication lasts for has passed
            if (diffference.getDays() >= reffilLength) {

                refillsAllowed.add(i);

            }

        }

    }

    public void createHTML() {

        int prescriptionNumber;

        patientHTML = "<label for='prescriptionLabel'>Allowed Prescritions:</label><br>";

        System.out.println("medicationData" + medicationData);

        for (int i = 0; i < refillsAllowed.size(); i++) {

            prescriptionNumber = refillsAllowed.get(i);

            patientHTML = patientHTML + "<input type ='checkbox' value ='" + precriptionData.get(prescriptionNumber * 5) + "' name = '" + "Med" + i + "'>";

            patientHTML = patientHTML + " <label>" + "Prescription for " + precriptionData.get(prescriptionNumber * 5) + ", Orignally issued on " + precriptionData.get(prescriptionNumber * 5 + 1);

            patientHTML = patientHTML + " last reffiled on " + precriptionData.get(prescriptionNumber * 5 + 2) + ". Reffils Remaining: " + precriptionData.get(prescriptionNumber * 5 + 4) + "</label><br>";

        }

        patientHTML = patientHTML + "<input type='submit' value='Request Refill'";

        System.out.println("patientHTML" + patientHTML);

    }

    public String updateDB(String medication) {
        System.out.println("medication" + medication);

        DBbean dao = new DBbean();

        dao.getConnection(conn);

        int prescriptionNumber;

        DateTimeFormatter dateTimeFormatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");

        LocalDateTime now = LocalDateTime.now();

        String currentDate = dateTimeFormatter.format(now);

        int patientID = patient.getPatientID();

        for (int i = 0; i < refillsAllowed.size(); i++) {

            System.out.println("i" + i);

            prescriptionNumber = refillsAllowed.get(i);

            if (medication.equals(precriptionData.get(prescriptionNumber * 5))) {

                System.out.println("if opened!!");

                int intInitalRefills = Integer.parseInt(precriptionData.get(prescriptionNumber * 5 + 4)) - 1;

                String newRefills = Integer.toString(intInitalRefills);

                String sqlUpdate = "UPDATE PRESCRIPTIONS SET LASTREFILLDATE ='" + currentDate + "', REMAININGREFILLS =";

                sqlUpdate = sqlUpdate + newRefills + ",APPROVED = false WHERE PATIENTID = " + patientID;

                sqlUpdate = sqlUpdate + " AND MEDICATIONNAME = '" + medication + "'";

                boolean sucsess = dao.update(sqlUpdate);

                System.out.println("sucsess" + sucsess);

                if (sucsess) {
                    return "<p>Your refill request for " + medication + " has been accepted. Please wait for staff member to approve.<p>";
                } else {
                    return "<p>Your refill request for " + medication + " has failed</p>";
                }
            }

        }
        return "<p>Your refill request for " + medication + " has failed</p>"; 
    }

    public ArrayList<Integer> getRefillsAllowed() {
        return refillsAllowed;
    }

    public String getpatientHTML() {
        return patientHTML;
    }

}
