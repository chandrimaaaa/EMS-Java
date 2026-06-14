package com.ems.controller;

import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.ems.model.Employee;
import com.ems.util.DBConnection;

@WebServlet("/LoginServlet")
public class LoginServlet extends HttpServlet {
    private static final long serialVersionUID = 1L;

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String roleInput = request.getParameter("role");
        String usernameInput = request.getParameter("username");
        String passwordInput = request.getParameter("password");

        String sql = "SELECT * FROM employees WHERE username = ? AND password = ? AND role = ?";

        try (Connection conn = DBConnection.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql)) {

            ps.setString(1, usernameInput);
            ps.setString(2, passwordInput);
            ps.setString(3, roleInput);

            try (ResultSet rs = ps.executeQuery()) {
                if (rs.next()) {
                    // Instantiating a persistent secure session layer mapping
                    HttpSession session = request.getSession(true);
                    
                    Employee user = new Employee(
                        rs.getString("emp_id"),
                        rs.getString("username"),
                        rs.getString("password"),
                        rs.getString("name"),
                        rs.getString("department"),
                        rs.getDouble("salary"),
                        rs.getString("email"),
                        rs.getString("role")
                    );
                    
                    session.setAttribute("userSession", user);

                    // Dual tracking conditional routing redirect logic
                    if ("ADMIN".equals(user.getRole())) {
                        response.sendRedirect("AdminDashboardServlet");
                    } else {
                        response.sendRedirect("EmployeeProfileServlet");
                    }
                } else {
                    request.setAttribute("errorMessage", "Invalid account matching credentials or incorrect role specification.");
                    request.getRequestDispatcher("index.jsp").forward(request, response);
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
            request.setAttribute("errorMessage", "System database processing failure during login query.");
            request.getRequestDispatcher("index.jsp").forward(request, response);
        }
    }
}