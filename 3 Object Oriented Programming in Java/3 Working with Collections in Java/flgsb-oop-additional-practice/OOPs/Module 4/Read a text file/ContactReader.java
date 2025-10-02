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
