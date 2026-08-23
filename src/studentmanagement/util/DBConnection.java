package studentmanagement.util;

import java.sql.Connection;
import java.sql.DriverManager;

public class DBConnection {

    public static Connection getConnection() {

        Connection con = null;

        try {

            String url = "jdbc:mysql://localhost:3306/student_db";

            String username = "root";

            // Password is read from Windows environment variable
            String password = System.getenv("DB_PASSWORD");

            con = DriverManager.getConnection(url, username, password);

            System.out.println("Database Connected Successfully!");

        } catch (Exception e) {

            e.printStackTrace();

        }

        return con;
    }
}