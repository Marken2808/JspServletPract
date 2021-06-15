package controler;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import model.StaffListOfPrescriptions;

/**
 *
 * @author Eli
 */
public class StaffSelectPrescriptionController extends HttpServlet {

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
        
        String sucsessHTML = (String) session.getAttribute("sucsessHTML");
       

        Connection conn = (Connection) getServletContext().getAttribute("conn");
        String patientTable = (String) getServletContext().getAttribute("patientTable");
        String medicationTable = (String) getServletContext().getAttribute("medicationTable");
        String prescriptionTable = (String) getServletContext().getAttribute("prescriptionTable");

        StaffListOfPrescriptions listOfPrescriptions = new StaffListOfPrescriptions(conn, patientTable, medicationTable, prescriptionTable);
        listOfPrescriptions.dbSelect();
        listOfPrescriptions.createHTML();

        String patientHTML = listOfPrescriptions.getPatientHTML();
        String medicationHTML = listOfPrescriptions.getMedicationHTML();
        String refillsHTML = listOfPrescriptions.getRefillsHTML();
        
        System.out.println("listOfPrescriptions");
        System.out.println(listOfPrescriptions);
        
        session.setAttribute("listOfPrescriptions",listOfPrescriptions);
        request.setAttribute("patientHTML", patientHTML);
        request.setAttribute("medicationHTML", medicationHTML);
        request.setAttribute("refillsHTML", refillsHTML);
        request.setAttribute("sucsessHTML", sucsessHTML);
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
