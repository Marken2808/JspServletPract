<%-- 
    Document   : AdminApproveStaffView
    Created on : 17-Dec-2020, 00:56:43
    Author     : Eli
--%>


<%@page import="java.util.ArrayList"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<jsp:include page="/AdminSelectAproveStaffController" ></jsp:include>
<%

    ArrayList<ArrayList> unapprovedStaff = (ArrayList) request.getAttribute("unapprovedStaff");

%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <%@ include file="AdminNavigator.jsp" %>
        <title>Applications Page</title>
    </head>
    <body>
        
        <form action="AdminActionAproveStaffController" method="post" >
            <%            for (ArrayList i : unapprovedStaff) {

                    ArrayList<String> list = (ArrayList) i;
                    for (String j : list) {
                        out.println(j);

                    }
                    out.println("<b>Approve</b> :<input type='submit' name='action' value='");
                    out.println(list.get(0));
                    out.println("'");
                    out.println("<br><br>");
                }

            %>
        </form>
    </body>

</html>
