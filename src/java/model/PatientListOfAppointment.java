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
 * @author Marken Tuan Nguyen
 */
public class PatientListOfAppointment {
    private Connection conn;
    private String patientTable;
    private ArrayList<String> staffName;
    

    public PatientListOfAppointment(Connection conn, String patientTable) {
        this.conn = conn;
        this.patientTable = patientTable;

    }
    
    public void showStaffName(){
        
        DBbean dao = new DBbean();

        dao.getConnection(conn);

        String sqlQuery = "SELECT staffname FROM users,staffs WHERE  users.username = staffs.USERNAME AND users.role = 'Doctor'";
        System.out.println("sql: "+sqlQuery);
        staffName = dao.selectByQuery(sqlQuery);
    }
}
