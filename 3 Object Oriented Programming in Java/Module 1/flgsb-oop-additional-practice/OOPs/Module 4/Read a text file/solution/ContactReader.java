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
