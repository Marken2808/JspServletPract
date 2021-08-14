<%-- 
    Document   : Dashboard
    Created on : 13-Aug-2021, 18:36:19
    Author     : Marken Tuan Nguyen
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Dashboard</title>
    </head>
    <body>
        <%@ include file="NavigationBar.jsp" %>
        
        <%= user %>
        <h2><%= user.getuRole().equals("Admin") ? request.getAttribute("userList") : "" %> </h2>
        </br>
    </body>
    
</html>
