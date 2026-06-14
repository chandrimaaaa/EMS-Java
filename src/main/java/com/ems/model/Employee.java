package com.ems.model;

public class Employee {
    private String empId;
    private String username;
    private String password;
    private String name;
    private String department;
    private double salary;
    private String email;
    private String role;

    public Employee() {}

    public Employee(String empId, String username, String password, String name, String department, double salary, String email, String role) {
        this.empId = empId;
        this.username = username;
        this.password = password;
        this.name = name;
        this.department = department;
        this.salary = salary;
        this.email = email;
        this.role = role;
    }

    public String getEmpId() { return empId; }
    public void setEmpId(String empId) { this.empId = empId; }

    public String getUsername() { return username; }
    public void setUsername(String username) { this.username = username; }

    public String getPassword() { return password; }
    public void setPassword(String password) { this.password = password; }

    public String getName() { return name; }
    public void setName(String name) { this.name = name; }

    public String getDepartment() { return department; }
    public void setDepartment(String department) { this.department = department; }

    public double getSalary() { return salary; }
    public void setSalary(double salary) { this.salary = salary; }

    public String getEmail() { return email; }
    public void setEmail(String email) { this.email = email; }

    public String getRole() { return role; }
    public void setRole(String role) { this.role = role; }
}