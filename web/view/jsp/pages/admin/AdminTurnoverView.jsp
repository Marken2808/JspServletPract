<%-- 
    Document   : AdminTurnoverView
    Created on : 21-Jan-2021, 08:29:36
    Author     : Eli
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<jsp:include page="/AdminSelectTurnoverController" ></jsp:include>
    <!DOCTYPE html>
    <html>
        <head>
            <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <%@ include file="AdminNavigator.jsp" %>
        <title>JSP Page</title>
    </head>
    <body>
        <form action="AdminActionTurnoverController" method = "post">
            <%=request.getAttribute("turnoverHTML")%>
        </form>

    </body>
</html>
