/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controler;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.io.PrintWriter;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import model.DoctorListOfSpecalists;
import model.StaffListOfInvoices;

/**
 *
 * @author Eli
 */
public class StaffActionInvoiceController extends HttpServlet {

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

        StaffListOfInvoices listOfInvoices = (StaffListOfInvoices) session.getAttribute("listOfInvoices");

        String invoice = request.getParameter("invoice");

        ServletContext context = request.getServletContext();
        String path = context.getRealPath("/") + "Invoice-";

        if (invoice != null) {
            String nhsEmail = (String) getServletContext().getAttribute("nhsEmail");
            String downLoadHTML = listOfInvoices.createPDF(path, invoice, nhsEmail);

            request.setAttribute("downLoadHTML", downLoadHTML);
            session.setAttribute("listOfInvoices", listOfInvoices);
            request.getRequestDispatcher("view/jsp/pages/staff/StaffCreateInvoice.jsp").forward(request, response);
        } else {

            String letter = listOfInvoices.getFilename();
            System.out.println("letter" + letter);

            File pdfFile = new File(path + letter +".pdf");
            System.out.println("pdfFile+ "+pdfFile);

            response.setContentType("application/pdf");
            response.addHeader("Content-Disposition", "attachment; filename=" +"Invoice-" +letter +".pdf");
            response.setContentLength((int) pdfFile.length());

            FileInputStream fileInputStream = new FileInputStream(pdfFile);
            OutputStream responseOutputStream = response.getOutputStream();
            int bytes;
            while ((bytes = fileInputStream.read()) != -1) {
                System.out.println("bytes " + bytes);
                responseOutputStream.write(bytes);
            }
        }

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
