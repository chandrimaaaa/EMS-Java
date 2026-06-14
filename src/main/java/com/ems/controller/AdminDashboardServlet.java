package com.ems.controller;

import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.ems.model.Employee;
import com.ems.util.DBConnection;

@WebServlet("/AdminDashboardServlet")
public class AdminDashboardServlet extends HttpServlet {
    private static final long serialVersionUID = 1L;

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        HttpSession session = request.getSession(false);
        if (session == null || session.getAttribute("userSession") == null) {
            response.sendRedirect("index.jsp");
            return;
        }
        Employee currentUser = (Employee) session.getAttribute("userSession");
        if (!"ADMIN".equals(currentUser.getRole())) {
            response.sendRedirect("index.jsp");
            return;
        }

        int page = 1;
        int recordsPerPage = 5;
        if (request.getParameter("page") != null) {
            try {
                page = Integer.parseInt(request.getParameter("page"));
            } catch (NumberFormatException e) {
                page = 1;
            }
        }

        String sortColumn = request.getParameter("sort");
        String sortOrder = request.getParameter("order");

        if (sortColumn == null || (!sortColumn.equals("name") && !sortColumn.equals("department") && !sortColumn.equals("salary") && !sortColumn.equals("emp_id"))) {
            sortColumn = "emp_id"; 
        }
        if (sortOrder == null || (!sortOrder.equals("ASC") && !sortOrder.equals("DESC"))) {
            sortOrder = "ASC";
        }

        List<Employee> employeeList = new ArrayList<>();
        int totalRecords = 0;
        int startRecordIndex = (page - 1) * recordsPerPage;

        String countQuery = "SELECT COUNT(*) FROM employees WHERE role = 'EMPLOYEE'";
        String dataQuery = "SELECT * FROM employees WHERE role = 'EMPLOYEE' ORDER BY " + sortColumn + " " + sortOrder + " LIMIT ?, ?";

        try (Connection conn = DBConnection.getConnection()) {
            
            try (PreparedStatement countPs = conn.prepareStatement(countQuery);
                 ResultSet countRs = countPs.executeQuery()) {
                if (countRs.next()) {
                    totalRecords = countRs.getInt(1);
                }
            }
            
            try (PreparedStatement dataPs = conn.prepareStatement(dataQuery)) {
                dataPs.setInt(1, startRecordIndex);
                dataPs.setInt(2, recordsPerPage);

                try (ResultSet rs = dataPs.executeQuery()) {
                    while (rs.next()) {
                        Employee emp = new Employee(
                            rs.getString("emp_id"),
                            rs.getString("username"),
                            rs.getString("password"),
                            rs.getString("name"),
                            rs.getString("department"),
                            rs.getDouble("salary"),
                            rs.getString("email"),
                            rs.getString("role")
                        );
                        employeeList.add(emp);
                    }
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        int numberOfPages = (int) Math.ceil((double) totalRecords / recordsPerPage);

        request.setAttribute("employeeList", employeeList);
        request.setAttribute("noOfPages", numberOfPages);
        request.setAttribute("currentPage", page);
        request.setAttribute("currentSort", sortColumn);
        request.setAttribute("currentOrder", sortOrder);

        request.getRequestDispatcher("admin-dashboard.jsp").forward(request, response);
    }
}