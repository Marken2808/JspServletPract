<%-- 
    Document   : PatientPrescriptionView
    Created on : 15-Jan-2021, 17:35:21
    Author     : Eli
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<jsp:include page="/PatientSelectPrescriptionController" ></jsp:include>
    <!DOCTYPE html>
    <html>
        <head>
            <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
            <%@ include file="PatientNavigator.jsp" %>
            <title>JSP Page</title>
        </head>
        <body>
            <form action = 'PatientActionPrescriptionController' method = 'POST'>
                <p><%=request.getAttribute("patientHTML")%></p>
            </form>
            <p><%=request.getAttribute("sucssesHTML")%></p>
        </body>
</html>
