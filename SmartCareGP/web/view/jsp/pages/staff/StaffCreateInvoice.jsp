<%-- 
    Document   : StaffCreateInvoice
    Created on : 20-Jan-2021, 14:46:11
    Author     : Eli
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<jsp:include page="/StaffSelectInvoiceController" ></jsp:include>
<%
    String downLoadHTML = (String) request.getAttribute("downLoadHTML");
    String downloadButton;
    if (downLoadHTML == null) {
        downLoadHTML = "";
        downloadButton = "";
    } else {

        downloadButton = "<input type='submit'  value = 'Download'>";
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
        <form action ="StaffActionInvoiceController" method="post">
            <%=request.getAttribute("invoiceHTML")%>
        </form>
          <form action="StaffActionInvoiceController" method="post" >
              <%=downLoadHTML%>
            <%=downloadButton%>
        </form>
        
        
        
    </body>
</html>
