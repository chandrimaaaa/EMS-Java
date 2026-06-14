package com.ems.controller;

import java.io.IOException;
import java.net.URLEncoder;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.ems.util.DBConnection;
import com.ems.util.EmailSender;
import com.ems.util.SecurityUtil;

@WebServlet("/AddEmployeeServlet")
public class AddEmployeeServlet extends HttpServlet {
    private static final long serialVersionUID = 1L;

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String name = request.getParameter("name");
        String username = request.getParameter("username");
        String department = request.getParameter("department");
        double salary = Double.parseDouble(request.getParameter("salary"));
        String email = request.getParameter("email");

        String autoId = SecurityUtil.generateNextEmployeeId();
        String autoPassword = SecurityUtil.generateRandomPassword();

        String sql = "INSERT INTO employees (emp_id, username, password, name, department, salary, email, role) VALUES (?, ?, ?, ?, ?, ?, ?, 'EMPLOYEE')";

        try (Connection conn = DBConnection.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql)) {

            ps.setString(1, autoId);
            ps.setString(2, username);
            ps.setString(3, autoPassword);
            ps.setString(4, name);
            ps.setString(5, department);
            ps.setDouble(6, salary);
            ps.setString(7, email);

            int result = ps.executeUpdate();

            if (result > 0) {
                EmailSender.sendCredentialsEmail(email, name, autoId, autoPassword);

                String flashMsg = URLEncoder.encode("Employee " + name + " provisioned successfully under ID: " + autoId, "UTF-8");
                response.sendRedirect("AdminDashboardServlet?success=" + flashMsg);
            } else {
                request.setAttribute("error", "Database validation failure encountered while appending record parameters.");
                request.getRequestDispatcher("AdminDashboardServlet").forward(request, response);
            }

        } catch (SQLException e) {
            e.printStackTrace();
            request.setAttribute("error", "Database processing error occurred: " + e.getMessage());
            request.getRequestDispatcher("AdminDashboardServlet").forward(request, response);
        }
    }
}