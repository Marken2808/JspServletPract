<%-- 
    Document   : DoctorReferToSpecalist
    Created on : 19-Jan-2021, 22:49:51
    Author     : Eli
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<jsp:include page="/DoctorSelectSpecalistController" ></jsp:include>
<% 
    String sucssesHTML = (String) request.getAttribute("sucssesHTML");
    String downloadButton ;
    
    if(sucssesHTML == null){
        sucssesHTML = "";
        downloadButton = "";
    }
    else{
    
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
            <form method="post" action="DoctorActionSpecalistController" >
            <%=request.getAttribute("refillsHTML")%>

        </form>
            
            <%=sucssesHTML%>
        <form action="DoctorActionSpecalistController" method="post" >
  
            <%=downloadButton%>
        </form>

    </body>
</html>
