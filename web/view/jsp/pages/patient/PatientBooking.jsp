<%-- 
    Document   : Date
    Created on : 15-Dec-2020, 20:14:31
    Author     : Marken Tuan Nguyen
--%>

<%@page import="model.Staff"%>
<%@page import="java.util.ArrayList"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<jsp:include page="/PatientSelectBookingController"/>
<!DOCTYPE html>
<html>
    <div class="position-absolute top-50 start-50 translate-middle">

          <% 
                ArrayList<Staff> showStaff = (ArrayList<Staff>) session.getAttribute("showStaff");
          %>

            
        <form action="PatientActionBookingController" method="post">
            
            <div class="mb-4">
                <h4>Start New Booking</h4>
            </div>
            
            <div class="form-floating mb-2">      
                <select class="form-select mb-2" name="staffName" id="floatingInput">
                    <% for(Staff s: showStaff){ %>
                        <option><%=s.getStaffName()%></option>
                    <% } %>
                </select>
                <label class="fw-lighter" for="floatingInput">Staff</label>
            </div>
            
                    
                    
                    
            <div class="form-floating mb-2">   
                <input type="date" name="date" class="form-control is-invalid" id="floatingInput"/>
                <div class="invalid-feedback mb-2">Test!</div>
                <label class="fw-lighter" for="floatingInput">Booking Date</label>
            </div>
            
            <div class="form-floating mb-2">  
                <input type="time" name="time" class="form-control is-invalid" id="floatingInput">
                <div class="invalid-feedback mb-2">Test!</div>
                <label class="fw-lighter" for="floatingInput">Booking Time</label>
            </div>
            
            <div class="form-floating mb-2">  
                <select class="form-select mb-2" name="length" id="floatingInput">
                    <option>10</option>
                    <option>15</option>
                    <option>20</option>
                    <option>25</option>
                    <option>30</option>
                </select>
                <label class="fw-lighter" for="floatingInput">Length</label>
            </div>
            
            <button class="btn btn-primary col-12" type="submit" name="act" value="Book">Booking</button>
        </form>
    </div>
</html>
