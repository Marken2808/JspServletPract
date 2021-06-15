<%-- 
    Document   : AdminPrescriptionView
    Created on : 16-Dec-2020, 16:51:53
    Author     : Eli
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<jsp:include page="/AdminSelectPrescriptionController" ></jsp:include>
    <!DOCTYPE html>
    <html>
        <head>
            <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
            <%@ include file="AdminNavigator.jsp" %>
            <title>JSP Page</title>
        </head>
        <body>

            <h1><%=request.getAttribute("listOfPrescriptions")%></h1>
    </body>
</html>
