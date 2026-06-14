<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <title>EMS - Provision New Personnel Profile</title>
    <link rel="stylesheet" href="css/style.css">
    <style>
        .form-card {
            max-width: 600px;
            margin: 40px auto;
        }
    </style>
</head>
<body>

    <header class="md-appbar">
        <h1>EMS Profile Provisioning</h1>
        <a href="AdminDashboardServlet" class="md-logout-btn">Return To Dashboard</a>
    </header>

    <div class="md-container">
        <div class="md-card form-card">
            <h2 class="md-card-title">Add New Employee</h2>
            
            <form action="AddEmployeeServlet" method="POST">
                <div class="md-form-group">
                    <label class="md-label">Full Name</label>
                    <input type="text" name="name" class="md-input" placeholder="e.g., Alexander Pierce" required>
                </div>

                <div class="md-form-group">
                    <label class="md-label">System Login Username</label>
                    <input type="text" name="username" class="md-input" placeholder="e.g., apierce" required>
                </div>

                <div class="md-form-group">
                    <label class="md-label">Assigned Operating Department</label>
                    <select name="department" class="md-select" required>
                        <option value="Human Resources">Human Resources</option>
                        <option value="Engineering Services">Engineering Services</option>
                        <option value="Financial Management">Financial Management</option>
                        <option value="Corporate Marketing">Corporate Marketing</option>
                        <option value="Information Security">Information Security</option>
                    </select>
                </div>

                <div class="md-form-group">
                    <label class="md-label">Annual Base Salary (INR)</label>
                    <input type="number" name="salary" class="md-input" placeholder="e.g., 75000.00" required>
                </div>

                <div class="md-form-group">
                    <label class="md-label">Email Address</label>
                    <input type="email" name="email" class="md-input" placeholder="e.g., alex.pierce@company.com" required>
                </div>

                <blockquote style="background: #f0f4f8; border-left: 4px solid var(--md-primary); padding: 12px; font-size: 13px; margin-bottom: 24px; color: var(--md-on-surface-variant);">
                    <strong>System Note:</strong> The platform will automatically calculate a sequential Employee ID and generate an alphanumeric password. These credentials will be securely emailed to the provided address instantly upon submission.
                </blockquote>

                <div style="display: flex; justify-content: flex-end; gap: 12px;">
                    <a href="AdminDashboardServlet" class="md-btn md-btn-text">Cancel</a>
                    <button type="submit" class="md-btn md-btn-primary">Provision Account</button>
                </div>
            </form>
        </div>
    </div>

</body>
</html>