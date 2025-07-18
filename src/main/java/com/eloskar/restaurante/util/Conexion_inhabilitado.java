package com.eloskar.restaurante.util;

import io.github.cdimascio.dotenv.Dotenv;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class Conexion_inhabilitado {

    private static final Dotenv dotenv = Dotenv.configure()
            .ignoreIfMissing()    // No lanza error si no hay .env
            .ignoreIfMalformed()  // No lanza error si hay líneas raras
            .load();

    public static Connection getConnection() {
        try {
            String url = System.getenv("DB_URL");
            String driver = "com.microsoft.sqlserver.jdbc.SQLServerDriver";
            String usr = System.getenv("DB_USER");
            String pwd = System.getenv("DB_PASSWORD");

            if (url == null) url = dotenv.get("DB_URL");
            if (usr == null) usr = dotenv.get("DB_USER");
            if (pwd == null) pwd = dotenv.get("DB_PASSWORD");

            Class.forName(driver);
            return DriverManager.getConnection(url, usr, pwd);
        } catch (ClassNotFoundException | SQLException ex) {
            throw new RuntimeException("Error al crear la conexión", ex);
        }
    }
}
