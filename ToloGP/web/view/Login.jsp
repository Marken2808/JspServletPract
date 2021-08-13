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
        <title>Login</title>
    </head>
    <body>
        <h1>Login!</h1>
        <form action="SignInServlet" method="POST">
            <div class="form-floating mb-3">
                <input type="text" name="username" class="form-control is-valid" id="floatingInput" placeholder="Username" >
                <label for="floatingInput">Username</label>
            </div>
            <div class="form-floating mb-3">
                <input type="password" name="password" class="form-control is-valid" id="floatingInput" placeholder="Password" />
                <label for="floatingInput">Password</label> 
            </div>
            <button type="submit" name="action" value="signIn">
                Sign In
            </button>
        </form>
        
        <form action="SignUpServlet" method="POST">
            <p>Not have account yet, 
                <button type="submit" name="action" value="register" >
                    Register
                </button>
            </p>
        </form>
    </body>
</html>
