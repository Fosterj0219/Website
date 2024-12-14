import java.sql.*;

public class AddBook {

    // Database connection details
    private static final String URL = "jdbc:mysql://localhost:3306/midtermproj";
    private static final String USER = "root";
    private static final String PASSWORD = "your_password";

    // Method to add a new book
    public static void addNewBook(String title, String author, String publisher) {
        try {
            // Load MySQL JDBC driver
            Class.forName("com.mysql.cj.jdbc.Driver");

            // Connect to the database
            Connection conn = DriverManager.getConnection(URL, USER, PASSWORD);

            // Query to insert a new book into the database
            String query = "INSERT INTO longlistA (title, author, publisher) VALUES (?, ?, ?)";
            PreparedStatement stmt = conn.prepareStatement(query);
            stmt.setString(1, title);
            stmt.setString(2, author);
            stmt.setString(3, publisher);

            // Execute the query
            int rowsAffected = stmt.executeUpdate();
            if (rowsAffected > 0) {
                System.out.println("New book added successfully!");
            } else {
                System.out.println("Failed to add the book.");
            }

            // Close resources
            stmt.close();
            conn.close();
        } catch (SQLException | ClassNotFoundException e) {
            System.out.println("Error: " + e.getMessage());
        }
    }
}
