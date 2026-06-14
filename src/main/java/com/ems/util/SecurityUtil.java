package com.ems.util;

import java.security.SecureRandom;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class SecurityUtil {
    private static final String ALPHA_CAPS = "ABCDEFGHIJKLMNOPQRSTUVWXYZ";
    private static final String ALPHA_LOW = "abcdefghijklmnopqrstuvwxyz";
    private static final String NUMERIC = "0123456789";
    private static final SecureRandom random = new SecureRandom();

    public static synchronized String generateNextEmployeeId() {
        String nextId = "E01"; 
        String query = "SELECT emp_id FROM employees WHERE role = 'EMPLOYEE' ORDER BY CAST(SUBSTRING(emp_id, 2) AS UNSIGNED) DESC LIMIT 1";
        
        try (Connection conn = DBConnection.getConnection();
             PreparedStatement ps = conn.prepareStatement(query);
             ResultSet rs = ps.executeQuery()) {
            
            if (rs.next()) {
                String lastId = rs.getString("emp_id");
                int numericPart = Integer.parseInt(lastId.substring(1));
                nextId = String.format("E%02d", numericPart + 1);
            }
        } catch (SQLException | NumberFormatException e) {
            e.printStackTrace();
        }
        return nextId;
    }

   
    public static String generateRandomPassword() {
        StringBuilder result = new StringBuilder();
        result.append(ALPHA_CAPS.charAt(random.nextInt(ALPHA_CAPS.length())));
        result.append(NUMERIC.charAt(random.nextInt(NUMERIC.length())));
        result.append(ALPHA_LOW.charAt(random.nextInt(ALPHA_LOW.length())));
        
        String combined = ALPHA_CAPS + ALPHA_LOW + NUMERIC;
        for (int i = 0; i < 5; i++) {
            result.append(combined.charAt(random.nextInt(combined.length())));
        }
        return result.toString();
    }
}