import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class TestDatabaseConnection {
    public static void main(String[] args) {
        try {
            // Load the driver
            Class.forName("org.mariadb.jdbc.Driver");

            // Attempt to connect
            String url = "jdbc:mariadb://localhost:3307/todo_tracker";
            String username = "root";
            String password = "SanRed@1998";
            Connection conn = DriverManager.getConnection(url, username, password);
            System.out.println("Connected to the database!");
            conn.close();
        } catch (ClassNotFoundException | SQLException e) {
            e.printStackTrace();
        }
    }
}
