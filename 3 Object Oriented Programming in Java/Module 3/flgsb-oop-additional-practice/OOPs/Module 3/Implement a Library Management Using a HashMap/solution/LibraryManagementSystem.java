import java.util.HashMap;
import java.util.Scanner;
import java.util.TreeMap;
import java.util.Map;
import java.util.Calendar;
import java.util.Comparator;

public class LibraryManagementSystem {
    // Book class to represent book information
    static class Book {
        // Step 1: Declare variables for title, author, genre, and publication year
        private String title;
        private String author;
        private String genre;
        private int publicationYear;
        
        // Step 2: Create a constructor for the Book class
        public Book(String title, String author, String genre, int publicationYear) {
            this.title = title;
            this.author = author;
            this.genre = genre;
            this.publicationYear = publicationYear;
        }
        
        // Step 3: Create getter methods for each attribute
        public String getTitle() {
            return title;
        }
        
        public String getAuthor() {
            return author;
        }
        
        public String getGenre() {
            return genre;
        }
        
        public int getPublicationYear() {
            return publicationYear;
        }
        
        // Step 4: Create a method to display book details
        @Override
        public String toString() {
            return "Title: " + title + 
                   ", Author: " + author + 
                   ", Genre: " + genre + 
                   ", Publication Year: " + publicationYear;
        }
    }
    
    // Method to validate if the title and author have valid formats
    private static boolean isValidText(String text) {
        // Step 5: Implement validation to ensure text isn't empty
        return text != null && !text.trim().isEmpty();
    }
    
    // Method to validate publication year
    private static boolean isValidYear(int year) {
        // Step 6: Implement validation for publication year
        int currentYear = Calendar.getInstance().get(Calendar.YEAR);
        return year >= 1000 && year <= currentYear; // Basic validation
    }
    
    public static void main(String[] args) {
        try {
            // Step 7: Create a Scanner for user input
            Scanner scanner = new Scanner(System.in);
            
            // Step 8: Create a HashMap to store books (with ISBN as the key)
            HashMap<String, Book> library = new HashMap<>();
            
            // Step 9: Implement the main loop with menu options
            while (true) {
                System.out.println("\n===== Library Management System =====");
                System.out.println("1. Add a book");
                System.out.println("2. View all books");
                System.out.println("3. Search for a book");
                System.out.println("4. Remove a book");
                System.out.println("5. View sorted books");
                System.out.println("6. Exit");
                System.out.print("Enter your choice: ");
                
                String choice = scanner.nextLine();
                
                // Option 1: Add a book
                if (choice.equals("1")) {
                    // Step 10: Implement the "Add a book" option
                    System.out.print("Enter ISBN: ");
                    String isbn = scanner.nextLine();
                    
                    // Check if ISBN already exists
                    if (library.containsKey(isbn)) {
                        System.out.println("A book with this ISBN already exists!");
                        System.out.print("Do you want to replace it? (y/n): ");
                        String replace = scanner.nextLine();
                        if (!replace.equalsIgnoreCase("y")) {
                            continue;
                        }
                    }
                    
                    System.out.print("Enter title: ");
                    String title = scanner.nextLine();
                    if (!isValidText(title)) {
                        System.out.println("Invalid title! Title cannot be empty.");
                        continue;
                    }
                    
                    System.out.print("Enter author: ");
                    String author = scanner.nextLine();
                    if (!isValidText(author)) {
                        System.out.println("Invalid author! Author cannot be empty.");
                        continue;
                    }
                    
                    System.out.print("Enter genre: ");
                    String genre = scanner.nextLine();
                    if (!isValidText(genre)) {
                        System.out.println("Invalid genre! Genre cannot be empty.");
                        continue;
                    }
                    
                    System.out.print("Enter publication year: ");
                    int year;
                    try {
                        year = Integer.parseInt(scanner.nextLine());
                        if (!isValidYear(year)) {
                            System.out.println("Invalid year! Year must be between 1000 and current year.");
                            continue;
                        }
                    } catch (NumberFormatException e) {
                        System.out.println("Invalid year format! Please enter a valid number.");
                        continue;
                    }
                    
                    // Create book object and add to the library
                    Book newBook = new Book(title, author, genre, year);
                    library.put(isbn, newBook);
                    System.out.println("Book added successfully!");
                }
                
                // Option 2: View all books
                else if (choice.equals("2")) {
                    // Step 11: Implement the "View all books" option
                    if (library.isEmpty()) {
                        System.out.println("The library is empty!");
                    } else {
                        System.out.println("\n===== Library Catalog =====");
                        for (Map.Entry<String, Book> entry : library.entrySet()) {
                            System.out.println("ISBN: " + entry.getKey() + ", " + entry.getValue());
                        }
                    }
                }
                
                // Option 3: Search for a book
                else if (choice.equals("3")) {
                    // Step 12: Implement the "Search for a book" option
                    System.out.println("\n===== Search Books =====");
                    System.out.println("1. Search by ISBN");
                    System.out.println("2. Search by Title");
                    System.out.println("3. Search by Author");
                    System.out.print("Enter your choice: ");
                    String searchChoice = scanner.nextLine();
                    
                    if (searchChoice.equals("1")) {
                        System.out.print("Enter ISBN: ");
                        String isbn = scanner.nextLine();
                        if (library.containsKey(isbn)) {
                            System.out.println("\nBook found:");
                            System.out.println("ISBN: " + isbn + ", " + library.get(isbn));
                        } else {
                            System.out.println("No book found with ISBN: " + isbn);
                        }
                    }
                    else if (searchChoice.equals("2")) {
                        System.out.print("Enter title (or part of title): ");
                        String titleSearch = scanner.nextLine().toLowerCase();
                        boolean found = false;
                        
                        for (Map.Entry<String, Book> entry : library.entrySet()) {
                            if (entry.getValue().getTitle().toLowerCase().contains(titleSearch)) {
                                if (!found) {
                                    System.out.println("\nBooks found:");
                                    found = true;
                                }
                                System.out.println("ISBN: " + entry.getKey() + ", " + entry.getValue());
                            }
                        }
                        
                        if (!found) {
                            System.out.println("No books found with title containing: " + titleSearch);
                        }
                    }
                    else if (searchChoice.equals("3")) {
                        System.out.print("Enter author (or part of author name): ");
                        String authorSearch = scanner.nextLine().toLowerCase();
                        boolean found = false;
                        
                        for (Map.Entry<String, Book> entry : library.entrySet()) {
                            if (entry.getValue().getAuthor().toLowerCase().contains(authorSearch)) {
                                if (!found) {
                                    System.out.println("\nBooks found:");
                                    found = true;
                                }
                                System.out.println("ISBN: " + entry.getKey() + ", " + entry.getValue());
                            }
                        }
                        
                        if (!found) {
                            System.out.println("No books found with author containing: " + authorSearch);
                        }
                    }
                    else {
                        System.out.println("Invalid choice!");
                    }
                }
                
                // Option 4: Remove a book
                else if (choice.equals("4")) {
                    // Step 13: Implement the "Remove a book" option
                    System.out.print("Enter the ISBN of the book to remove: ");
                    String isbn = scanner.nextLine();
                    
                    if (library.containsKey(isbn)) {
                        Book removedBook = library.remove(isbn);
                        System.out.println("Book removed successfully:");
                        System.out.println("ISBN: " + isbn + ", " + removedBook);
                    } else {
                        System.out.println("No book found with ISBN: " + isbn);
                    }
                }
                
                // Option 5: View sorted books
                else if (choice.equals("5")) {
                    // Step 14: Implement the "View sorted books" option
                    if (library.isEmpty()) {
                        System.out.println("The library is empty!");
                    } else {
                        System.out.println("\n===== Sort Books =====");
                        System.out.println("1. Sort by Title");
                        System.out.println("2. Sort by Author");
                        System.out.print("Enter your choice: ");
                        String sortChoice = scanner.nextLine();
                        
                        if (sortChoice.equals("1")) {
                            // Sort by title using TreeMap with custom comparator
                            TreeMap<String, Book> sortedByTitle = new TreeMap<>(
                                Comparator.comparing(isbn -> library.get(isbn).getTitle())
                            );
                            sortedByTitle.putAll(library);
                            
                            System.out.println("\n===== Books Sorted by Title =====");
                            for (Map.Entry<String, Book> entry : sortedByTitle.entrySet()) {
                                System.out.println("ISBN: " + entry.getKey() + ", " + entry.getValue());
                            }
                        }
                        else if (sortChoice.equals("2")) {
                            // Sort by author using TreeMap with custom comparator
                            TreeMap<String, Book> sortedByAuthor = new TreeMap<>(
                                Comparator.comparing(isbn -> library.get(isbn).getAuthor())
                            );
                            sortedByAuthor.putAll(library);
                            
                            System.out.println("\n===== Books Sorted by Author =====");
                            for (Map.Entry<String, Book> entry : sortedByAuthor.entrySet()) {
                                System.out.println("ISBN: " + entry.getKey() + ", " + entry.getValue());
                            }
                        }
                        else {
                            System.out.println("Invalid choice!");
                        }
                    }
                }
                
                // Option 6: Exit
                else if (choice.equals("6")) {
                    System.out.println("Thank you for using the Library Management System!");
                    break;
                }
                else {
                    System.out.println("Invalid choice! Please try again.");
                }
            }
            
            scanner.close();
        } catch (Exception e) {
            System.out.println("An error occurred: " + e.getMessage());
            e.printStackTrace();
        }
    }
}