<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <title>EMS - System Gateway Sign In</title>
    <link rel="stylesheet" href="css/style.css">
    <style>
        body {
            justify-content: center;
            align-items: center;
            background-color: var(--md-background);
        }
        .login-card {
            width: 100%;
            max-width: 420px;
            padding: 32px;
        }
        .brand-header {
            text-align: center;
            margin-bottom: 32px;
        }
        .brand-header h2 {
            color: var(--md-primary);
            font-weight: 500;
            font-size: 28px;
            margin-bottom: 8px;
        }
        .brand-header p {
            color: var(--md-on-surface-variant);
            font-size: 14px;
        }
    </style>
</head>
<body>

    <div class="md-card login-card">
        <div class="brand-header">
            <h2>EMS Portal</h2>
            <p>Sign in to manage records or view profiles</p>
        </div>

        <%-- Dynamic Error Banner Presentation --%>
        <% if (request.getAttribute("errorMessage") != null) { %>
            <div class="md-alert md-alert-error">
                <%= request.getAttribute("errorMessage") %>
            </div>
        <% } %>

        <form action="LoginServlet" method="POST">
            <div class="md-form-group">
                <label class="md-label">System Access Identity Role</label>
                <select name="role" class="md-select" required>
                    <option value="EMPLOYEE">Regular Employee</option>
                    <option value="ADMIN">System Administrator</option>
                </select>
            </div>

            <div class="md-form-group">
                <label class="md-label">Username Account Tag</label>
                <input type="text" name="username" class="md-input" placeholder="e.g., john_doe" required autocomplete="off">
            </div>

            <div class="md-form-group">
                <label class="md-label">Secure Access Password</label>
                <input type="password" name="password" class="md-input" placeholder="••••••••" required>
            </div>

            <button type="submit" class="md-btn md-btn-primary" style="width: 100%; height: 48px; margin-top: 12px;">
                Sign In
            </button>
        </form>
    </div>

</body>
</html>