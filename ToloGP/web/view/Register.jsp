<%-- 
    Document   : Register
    Created on : 10-Aug-2021, 20:03:57
    Author     : Marken Tuan Nguyen
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>

<%
    String[] roleList = {"Patient-Private","Patient-NHS","Nurse", "Doctor"};
    String roleTag = "";
    for (int i = 0; i < roleList.length; i++) {
        roleTag += "<option>" + roleList[i] + "</option>";
    }
%>

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
            <div class="form-floating mb-2">
                <input type="text" name="name" class="form-control is-valid" id="floatingInput" placeholder="Name" />
                <label for="floatingInput">Name</label>
            </div>
            <div class="form-floating mb-2">
                <input type="text" name="phone" class="form-control is-valid" id="floatingInput" placeholder="Phone" />
                <label for="floatingInput">Phone</label>
            </div>
            <div class="form-floating mb-2">
                <input type="text" name="address" class="form-control is-valid" id="floatingInput" placeholder="Address" />
                <label for="floatingInput">Address</label>
            </div>
            
            <div class="form-floating mb-2">     
                <select class="form-select mb-2" name="role" id="floatingInput">
                    <%=roleTag%>
                </select>
                <label class="fw-lighter" for="floatingInput">Role</label>
            </div>
            <button type="submit" name="action" value="signUp">
                Sign Up
            </button>
        </form>
    </body>
</html>
