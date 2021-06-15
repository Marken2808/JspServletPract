/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package model;

import database.DBbean;
import java.sql.Connection;

import org.joda.time.format.DateTimeFormatter;
import org.joda.time.format.DateTimeFormat;
import java.util.ArrayList;
import org.joda.time.*;
import org.joda.time.DateTime;

import java.text.SimpleDateFormat;
import java.util.Date;

//import java.time.LocalDate;
//import java.time.ZoneId;
//import java.time.ZonedDateTime;
//import java.time.temporal.ChronoUnit;
//
//import java.time.format.DateTimeFormatter;
/**
 *
 * @author Eli
 */
public class AdminlistOfTurnovers {

    private Connection conn;

    private ArrayList<String> invoiceData;

    private String turnoverHTML;

    public AdminlistOfTurnovers(Connection conn) {
        this.conn = conn;

    }

    public void dbSelect(String table) {

        DBbean dao = new DBbean();
        dao.getConnection(conn);

        turnoverHTML = "<h1>Turnover: <br>";
        turnoverHTML = turnoverHTML + "Type, Date, Ammount <br>";

        turnoverHTML = turnoverHTML + dao.selectByTable(table) + "</h1>";
        turnoverHTML = turnoverHTML + "<input name ='action' type='submit' value='Daily'>";
        turnoverHTML = turnoverHTML + "<input name ='action'  type= 'submit' value='Weekly'>";
        turnoverHTML = turnoverHTML + "<input name ='action'  type= 'submit' value='Monthly'>";

    }

    public void generateTurnover(String type) {
        System.out.println("Makes it heree");
        DBbean dao = new DBbean();
        dao.getConnection(conn);
        invoiceData = dao.selectInvoices();
        System.out.println("invoiceData " + invoiceData);

        int typeInt = 0;
        if (type.equals("Daily")) {
            typeInt = 0;
        } else if (type.equals("Weekly")) {
            typeInt = 7;
        } else if (type.equals("Monthly")) {
            typeInt = 30;
        }

        DateTime currentDate = DateTime.now().withTimeAtStartOfDay();

        DateTimeFormatter formatter = DateTimeFormat.forPattern("YYYY-MM-DD");

        DateTime invoiceDate;

        ArrayList<Integer> validInvoices = new ArrayList<Integer>();
        Duration duration;
        long differnceIndays;
        int totalMoneyGenerated = 0;
        for (int i = 0; i < invoiceData.size(); i += 4) {
            invoiceDate = formatter.parseDateTime(invoiceData.get(i + 2)).withTimeAtStartOfDay();

            duration = new Duration(invoiceDate, currentDate);
            differnceIndays = duration.getStandardDays();

            if (differnceIndays <= typeInt) {

                validInvoices.add(i);
                totalMoneyGenerated = totalMoneyGenerated + Integer.parseInt(invoiceData.get(i + 1));
            }
        }

        Date date = new Date();
        SimpleDateFormat formatterStr = new SimpleDateFormat("YYYY-MM-DD");
        String strDate = formatterStr.format(date);

        dao.insertTurnover(type, strDate, Integer.toString(totalMoneyGenerated));

    }

    public String getTurnoverHTML() {
        return turnoverHTML;
    }

}
