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
            String create = "INSERT INTO " + table + " (U_USERNAME, U_PASSWORD, U_ROLE, U_NAME, U_ADDRESS, U_PHONE) VALUES (?, ?, ?, ?, ?, ?)";
            preparedStatement = connection.prepareStatement(create);
            preparedStatement.setString(1, user.getuUsername().equals("") ? null : user.getuUsername());
            preparedStatement.setString(2, user.getuPassword().equals("") ? null : user.getuPassword());
            preparedStatement.setString(3, user.getuRole());
            preparedStatement.setString(4, user.getuName().equals("") ? null : user.getuName());
            preparedStatement.setString(5, user.getuAddress().equals("") ? null : user.getuAddress());
            preparedStatement.setString(6, user.getuPhone().equals("") ? null : user.getuPhone());
            
            preparedStatement.executeUpdate();
            preparedStatement.close();
        } catch (SQLException ex) {
            System.out.println("cannot createUser");
        }
    }
    
    public User authenticateUser(String table, User user) {
        try {
            String authenticate = "SELECT * FROM " + table + " WHERE U_USERNAME = ? AND U_PASSWORD = ?";
            preparedStatement = connection.prepareStatement(authenticate);
            preparedStatement.setString(1, user.getuUsername());    // 1st ?
            preparedStatement.setString(2, user.getuPassword());    // 2nd ?
            resultSet = preparedStatement.executeQuery();
            while (resultSet.next()) {
                User userData = new User(
                        resultSet.getInt(1), 
                        resultSet.getString(2), 
                        resultSet.getString(3), 
                        resultSet.getString(4),
                        resultSet.getString(5), 
                        resultSet.getString(6), 
                        resultSet.getString(7)
                );
                return userData;
            }
            resultSet.close();
            preparedStatement.close();
        } catch (SQLException ex) {
            System.out.println("cannot authenticateUser");
        }
        return null;
    }
}
