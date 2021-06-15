<%-- 
    Document   : DbView
    Created on : 02-Dec-2020, 12:55:37
    Author     : ESD20
--%>
<%@ page import = "java.io.*,java.util.*,java.sql.*"%>
<%@ page import = "javax.servlet.http.*,javax.servlet.*" %>
<%@ taglib uri = "http://java.sun.com/jsp/jstl/core" prefix = "c"%>
<%@ taglib uri = "http://java.sun.com/jsp/jstl/sql" prefix = "sql"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>

    <sql:setDataSource 
        var = "snapshot" 
        driver = "org.apache.derby.jdbc.ClientDriver"
        url = "jdbc:derby://localhost:1527/ESDweb"
        user = "root" password = "123456"/>
    <sql:query dataSource = "${snapshot}" var = "resultUser">
        SELECT * from USERS
    </sql:query>
    <sql:query dataSource = "${snapshot}" var = "resultStaff">
        SELECT * from STAFFS
    </sql:query>
    <sql:query dataSource = "${snapshot}" var = "resultPatient">
        SELECT * from PATIENTS
    </sql:query>
    <sql:query dataSource = "${snapshot}" var = "resultAppointment">
        SELECT * from APPOINTMENTS
    </sql:query>

    <form action="ActionServlet" method="post">    
        <!--TABLE USER-->
        <table class="table table-sm table-hover table-bordered text-center">
            <thead class="table-dark "id="tableUser" data-bs-toggle="collapse" data-bs-target="#collapse1" aria-expanded="true" aria-controls="collapse1">
                <tr>
                    <th>Username</th>
                    <th>Password</th>
                    <th>Role</th>
                    <th>Action</th>
                </tr>
            </thead>   

            <tbody id="collapse1" class="collapse" aria-labelledby="tableUser" >
                <c:forEach var = "row" items = "${resultUser.rows}">
                    <tr>
                       <td><c:out value = "${row.USERNAME}"/></td>
                        <td><c:out value = "${row.PASSWORD}"/></td>
                        <td><c:out value = "${row.ROLE}"/></td>
                        <td><button type="submit" 
                                    name="deleteUser" 
                                    value="${row.USERNAME}"
                                    class="btn btn-sm btn-danger"
                                    onclick="return confirm('WARNING!!! Permanently Delete this [User : ${row.USERNAME}] ?')"
                                    >
                                <i data-feather="x"></i><script>feather.replace();</script>
                            </button>
                                   
                            <button type="submit" 
                                    name="updateUser" 
                                    value="${row.USERNAME}"
                                    class="btn btn-sm btn-success"
                                    > 
                                <i data-feather="edit-3"></i><script>feather.replace();</script>
                            </button>
                    </tr>
                </c:forEach> 
            </tbody>
        </table>

        <!--TABLE STAFF-->
        <table class="table table-sm table-hover table-bordered text-center">
            <thead class="table-dark "id="tableStaff" data-bs-toggle="collapse" data-bs-target="#collapse2" aria-expanded="true" aria-controls="collapse2">
                <tr>
                    <th>Staff ID</th>
                    <th>Staff Name</th>
                    <th>Staff Address</th>
                    <th>Uname</th>
                    <th>Action</th>
                </tr>
            </thead>

            <tbody id="collapse2" class="collapse" aria-labelledby="tableStaff" >
                <c:forEach var = "row" items = "${resultStaff.rows}">
                    <tr>
                        <td><c:out value = "${row.STAFFID}"/></td>
                        <td><c:out value = "${row.STAFFNAME}"/></td>
                        <td><c:out value = "${row.STAFFADDRESS}"/></td>
                        <td><c:out value = "${row.UNAME}"/></td>
                        <td><button type="submit" 
                                    name="deleteStaff" 
                                    value="${row.STAFFID}" 
                                    class="btn btn-sm btn-danger"
                                    onclick="return confirm('WARNING!!! Delete this [staff : ${row.STAFFNAME}] ?')"
                                    >
                                <i data-feather="x"></i><script>feather.replace();</script>
                            </button>
                            <button type="submit" 
                                    name="updateStaff" 
                                    value="${row.STAFFID}"
                                    class="btn btn-sm btn-success"
                                    >
                                <i data-feather="edit-3"></i><script>feather.replace();</script>
                            </button>
                    </tr>
                </c:forEach>
            </tbody>  
        </table>

        <!--TABLE PATIENT-->
        <table class="table table-sm table-hover table-bordered text-center">
            <thead class="table-dark "id="tablePatient" data-bs-toggle="collapse" data-bs-target="#collapse3" aria-expanded="true" aria-controls="collapse3">
                <tr>
                    <th>Patient ID</th>
                    <th>Patient Name</th>
                    <th>Patient Address</th>
                    <th>Patient Type</th>
                    <th>Uname</th>
                    <th>Action</th>
                </tr>  
            </thead>

            <tbody id="collapse3" class="collapse" aria-labelledby="tablePatient" >
                <c:forEach var = "row" items = "${resultPatient.rows}">
                    <tr>
                        <td><c:out value = "${row.PATIENTID}"/></td>
                        <td><c:out value = "${row.PATIENTNAME}"/></td>
                        <td><c:out value = "${row.PATIENTADDRESS}"/></td>
                        <td><c:out value = "${row.PATIENTTYPE}"/></td>
                        <td><c:out value = "${row.UNAME}"/></td>
                        <td><button type="submit" 
                                    name="deletePatient" 
                                    value="${row.PATIENTID}"
                                    class="btn btn-sm btn-danger"
                                    onclick="return confirm('WARNING!!! Delete this [staff : ${row.PATIENTNAME}] ?')"
                                    >
                                <i data-feather="x"></i><script>feather.replace();</script>
                            </button>
                            <button type="submit" 
                                    name="updatePatient" 
                                    value="${row.PATIENTID}"
                                    class="btn btn-sm btn-success"
                                    >
                                <i data-feather="edit-3"></i><script>feather.replace();</script>
                            </button>
                    </tr>
                </c:forEach>
            </tbody>  
        </table>

        <!--TABLE APPOINTMENT-->
        <table class="table table-sm table-hover table-bordered text-center">
            <thead class="table-dark "id="tableAppointment" data-bs-toggle="collapse" data-bs-target="#collapse4" aria-expanded="true" aria-controls="collapse4">
                <tr>
                    <th>A_ID</th>
                    <th>S_ID</th>
                    <th>P_ID</th>
                    <th>DATE</th>
                    <th>TIME</th>
                    <th>SLOT</th>
                    <th>CHARGE</th>
                    <th>Action</th>
                </tr>                  
            </thead>

            <tbody id="collapse4" class="collapse" aria-labelledby="tableAppointment" >
                <c:forEach var = "row" items = "${resultAppointment.rows}">
                    <tr>
                        <td><c:out value = "${row.AID}"/></td>
                        <td><c:out value = "${row.SID}"/></td>
                        <td><c:out value = "${row.PID}"/></td>
                        <td><c:out value = "${row.ADATE}"/></td>
                        <td><c:out value = "${row.ATIME}"/></td>
                        <td><c:out value = "${row.ASLOT}"/></td>
                        <td><c:out value = "${row.ACHARGE}"/></td>
                        <td><button type="submit" 
                                    name="" 
                                    value=""
                                    class="btn btn-sm btn-danger"
                                    onclick="return confirm('WARNING!!! Delete this [Appointment : ${row}] ?')"
                                    >
                                <i data-feather="x"></i><script>feather.replace();</script>
                            </button>
                            <button type="submit" 
                                    name="updatePatient" 
                                    value="${row}"
                                    class="btn btn-sm btn-success"
                                    >
                                <i data-feather="edit-3"></i><script>feather.replace();</script>
                            </button>
                    </tr>
                </c:forEach>
            </tbody>
        </table>

    </form>

</html>
