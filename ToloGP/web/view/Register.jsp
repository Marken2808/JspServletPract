<%-- 
    Document   : Register
    Created on : 10-Aug-2021, 20:03:57
    Author     : Marken Tuan Nguyen
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Register</title>
    </head>
    <body>
        <h1>Register!</h1>
        <form action="SignUpServlet" method="POST">  
            <div class="form-floating mb-3">
                <input type="text" name="username" class="form-control is-valid" id="floatingInput" placeholder="Username" >
                <label for="floatingInput">Username</label>
            </div>
            <div class="form-floating mb-3">
                <input type="password" name="password" class="form-control is-valid" id="floatingInput" placeholder="Password" />
                <label for="floatingInput">Password</label> 
            </div>
            <button type="submit" name="action" value="signUp">
                Sign Up
            </button>
        </form>
    </body>
</html>
