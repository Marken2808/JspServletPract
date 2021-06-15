/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controler;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import javax.servlet.ServletContext;
import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;

/**
 * Web application lifecycle listener.
 *
 * @author ESD20
 */
public class BaseListener implements ServletContextListener {

//  declare connection
    Connection conn = null;

    @Override
    public void contextInitialized(ServletContextEvent sce) {
//      create context 
        ServletContext context = sce.getServletContext();
//      mapping context fromm -------------------web.xml------------------------
//      mapping context of DbContext to access Dbname "ESDweb"
        String database = context.getInitParameter("DbContext");
        String dbAccName = "root";
        String dbAccPass = "123456";
//      mapping context of UserContext to access table "USERS"
        String userTable = context.getInitParameter("UserContext");
//      mapping context of StaffContext to access table "STAFFS"
        String staffTable = context.getInitParameter("StaffContext");
//      mapping context of PatientContext to access table "PATIENTS"
        String patientTable = context.getInitParameter("PatientContext");

        //mapping context of PrescriptionContext to access table "PRESCRIPTION"
        String prescriptionTable = context.getInitParameter("PrescriptionContext");
        
        String medicationTable = context.getInitParameter("MedicationContext");

        String appointmentTable = context.getInitParameter("AppointmentContext");
        
        String referralsTable = context.getInitParameter("ReferralsContext");
        
        String specalistTable = context.getInitParameter("SpecalistContext");

        String turnoverTable = context.getInitParameter("TurnoverContext");
        
        String invoiceTable = context.getInitParameter("InvoiceContext");
        
        String nhsEmail = context.getInitParameter("nhsEmail");
        
        
        try {
            Class.forName("org.apache.derby.jdbc.ClientDriver");
            conn = DriverManager.getConnection("jdbc:derby://localhost:1527/" + database.trim(), dbAccName, dbAccPass);
        } catch (SQLException | ClassNotFoundException e) {
//            System.out.println(e.getMessage());
        }

//      set shortcut to callback
        context.setAttribute("conn", conn);
        context.setAttribute("userTable", userTable);
        context.setAttribute("staffTable", staffTable);
        context.setAttribute("patientTable", patientTable);
        context.setAttribute("appointmentTable", appointmentTable);
        context.setAttribute("medicationTable", medicationTable);
        context.setAttribute("prescriptionTable", prescriptionTable);
        
        context.setAttribute("referralsTable", referralsTable);
        context.setAttribute("specalistTable", specalistTable);
        context.setAttribute("turnoverTable", turnoverTable);
        context.setAttribute("invoiceTable", invoiceTable);
        context.setAttribute("nhsEmail",nhsEmail);
    }

    @Override
    public void contextDestroyed(ServletContextEvent sce) {
        try {
//          close connection            
            conn.close();
        } catch (SQLException ex) {
//            Logger.getLogger(BaseListener.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
}
