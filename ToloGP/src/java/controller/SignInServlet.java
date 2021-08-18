/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controller;

import database.DBconnection;
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

@WebServlet("/signIn")
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
        response.setContentType("text/html;charset=UTF-8");
        
        String path = "/";
        
//        System.out.println("test: " + request.getParameter("action"));
        
        switch(request.getParameter("action")) {
            case "logIn":
                path = "/view/Login.jsp";
                request.getServletContext().getRequestDispatcher(path).forward(request, response);
                break;
            case "signIn":
                startConnection(request, response);
                break;
        }
    }

    public void startConnection(HttpServletRequest request, HttpServletResponse response) throws IOException {
        
        HttpSession session = request.getSession(false);
        session.setAttribute("sessionKey", session.getId());
        
        String username = request.getParameter("username");
        String password = request.getParameter("password");
        String path = "";
        
        Connection connection = (Connection) getServletContext().getAttribute("connection");
        User user = userAuthentication(connection, new User(username, password));
        session.setAttribute("user", user);
        
        switch(user.getuRole()){
            case "Admin":
                path = "/admin";
                break;
            case "Doctor":
            case "Nurse":
                path = "/staff";
                break;
            case "Patient-Private":
            case "Patient-NHS":
                path = "/patient";
            default:
                path = "/" ;
                break;

        }

        response.sendRedirect(request.getContextPath() + path);
        
    }
    
    public User userAuthentication (Connection connection, User user) {
              
        String userTable = (String) getServletContext().getAttribute("userTable");
        
        UserDAO userDB = new UserDAO();
        userDB.getConnection(connection);

        return user!=null ? userDB.authenticateUser(userTable, user) : null;

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
