package com.w1zer.utils;

import com.microsoft.sqlserver.jdbc.SQLServerDriver;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public final class JDBCUtils {
    private static final String USERNAME = "w1zer";
    private static final String PASSWORD = "meow";
    private static final String URL = "jdbc:sqlserver://pc;databaseName=ToDoList;encrypt=true;trustServerCertificate=true";

    private JDBCUtils() {
    }

    public static void registerDriver() throws SQLException {
        DriverManager.registerDriver(new SQLServerDriver());
    }

    public static Connection getConnection() throws SQLException {
        return DriverManager.getConnection(URL, USERNAME, PASSWORD);
    }
}
