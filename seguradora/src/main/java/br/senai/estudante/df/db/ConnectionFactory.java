package br.senai.estudante.df.db;

import lombok.extern.log4j.Log4j2;

import java.io.FileInputStream;
import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.Properties;

@Log4j2
public class ConnectionFactory {

    private static Connection connection;

    public static Connection getConnection() throws SQLException {
        if (connection == null) {
            try {
                Properties properties = loadProperties();
                String url = properties.getProperty("dburl");

                connection = DriverManager.getConnection(url, properties);
            } catch (SQLException e) {
                log.error("getConnection erro {}", e);
                // throw new DbException(e.getMessage());
            }

            return connection;
        }

        return connection;
    }

    private static Properties loadProperties() {
        try (FileInputStream fs = new FileInputStream("db.properties")) {
            Properties properties = new Properties();
            properties.load(fs);

            return properties;
        } catch (IOException e) {
            log.error("loadProperties erro {}", e);
            throw new DbException(e.getMessage());
        }
    }

    public static boolean status() {
        boolean valid = false;
        try {
            valid = getConnection().isValid(30);
        } catch (SQLException e) {
            log.error("Connection falid {}", e);
        }
        return valid;
    }
}
