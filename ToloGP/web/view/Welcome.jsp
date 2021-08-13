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
        <title>ToloGP</title>
    </head>
    <body>
        <h2> Welcome to Tolo GP </h2>
        
        <form action="SignInServlet" method="POST">            
            <button type="submit" name="action" value="logIn">
                Log In
            </button>
        </form>
        
        <form action="SignUpServlet" method="POST">      
            <button type="submit" name="action" value="register">
                Register
            </button>
        </form>
    </body>
</html>
