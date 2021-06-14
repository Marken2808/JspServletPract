<%-- 
    Document   : Dashboard
    Created on : 06-Dec-2020, 01:46:22
    Author     : ESD20
--%>

<%@page import="model.Staff"%>
<%@page import="model.Patient"%>
<%@page import="model.User"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <%@ include file="../components/Navigator.jsp" %>
        <title>Dashboard Page</title>
    </head>
    <body>
        
        <%
            User user = (User) request.getSession().getAttribute("userData");
            String role = user.getUserRole();
            
            if(role.equals("Admin")){
        %>      
                <h1> Dashboard <% out.print(role); %> </h1>
                <%--<%@ include file="../components/AdminDashboard.jsp" %>--%>
                <%@ include file="../pages/admin/AdminDashboard.jsp" %>
        <%  } 
            else if(role.equals("Patient")){
                Patient patient = (Patient) request.getSession().getAttribute("ThisPatientData");
        %>
                <h1> Welcome, <% out.print(role + " " + patient.getPatientName()); %> </h1>
                <%@ include file="../pages/patient/PatientDashboard.jsp" %>
        <%  }
            else{
                Staff staff = (Staff) request.getSession().getAttribute("ThisStaffData");
        %>      
                <h1> Welcome, <% out.print(role + " " + staff.getStaffName()); %> </h1>
                <%@ include file="../pages/staff/StaffDashboard.jsp" %>
        <%  }
        %>        
        
    </body>
    <footer>
        <%@ include file="../components/Footer.jsp" %>
    </footer>
</html>
