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

@WebServlet("/EmployeeProfileServlet")
public class EmployeeProfileServlet extends HttpServlet {
    private static final long serialVersionUID = 1L;

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        // Enforce valid user session existence
        HttpSession session = request.getSession(false);
        if (session == null || session.getAttribute("userSession") == null) {
            response.sendRedirect("index.jsp");
            return;
        }

        Employee sessionUser = (Employee) session.getAttribute("userSession");
        Employee profileData = null;

        // Query fresh data directly matching the active user session ID
        String sql = "SELECT * FROM employees WHERE emp_id = ?";

        try (Connection conn = DBConnection.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql)) {
            
            ps.setString(1, sessionUser.getEmpId());

            try (ResultSet rs = ps.executeQuery()) {
                if (rs.next()) {
                    profileData = new Employee(
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

        if (profileData != null) {
            request.setAttribute("profile", profileData);
            request.getRequestDispatcher("profile.jsp").forward(request, response);
        } else {
            response.sendRedirect("index.jsp");
        }
    }
}