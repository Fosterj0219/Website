import java.sql.*;

public class DatabaseHandler {

    // Database connection details
    private static final String URL = "jdbc:mysql://localhost:3306/midtermproj";
    private static final String USER = "root";
    private static final String PASSWORD = "your_password";

    // Method to search for a book by title
    public static void searchBookByTitle(String title) {
        try {
            // Load MySQL JDBC driver
            Class.forName("com.mysql.cj.jdbc.Driver");

            // Connect to the database
            Connection conn = DriverManager.getConnection(URL, USER, PASSWORD);

            // Query to search for the book
            String query = "SELECT * FROM longlistA WHERE title = ?";
            PreparedStatement stmt = conn.prepareStatement(query);
            stmt.setString(1, title);

            // Execute the query and process the result
            ResultSet rs = stmt.executeQuery();

            if (rs.next()) {
                System.out.println("Book found:");
                System.out.println("Title: " + rs.getString("title"));
                System.out.println("Author: " + rs.getString("author"));
            } else {
                System.out.println("This book isn't available: " + title);
            }

            // Close resources
            rs.close();
            stmt.close();
            conn.close();
        } catch (SQLException | ClassNotFoundException e) {
            System.out.println("Error: " + e.getMessage());
        }
    }

    // Method to track how many times a book is requested
    public static void requestTracker(int bookId) {
        try {
            // Connect to the database
            Connection conn = DriverManager.getConnection(URL, USER, PASSWORD);

            // Query to count the number of requests
            String query = "SELECT COUNT(*) FROM assigned_books WHERE book_id = ?";
            PreparedStatement stmt = conn.prepareStatement(query);
            stmt.setInt(1, bookId);

            // Execute the query
            ResultSet rs = stmt.executeQuery();

            if (rs.next()) {
                int requestCount = rs.getInt(1);
                System.out.println("The book associated with ID " + bookId + " is being requested " + requestCount + " times.");
            } else {
                System.out.println("Book ID not found.");
            }

            // Close resources
            rs.close();
            stmt.close();
            conn.close();
        } catch (SQLException e) {
            System.out.println("Database error: " + e.getMessage());
        }
    }
}
