<%-- 
    Document   : NavigationBar
    Created on : 13-Aug-2021, 19:05:27
    Author     : Marken Tuan Nguyen
--%>

<%@page import="model.User"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<% 
    User user = (User) session.getAttribute("user");
%>


<!DOCTYPE html>
<div>
    <h1>Navigation Bar, Hello <%= user.getuUsername()%> [<%= user.getuRole()%>] <%@ include file="Footer.jsp" %> </h1>
</div>
