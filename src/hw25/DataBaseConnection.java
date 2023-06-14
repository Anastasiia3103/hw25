package hw25;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

    public class DataBaseConnection {
        private static final String DB_URL = "jdbc:postgresql://localhost:5432/mydatabase";
        private static final String USER = "username";
        private static final String PASSWORD = "password";

        public static Connection getConnection() throws SQLException {
            return DriverManager.getConnection(DB_URL, USER, PASSWORD);
        }

        public static void close(Connection connection) throws SQLException {
            if (connection != null) {
                connection.close();
            }
        }
    }

