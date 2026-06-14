package com.ems.controller;

import java.io.IOException;
import java.net.URLEncoder;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.ems.model.Employee;
import com.ems.util.DBConnection;

@WebServlet("/UpdateEmployeeServlet")
public class UpdateEmployeeServlet extends HttpServlet {
    private static final long serialVersionUID = 1L;
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String empId = request.getParameter("id");
        Employee emp = null;

        String sql = "SELECT * FROM employees WHERE emp_id = ? AND role = 'EMPLOYEE'";

        try (Connection conn = DBConnection.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql)) {
            
            ps.setString(1, empId);
            try (ResultSet rs = ps.executeQuery()) {
                if (rs.next()) {
                    emp = new Employee(
                        rs.getString("emp_id"),
                        rs.getString("username"),
                        rs.getString("password"),
                        rs.getString("name"),
                        rs.getString("department"),
                        rs.getDouble("salary"),
                        rs.getString("email"),
                        rs.getString("role")
                    );
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        if (emp != null) {
            request.setAttribute("employee", emp);
            request.getRequestDispatcher("update-employee.jsp").forward(request, response);
        } else {
            response.sendRedirect("AdminDashboardServlet");
        }
    }

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String empId = request.getParameter("empId");
        String name = request.getParameter("name");
        String department = request.getParameter("department");
        double salary = Double.parseDouble(request.getParameter("salary"));
        String email = request.getParameter("email");

        String sql = "UPDATE employees SET name = ?, department = ?, salary = ?, email = ? WHERE emp_id = ?";

        try (Connection conn = DBConnection.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql)) {

            ps.setString(1, name);
            ps.setString(2, department);
            ps.setDouble(3, salary);
            ps.setString(4, email);
            ps.setString(5, empId);

            ps.executeUpdate();
            
            String flashMsg = URLEncoder.encode("Employee file for entry " + empId + " updated successfully.", "UTF-8");
            response.sendRedirect("AdminDashboardServlet?success=" + flashMsg);

        } catch (SQLException e) {
            e.printStackTrace();
            response.sendRedirect("AdminDashboardServlet");
        }
    }
}