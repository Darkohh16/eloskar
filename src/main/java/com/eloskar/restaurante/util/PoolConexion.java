package com.eloskar.restaurante.util;

import com.zaxxer.hikari.HikariConfig;
import com.zaxxer.hikari.HikariDataSource;
import io.github.cdimascio.dotenv.Dotenv;

import java.sql.Connection;
import java.sql.SQLException;

public class PoolConexion {
    private static final HikariDataSource dataSource;

    static {
        String url  = System.getenv("DB_URL");
        String user = System.getenv("DB_USER");
        String pass = System.getenv("DB_PASSWORD");

        if (url == null || user == null || pass == null) {
            Dotenv dotenv = Dotenv.configure()
                    .ignoreIfMissing()
                    .ignoreIfMalformed()
                    .load();

            if (url == null)  url  = dotenv.get("DB_URL");
            if (user == null) user = dotenv.get("DB_USER");
            if (pass == null) pass = dotenv.get("DB_PASSWORD");
        }

        if (url == null || user == null || pass == null) {
            throw new IllegalStateException(
                    "No se pudieron cargar las variables del entorno."
            );
        }

        String driver = "com.microsoft.sqlserver.jdbc.SQLServerDriver";

        // Configuración de Hikari
        HikariConfig config = new HikariConfig();
        config.setDriverClassName(driver);
        config.setJdbcUrl(url);
        config.setUsername(user);
        config.setPassword(pass);

        config.setMaximumPoolSize(10);
        config.setConnectionTimeout(5000);

        dataSource = new HikariDataSource(config);
    }

    public static Connection getConnection() throws SQLException {
        return dataSource.getConnection();
    }

    public static void cerrarPool() {
        if (dataSource != null) {
            dataSource.close();
        }
    }
}
