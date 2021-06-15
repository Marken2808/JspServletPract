<%-- 
    Document   : PatientDashboard
    Created on : 15-Jan-2021, 16:59:56
    Author     : ESD20
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@ include file="/view/jsp/components/Header.jsp" %>


<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <%@ include file="PatientNavigator.jsp" %>
        <title>Patient Page</title>
    </head>
    
    <body>

<!--        <form method="post" action="PatientViewController">
            <input type="submit" name="action" value="My Appointment">
            <input type="submit" name="action" value="Request Refill">
        </form>-->

        <%@include file="/view/jsp/pages/patient/PatientBooking.jsp" %>
        
    </body>

</html>
