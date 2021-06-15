<%-- 
    Document   : Navigator
    Created on : 15-Dec-2020, 17:05:07
    Author     : Marken Tuan Nguyen
--%>

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
            <form class="me-auto container-fluid " method="post" action="AdminViewController">
                <button type="submit" name="action" value="Home" class="btn btn-outline-success navbar-brand mb-0 h1">Admin Dashboard</button>
                <!--<a class="btn btn-outline-success navbar-brand mb-0 h1" href="#">Admin Dashboard</a>-->
                <button class="navbar-toggler" type="button" data-bs-toggle="collapse" data-bs-target="#navbarSupportedContent" aria-controls="navbarSupportedContent" aria-expanded="false" aria-label="Toggle navigation">
                    <span class="navbar-toggler-icon"></span>
                </button>
                <div class="collapse navbar-collapse " id="navbarSupportedContent">
                    
                        <ul class="navbar-nav me-auto mb-2 mb-lg-0">
                       
                            <li class="nav-item">
                                <button type="submit" name="action" value="Approve Staff" class="btn">Applications</button>
                                <!--<a class="nav-link" href="AdminApproveStaffView.jsp">Applications</a>-->
                            </li>
                            <li class="nav-item">
                                <button type="submit" name="action" value="Handle Turnover" class="btn">Turnover</button>
                                <!--<a class="nav-link" href="#">Turnover</a>-->
                            </li>
                            <li class="nav-item dropdown">

                                <button class="btn dropdown-toggle " id="navbarDropdown" type="button" data-bs-toggle="dropdown" aria-expanded="false">
                                    Access
                                </button>

                                <ul class="dropdown-menu" aria-labelledby="navbarDropdown">
                                    <li><button type="submit" name="action" value="Accsess Users" class="dropdown-item">Users</button></li>
                                    <li><button type="submit" name="action" value="Acssess Invoices" class="dropdown-item">Invoices</button></li>
                                    <li><button type="submit" name="action" value="Accsess Prescriptions" class="dropdown-item">Prescriptions</button></li>
                                    <li><button type="submit" name="action" value="Accsess Appointments" class="dropdown-item">Appointments</button></li>
                                </ul>
                            </li>
                        </ul>
                    
                    <div>
                        <label>
                            Hi, Admin
                        </label>
                    </div>
                </div>
            </form>
            <form action="SignOutServlet" method="POST">
                <button type="submit" class="btn" value="GoHome">
                    <i data-feather="log-out"></i><script>feather.replace();</script>
                </button>
            </form>
        </nav>
    </body>
</html>

