/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package database;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;

/**
 *
 * @author Marken Tuan Nguyen
 */
public class DatabaseRun {

    static Connection conn;
    static PreparedStatement ps;
    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) throws ClassNotFoundException, SQLException {
        
        System.out.println("Creating DB first time ...");
        run();
//        System.out.println("Re-run by deleting old table only");
    }
    
    public static void run() throws ClassNotFoundException, SQLException{
        
        Class.forName("org.apache.derby.jdbc.ClientDriver");
        conn = DriverManager.getConnection("jdbc:derby://localhost:1527/ESDweb", "root", "123456");
           
        tableUSERS(conn);
        tableSTAFFS(conn);
        tablePATIENTS(conn);
        tableMEDICATIONS(conn);
        tablePRESCRIPTIONS(conn);
        tableAPPOINTMENTS(conn);
        tablePRICES(conn);

    }
    
    public static void exeQuery(String query) throws SQLException{
        String[] insert = query.split(";");
//        System.out.println(insert.length);
        for (String i : insert) {
//            System.out.println(i);
            ps = conn.prepareStatement(i);
            ps.executeUpdate();
        }
    }
    
    public static void tableUSERS(Connection conn) throws SQLException{
        String create = 
            "CREATE TABLE USERS (\n" +
            "USERNAME VARCHAR(20) NOT NULL, \n" +
            "PASSWORD VARCHAR(20) NOT NULL, \n" +
            "ROLE VARCHAR(10) NOT NULL, \n" +
            "PRIMARY KEY (USERNAME)\n" +
            ");";
        exeQuery(create);
        System.out.println("Table USERS created successfully!");
        
        String query =
            "INSERT INTO ROOT.USERS (USERNAME, PASSWORD, ROLE) \n" +
            "	VALUES ('meaydin', 'aydinme', 'Doctor');\n" +
            "INSERT INTO ROOT.USERS (USERNAME, PASSWORD, ROLE) \n" +
            "	VALUES ('eaydin', '12345me', 'Nurse');\n" +
            "INSERT INTO ROOT.USERS (USERNAME, PASSWORD, ROLE) \n" +
            "	VALUES ('caidan', '5432@10', 'Patient');\n" +
            "INSERT INTO ROOT.USERS (USERNAME, PASSWORD, ROLE) \n" +
            "	VALUES ('princehassan', 'prince_passwd', 'Patient');\n" +
            "INSERT INTO ROOT.USERS (USERNAME, PASSWORD, ROLE) \n" +
            "	VALUES ('admin', 'admin', 'Admin');"+
            "INSERT INTO ROOT.USERS (USERNAME, PASSWORD, ROLE) \n" +
                "	VALUES ('doctor', 'doctor', 'Doctor');"+
            "INSERT INTO ROOT.USERS (USERNAME, PASSWORD, ROLE) \n" +
            "	VALUES ('nurse', 'nurse', 'Nurse');"+
            "INSERT INTO ROOT.USERS (USERNAME, PASSWORD, ROLE) \n" +
            "	VALUES ('patient', 'patient', 'Patient');";
        exeQuery(query);
        System.out.println("Table USERS insert successfully!");
        
    }
    
    public static void tableSTAFFS(Connection conn) throws SQLException {
        String create = 
            "CREATE TABLE STAFFS(\n" +
            "STAFFID INTEGER NOT NULL GENERATED ALWAYS AS IDENTITY,\n" +
            "STAFFNAME VARCHAR(20) NOT NULL, \n" +
            "STAFFADDRESS VARCHAR(100), \n" +
            "APPROVED BOOLEAN DEFAULT FALSE  NOT NULL,\n" +
            "USERNAME VARCHAR(20) REFERENCES USERS(USERNAME),\n" +
            "PRIMARY KEY (STAFFID)\n" +
            ");";
        
       
        exeQuery(create);
        System.out.println("Table STAFFS created successfully!");
        
        String query =
            "INSERT INTO ROOT.STAFFS (STAFFNAME, STAFFADDRESS, USERNAME) \n" +
            "	VALUES ('Mehmet Aydin', 'Mehmets Address, London, NW4 0BH', 'meaydin');\n" +
            "INSERT INTO ROOT.STAFFS (STAFFNAME, STAFFADDRESS, USERNAME) \n" +
            "	VALUES ('Emin Aydin', 'Emiin''s Address, Bristol, BS16', 'eaydin');"+
            "INSERT INTO ROOT.STAFFS (STAFFNAME, STAFFADDRESS, USERNAME) \n" +
            "	VALUES ('Doctor TTT', 'Emiin''s Address, Bristol, BS16', 'doctor');"+
            "INSERT INTO ROOT.STAFFS (STAFFNAME, STAFFADDRESS, USERNAME) \n" +
            "	VALUES ('Nurse TTT', 'Emiin''s Address, Bristol, BS16', 'nurse');";
        exeQuery(query);
        System.out.println("Table STAFFS insert successfully!");
    }
    
    public static void tablePATIENTS(Connection conn) throws SQLException {
        String create = 
            "CREATE TABLE PATIENTS (\n" +
            "PATIENTID INTEGER NOT NULL GENERATED ALWAYS AS IDENTITY,\n" +
            "PATIENTNAME VARCHAR(20) NOT NULL, \n" +
            "PATIENTADDRESS VARCHAR(100), \n" +
            "PATIENTTYPE VARCHAR(10), \n" +
            "USERNAME VARCHAR(20) REFERENCES USERS(USERNAME),\n" +
            "PRIMARY KEY (PATIENTID)\n" +
            ");";
        exeQuery(create);
        System.out.println("Table PATIENTS created successfully!");
        
        String query =
            "INSERT INTO ROOT.PATIENTS (PATIENTNAME, PATIENTADDRESS, PATIENTTYPE, USERNAME) \n" +
            "	VALUES ('Charly Aidan', '14 King Street, Aberdeen, AB24 1BR', 'NHS', 'caidan');\n" +
            "INSERT INTO ROOT.PATIENTS (PATIENTNAME, PATIENTADDRESS, PATIENTTYPE, USERNAME) \n" +
            "	VALUES ('Prince Hassan', 'Non-UK street, Non-UK Town, Non_UK', 'private', 'princehassan');"+
            "INSERT INTO ROOT.PATIENTS (PATIENTNAME, PATIENTADDRESS, PATIENTTYPE, USERNAME) \n" +
            "	VALUES ('Patient TTT', 'Non-UK street, Non-UK Town, Non_UK', 'private', 'patient');";
       
        
        exeQuery(query);
        System.out.println("Table PATIENTS insert successfully!");
    }
    
    public static void tableMEDICATIONS(Connection conn) throws SQLException {
        String create = 
            "CREATE TABLE MEDICATIONS (\n" +
            "MEDICATIONNAME VARCHAR(20) NOT NULL, \n" +
            "QUANTITY INTEGER, \n" +
            "PRIMARY KEY (MEDICATIONNAME)\n" +
            ");";
        
        exeQuery(create);
        System.out.println("Table MEDICATIONS created successfully!");
        
        String query =
            "INSERT INTO ROOT.MEDICATIONS (MEDICATIONNAME, QUANTITY) \n" +
            "	VALUES ('Asprin', 20);\n" +
            "INSERT INTO ROOT.MEDICATIONS (MEDICATIONNAME, QUANTITY) \n" +
            "	VALUES ('Parctamol', 30);\n" +
            "INSERT INTO ROOT.MEDICATIONS (MEDICATIONNAME, QUANTITY)\n" +
            "        VALUES ('Calpol', 12);";
        exeQuery(query);
        System.out.println("Table MEDICATIONS insert successfully!");
    }
    
    public static void tablePRESCRIPTIONS(Connection conn) throws SQLException {
        String create = 
            "CREATE TABLE PRESCRIPTIONS (\n" +
            "PATIENTID INTEGER REFERENCES PATIENTS(PATIENTID), \n" +
            "MEDICATIONNAME VARCHAR(20) REFERENCES MEDICATIONS(MEDICATIONNAME), \n" +
            "DATECREATED DATE, \n" +
            "LASTREFILLDATE DATE, \n" +
            "INITIALREFILLS INTEGER, \n" +
            "REMAININGREFILLS INTEGER, \n" +
            "APPROVED BOOLEAN, \n" +
            "PRIMARY KEY (PATIENTID, MEDICATIONNAME)\n" +
            ");";
        
        
        exeQuery(create);
        System.out.println("Table PRESCRIPTIONS created successfully!");
        
        String query =
//            "INSERT INTO ROOT.PRESCRIPTIONS (PATIENTID, MEDICATIONNAME, REFILLS) \n" +
//            "	VALUES (1, 'Parctamol',2);\n" +
//            "INSERT INTO ROOT.PRESCRIPTIONS (PATIENTID, MEDICATIONNAME, REFILLS) \n" +
//            "	VALUES (2, 'Asprin', 1);\n" +
//            "INSERT INTO ROOT.PRESCRIPTIONS (PATIENTID, MEDICATIONNAME, REFILLS) \n" +
//            "	VALUES (2, 'Calpol', 0);";
            "INSERT INTO ROOT.PRESCRIPTIONS (PATIENTID, MEDICATIONNAME, DATECREATED, LASTREFILLDATE, INITIALREFILLS, REMAININGREFILLS, APPROVED) \n" +
                " VALUES (2, 'Asprin', '2021-01-16', '2021-01-16', 4, 4, true);\n" +
            "INSERT INTO ROOT.PRESCRIPTIONS (PATIENTID, MEDICATIONNAME, DATECREATED, LASTREFILLDATE, INITIALREFILLS, REMAININGREFILLS, APPROVED) \n" +
                " VALUES (1, 'Parctamol', '2020-12-17', '2020-12-17', 1, 1, true);\n" +
            "INSERT INTO ROOT.PRESCRIPTIONS (PATIENTID, MEDICATIONNAME, DATECREATED, LASTREFILLDATE, INITIALREFILLS, REMAININGREFILLS, APPROVED) \n" +
                " VALUES (1, 'Asprin', '2021-01-16', '2021-01-16', 3, 3, true);\n" +
            "INSERT INTO ROOT.PRESCRIPTIONS (PATIENTID, MEDICATIONNAME, DATECREATED, LASTREFILLDATE, INITIALREFILLS, REMAININGREFILLS, APPROVED) \n" +
                " VALUES (1, 'Calpol', '2021-01-03', '2021-01-03', 5, 5, true);";
        
        exeQuery(query);
        System.out.println("Table PRESCRIPTIONS insert successfully!");
    }
    
    public static void tableAPPOINTMENTS(Connection conn) throws SQLException{
        String create = "CREATE TABLE APPOINTMENTS (\n" +
            "    AID INTEGER NOT NULL GENERATED ALWAYS AS IDENTITY, \n" +
            "    SID INTEGER REFERENCES STAFFS(STAFFID),\n" +
            "    PID INTEGER REFERENCES PATIENTS(PATIENTID),\n" +
            "    ADATE DATE, \n" +
            "    ATIME TIME, \n" +
            "    ALENGTH INTEGER, \n" +
            "    PRIMARY KEY (AID)\n" +
            ");";
        
        exeQuery(create);
        System.out.println("Table APPOINTMENTS created successfully!");
        
    }
    
    public static void tablePRICES(Connection conn) throws SQLException{
        String create = "CREATE TABLE PRICES (LENGTH INTEGER NOT NULL, COST INTEGER, PRIMARY KEY (LENGTH));";
        
        exeQuery(create);
        System.out.println("Table PRICES created successfully!");
        
    }
    
}
