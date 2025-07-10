package com.eloskar.restaurante.util;

import com.zaxxer.hikari.HikariConfig;
import com.zaxxer.hikari.HikariDataSource;
import io.github.cdimascio.dotenv.Dotenv;

import java.sql.Connection;
import java.sql.SQLException;

public class PoolConexion {
    //Una sola pool
    private static final HikariDataSource dataSource;

    static {
        Dotenv dotenv = Dotenv.configure()
                .ignoreIfMissing()
                .ignoreIfMalformed()
                .load();
        String url = dotenv.get("DB_URL");
        String user = dotenv.get("DB_USER");
        String pass = dotenv.get("DB_PASSWORD");
        String driver = "com.microsoft.sqlserver.jdbc.SQLServerDriver";

        //Configuracion de Hikari
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

    public static void cerrarPool(){
        if (dataSource != null) {
            dataSource.close();
        }
    }
}
