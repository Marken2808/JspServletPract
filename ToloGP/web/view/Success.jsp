<%-- 
    Document   : Success
    Created on : 10-Aug-2021, 19:55:38
    Author     : Marken Tuan Nguyen
--%>

<%@page import="model.User"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>JSP Page</title>
    </head>
    <body>
        <h1>SUCCESSFULLY!</h1>
        <%
            String key = (String) session.getAttribute("sessionKey");
            User user = (User) request.getAttribute("user");
        %>
        
        <%=key%>
        <%=user.getuUsername()%>
        
        <footer>
            <%@ include file="Footer.jsp" %>
        </footer>
    </body>
</html>
