<%-- 
    Document   : Date
    Created on : 15-Dec-2020, 20:14:31
    Author     : ESD20
--%>

<%@page import="database.DBbean"%>
<%@page import="model.Appointment"%>
<%@page import="java.util.ArrayList"%>
<%@ include file="/view/jsp/components/Header.jsp" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>

<jsp:include page="/PatientSelectAppointmentController" ></jsp:include>
<!DOCTYPE html>

<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <%@ include file="PatientNavigator.jsp" %>
        <title>JSP Page</title>
    </head>
    <body>
        
        <%
            ArrayList<Appointment> patientAptList = (ArrayList<Appointment>) session.getAttribute("patientAptList");     
        %>      
        
        <form action="PatientActionAppointmentController" method="post"> 
            <table class="table table-sm table-hover table-bordered text-center">
                <thead class="table-dark "id="tableUser" data-bs-toggle="collapse" data-bs-target="#collapse1" aria-expanded="true" aria-controls="collapse1">
                    <tr>
                        <th>AppointmentID</th>
                        <th>Staff Name</th>
                        <th>Patient Name</th>
                        <th>Date</th>
                        <th>Time</th>
                        <th>Length</th>
                        <th>Action</th>
                    </tr>
                </thead>   

                <tbody id="collapse1" class="collapse" aria-labelledby="tableUser" >
         
                    <% for(Appointment apt: patientAptList){ %>
                        <tr>      
                            <td><%=apt.getAID()%></td>
                            <td><%=DBbean.selectNameById("staff", apt.getStaffID())%></td>
                            <td><%=patient.getPatientName()%></td>
                            <td><%=apt.getADate()%></td>
                            <td><%=apt.getATime()%></td>
                            <td><%=apt.getALength()%></td>
                            <td><button 
                                    type="submit"
                                    name="delete"
                                    value="<%=apt.getAID()%>"
                                    class="btn btn-sm btn-danger"
                                    >
                                <i data-feather="x"></i><script>feather.replace();</script>
                                </button>
                            </td>
                        </tr>
                    <%  }
                        
                        String id = (request.getParameter("delete"));
                        if(id !=null && !id.isEmpty()){
                            int checkid=Integer.parseInt(id.trim());
//                                    System.out.println("int: "+checkid);
                            DBbean.deleteAppointment(checkid);
                        }
                    %>

                </tbody>
            </table>
        </form>
                    
    </body>
</html>
