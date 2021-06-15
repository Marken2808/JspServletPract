<%-- 
    Document   : RegisterPage
    Created on : 28-Nov-2020, 12:07:33
    Author     : ESD20
--%>



<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<%
 // if statements are used to check if these values exist yet, this is done in order to have blank value appear in box and correct values to appear if the adress look is pressed
 // they are echoed using the printer writer scriplet in the value box later on
    String userName = (String) request.getAttribute("us");
    if (userName == null) {
        userName = "";
    }

    String password = (String) request.getAttribute("pw");
    if (password == null) {
        password = "";
    }

    String name = (String) request.getAttribute("name");
    if (name == null) {
        name = "";
    }

    String address = (String) request.getAttribute("address");
    if (address == null) {
        address = "";
    }

    String addressPull = (String) request.getAttribute("addressHTML");
    if (addressPull == null) {
        addressPull = "";
    }

    String role = (String) request.getAttribute("role");
    String[] roles = {"Patient-Private","Patient-NHS","Nurse", "Doctor"};
    String roleHTML = "";
// if there isn't a value default is set to patient as it is the first
    if (role == null) {
        role = "Patient-Private";
    }
    
    
     String email = (String) request.getAttribute("email");
    if (email == null) {
        email = "";
    }
    
    
    
    
// so box for sign up is kept if the role is the same as the list that is the one preselected
      for (int i = 0; i < roles.length; i++) {
            roleHTML = roleHTML + "<option";
            if(role.equals(roles[i])){
                roleHTML = roleHTML + " selected";
            }   
            roleHTML = roleHTML + ">" + roles[i] + "</option>";
    }

%>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <%@ include file="../components/Header.jsp" %>
        <title>Register Page</title>
    </head>
    <body>
        <form action="SignUpServlet" method="post" class="position-absolute top-50 start-50 translate-middle">
            <div class="d-flex flex-column mb-2">
                <div class="form-floating mb-2">
                    <input type="text" name="us" class="form-control is-valid" id="floatingInput" placeholder="Username" value =<%= "'" + userName + "'"%> />
                    <label for="floatingInput">Username</label>
                </div>



                <div class="form-floating mb-2">
                    <input type="password" name="pw" class="form-control is-valid" id="floatingInput" placeholder="Password"  value =<%= "'" + password + "'"%>  />
                    <label for="floatingInput">Password</label>
                </div>
                <div class="form-floating mb-2">
                    <input type="text" name="name" class="form-control is-valid" id="floatingInput" placeholder="Name"  value =<%= "'" + name + "'"%>/>
                    <label for="floatingInput">Name</label>
                </div>



                <div class="form-floating mb-2" id="addressFramee" data-bs-target="#addressControl" name = "Address"> 
                    <input id="autocomplete" 
                           class="form-control is-valid "
                           placeholder="Enter your address"
                           name = "address"
                           type="text"
                           value =<%= "'" + address + "'"%>
                           >

                    </input>




                    <label for="floatingInput">Address</label>



                    <button class="btn btn-primary col-12" type="submit" name="act" value="FindAddress">Find Address</button>




                </div>

                <%=addressPull%>

                <div class="form-floating mb-2">
                    <input type="text" name="email" class="form-control is-valid" id="floatingInput" placeholder="Email"  value =<%= "'" + email + "'"%>/>
                    <label for="floatingInput">Email</label>
                </div>

                <div class="form-floating mb-2">     
                    <select class="form-select mb-2" name="role" id="floatingInput">
                        <%= roleHTML%>
                    </select>
                    <label class="fw-lighter" for="floatingInput">Role</label>
                </div>
                <button class="btn btn-primary col-12" type="submit" name="act" value="SignUp">Sign Up</button>
            </div>
                    
            


        </form>


    </body>
</html>