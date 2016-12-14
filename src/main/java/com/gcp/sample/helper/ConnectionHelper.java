package com.gcp.sample.helper;

import wekaservice.factory.PropertiesFactory;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

/**
 * Created by lunar on 11/28/2016.
 */
public class ConnectionHelper {
    private static Connection _connection;

    public static Connection getConnection() throws SQLException, ClassNotFoundException {
        if (ConnectionHelper._connection == null && ConnectionHelper._connection.isClosed()) {
            String connectionString = "";
            try {
                connectionString = PropertiesFactory.getInstance().getProperties().getProperty("connectionString");
            } catch (Exception ex) {
                System.out.println("Can not get connection string from properties");
                return null;
            }

            // Declare the JDBC objects.
            Class.forName("com.mysql.jdbc.Driver"); // Force server to load SQL JDBC driver
            ConnectionHelper._connection = DriverManager.getConnection(connectionString);
        }

        return ConnectionHelper._connection;
    }
}
