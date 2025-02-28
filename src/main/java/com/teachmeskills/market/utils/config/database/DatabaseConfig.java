package com.teachmeskills.market.utils.config.database;

import lombok.Getter;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.IOException;
import java.io.InputStream;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.Properties;

@Getter
public final class DatabaseConfig {
    private static final Logger logger = LoggerFactory.getLogger(DatabaseConfig.class);
    private static volatile DatabaseConfig instance;
    private String url;
    private String user;
    private String password;

    private DatabaseConfig() {
        loadProperties();
    }

    public static DatabaseConfig getInstance() {
        if (instance == null) {
            synchronized (DatabaseConfig.class) {
                if (instance == null) {
                    instance = new DatabaseConfig();
                }
            }
        }
        return instance;
    }

    private void loadProperties() {
        try (InputStream input = getClass().getClassLoader().getResourceAsStream("database_config.properties")) {
            if (input == null) {
                logger.error("Sorry, unable to find database_config.properties");
                return;
            }

            Properties prop = new Properties();
            prop.load(input);
            url = prop.getProperty("db.url");
            user = prop.getProperty("db.user");
            password = prop.getProperty("db.password");

            if (url == null || user == null || password == null) {
                logger.error("Database configuration properties are missing or invalid.");
            }
        } catch (IOException ex) {
            logger.error("Error loading configuration file -> {}", ex.getMessage(), ex);
        }
    }

    public Connection getConnection() throws SQLException {
        return DriverManager.getConnection(url, user, password);
    }
}