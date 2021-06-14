<%-- 
    Document   : StaffSetPrescriptionView
    Created on : 18-Dec-2020, 19:23:10
    Author     : Eli
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<jsp:include page="/StaffSelectPrescriptionController" ></jsp:include>
<% String sucsessHTML = (String) request.getAttribute("sucsessHTML"); 
   if (sucsessHTML == null){
       sucsessHTML= "";
   }
%>
    <!DOCTYPE html>
    <html>
        <head>
            <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
            <%@ include file="StaffNavigator.jsp" %>
            <title>JSP Page</title>
        </head>
        <body>
            <form method="post" action ="StaffActionPrescriptionController"  >
            <%=request.getAttribute("patientHTML")%>
            <%=request.getAttribute("medicationHTML")%>
            <%=request.getAttribute("refillsHTML")%>
            <input type="submit">
            <%=sucsessHTML%>
        </form>
    </body>
</html>
