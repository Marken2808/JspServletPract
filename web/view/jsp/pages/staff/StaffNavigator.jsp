<%-- 
    Document   : Navigator
    Created on : 15-Dec-2020, 17:05:07
    Author     : Marken Tuan Nguyen
--%>

<%@page import="model.Staff"%>
<%@page import="model.User"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.0.0-beta1/dist/css/bootstrap.min.css" 
              rel="stylesheet" 
              integrity="sha384-giJF6kkoqNQ00vy+HMDP7azOuL0xtbfIcaT9wjKHr8RbDVddVHyTfAAsrekwKmP1" 
              crossorigin="anonymous">
        <script src="https://cdn.jsdelivr.net/npm/bootstrap@5.0.0-beta1/dist/js/bootstrap.bundle.min.js" 
                integrity="sha384-ygbV9kiqUc6oa4msXn9868pTtWMgiQaeYH7/t7LECLbyPA2x65Kgf80OJFdroafW" 
                crossorigin="anonymous"></script>
        <script src="https://unpkg.com/feather-icons/dist/feather.min.js"></script>
                
    </head>
    <body>
        <nav class="navbar navbar-light navbar-expand-lg bg-light">
            <form class="me-auto container-fluid" method="post" action="StaffViewController">
                <button type="submit" name="action" value="Home" class="btn btn-outline-success navbar-brand mb-0 h1">Staff Dashboard</button>
                <!--<a class="btn btn-outline-success navbar-brand mb-0 h1" href="#">Staff Dashboard</a>-->
                <button class="navbar-toggler" type="button" data-bs-toggle="collapse" data-bs-target="#navbarSupportedContent" aria-controls="navbarSupportedContent" aria-expanded="false" aria-label="Toggle navigation">
                    <span class="navbar-toggler-icon"></span>
                </button>
                <div class="collapse navbar-collapse " id="navbarSupportedContent">
                    
                        <ul class="navbar-nav me-auto mb-2 mb-lg-0">
                           
                            
                            <%
                                User user = (User) request.getSession().getAttribute("userData");
                                if (user.getUserRole().equals("Doctor")) {
                            %>
                                    <li class="nav-item">
                                        <button type="submit" name="action" value="Refer To Specalist" class="btn">Refer To Specalist</button>
                                    </li>
                            <%  } %>
                            
                            <li class="nav-item">
                                <button type="submit" name="action" value="Set Patient Prescription" class="btn">Set Patient Prescription</button>
                            </li>
                            <li class="nav-item">
                                <button type="submit" name="action" value="Approve Prescription Refill" class="btn">Approve Prescription Refill</button>
                            </li>
                            <li class="nav-item">
                                <button type="submit" name="action" value="View Appointments" class="btn">View Appointments</button>
                            </li>
                            <li class="nav-item">
                                <button type="submit" name="action" value="Create Invoice" class="btn">Create Invoice</button>
                            </li>
                            
                        </ul>
                    
                    <div>
                        
                        <%Staff staff = (Staff) session.getAttribute("ThisStaffData");%>
                        <label>
                            Hi, <%=staff.getStaffName()%>
                        </label>
                    </div>
                </div>
            </form>
            <form action="SignOutServlet" method="POST">
                <button type="submit" class="btn" value="GoHome">
                    <i data-feather="log-out"></i><script>feather.replace();</script>
                </button>
            </form>
            <!--</div>-->
        </nav>
    </body>
</html>

