/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package model;

import database.DBconnection;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

/**
 *
 * @author Marken Tuan Nguyen
 */
public class StaffDAO {
    
    Connection connection = null;
    PreparedStatement preparedStatement = null;
    ResultSet resultSet = null;

    public void getConnection(Connection connection) {
        this.connection = connection;
    }
    
    public String getStaffLists(String staffTable) {
        DBconnection db = new DBconnection();
        db.getConnection(connection);
        return db.selectByTable(staffTable);
    } 
    
}
