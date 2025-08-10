package com.shop.zenstore.Database;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class MySQL_Connection {

    private static final String URL = "jdbc:mysql://localhost:3306/Zen Store";
    private static final String USERNAME = "root";
    private static final String PASSWORD = "Shrish@2004";

    public static Connection getConnection() {

        try {
            return DriverManager.getConnection(URL, USERNAME, PASSWORD);
        }

        catch (SQLException e) {
            e.printStackTrace();
            throw new IllegalStateException("Cannot Connect To The MySQL Database!", e);
        }

    }


}
