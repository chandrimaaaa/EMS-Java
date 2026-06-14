# Employee Management System (EMS)

A secure, enterprise-inspired web application designed for organizations to streamline personnel directory management. Built entirely using modern **Java EE / Jakarta EE architecture**, this system features a split role-based access design (Administrators vs. Regular Employees) wrapped in a custom, responsive **Google Material Design** UI framework written in pure CSS.

---

<img width="597" height="680" alt="image" src="https://github.com/user-attachments/assets/dd6e02dd-e9e7-4e10-baa4-cca0890b49e5" />


## 🚀 Key System Features

### 🔐 1. Dual-Role Authentication

* **Secure Gateway Gateway Routing:** Universal login panel featuring an explicit role configuration selection dropdown (**Admin** vs. **Employee**).
* **Stateful Session Control:** Route guard interception prevents unauthenticated access or path-traversal attempts across endpoints.

### 📊 2. Dynamic Administrative Control Panel (CRUD)

* **Full Data Lifecycle Management:** Comprehensive ability to **Create, Read, Update, and Delete (CRUD)** personnel profiles via parameterized JDBC queries.
* **Optimized Performance Pagination:** Server-side calculation segment controls that display records in concise 5-row windows to keep load times instantaneous.
* **Interactive Header Sorting:** Clickable table column headers (**ID, Name, Department, Salary**) to dynamically toggle alphanumeric or numeric sorting criteria (`ASC` / `DESC`).
* **Isolated Views Protection:** Internal query validation automatically excludes administrative system accounts from rendering inside public directory management tables.

### ✉️ 3. Automatic Credentials Provisioning & Mail Engine

* **Sequential ID Assignment:** Generates unique tracking IDs (`E01`, `E02`, `E03`...) dynamically based on the current records inside the database.
* **Secure Random Passwords:** Programmatically creates cryptographically secure alphanumeric temporary login tokens.
* **Asynchronous JavaMail Engine:** Instantly sends welcome emails containing fresh login credentials using background worker threads so the user interface never freezes.

### 👤 4. Isolated Employee Portals

* **Read-Only Profile View:** Non-administrative accounts automatically land on a personal dashboard showing only their specific profile information (Salary, Department, Identity mapping fields).

---

## 🛠️ Environmental Architecture Stack

The platform is designed and fully verified against the following infrastructure components:

* **Language Runtime:** Java SE Development Kit (JDK) 21 or higher.
* **Integrated Development Environment:** Eclipse IDE (Enterprise Java and Web Developer Edition).
* **Application Server:** Apache Tomcat 9.0.x (Servlet API 4.0 / JSP 2.3).
* **Relational Database Engine:** MySQL Server 8.x / 9.x.
* **Application Framework:** Vanilla Java Servlets (`javax.servlet`) and JavaServer Pages (`jsp`).
* **Style Framework:** Custom pure Google Material UI Specification Sheet (**No Tailwind / No Bootstrap**).

### 📦 Mandatory Library Dependencies (`WEB-INF/lib`)

Ensure the following `.jar` binaries are added to your build path and copied into your local web output library directory:

1. `mysql-connector-j-9.x.jar` (MySQL Database JDBC Connectivity Driver)
2. `javax.mail.jar` (JavaMail API standard core implementation framework)
3. `activation-1.1.1.jar` (Java Activation Framework dependency required for mail transport streams)

---

## 📂 Project Directory Structure

```text
EmployeeManagementSystem/
├── src/main/java/                 
│   └── com/ems/
│       ├── controller/            <-- System Access Servlet Controllers
│       │   ├── LoginServlet.java
│       │   ├── AdminDashboardServlet.java
│       │   ├── AddEmployeeServlet.java
│       │   ├── UpdateEmployeeServlet.java
│       │   ├── DeleteEmployeeServlet.java
│       │   └── LogoutServlet.java
│       ├── model/                 
│       │   └── Employee.java      <-- Core Encapsulated Entity POJO
│       └── util/                  
│           ├── DBConnection.java  <-- JDBC Connection Provider Utility
│           ├── SecurityUtil.java  <-- Credentials Auto-Generation Engine
│           └── EmailSender.java   <-- Background Mail Transport Worker
│           └── ConfigUtil.java   <-- System for getting config.properties values
├── src/main/webapp/
│   ├── css/
│   │   └── style.css              <-- Design CSS
│   ├── WEB-INF/
│   │   └── lib/                   <-- Mandatory JAR Dependency Binaries
│   │   └── classes/                   <-- Config File Storage for Secure Credential
│           └── config.properties   <-- Credentials for DB, App Passwords etc.
│   ├── index.jsp                  <-- Gateway Login View
│   ├── admin-dashboard.jsp        <-- Admin Master Panel View
│   ├── add-employee.jsp           <-- Personnel Provisioning Form
│   ├── update-employee.jsp        <-- Profile Modification Form
│   └── profile.jsp                <-- Isolated Employee View Page

```

---

## ⚙️ Initial Setup & Installation Checklist

### 1. Database Component Provisioning

Log into your local MySQL instance and run the setup script below. This creates the workspace database schema, sets up your constraints, and seeds your master administrator profile:

```sql
CREATE DATABASE employee_db;
USE employee_db;

CREATE TABLE employees (
    emp_id VARCHAR(20) PRIMARY KEY,
    username VARCHAR(50) UNIQUE NOT NULL,
    password VARCHAR(100) NOT NULL,
    name VARCHAR(100) NOT NULL,
    department VARCHAR(50) NOT NULL,
    salary DECIMAL(10,2) NOT NULL,
    email VARCHAR(100) NOT NULL,
    role VARCHAR(20) NOT NULL
);

-- Seed your core Master Admin Access Account
INSERT INTO employees VALUES ('A01', 'admin', 'admin123', 'System Administrator', 'IT Management', 0.00, 'admin@company.com', 'ADMIN');

```

### 2. Configure the config.properties file 

Inside WebContent/WEB-INF/classes/ folder
```

Example file :
EMAIL_USER=your-email@gmail.com
EMAIL_PASS=your-app-password

DB_URL=jdbc:mysql://localhost:3306/yourdbname
DB_USER=root
DB_PASS=your_mysql_password
```
---

## Screenshots of the Application
1. **Login Page**
<img width="597" height="680" alt="image" src="https://github.com/user-attachments/assets/a50a0214-1a76-4765-8bd2-f9529239058b" />
---


2. **Admin Dashboard Page**
<img width="1246" height="712" alt="image" src="https://github.com/user-attachments/assets/9425573d-b1cc-4a1d-adf4-e8bcdf97d524" />
---


3. **Add Employee Page**
<img width="1251" height="947" alt="image" src="https://github.com/user-attachments/assets/3406c4be-5fa3-4d9b-bdce-4411cf67d5f8" />
---


4. **Updated Admin Dashboard Page**
<img width="1303" height="683" alt="image" src="https://github.com/user-attachments/assets/bf51a71e-b718-4060-ac0e-4eec3ee3e6b4" />
---


5. **Credential Email**
<img width="1107" height="705" alt="image" src="https://github.com/user-attachments/assets/a1a25f57-0bef-4984-b8a9-78dbc3cf0096" />
---


6. **Employee Dashboard**
<img width="1308" height="680" alt="image" src="https://github.com/user-attachments/assets/438e0387-38b2-433c-975b-78d03b117a76" />


## 🏗️ Compilation & Deployment Steps

1. **Rebuild Workspace:** Within Eclipse, click **Project** -> **Clean...** -> Select your workspace target and re-compile your java source structures into deployment `.class` binaries.
2. **Compile Export WAR:** Right-click the root project folder `EmployeeManagementSystem` -> **Export** -> **WAR File**. Name the compiled artifact `EmployeeManagementSystem.war`.
3. **Deploy on Tomcat Container:** Copy the exported `.war` file and drop it into your local Tomcat web deployment path directory (`apache-tomcat-9.0.x/webapps/`).
4. **Boot Engine:** Run the server execution script binary (`startup.bat` or `startup.sh`). Tomcat will extract and mount the application routes instantly.
5. **Access the Application:** Launch your web browser and navigate to:
```text
http://localhost:8080/EmployeeManagementSystem/index.jsp

```
