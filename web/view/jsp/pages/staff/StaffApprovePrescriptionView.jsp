<%-- 
    Document   : StaffApprovePrescriptionView
    Created on : 17-Dec-2020, 06:04:37
    Author     : Eli
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<jsp:include page="/StaffSelectRefillController" ></jsp:include>
<% String sucsessHTML = (String) request.getAttribute("sucsessHTML"); 
   if (sucsessHTML == null){
       sucsessHTML= "";
   }
%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <%@ include file="StaffNavigator.jsp" %>
        <title>JSP Page</title>
    </head>
    <body>
        <form  method = 'post' action = 'StaffActionRefillController' >
            <%=request.getAttribute("prescriptionHTML")%>
            
        </form>
        
        <p><%=sucsessHTML%></p>
    </body>
</html>
