/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controler;

import database.DBbean;
import java.io.IOException;
import java.sql.Connection;
import java.util.ArrayList;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import model.Patient;
import model.Staff;
import model.User;

/**
 *
 * @author ESD20
 */
@WebServlet("/LoginPage")
public class SignInServlet extends HttpServlet {

    /**
     * Processes requests for both HTTP <code>GET</code> and <code>POST</code>
     * methods.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        HttpSession session = request.getSession(false);

//      get context from BaseListener
        Connection conn = (Connection) getServletContext().getAttribute("conn");
        String userTable = (String) getServletContext().getAttribute("userTable");
        String patientTable = (String) getServletContext().getAttribute("patientTable");
        String staffTable = (String) getServletContext().getAttribute("staffTable");

//      apply context into database
        DBbean db = new DBbean();
        db.getConnection(conn);

//      Set session at beginning
        

//      get parameter from front-end file
        String username = request.getParameter("us");
        String password = request.getParameter("pw");

        String action = request.getParameter("act");

//        System.out.println("GET DATE: " + datetime);
//      save path string       
        String path = null;
//        if front-end click btn Login
        if (action.equals("Login")) {
//          check Auth from DBbean.signInAuth
            User user = db.signInAuth(userTable, new User(username, password));

//          check valid user            
            if (user == null) {
                path = "/view/jsp/pages/ErrorPage.jsp";

            } else {

                session.setAttribute("userData", user);
                session.setAttribute("sessionKey", session.getId());
                
               

//                System.out.println("test: "+user.getUserRole());
                switch (user.getUserRole()) {
                    case "Doctor":
                    case "Nurse":
                        Staff staff = db.retreiveStaff(user.getUserName());
                        System.out.println("test: "+staff.getStaffName() + staff.getStaffID());
                        session.setAttribute("ThisStaffData", staff);
//                        Staff staff = new Staff();
//
//                        String staffName = db.selectNameByRole(staffTable, "staffname", user);
//                        session.setAttribute("staffName", staffName);
                        

//                        ArrayList<ArrayList<String>> staffData = db.selectByQuery("SELECT * FROM STAFFS WHERE USERNAME = '" + username + "'");
//                        String staffID = staffData.get(0).get(0);
//                        System.out.println("staffID"+staffID);
//                        staff.setStaffID(staffID);
//                        session.setAttribute("staff", staff);
                        path = "/view/jsp/pages/staff/StaffDashboard.jsp";
                        break;
                    case "Patient":
                        Patient patient = db.retreivePatient(user.getUserName());
                        System.out.println("test: "+patient.getPatientName() + patient.getPatientUsername());
                        session.setAttribute("ThisPatientData", patient);
                        path = "/view/jsp/pages/patient/PatientDashboard.jsp";
                        break;
                    case "Admin":
                        path = "/view/jsp/pages/admin/AdminDashboard.jsp";
                        break;
                }

//              init path
            }

//        if front-end click btn FastTrack
        }
//      access path
        request.getServletContext().getRequestDispatcher(path).forward(request, response);

    }

    // <editor-fold defaultstate="collapsed" desc="HttpServlet methods. Click on the + sign on the left to edit the code.">
    /**
     * Handles the HTTP <code>GET</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        processRequest(request, response);

    }

    /**
     * Handles the HTTP <code>POST</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        processRequest(request, response);

    }

    /**
     * Returns a short description of the servlet.
     *
     * @return a String containing servlet description
     */
    @Override
    public String getServletInfo() {
        return "Short description";
    }// </editor-fold>

}
