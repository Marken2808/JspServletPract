/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controler;

import database.DBbean;
import java.io.IOException;
import java.sql.Connection;

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
@WebServlet("/RegisterPage")
public class SignUpServlet extends HttpServlet {

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

//      get context from BaseListener
        Connection conn = (Connection) getServletContext().getAttribute("conn");
        String userTable = (String) getServletContext().getAttribute("userTable");

        HttpSession session = request.getSession(false);

//      apply context into database
        DBbean db = new DBbean();
        db.getConnection(conn);

//      get parameter from front-end file
        String action = request.getParameter("act");
        String username = request.getParameter("us");
        String password = request.getParameter("pw");
        String role = request.getParameter("role");
        String name = request.getParameter("name");
        
        String email = request.getParameter("email");

        String address = request.getParameter("address");
//        String number   = request.getParameter("number");
//        String route    = request.getParameter("route");
//        String postcode = request.getParameter("postcode");
//        String city     = request.getParameter("city");
//        String area     = request.getParameter("area");
//        String country  = request.getParameter("country");
//        String address = number+", "+route+", "+postcode+", "+city+", "+area+", "+country;

//      save path string
        String path = null;
        System.out.println("Action Equals" + action);
        //      if front-end click btn Register
        if (action.equals("Register")) {
            request.setAttribute("addressHTML", "");
//          init path
            path = "/view/jsp/pages/RegisterPage.jsp";
        } else if (action.equals("SignUp")) {
            String patitentType = null;
            if (role.equals("Patient-Private") || role.equals("Patient-NHS")) {
                String[] patientRole = role.split("-");
                role = patientRole[0];
                 patitentType = patientRole[1];
                System.out.println("patitentType" + patitentType);

            }
//          create user from DBbean.createUser
            User newUser = new User(username, password, role);
            db.createUser(userTable, newUser);

            switch (role) {
                case "Patient":

                    Patient newPatient = new Patient(name, address, username, password);
                    String patientTable = (String) getServletContext().getAttribute("patientTable");
                    System.out.println("patientTable"+patientTable);
                    System.out.println("newPatient"+newPatient);
                    System.out.println("patitentType"+patitentType);
                    System.out.println("email"+email);
                    db.createPatient(patientTable, newPatient, patitentType,email);
                    session.setAttribute("patientData", newPatient);
                    break;

                case "Doctor":
                case "Nurse":
                    Staff newStaff = new Staff(name, address, username, password);
                    String staffTable = (String) getServletContext().getAttribute("staffTable");
                    db.createStaff(staffTable, newStaff);
                    session.setAttribute("staffData", newStaff);
                    break;
            }

//          init path
            path = "/view/jsp/pages/SuccessPage.jsp";
        } else if (action.equals("FindAddress")) {
//            If look up adress button pressed sent to this controller
            path = "/AutoCompleteController";

        } else if (action.equals("SelectAddress")) {
//            if select adress is pressed in the look up box these other parameters must also be countinly pased so boxes remain full
            request.setAttribute("us", username);
            request.setAttribute("pw", password);
            request.setAttribute("name", name);
            request.setAttribute("role", role);
            request.setAttribute("email", email);

            String addressPull = (String) request.getParameter("addressPull");
            System.out.println("addressPull" + addressPull);
            request.setAttribute("address", addressPull);

            path = "/view/jsp/pages/RegisterPage.jsp";

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
