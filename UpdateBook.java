import java.sql.*;

public class UpdateBook {

    // Database connection details
    private static final String URL = "jdbc:mysql://localhost:3306/midtermproj";
    private static final String USER = "root";
    private static final String PASSWORD = "your_password";

    // Method to update book details
    public static void updateBookDetails(int bookId, String title, String author, String publisher) {
        try {
            // Load MySQL JDBC driver
            Class.forName("com.mysql.cj.jdbc.Driver");

            // Connect to the database
            Connection conn = DriverManager.getConnection(URL, USER, PASSWORD);

            // Query to update book details
            String query = "UPDATE longlistA SET title = ?, author = ?, publisher = ? WHERE book_id = ?";
            PreparedStatement stmt = conn.prepareStatement(query);
            stmt.setString(1, title);
            stmt.setString(2, author);
            stmt.setString(3, publisher);
            stmt.setInt(4, bookId);

            // Execute the query
            int rowsAffected = stmt.executeUpdate();
            if (rowsAffected > 0) {
                System.out.println("Book updated successfully!");
            } else {
                System.out.println("Failed to update the book.");
            }

            // Close resources
            stmt.close();
            conn.close();
        } catch (SQLException | ClassNotFoundException e) {
            System.out.println("Error: " + e.getMessage());
        }
    }
}
