/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package database;

import java.beans.Statement;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author Marken Tuan Nguyen
 */
public class DBconnection {
    Connection connection = null;
    PreparedStatement preparedStatement = null;
    ResultSet resultSet = null;

    public void getConnection(Connection connection) {
        this.connection = connection;
    }
    
    public String selectByTable(String table) {
        try {
            
            String query = "SELECT * FROM " + table;
            System.out.println(">>>" + query);
            preparedStatement = connection.prepareStatement(query);
            resultSet = preparedStatement.executeQuery();
            StringBuilder sb = new StringBuilder();
            ResultSetMetaData metaData = resultSet.getMetaData();
            int size = metaData.getColumnCount();

            while (resultSet.next()) {
                for (int i = 0; i < size; i++) {  // check how many column
                    String temp = resultSet.getString(i + 1);
                    sb.append(" ");
                    sb.append(temp);

                }
                sb.append("<br>");
            }

            resultSet.close();
            preparedStatement.close();

            return sb.toString();

        } catch (SQLException ex) {
            Logger.getLogger(DBconnection.class.getName()).log(Level.SEVERE, null, ex);
        
        }
        return null;
    }
    
    

}
