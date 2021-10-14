package peaksoft.util;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class Util {
    private static final String url = "jdbc:postgresql://localhost:27015/postgres";
    private static final String login = "postgres";
    private static final String password = "admin";

    public static Connection connect() {
        Connection conn = null;
        try {
            conn = DriverManager.getConnection(url, login, password);
            System.out.println("Connection to the PostgreSQL server successfully.");
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }
        return conn;
    }
}
