import java.sql.*;
import java.util.Scanner;

public class App {

    private static final String DB_URL = "jdbc:mysql://localhost:3306/midtermproj";
    private static final String DB_USER = "root";
    private static final String DB_PASSWORD = "College25!!";

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        System.out.println("Please tell me the title of the book you are looking for:");
        String title = scanner.nextLine(); 

        // Fetch book details
        Book book = getBookByTitle(title);
        
        if (book != null) {
            // Print book details if found
            printBookDetails(book);
        } else {
            System.out.println("No book found with the title: " + title);
        }

        // Close the scanner
        scanner.close();
    }

    // Function to fetch a book by title
    private static Book getBookByTitle(String title) {
        String query = "SELECT * FROM longlistA WHERE title = ?";
        
        try (Connection conn = DriverManager.getConnection(DB_URL, DB_USER, DB_PASSWORD);
             PreparedStatement stmt = conn.prepareStatement(query)) {
            
            stmt.setString(1, title);  // Set the title parameter
            
            try (ResultSet rs = stmt.executeQuery()) {
                if (rs.next()) {
                    // Map the result to a Book object
                    return new Book(
                        rs.getString("title"),
                        rs.getString("author"),
                        rs.getString("translator"),
                        rs.getString("format"),
                        rs.getString("publisher"),
                        rs.getInt("votes"),
                        rs.getDouble("rating"),
                        rs.getBoolean("checked_out"),
                        rs.getDate("borrowed_date"),
                        rs.getDate("due_date"),
                        rs.getString("borrower"),
                        rs.getDate("return_date")
                    );
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    // Function to print book details
    private static void printBookDetails(Book book) {
        System.out.println("Book found:");
        System.out.println("Title: " + book.getTitle());
        System.out.println("Author: " + book.getAuthor());
        System.out.println("Translator: " + book.getTranslator());
        System.out.println("Format: " + book.getFormat());
        System.out.println("Publisher: " + book.getPublisher());
        System.out.println("Votes: " + book.getVotes());
        System.out.println("Rating: " + book.getRating());
        System.out.println("Checked Out: " + book.isCheckedOut());
        System.out.println("Borrowed Date: " + book.getBorrowedDate());
        System.out.println("Due Date: " + book.getDueDate());
        System.out.println("Borrower: " + book.getBorrower());
        System.out.println("Return Date: " + book.getReturnDate());
    }

    // Book class to hold book data
    static class Book {
        private String title, author, translator, format, publisher, borrower;
        private int votes;
        private double rating;
        private boolean checkedOut;
        private Date borrowedDate, dueDate, returnDate;

        // Constructor
        public Book(String title, String author, String translator, String format, String publisher,
                    int votes, double rating, boolean checkedOut, Date borrowedDate, Date dueDate,
                    String borrower, Date returnDate) {
            this.title = title;
            this.author = author;
            this.translator = translator;
            this.format = format;
            this.publisher = publisher;
            this.votes = votes;
            this.rating = rating;
            this.checkedOut = checkedOut;
            this.borrowedDate = borrowedDate;
            this.dueDate = dueDate;
            this.borrower = borrower;
            this.returnDate = returnDate;
        }

        // Getters for book properties
        public String getTitle() { return title; }
        public String getAuthor() { return author; }
        public String getTranslator() { return translator; }
        public String getFormat() { return format; }
        public String getPublisher() { return publisher; }
        public int getVotes() { return votes; }
        public double getRating() { return rating; }
        public boolean isCheckedOut() { return checkedOut; }
        public Date getBorrowedDate() { return borrowedDate; }
        public Date getDueDate() { return dueDate; }
        public String getBorrower() { return borrower; }
        public Date getReturnDate() { return returnDate; }
    }
}
