import java.util.HashMap;
import java.util.Scanner;
import java.util.TreeMap;
import java.util.Map;

public class LibraryManagementSystem {
    // Book class to represent book information
    static class Book {
        // Step 1: Declare variables for title, author, genre, and publication year
        // Hint: Use appropriate data types (String for text, int for year)
        
        // Step 2: Create a constructor for the Book class
        // Hint: The constructor should take parameters for all book attributes
        
        // Step 3: Create getter methods for each attribute
        // Hint: Use the format: public dataType getAttribute()
        
        // Step 4: Create a method to display book details
        // Hint: Return a formatted string with all book information
    }
    
    // Method to validate if the title and author have valid formats
    private static boolean isValidText(String text) {
        // Step 5: Implement validation to ensure text isn't empty
        // Hint: Check if the string is null, empty, or only whitespace
        return false; // Replace this with your implementation
    }
    
    // Method to validate publication year
    private static boolean isValidYear(int year) {
        // Step 6: Implement validation for publication year
        // Hint: Check if the year is reasonable (e.g., between 1000 and current year)
        return false; // Replace this with your implementation
    }
    
    public static void main(String[] args) {
        // Step 7: Create a Scanner for user input
        
        // Step 8: Create a HashMap to store books (with ISBN as the key)
        
        // Step 9: Implement the main loop with menu options
        // Hint: Options should include adding a book, viewing all books, 
        // searching for books, removing a book, viewing sorted books, and exiting
        
        // Step 10: Implement the "Add a book" option
        // Hint: Prompt user for book details (ISBN, title, author, genre, year)
        // Validate input and add to the HashMap
        
        // Step 11: Implement the "View all books" option
        // Hint: Iterate through the HashMap and display all books
        
        // Step 12: Implement the "Search for a book" option
        // Hint: Allow searching by ISBN, title or author
        
        // Step 13: Implement the "Remove a book" option
        // Hint: Remove a book from the collection using its ISBN
        
        // Step 14: Implement the "View sorted books" option
        // Hint: Use TreeMap to sort books by title or author
    }
}