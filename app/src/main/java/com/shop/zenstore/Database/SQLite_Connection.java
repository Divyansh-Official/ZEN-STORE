package com.shop.zenstore.Database;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class SQLite_Connection {

    private static final String DATABASE = "zenstore.db";
    private static final String URL = "jdbc:sqlite:" + DATABASE;
//    private static final String USERNAME = "";
//    private static final String PASSWORD = "";

    public static Connection getConnection() {

        try {
            Class.forName("org.sqlite.JDBC");
            return DriverManager.getConnection(URL);
        }

        catch (ClassNotFoundException e) {
            System.err.println("SQLite JDBC Driver Not Found.");
            e.printStackTrace();
            throw new IllegalStateException("Could Not Find The SQLite JDBC Driver.", e);
        }

        catch (SQLException e) {
            System.err.println("Failed To Connect To SQLite Database.");
            e.printStackTrace();
            throw new IllegalStateException("Cannot connect to the SQLite database!", e);
        }
    }

}
