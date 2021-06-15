/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package model;

import database.DBbean;
import java.sql.Connection;
import java.util.ArrayList;

/**
 *
 * @author Eli
 */
public class StaffListOfRefills {

    private Connection conn;
    public String prescriptionTable;
    public String patientTable;

    private ArrayList<String> prescriptionData;
    private ArrayList<String> patientData;

    private String prescriptionHTML;

    public StaffListOfRefills(Connection conn, String prescriptionTable, String patientTable) {
        this.conn = conn;
        this.prescriptionTable = prescriptionTable;
        this.patientTable = patientTable;
    }

    public void dbSelect() {
        DBbean dao = new DBbean();
        dao.getConnection(conn);

        prescriptionData = dao.selectUnapprovedPrescriptions();

        String patientID;
        String patientName;
        patientData = new ArrayList<String>();
        for (int i = 0; i < prescriptionData.size(); i += 4) {
            System.out.println("i" + i);
            patientID = prescriptionData.get(i);
            patientName = dao.selectPatientName(patientID);
            System.out.println("patientName" + patientName);
            patientData.add(patientName);
        }

        System.out.println("prescriptionData" + prescriptionData);
        System.out.println("patientData" + patientData);

    }

    public String createHTML() {
        System.out.println("OPEN create html");
        prescriptionHTML = "<label for='prescriptionLabel'>Prescriptions to be approved:</label><br>";
        System.out.println("prescriptionHTML" + prescriptionHTML);

        for (int i = 0; i < prescriptionData.size(); i += 4) {
            prescriptionHTML = prescriptionHTML + "<input type ='checkbox' value ='" + prescriptionData.get(i) + prescriptionData.get(i + 1) + "'";

            prescriptionHTML = prescriptionHTML + "name ='" + prescriptionData.get(i) + prescriptionData.get(i + 1) + "'";

            prescriptionHTML = prescriptionHTML + "<label>" + patientData.get(i / 4) + "' has requested a Prescription refill for " + prescriptionData.get(i + 1) + "<br>";

            System.out.println("prescriptionHTML" + prescriptionHTML);

        }

        prescriptionHTML = prescriptionHTML + "<input type='submit' value='Approve Request'>";

        System.out.println("prescriptionHTML" + prescriptionHTML);
        return prescriptionHTML;
    }

    public String dbUpdate(String id, String medicine) {
        DBbean dao = new DBbean();

        dao.getConnection(conn);

        for (int i = 0; i < prescriptionData.size(); i += 4) {
            if (prescriptionData.get(i) == id && prescriptionData.get(i + 1) == medicine) {

                if (Integer.parseInt(prescriptionData.get(i + 3)) == 0) {
                    System.out.println("Final countdown");
                    boolean sucsess = dao.deletePrescription(id, medicine);
                    if (sucsess) {
                        return "<p>Patient ID:" + id + "prescription for " + medicine + " was sucsessfully approved, this was there last refill and they have been removed from the system<p>";
                    } else {
                        return "<p>Patient ID:" + id + "prescription for " + medicine + " was FAILED to approved<p>";
                    }

                } else {
                    System.out.println("Still some time left");
                    String sqlQuery = "UPDATE PRESCRIPTIONS SET APPROVED = true WHERE PATIENTID = " + id;
                    sqlQuery = sqlQuery + " AND MEDICATIONNAME = '" + medicine + "'";
                    System.out.println("sqlQuery" + sqlQuery);
                    boolean sucsess = dao.update(sqlQuery);
                    if (sucsess) {
                        return "<p>Patient ID:" + id + "prescription for " + medicine + " was sucsessfully approved<p>";
                    } else {
                        return "<p>Patient ID:" + id + "prescription for " + medicine + " was FAILED to approved<p>";
                    }

                }
            }

        }

        return null;
    }

    public ArrayList<String> getPrescriptionData() {
        return prescriptionData;
    }

}
