<%-- 
    Document   : AdminAppointmentView
    Created on : 17-Dec-2020, 01:34:40
    Author     : Eli
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<jsp:include page="/AdminSelectAppointmentController" ></jsp:include>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <%@ include file="AdminNavigator.jsp" %>
        <title>JSP Page</title>
    </head>
    <body>
       <h1><%=request.getAttribute("listOfAppointments") %></h1>
    </body>
</html>
