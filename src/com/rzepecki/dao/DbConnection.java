package com.rzepecki.dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DbConnection {
    private static final String URL = "jdbc:mysql://127.0.0.1:3306/jdbc_test?useSSL=false&characterEncoding=utf8&serverTimezone=Europe/Warsaw";
    private static  final String USER = "root";
    private static  final String PASSWORD = "Nokia2020lte";


    public static Connection getConnection() {
        Connection conn = null;
        try {
            conn = DriverManager.getConnection(URL, USER, PASSWORD);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return conn;
    }
}
