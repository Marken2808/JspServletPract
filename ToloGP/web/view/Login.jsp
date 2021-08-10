<%-- 
    Document   : Login
    Created on : 10-Aug-2021, 19:24:50
    Author     : Marken Tuan Nguyen
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>JSP Page</title>
    </head>
    <body>
        <h1>Login!</h1>
        <form action="SignInServlet" method="POST">            
            <input type="submit" name="actionSignIn" value="Sign In"/>              
        </form>
    </body>
</html>
