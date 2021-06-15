<%-- 
    Document   : AdminUserView
    Created on : 15-Dec-2020, 22:29:17
    Author     : Eli
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<jsp:include page="/AdminSelectUserController" ></jsp:include>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <%@ include file="AdminNavigator.jsp" %>
        <title>List Users</title>
    </head>
    <body>
        <h1><%=request.getAttribute("listOfUsers") %></h1>
    </body>
</html>
