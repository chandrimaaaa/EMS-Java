<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ page import="java.util.List" %>
<%@ page import="com.ems.model.Employee" %>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <title>EMS - Administrator Master Control Center</title>
    <link rel="stylesheet" href="css/style.css">
</head>
<body>

    <header class="md-appbar">
        <h1>EMS Control Dashboard</h1>
        <div>
            <span style="margin-right: 20px; font-size: 14px; opacity: 0.9;">System Admin Mode</span>
            <a href="LogoutServlet" class="md-logout-btn">Sign Out</a>
        </div>
    </header>

    <div class="md-container">
        
        <%-- Runtime Activity Context Processing Feedback Display Banner --%>
        <% if (request.getParameter("success") != null) { %>
            <div class="md-alert md-alert-success">
                <%= request.getParameter("success") %>
            </div>
        <% } %>
        <% if (request.getAttribute("error") != null) { %>
            <div class="md-alert md-alert-error">
                <%= request.getAttribute("error") %>
            </div>
        <% } %>

        <div style="display: flex; justify-content: space-between; align-items: center; margin-bottom: 24px;">
            <h2 style="font-weight: 400; font-size: 24px;">Active Personnel Directory</h2>
            <a href="add-employee.jsp" class="md-btn md-btn-primary">+ Add New Record</a>
        </div>

        <%
            List<Employee> list = (List<Employee>) request.getAttribute("employeeList");
            int currentPage = (Integer) request.getAttribute("currentPage");
            int noOfPages = (Integer) request.getAttribute("noOfPages");
            String currentSort = (String) request.getAttribute("currentSort");
            String currentOrder = (String) request.getAttribute("currentOrder");
            
            // Toggle orientation indicators flags mapping
            String invertedOrder = "ASC".equals(currentOrder) ? "DESC" : "ASC";
        %>

        <div class="md-table-container">
            <table class="md-table">
                <thead>
                    <tr>
                        <th><a href="AdminDashboardServlet?sort=emp_id&order=<%= invertedOrder %>&page=<%= currentPage %>">ID <%= "emp_id".equals(currentSort) ? ("ASC".equals(currentOrder) ? "▲" : "▼") : "" %></a></th>
                        <th><a href="AdminDashboardServlet?sort=name&order=<%= invertedOrder %>&page=<%= currentPage %>">Full Name <%= "name".equals(currentSort) ? ("ASC".equals(currentOrder) ? "▲" : "▼") : "" %></a></th>
                        <th><a href="AdminDashboardServlet?sort=department&order=<%= invertedOrder %>&page=<%= currentPage %>">Department <%= "department".equals(currentSort) ? ("ASC".equals(currentOrder) ? "▲" : "▼") : "" %></a></th>
                        <th><a href="AdminDashboardServlet?sort=salary&order=<%= invertedOrder %>&page=<%= currentPage %>">Salary Model <%= "salary".equals(currentSort) ? ("ASC".equals(currentOrder) ? "▲" : "▼") : "" %></a></th>
                        <th>Email Communications Address</th>
                        <th style="text-align: right;">Directory Management Actions</th>
                    </tr>
                </thead>
                <tbody>
                    <% if (list == null || list.isEmpty()) { %>
                        <tr>
                            <td colspan="6" style="text-align: center; color: var(--md-on-surface-variant); padding: 32px;">No employee tracking records located in database repository.</td>
                        </tr>
                    <% } else { 
                        for (Employee emp : list) { %>
                            <tr>
                                <td><strong style="color: var(--md-primary);"><%= emp.getEmpId() %></strong></td>
                                <td><%= emp.getName() %></td>
                                <td><%= emp.getDepartment() %></td>
                                <td>$<%= String.format("%.2f", emp.getSalary()) %></td>
                                <td><%= emp.getEmail() %></td>
                                <td style="text-align: right;">
                                    <a href="UpdateEmployeeServlet?action=edit&id=<%= emp.getEmpId() %>" class="md-btn md-btn-text" style="padding: 0 8px; min-width: auto; margin-right: 8px;">Edit</a>
                                    <a href="DeleteEmployeeServlet?id=<%= emp.getEmpId() %>" class="md-btn md-btn-text" style="color: var(--md-error); padding: 0 8px; min-width: auto;" onclick="return confirm('Confirm complete removal of record entry data permanently?');">Delete</a>
                                </td>
                            </tr>
                    <%   }
                       } %>
                </tbody>
            </table>

            <%-- Unified System Navigation Component Architecture Pagination --%>
            <div class="md-pagination">
                <span>Page <%= currentPage %> of <%= Math.max(noOfPages, 1) %></span>
                <div>
                    <a href="AdminDashboardServlet?page=<%= currentPage - 1 %>&sort=<%= currentSort %>&order=<%= currentOrder %>" class="md-page-link <%= currentPage <= 1 ? "disabled" : "" %>">Previous</a>
                    <a href="AdminDashboardServlet?page=<%= currentPage + 1 %>&sort=<%= currentSort %>&order=<%= currentOrder %>" class="md-page-link <%= currentPage >= noOfPages ? "disabled" : "" %>">Next</a>
                </div>
            </div>
        </div>
    </div>

</body>
</html>