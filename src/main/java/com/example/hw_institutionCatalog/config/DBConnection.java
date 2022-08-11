package com.example.hw_institutionCatalog.config;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.Properties;

//@Component
public class DBConnection {

//    public static final String DATABASE_URL = "jdbc:mysql://localhost:3306/institution_db";
    public static final String DATABASE_URL = "jdbc:postgresql://localhost:5432/postgres";
    public static Connection connection;

    private static final Logger log = LoggerFactory.getLogger(DBConnection.class);

    static {
        try {
             connection = DriverManager.getConnection(DATABASE_URL, getConnection());
        } catch (SQLException e) {
            log.debug("Incorrect database URL '{}' or connection props '{}'", DATABASE_URL, getConnection());
        }
    }

    public static Properties getConnection(){
        Properties properties = new Properties();
        properties.setProperty("user", "postgres");
        properties.setProperty("password", "root");
        return properties;
    }
}
