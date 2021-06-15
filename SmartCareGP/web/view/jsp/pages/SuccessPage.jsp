<%-- 
    Document   : LoginJSP
    Created on : 22-Nov-2020, 13:59:03
    Author     : ESD20
--%>

<%@page import="model.Staff"%>
<%@page import="model.Patient"%>
<%@page import="model.User"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<%
    Staff staff = (Staff) session.getAttribute("staffData");

    Patient patient = (Patient) session.getAttribute("patientData");

    String name = "";
    String username = "";
    String end = "";

    if (staff != null) {
        name = staff.getStaffUsername();
        username = staff.getStaffUsername();
        end = " return to home to log in once approved!";
    }

    if (patient != null) {
        name = patient.getPatientName();
        username = patient.getPatientUsername();
        end =  " return to home to log in!";
    }
%>


<head>
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">


    <title>Success Page</title>
</head>
<body>
    <h1>REGISTER SUCCESSFULLY</h1>
<p1>Thank you <%=name%> ,  for creating an account with SmartCare GP.<br>
    Your username is <%=username%>, <%=end%>

</p1>

<%@ include file="../components/Footer.jsp" %>
</body>


</html>