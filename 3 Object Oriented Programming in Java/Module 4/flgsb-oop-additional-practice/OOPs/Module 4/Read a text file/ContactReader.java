/*



// ContactReader.java
// This program reads contact information from a file and displays it in a formatted way

// Step 1: Import necessary packages for file I/O operations
// Hint: You'll need classes from java.io or java.nio.file packages
// You'll also need Scanner for user input

public class ContactReader {
    public static void main(String[] args) {
        // Step 2: Create a Scanner object to read user input
        
        // Step 3: Prompt the user to enter the file name containing contacts
        // Example: "Enter the name of the contacts file:"
        
        // Step 4: Read the file name entered by the user
        
        try {
            // Step 5: Create a FileReader or similar object to read the file
            // Hint: You can use FileReader, BufferedReader, or Files class
            
            // Step 6: Read the file line by line
            // Hint: Use a loop to process each line
            
                // Step 7: Parse each line to extract name and phone number
                // Hint: Use String methods like split() to separate by colon
                
                // Step 8: Display the contact information in a formatted way
                // Example: "Contact: John Doe | Phone: +1-555-123-4567"
            
            // Step 9: Close the file reader when done
            
        } catch (Exception e) {
            // Step 10: Handle exceptions appropriately
            // Display a user-friendly error message
            
        }
        
        // Optional Bonus:
        // Step 11: Add a feature to count and display the total number of contacts read
    }
}



*/


// ContactReader.java
// This program reads contact information from a file and displays it in a formatted way

// Import necessary packages for file I/O operations and user input
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Scanner;

public class ContactReader {
    public static void main(String[] args) {
        // Create a Scanner object to read user input from the console
        Scanner scanner = new Scanner(System.in);
        
        // Prompt the user to enter the file name containing contacts
        System.out.println("Enter the name of the contacts file:");
        
        // Read the file name entered by the user
        String fileName = scanner.nextLine();
        
        // Variable to keep track of the number of contacts read
        int contactCount = 0;
        
        try {
            // Create a BufferedReader to read from the file
            // BufferedReader is more efficient for reading lines from a file
            BufferedReader reader = new BufferedReader(new FileReader(fileName));
            
            // String to hold each line from the file
            String line;
            
            System.out.println("\n===== CONTACT LIST =====");
            
            // Read the file line by line until we reach the end (null)
            while ((line = reader.readLine()) != null) {
                // Make sure line is not empty
                if (!line.trim().isEmpty()) {
                    // Parse the line to extract name and phone number
                    // The format is expected to be: Name:PhoneNumber
                    String[] parts = line.split(":");
                    
                    // Verify the line has the expected format
                    if (parts.length == 2) {
                        String name = parts[0].trim();
                        String phoneNumber = parts[1].trim();
                        
                        // Display the contact information in a formatted way
                        System.out.println("Contact: " + name + " | Phone: " + phoneNumber);
                        
                        // Increment the contact count
                        contactCount++;
                    } else {
                        // Line doesn't match expected format
                        System.out.println("Warning: Skipping improperly formatted line: " + line);
                    }
                }
            }
            
            // Close the reader when done to free up resources
            reader.close();
            
            // Display the total number of contacts read
            System.out.println("\nTotal contacts read: " + contactCount);
            
        } catch (FileNotFoundException e) {
            // Handle case where the file doesn't exist
            System.err.println("Error: File not found - " + fileName);
            System.err.println("Please check the file name and path and try again.");
        } catch (IOException e) {
            // Handle other I/O errors that may occur during reading
            System.err.println("Error reading from file: " + e.getMessage());
        } catch (Exception e) {
            // Handle any other unexpected errors
            System.err.println("An unexpected error occurred: " + e.getMessage());
        } finally {
            // Close the scanner
            scanner.close();
        }
    }
}