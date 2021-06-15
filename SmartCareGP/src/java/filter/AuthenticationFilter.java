/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package filter;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Enumeration;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import model.Staff;
import model.User;

@WebFilter("/AuthenticationFilter")
public class AuthenticationFilter implements Filter {

    private ServletContext context;

    @Override
    public void init(FilterConfig fConfig) throws ServletException {
        this.context = fConfig.getServletContext();
        this.context.log("AuthenticationFilter initialized");
    }

    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {

        HttpServletRequest req = (HttpServletRequest) request;
        HttpServletResponse res = (HttpServletResponse) response;

        String uri = req.getRequestURI();

        if (uri.contains("SignInServlet")) {
            String action = request.getParameter("act");
            if (action.equals("Login")) {

                HttpSession session = req.getSession();
            }

        }
        HttpSession session = req.getSession(false);

        System.out.println("uri " + uri);
        this.context.log("Requested Resource::" + uri); 
        System.out.println("STARTS");

        if (!(uri.contains("SignUpServlet") || (uri.contains("SignInServlet")) || uri.equals("/ESDweb/")   ||   uri.contains("SignOutServlet")  )) {

            if (session == null) {
                System.out.println("Session timed out");
                req.setAttribute("Session", "<p1>Your session has timed out please log back in.<p1>");
                req.getServletContext().getRequestDispatcher("/view/jsp/pages/LoginPage.jsp").forward(req, res);
            }

            System.out.println("Skip over test");
            User user = (User) session.getAttribute("userData");
            String role = user.getUserRole();
            if (role.equals("Doctor")) {
                System.out.println("doctor!!!!");
                if (uri.contains("Patient") || uri.contains("Admin")) {
                    req.getServletContext().getRequestDispatcher("/view/jsp/pages/staff/StaffDashboard.jsp").forward(req, res);
                }
            } else if (role.equals("Nurse")) {

                if (uri.contains("Patient") || uri.contains("Admin") || uri.contains("Doctor")) {
                    req.getServletContext().getRequestDispatcher("/view/jsp/pages/staff/StaffDashboard.jsp").forward(req, res);
                }

            } else if (role.equals("Admin")) {

                if (uri.contains("Patient") || uri.contains("Staff") || uri.contains("Doctor")) {
                    req.getServletContext().getRequestDispatcher("/view/jsp/pages/admin/AdminDashboard.jsp").forward(req, res);
                }

            } else if (role.equals("Patient")) {
                if (uri.contains("Admin") || uri.contains("Staff") || uri.contains("Doctor")) {
                    req.getServletContext().getRequestDispatcher("/view/jsp/pages/patient/PatientDashboard.jsp").forward(req, res);
                }

            }
        }

        if (session == null && (!(uri.contains("SignUpServlet") || (uri.contains("SignInServlet")) || uri.equals("/ESDweb/")      ||   uri.contains("SignOutServlet")   ))) {

            req.getServletContext().getRequestDispatcher("/view/jsp/pages/LoginPage.jsp").forward(req, res);
        }

        chain.doFilter(req, res);
    }

    @Override
    public void destroy() {
        //close any resources here
    }

}
