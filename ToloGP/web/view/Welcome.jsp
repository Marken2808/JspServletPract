<%-- 
    Document   : Welcome
    Created on : 10-Aug-2021, 12:51:26
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
        <h2> Welcome to Tolo GP </h2>
        
        <form action="SignInServlet" method="POST">            
            <input type="submit" name="actionSignIn" value="Log In"/>              
        </form>
        
        <form action="SignUpServlet" method="POST">      
            <input type="submit" name="actionSignUp" value="Register"/>  
        </form>
    </body>
</html>
