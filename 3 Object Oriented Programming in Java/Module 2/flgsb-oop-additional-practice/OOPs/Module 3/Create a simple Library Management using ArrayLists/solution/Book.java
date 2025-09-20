// Book class to represent a library book
public class Book {
    // Step 1: Declare private variables for bookTitle, author, publicationYear, and isAvailable
    private String bookTitle;
    private String author;
    private int publicationYear;
    private boolean isAvailable;
    
    // Step 2: Create a constructor that accepts bookTitle, author, and publicationYear
    public Book(String bookTitle, String author, int publicationYear) {
        this.bookTitle = bookTitle;
        this.author = author;
        this.publicationYear = publicationYear;
        this.isAvailable = true; // Default value - when a book is added, it's available
    }
    
    // Step 3: Create public getter methods for each variable
    public String getBookTitle() {
        return bookTitle;
    }
    
    public String getAuthor() {
        return author;
    }
    
    public int getPublicationYear() {
        return publicationYear;
    }
    
    public boolean isAvailable() {
        return isAvailable;
    }
    
    // Step 4: Create public setter methods for relevant variables
    // We generally don't allow changing the title, author, or year once a book is created
    
    // Step 5: Create methods to check out and return a book
    public boolean checkOut() {
        if (isAvailable) {
            isAvailable = false;
            return true; // Successfully checked out
        }
        return false; // Book was not available
    }
    
    public boolean returnBook() {
        if (!isAvailable) {
            isAvailable = true;
            return true; // Successfully returned
        }
        return false; // Book was not checked out
    }
    
    // Step 6: Override toString() method to display book details
    @Override
    public String toString() {
        return "\"" + bookTitle + "\" by " + author + " (" + publicationYear + ") - " 
               + (isAvailable ? "Available" : "Checked Out");
    }
}
