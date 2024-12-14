import java.util.Scanner;

public class Driver {
    public static void main(String[] args) {
        // Scanner for user input
        Scanner userInput = new Scanner(System.in);

        // Prompt the user to choose a book
        System.out.println("Please tell me the title of the book you are looking for:");
        String title = userInput.nextLine();

        // Call method to search the book in the database
        DatabaseHandler.searchBookByTitle(title);

        // Close the scanner
        userInput.close();
    }
}
