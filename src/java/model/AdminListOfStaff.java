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
public class AdminListOfStaff {

    private Connection conn;
    private String staffTable;
    private ArrayList<ArrayList> unapprovedStaff;

    public AdminListOfStaff(Connection conn, String staffTable) {
        this.conn = conn;
        this.staffTable = staffTable;

    }

    public void dbSelectUnapproved() {

        DBbean dao = new DBbean();

        dao.getConnection(conn);

        String sqlQuery = "SELECT * FROM Staffs WHERE APPROVED = FALSE";

        unapprovedStaff = dao.selectByQuery(sqlQuery);

    }

    //This method updates model Db   
    public boolean dbUpdate(String action) {
        DBbean dao = new DBbean();

        dao.getConnection(conn);

        String sqlQuery = "UPDATE Staffs SET APPROVED = true WHERE STAFFID = ";

        sqlQuery = sqlQuery + action;

        Boolean dbSucsses = dao.update(sqlQuery);


        return dbSucsses;

    }

    public ArrayList<ArrayList> getUnapprovedStaff() {
        return unapprovedStaff;
    }
}
