/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package filter;

import java.io.IOException;
import java.io.PrintStream;
import java.io.PrintWriter;
import java.io.StringWriter;
import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

/**
 *
 * @author Marken Tuan Nguyen
 */

@WebFilter("/filter")
public class RouteFilter implements Filter {
    
    private ServletContext context;
    
    /**
     * Init method for this filter
     */
    public void init(FilterConfig fConfig) {        
        this.context = fConfig.getServletContext();
        this.context.log("RouterFilter initialized");
    }

    /**
     *
     * @param request The servlet request we are processing
     * @param response The servlet response we are creating
     * @param chain The filter chain we are processing
     *
     * @exception IOException if an input/output error occurs
     * @exception ServletException if a servlet error occurs
     */
    public void doFilter(ServletRequest request, ServletResponse response,
            FilterChain chain) throws IOException, ServletException {
        
        
            HttpServletRequest req = (HttpServletRequest) request;
            HttpServletResponse res = (HttpServletResponse) response;
            HttpSession session = req.getSession(false);
            
            String uri = req.getRequestURI();
            String wel = req.getContextPath() + "/";
            
//          return first page when only session null and not initialise uri
            if (session == null && !uri.endsWith(req.getContextPath() + "/")) {
                System.out.println("Session timed out");
                res.sendRedirect(wel);
//                request.getRequestDispatcher("/view/Welcome.jsp").forward(request, response);
            } 
            
            if (session !=null && session.getAttribute("user")!=null) {
                System.out.println("you are in");
            } else {
                System.out.println("not set yet");
            }
            
            chain.doFilter(req, response);
            
        
        
        
    }

    /**
     * Destroy method for this filter
     */
    public void destroy() {        
    }
    
}
