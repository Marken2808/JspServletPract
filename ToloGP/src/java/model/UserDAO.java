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
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.servlet.http.HttpServlet;

/**
 *
 * @author Marken Tuan Nguyen
 */
public class UserDAO {
    

    Connection connection = null;
    PreparedStatement preparedStatement = null;
    ResultSet resultSet = null;

    public void getConnection(Connection connection) {
        this.connection = connection;
    }
    
    public String getUserLists(String userTable) {
        DBconnection db = new DBconnection();
        db.getConnection(connection);
        return db.selectByTable(userTable);
    } 
    
    public void createUser(String table, User user) {
        try {
            //      query
            String create = "INSERT INTO " + table + " VALUES (?, ?, ?)";
            //      prepare statement
            preparedStatement = connection.prepareStatement(create);
            //      set statement position
            preparedStatement.setString(1, user.getuUsername().equals("") ? null : user.getuUsername());
            preparedStatement.setString(2, user.getuPassword().equals("") ? null : user.getuPassword());
            preparedStatement.setString(3, user.getuRole());
            preparedStatement.executeUpdate();
            preparedStatement.close();
        } catch (SQLException ex) {
        }
    }
    
}
