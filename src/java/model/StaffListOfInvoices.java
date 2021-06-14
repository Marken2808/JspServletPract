/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package model;

import database.DBbean;
import java.sql.Connection;
import java.sql.Date;

import java.util.ArrayList;

import com.itextpdf.text.pdf.PdfWriter;
import com.itextpdf.text.Paragraph;

import com.itextpdf.text.Font;
import com.itextpdf.text.Document;
import java.io.FileOutputStream;

/**
 *
 * @author Eli
 */
public class StaffListOfInvoices {

    private Connection conn;
    private Staff staff;

    private ArrayList<ArrayList<String>> apointmentData;
    private ArrayList<ArrayList<String>> patientData;

    private String filename;

    public StaffListOfInvoices(Connection conn, Staff staff) {
        this.conn = conn;
        this.staff = staff;
    }

    public void dbSelect() {

        DBbean dao = new DBbean();
        dao.getConnection(conn);

        Date date = new Date(System.currentTimeMillis());
        System.out.println("staff+ " + staff);

//        selecting all todays current appointments
        String SqlQuery = "SELECT * FROM APPOINTMENTS WHERE ADATE = '" + date + "' AND" + " SID = " + Integer.toString(staff.getStaffID());
        apointmentData = dao.selectByQuery(SqlQuery);

        ArrayList<String> patientIDList = new ArrayList<String>();

        for (int i = 0; i < apointmentData.size(); i++) {
            patientIDList.add(apointmentData.get(i).get(2));
        }

        System.out.println("patientIDList" + patientIDList);
        ArrayList<String> patientLine = new ArrayList<String>();
        patientData = new ArrayList<ArrayList<String>>();
        String currentPatientID = "";
        for (int i = 0; i < patientIDList.size(); i++) {
            currentPatientID = patientIDList.get(i);
            patientLine = (ArrayList<String>) dao.selectByQuery("SELECT * FROM PATIENTS WHERE PATIENTID = " + currentPatientID).get(0);
            patientData.add(patientLine);

        }
        
        
        

        System.out.println("patientData " + patientData);
    }

    public String createHTML() {

        System.out.println("apointmentData" + apointmentData);
        System.out.println("patientData" + patientData);

        String invoiceListHTML = "<h2> Generate Invoices for Todays Appointments </h2>";

        invoiceListHTML = invoiceListHTML + "<select name='invoice'>";
        for (int i = 0; i < apointmentData.size(); i++) {
            invoiceListHTML = invoiceListHTML + "<option value = '" + apointmentData.get(i).get(2) + "-" + apointmentData.get(i).get(0);
            invoiceListHTML = invoiceListHTML + "'>" + "Patient: " + patientData.get(i).get(1) + " At: " + apointmentData.get(i).get(4);
            invoiceListHTML = invoiceListHTML + "</option>";

        }

        invoiceListHTML = invoiceListHTML + "</select>";
        invoiceListHTML = invoiceListHTML + "<input type='submit' value='Create Invoice'>";
        return invoiceListHTML;
    }

    public String createPDF(String directory, String invoice, String email) {
        
        System.out.println("directory"+directory);

        String[] patientAppointmentID = invoice.split("-");

        String patientID = patientAppointmentID[0];

        String appointmentID = patientAppointmentID[1];

        String Patient = "";

        String date = "";

        String time = "";

        String payee = "";

        
        String length = "";

        System.out.println("START");
        for (int i = 0; i < apointmentData.size(); i++) {
            if (patientID.equals(apointmentData.get(i).get(2)) && appointmentID.equals(apointmentData.get(i).get(0))) {
                System.out.println("OPENED");
                System.out.println("apointmentData.get(i)" + apointmentData.get(i));
                Patient = patientData.get(i).get(1);

                date = apointmentData.get(i).get(3);

                time = apointmentData.get(i).get(4);
                
                 length = apointmentData.get(i).get(5);

                if (patientData.get(i).get(3).equals("private")) {
                    payee = patientData.get(i).get(1);
                    email = patientData.get(i).get(5);

                } else {
                    payee = "NHS";
                }
                filename = Patient.replaceAll(" ", "") + date.replaceAll("-", "") + time.replaceAll(":", "");
            }

        }
        
        DBbean dao = new DBbean();
        dao.getConnection(conn);
        String SqlQuery = "SELECT COST FROM PRICES WHERE LENGTH = "+length +"";
       
        ArrayList<String> dbReturn = (ArrayList<String>) dao.selectByQuery(SqlQuery).get(0);
        System.out.println("dbReturn=" + dbReturn);
        String amount = dbReturn.get(0);
//        dao.insertInvoice(appointmentID,amount,date,filename);

//        using iText pdf to write to write to folder
        String file = directory + filename + ".pdf";
        
        
        
        try {
            Document document = new Document();
            PdfWriter.getInstance(document, new FileOutputStream(file));
            document.open();

            Font bigFont = new Font(Font.FontFamily.TIMES_ROMAN, 18,
                    Font.BOLD);

            Font smallFont = new Font(Font.FontFamily.TIMES_ROMAN, 12,
                    Font.NORMAL);
            Paragraph page = new Paragraph();

            // Will create: Report generated by: _name, _date
            page.add(
                    new Paragraph(
                            "SmartCare GP Sugery,",
                            smallFont));
            page.add(
                    new Paragraph(
                            "Smart Company Road,",
                            smallFont));
            page.add(
                    new Paragraph(
                            "BS1 111,",
                            smallFont));

            page.add(
                    new Paragraph(
                            "Bristol",
                            smallFont));
            page.add(
                    new Paragraph(
                            "United Kingdom,",
                            smallFont));

            // We add one empty line
            page.add(
                    new Paragraph(" "));
            // Lets write a big header
            page.add(
                    new Paragraph("LETTER OF PAYMENT", bigFont));

            page.add(
                    new Paragraph(" "));

            page.add(
                    new Paragraph(
                            "Dear " + payee,
                            smallFont));

            page.add(
                    new Paragraph(" "));

            page.add(
                    new Paragraph(
                            "This is a letter for payment for Patient " + Patient + ", for appointment that occured at " + date + " " + time + " Paymant amount is £" + amount + ". Please send money to either Account Number: 1432312, Sort Code: 33-12-33 or PayPal smartCareGP@gmail.com. Please use reference " + Patient.replaceAll(" ", "") + date.replaceAll("-", "") + time.replaceAll(":", ""),
                            smallFont));

            page.add(
                    new Paragraph(" "));

            page.add(
                    new Paragraph(
                            "Kind Regards",
                            smallFont));
            page.add(
                    new Paragraph(
                            "SmartCare GP",
                            smallFont));

            document.add(page);
            document.close();

        } catch (Exception e) {

            e.printStackTrace();

        }
        return "<p> Download invoice and email to " + email + "</p>";

    }

    public String getFilename() {
        return filename;
    }
    
    
}
