package com.eloskar.restaurante.util;
import io.github.cdimascio.dotenv.Dotenv;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class Conexion {

    private static final Dotenv dotenv = Dotenv.load();

    public static Connection getConnection() {
        try {
            String url = dotenv.get("DB_URL");
            String driver = "com.microsoft.sqlserver.jdbc.SQLServerDriver";
            String usr = dotenv.get("DB_USER");
            String pwd = dotenv.get("DB_PASSWORD");
            System.out.println(url + usr + pwd);
            Class.forName(driver);
            return DriverManager.getConnection(url, usr, pwd);
        } catch (ClassNotFoundException | SQLException ex) {
            throw new RuntimeException("Error al crear la conexión", ex);
        }
    }
}
