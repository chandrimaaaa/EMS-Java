<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ page import="com.ems.model.Employee" %>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <title>EMS - Update Record Dossier</title>
    <link rel="stylesheet" href="css/style.css">
</head>
<body>

    <header class="md-appbar">
        <h1>EMS Modification Center</h1>
        <a href="AdminDashboardServlet" class="md-logout-btn">Back to Control</a>
    </header>

    <div class="md-container">
        <div class="md-card" style="max-width: 600px; margin: 40px auto;">
            <h2 class="md-card-title">Modify Employee Records</h2>
            
            <% Employee emp = (Employee) request.getAttribute("employee"); %>
            
            <form action="UpdateEmployeeServlet" method="POST">
                <%-- Hidden tracking anchor linking modifications directly back onto targeted primary key rows --%>
                <input type="hidden" name="empId" value="<%= emp.getEmpId() %>">

                <div class="md-form-group">
                    <label class="md-label">Employee ID (Read Only)</label>
                    <input type="text" class="md-input" value="<%= emp.getEmpId() %>" disabled style="background-color: #eee;">
                </div>

                <div class="md-form-group">
                    <label class="md-label">Full Name Entry</label>
                    <input type="text" name="name" class="md-input" value="<%= emp.getName() %>" required>
                </div>

                <div class="md-form-group">
                    <label class="md-label">Operating Department Sector Alignment</label>
                    <select name="department" class="md-select" required>
                        <option value="Human Resources" <%= "Human Resources".equals(emp.getDepartment()) ? "selected" : "" %>>Human Resources</option>
                        <option value="Engineering Services" <%= "Engineering Services".equals(emp.getDepartment()) ? "selected" : "" %>>Engineering Services</option>
                        <option value="Financial Management" <%= "Financial Management".equals(emp.getDepartment()) ? "selected" : "" %>>Financial Management</option>
                        <option value="Corporate Marketing" <%= "Corporate Marketing".equals(emp.getDepartment()) ? "selected" : "" %>>Corporate Marketing</option>
                        <option value="Information Security" <%= "Information Security".equals(emp.getDepartment()) ? "selected" : "" %>>Information Security</option>
                    </select>
                </div>

                <div class="md-form-group">
                    <label class="md-label">Annual Salary</label>
                    <input type="number" name="salary" step="0.01" class="md-input" value="<%= emp.getSalary() %>" required>
                </div>

                <div class="md-form-group">
                    <label class="md-label">Communications Email Endpoint</label>
                    <input type="email" name="email" class="md-input" value="<%= emp.getEmail() %>" required>
                </div>

                <div style="display: flex; justify-content: flex-end; gap: 12px;">
                    <a href="AdminDashboardServlet" class="md-btn md-btn-text">Cancel Changes</a>
                    <button type="submit" class="md-btn md-btn-primary">Apply Changes</button>
                </div>
            </form>
        </div>
    </div>

</body>
</html>