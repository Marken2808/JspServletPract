/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controler;

import java.io.IOException;
import java.io.PrintWriter;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import model.Staff;

/**
 *
 * @author Eli
 */
public class StaffViewController extends HttpServlet {

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
        String action = request.getParameter("action");
        String path = "";

        HttpSession session = request.getSession(false);

        

      

        switch (action) {
            case "Home":
                path = "view/jsp/pages/staff/StaffDashboard.jsp";
                break;
                
            case "Refer To Specalist":
                path = "view/jsp/pages/staff/DoctorReferToSpecalist.jsp";
                break;

            case "Set Patient Prescription":
             
                path = "view/jsp/pages/staff/StaffSetPrescriptionView.jsp";
                break;

            case "Approve Prescription Refill":
                
                path = "view/jsp/pages/staff/StaffApprovePrescriptionView.jsp";
                break;

            case "View Appointments":
                path = "view/jsp/pages/staff/StaffAppointmentView.jsp";
                break;

            case "Create Invoice":
                System.out.println("gets to create invoice");
                path = "view/jsp/pages/staff/StaffCreateInvoice.jsp";
                break;

        }

        request.getRequestDispatcher(path).forward(request, response);

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
