package db;

import java.sql.Connection;
import java.sql.DriverManager;

public class DBConnection {

    public static Connection getConnection() {

        String url = "jdbc:mysql://localhost:3306/student_management";
        String username = "root";
        String password = "MySQL@2026";

        try {
            return DriverManager.getConnection(url, username, password);
        }
        catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }
}
