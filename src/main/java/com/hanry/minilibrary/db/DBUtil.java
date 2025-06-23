package com.hanry.minilibrary.db;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;

public final class DBUtil {
    private static final String URL = "jdbc:sqlite:diary.db";
    
    static {                        // tạo bảng nếu chưa có
        try (Connection c = get(); Statement st = c.createStatement()) {
            st.executeUpdate("""
              CREATE TABLE IF NOT EXISTS diary_entries (
                 id INTEGER PRIMARY KEY,
                 title TEXT,
                 timestamp TEXT,
                 mood TEXT,
                 content TEXT)
            """);
        } catch (SQLException e) { e.printStackTrace(); }
    }
    
    public static Connection get() throws SQLException {
        return DriverManager.getConnection(URL);
    }
} 