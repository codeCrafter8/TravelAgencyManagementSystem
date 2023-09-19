package com.server.database;

import java.sql.Connection;
import java.sql.DriverManager;

/**
 * A class that provides connection to the database
 */
public class DBContext {

    /**
     * Gets the connection to the database
     * @return the connection
     */
    public Connection getConnection() throws Exception {
        Class.forName("oracle.jdbc.driver.OracleDriver");
        return DriverManager.getConnection("jdbc:oracle:thin:@localhost:1521:xe", "system", "admin");
    }
}
