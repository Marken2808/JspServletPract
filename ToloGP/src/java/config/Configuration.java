/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package config;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import javax.servlet.ServletContext;
import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;

/**
 *
 * @author Marken Tuan Nguyen
 */
public class Configuration implements ServletContextListener {
    //  declare connection
    Connection connection = null;

    @Override
    public void contextInitialized(ServletContextEvent sce) {
    //  create context 
        ServletContext context = sce.getServletContext();
        
        
        String dbName = "tolo";
        String dbPass = "tolo";
        
        String database = context.getInitParameter("DbContext");
        String userTable = context.getInitParameter("UserContext");
        
        System.out.println("<<<" + database.trim());
        try {
            Class.forName("org.apache.derby.jdbc.ClientDriver");
            connection = DriverManager.getConnection("jdbc:derby://localhost:1527/" + database.trim(), dbName, dbPass);
        } catch (SQLException | ClassNotFoundException e) {
            System.out.println(e.getMessage());
        }

//      set shortcut to callback
        context.setAttribute("connection", connection);
        context.setAttribute("userTable", userTable);
        
    }

    @Override
    public void contextDestroyed(ServletContextEvent sce) {
        try {      
            connection.close();
        } catch (SQLException ex) {
        }
    }   
}
