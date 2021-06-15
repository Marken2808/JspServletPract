<%-- 
    Document   : AdminInvoiceView
    Created on : 21-Jan-2021, 12:13:40
    Author     : Eli
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<jsp:include page="/AdminSelectInvoiceController" ></jsp:include>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
          <%@ include file="AdminNavigator.jsp" %>
        <title>JSP Page</title>
    </head>
    <body>
        <<h1><%=request.getAttribute("invoiceHTML")%></h1>
    </body>
</html>
