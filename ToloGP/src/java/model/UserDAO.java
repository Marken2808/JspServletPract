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
            String create = "INSERT INTO " + table + " (USERNAME, PASSWORD, ROLE) VALUES (?, ?, ?)";
            preparedStatement = connection.prepareStatement(create);
            preparedStatement.setString(1, user.getuUsername().equals("") ? null : user.getuUsername());
            preparedStatement.setString(2, user.getuPassword().equals("") ? null : user.getuPassword());
            preparedStatement.setString(3, user.getuRole());
            preparedStatement.executeUpdate();
            preparedStatement.close();
        } catch (SQLException ex) {
            System.out.println("cannot");
        }
    }
    
    public User authenticateUser(String table, User user) {
        try {
            String authenticate = "SELECT * FROM " + table + " WHERE USERNAME = ? AND PASSWORD = ?";    
            preparedStatement = connection.prepareStatement(authenticate);
            preparedStatement.setString(1, user.getuUsername());    // 1st ?
            preparedStatement.setString(2, user.getuPassword());    // 2nd ?
            resultSet = preparedStatement.executeQuery();

            while (resultSet.next()) {
                User getUser = new User(resultSet.getInt(1), resultSet.getString(2), resultSet.getString(3), resultSet.getString(4));
//                if (getUser.getUserRole().equals("Doctor") || getUser.getUserRole().equals("Nurse")) {
//                    if (isStaffUnapproved(getUser.getUserName())) {
////                        System.out.println("again: "+isStaffUnapproved(getUser.getUserName()));
//                        return null;
//                    }
//                }
                return getUser;

            }
            resultSet.close();
            preparedStatement.close();
        } catch (SQLException ex) {
            
        }
        return null;
    }
}
