package com.ems.util;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DBConnection {
    // Modify database parameters if your system configuration alters credentials
	private static final String URL = ConfigUtil.get("DB_URL");
	private static final String USER = ConfigUtil.get("DB_USER");
	private static final String PASS = ConfigUtil.get("DB_PASS");

    static {
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
        } catch (ClassNotFoundException e) {
            System.err.println("Failed to load MySQL JDBC Driver configuration dependencies.");
            e.printStackTrace();
        }
    }

    public static Connection getConnection() throws SQLException {
        return DriverManager.getConnection(URL, USER, PASS);
    }
}