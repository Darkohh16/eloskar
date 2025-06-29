package com.eloskar.restaurante.util;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class Conexion {

    public static Connection getConnection() {
        try {
            String url = "jdbc:sqlserver://sqlserverdarkohh.cti60io0mpe0.us-east-2.rds.amazonaws.com:1433;" +
                    "databaseName=eloskar;encrypt=false;";
            String driver = "com.microsoft.sqlserver.jdbc.SQLServerDriver";
            String usr = "Admin";
            String pwd = "111100Jgmgr!";

            Class.forName(driver);
            return DriverManager.getConnection(url, usr, pwd);
        } catch (ClassNotFoundException | SQLException ex) {
            throw new RuntimeException("Error al crear la conexión", ex);
        }
    }
}
