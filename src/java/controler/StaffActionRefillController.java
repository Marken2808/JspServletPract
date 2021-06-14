/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controler;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import model.StaffListOfRefills;

/**
 *
 * @author Eli
 */
public class StaffActionRefillController extends HttpServlet {

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

        HttpSession session = request.getSession(false);
        StaffListOfRefills listOfRefills = (StaffListOfRefills) session.getAttribute("listOfRefills");

        ArrayList<String> prescriptionData = listOfRefills.getPrescriptionData();

        
        String idMedicine;
        String approvedMed;
        
        String sucssesHTML = "";
        
        for (int i = 0; i< prescriptionData.size(); i += 4) {
            idMedicine =  prescriptionData.get(i) + prescriptionData.get(i+1);
            approvedMed = request.getParameter(idMedicine);
            System.out.println("approvedMed "+approvedMed);
            if(approvedMed != null ){
                sucssesHTML = sucssesHTML + listOfRefills.dbUpdate(prescriptionData.get(i),prescriptionData.get(i+1)); 
                
            }
        }
        
         request.setAttribute("sucssesHTML", sucssesHTML);
        
        request.getRequestDispatcher("view/jsp/pages/staff/StaffApprovePrescriptionView.jsp").forward(request, response);
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
