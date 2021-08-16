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
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import model.User;

/**
 *
 * @author Marken Tuan Nguyen
 */
public class StaffFilter implements Filter {
    
    private ServletContext context;
    
    /**
     * Init method for this filter
     * @param fConfig
     */
    @Override
    public void init(FilterConfig fConfig) {        
        this.context = fConfig.getServletContext();
        this.context.log("StaffFilter initialized");
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
    @Override
    public void doFilter(ServletRequest request, ServletResponse response,
            FilterChain chain) throws IOException, ServletException {
        
        try {
            HttpServletRequest req = (HttpServletRequest) request;
            HttpServletResponse res = (HttpServletResponse) response;
            HttpSession session = req.getSession(false);

            String uri = req.getRequestURI();
            this.context.log("Requested Staff Resource::" + uri); 
            
//            User user = (User) session.getAttribute("user");
            
            
//            if (user.getuRole().equals("Admin")) {     
//                this.context.log("Admin session:: " + session.getAttribute("sessionKey"));
//                chain.doFilter(req, response);
//            } else {
//                System.out.println("Wrong place ?");
//            }

            
            chain.doFilter(req, response);
        } catch (IOException | ServletException e) {
        }
        
            
        
        
    }

    /**
     * Destroy method for this filter
     */
    @Override
    public void destroy() {        
    }
    
    
}
