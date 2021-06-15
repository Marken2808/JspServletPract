<%-- 
    Document   : index
    Created on : 10-Dec-2020, 11:38:56
    Author     : ESD20
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<jsp:include page="/SignOutServlet" ></jsp:include>
<%
 String sessionHTML = (String) request.getAttribute("Session");
 if(sessionHTML == null){
     sessionHTML = "";
 }
%>
<!DOCTYPE html>
<html>
    <head>
        <title>Login Page</title>
        <meta charset="UTF-8">
        <meta name="viewport" content="width=device-width, initial-scale=1.0">  
        <script src="view/js/datetime.js" type="text/javascript"></script>
        <%@ include file="../components/Header.jsp" %>
    </head>
  
    <body>
       
        <div class="position-absolute top-50 start-50 translate-middle">
           <h2> Welcome to SmartCare GP </h2>
            <div class="flex-column "> 
                <form action="SignInServlet" method="POST" class="d-flex flex-column align-items-center ">
                
                    
                    <div class="form-floating mb-3">
                        <input type="text" name="us" class="form-control is-valid" id="floatingInput" placeholder="Username" >
                        <label for="floatingInput">Username</label>
                    </div>

                    <div class="form-floating mb-3">
                        <input type="password" name="pw" class="form-control is-valid" id="floatingInput" placeholder="Password" />
                        <label for="floatingInput">Password</label> 
                    </div>

                    
                    <input type="submit" name="act" value="Login" class="btn btn-primary col-12 mb-2"/>
                </form>

                <form action="SignUpServlet" method="POST" class="d-flex flex-column align-items-center">
                    <input type="submit" name="act" value="Register" class="btn btn-light col-12"/>  
                </form>
               
            </div>
            <%=sessionHTML%>
        </div>
            
         
    </body>
</html>
