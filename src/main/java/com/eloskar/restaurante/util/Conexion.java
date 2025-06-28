package com.eloskar.restaurante.util;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class Conexion {

    public static Connection getConnection() {
        try {
            String url = "jdbc:sqlserver://localhost:1433;databaseName=eloskar;encrypt=false;";
            String driver = "com.microsoft.sqlserver.jdbc.SQLServerDriver";
            String usr = "sa";
            String pwd = "dba";

            Class.forName(driver);
            return DriverManager.getConnection(url, usr, pwd);
        } catch (ClassNotFoundException | SQLException ex) {
            throw new RuntimeException("Error al crear la conexión", ex);
        }
    }
}
