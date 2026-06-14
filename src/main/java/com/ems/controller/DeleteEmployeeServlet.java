package com.ems.controller;

import java.io.IOException;
import java.net.URLEncoder;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.ems.util.DBConnection;

@WebServlet("/DeleteEmployeeServlet")
public class DeleteEmployeeServlet extends HttpServlet {
    private static final long serialVersionUID = 1L;

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws java.io.IOException {
        String empId = request.getParameter("id");

        String sql = "DELETE FROM employees WHERE emp_id = ? AND role = 'EMPLOYEE'";

        try (Connection conn = DBConnection.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql)) {
            
            ps.setString(1, empId);
            ps.executeUpdate();
            
            String flashMsg = URLEncoder.encode("Personnel tracking profile record matching target ID: " + empId + " deleted successfully.", "UTF-8");
            response.sendRedirect("AdminDashboardServlet?success=" + flashMsg);

        } catch (SQLException e) {
            e.printStackTrace();
            response.sendRedirect("AdminDashboardServlet");
        }
    }
}