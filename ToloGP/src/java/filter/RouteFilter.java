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
public class RouteFilter implements Filter {
    
    private ServletContext context;
    
    /**
     * Init method for this filter
     */
    public void init(FilterConfig fConfig) {        
        this.context = fConfig.getServletContext();
        this.context.log("AuthenticationFilter initialized");
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
        
        
        
        try {
            HttpServletRequest req = (HttpServletRequest) request;
            HttpServletResponse res = (HttpServletResponse) response;
            HttpSession session = req.getSession(false);

            String uri = req.getRequestURI();
            this.context.log("Requested Resource::" + uri); 
            if (session == null) {
                System.out.println("Session timed out");
    //            req.setAttribute("Session", "<p1>Your session has timed out please log back in.<p1>");            
                this.context.log("None authenticatied request, session:: " + session);
                request.getRequestDispatcher("/view/Welcome.jsp").forward(request, response);
            } else {
                this.context.log("Authenticatied request, session:: " + session);
                chain.doFilter(req, response);
            } 
        } catch (Throwable e) {
        }
        
            
        
        
    }

    /**
     * Destroy method for this filter
     */
    public void destroy() {        
    }
    
}
