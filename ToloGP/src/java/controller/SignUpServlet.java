/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controller;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import model.Staff;
import model.StaffDAO;
import model.User;
import model.UserDAO;

/**
 *
 * @author Marken Tuan Nguyen
 */
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
        response.setContentType("text/html;charset=UTF-8");
        
        String path = "";
        
        if (request.getParameter("action").equals("register")){
                path = "/view/Register.jsp";
        } else {
                path = "/view/Success.jsp";
                
                startSession(request);                
                startConnection(request);
        }
                
        request.getServletContext().getRequestDispatcher(path).forward(request, response);
    }
    
    public void startSession(HttpServletRequest request) {
        HttpSession session = request.getSession(false);
        session.setAttribute("sessionKey", session.getId());
    }
    
    public void startConnection(HttpServletRequest request) {
        
        String username = (request.getParameter("username"));
        String password = (request.getParameter("password"));
        String name = (request.getParameter("name"));
        String phone = (request.getParameter("phone"));
        String address = (request.getParameter("address"));
        String role = (request.getParameter("role"));
        
        Connection connection = (Connection) getServletContext().getAttribute("connection");
        
        userConnection(request, connection, new User(0, username, password, role, name, address, phone));
        
        switch (role){
            case "Doctor":
            case "Nurse" :
//                staffConnection(request, connection, new Staff(name, address, phone));
                break;
                
            case "Patient-Private":
            case "Patient-NHS":
//                patientConnection(request, connection);
                break;
        }

    }
    
    public void userConnection(HttpServletRequest request, Connection connection, User user) {
        String userTable = (String) getServletContext().getAttribute("userTable");
                
        UserDAO userDB = new UserDAO();
        userDB.getConnection(connection);
        userDB.createUser(userTable, user);

        request.setAttribute("user", user);
    }
    
    public void staffConnection(HttpServletRequest request, Connection connection, Staff staff) {
        

        StaffDAO staffDB = new StaffDAO();
        staffDB.getConnection(connection);

        System.out.println("Staff ok??");
    }
    
    public void patientConnection(HttpServletRequest request, Connection connection) {
        

        System.out.println("Patient connected");
        
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
