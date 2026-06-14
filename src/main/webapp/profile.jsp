<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ page import="com.ems.model.Employee" %>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <title>EMS - My Corporate Profile</title>
    <link rel="stylesheet" href="css/style.css">
    <style>
        .profile-layout {
            max-width: 700px;
            margin: 40px auto;
        }
        .profile-grid {
            display: grid;
            grid-template-columns: 1fr 1fr;
            gap: 24px;
            margin-top: 16px;
        }
        .data-segment h4 {
            font-size: 12px;
            text-transform: uppercase;
            color: var(--md-primary);
            margin-bottom: 4px;
            letter-spacing: 0.5px;
        }
        .data-segment p {
            font-size: 16px;
            color: var(--md-on-surface);
            font-weight: 400;
        }
    </style>
</head>
<body>

    <% Employee profile = (Employee) request.getAttribute("profile"); %>

    <header class="md-appbar">
        <h1>Employee Information System</h1>
        <div>
            <span style="margin-right: 20px; font-size: 14px; opacity: 0.9;">Welcome, <%= profile.getName() %></span>
            <a href="LogoutServlet" class="md-logout-btn">Logout</a>
        </div>
    </header>

    <div class="md-container">
        <div class="md-card profile-layout">
            <h2 class="md-card-title" style="border-bottom: 1px solid rgba(0,0,0,0.08); padding-bottom: 16px;">
                Employee Information
            </h2>

            <div class="profile-grid">
                <div class="data-segment">
                    <h4>Assigned Employee ID</h4>
                    <p style="font-weight: bold; color: var(--md-primary);"><%= profile.getEmpId() %></p>
                </div>
                
                <div class="data-segment">
                    <h4>Portal Login Username</h4>
                    <p><%= profile.getUsername() %></p>
                </div>

                <div class="data-segment">
                    <h4>Legal Full Name</h4>
                    <p><%= profile.getName() %></p>
                </div>

                <div class="data-segment">
                    <h4>Corporate Department Section</h4>
                    <p><%= profile.getDepartment() %></p>
                </div>

                <div class="data-segment">
                    <h4>Base Compensation Model (Annual)</h4>
                    <p>₹<%= String.format("%.2f", profile.getSalary()) %></p>
                </div>

                <div class="data-segment">
                    <h4>Registered Email</h4>
                    <p><%= profile.getEmail() %></p>
                </div>
            </div>
            
            <hr style="margin: 32px 0 16px; border: 0; border-top: 1px solid rgba(0,0,0,0.08);">
            <p style="font-size: 12px; color: var(--md-on-surface-variant); font-style: italic; text-align: center;">
                For any correction or profile update requirements, please contact admin.
            </p>
        </div>
    </div>

</body>
</html>