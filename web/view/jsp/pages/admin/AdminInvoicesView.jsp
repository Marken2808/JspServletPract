<%-- 
    Document   : AdminInvoicesView
    Created on : 21-Jan-2021, 07:52:00
    Author     : Marken Tuan Nguyen
--%>

<%@page import="java.util.ArrayList"%>
<%@page import="java.sql.Date"%>
<%@page import="database.DBbean"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>JSP Page</title>
    </head>
    <body
        <h2>Appointment, Money Generated, Date, Document</h2></br>
        
        <%
//            DBbean.insertInvoice("1", "2", Date.valueOf("2021-01-21"), "4");
            ArrayList<ArrayList> test = DBbean.selectByQuery("Select * from invoices");
            
            StringBuilder sb = new StringBuilder();
            for(ArrayList i : test){
                System.out.println("i="+i);
                for(int j=0; j<i.size(); j++){
                    System.out.println("j="+i.get(j));
                    sb.append(i.get(j));
        %>
                    <label><%=i.get(j)%></label>
  
        <%      } %>
        </br>
        <%
            }
        %>
    </body>
</html>
