import java.io.File;
import java.io.IOException;
import java.util.Scanner;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * FileSystemManager - A command-line file management system
 * 
 * This class provides a shell-like interface for managing files and directories
 */
public class FileSystemManager {
    
    // The current working directory
    private File currentDirectory;
    
    // Scanner for user input
    private Scanner scanner;
    
    // Date formatter for file timestamps
    private SimpleDateFormat dateFormat;
    
    /**
     * Constructor to initialize the file system manager
     */
    public FileSystemManager() {
        // TODO: Initialize the current directory to the user's current directory
        // Hint: Use System.getProperty("user.dir") to get the current working directory
        
        // TODO: Initialize the scanner for reading user input
        
        // TODO: Initialize the date formatter for displaying timestamps
        // Hint: Use "yyyy-MM-dd HH:mm:ss" as the date format pattern
    }
    
    /**
     * Start the file system manager
     */
    public void start() {
        System.out.println("Welcome to the File System Manager!");
        System.out.println("Type 'help' to see available commands.");
        
        boolean running = true;
        while (running) {
            // TODO: Display the current directory path as a prompt
            
            // TODO: Read user command
            
            // TODO: Process the command
            // If command is "exit", set running to false
            // Otherwise, call processCommand() method
        }
        
        // TODO: Close the scanner before exiting
    }
    
    /**
     * Process a user command
     * 
     * @param command The command entered by the user
     * @return true to continue, false to exit
     */
    private boolean processCommand(String command) {
        // Split the command into parts (command name and arguments)
        String[] parts = command.trim().split("\\s+", 2);
        String commandName = parts[0].toLowerCase();
        String args = (parts.length > 1) ? parts[1] : "";
        
        switch (commandName) {
            case "help":
                displayHelp();
                break;
            case "ls":
                // TODO: Implement listing files and directories
                break;
            case "cd":
                // TODO: Implement changing directories
                // Hint: Handle "cd .." (parent directory) and "cd directoryName"
                break;
            case "pwd":
                // TODO: Implement displaying current directory path
                break;
            case "mkdir":
                // TODO: Implement creating a new directory
                break;
            case "touch":
                // TODO: Implement creating a new file
                break;
            case "rm":
                // TODO: Implement deleting a file or directory
                break;
            case "rename":
                // TODO: Implement renaming a file or directory
                // Hint: The args will contain both old and new names
                break;
            case "find":
                // TODO: Implement searching for files by name pattern
                break;
            case "info":
                // TODO: Implement displaying file information
                break;
            case "exit":
                return false;
            default:
                System.out.println("Unknown command. Type 'help' for available commands.");
        }
        
        return true;
    }
    
    /**
     * Display help information
     */
    private void displayHelp() {
        System.out.println("\nAvailable commands:");
        System.out.println("  help              - Display this help message");
        System.out.println("  ls                - List files in current directory");
        System.out.println("  cd <directory>    - Change to specified directory (use .. for parent)");
        System.out.println("  pwd               - Print current directory path");
        System.out.println("  mkdir <name>      - Create a new directory");
        System.out.println("  touch <name>      - Create a new file");
        System.out.println("  rm <name>         - Delete a file or directory");
        System.out.println("  rename <old> <new> - Rename a file or directory");
        System.out.println("  find <pattern>    - Search for files matching pattern");
        System.out.println("  info <name>       - Display file information");
        System.out.println("  exit              - Exit the program");
    }
    
    /**
     * List files and directories in the current directory
     */
    private void listFiles() {
        // TODO: Get the list of files and directories in the current directory
        
        // TODO: Display the list of files and directories
        // For each file, show:
        // - 'd' if it's a directory or '-' if it's a file
        // - The file name
    }
    
    /**
     * Change to a different directory
     * 
     * @param dirName The name of the directory to change to
     */
    private void changeDirectory(String dirName) {
        // TODO: Implement changing to a directory
        // If dirName is "..", go to parent directory
        // Otherwise, change to the specified directory if it exists
    }
    
    /**
     * Create a new directory
     * 
     * @param dirName The name of the directory to create
     */
    private void createDirectory(String dirName) {
        // TODO: Implement creating a new directory
        // Create a new directory with the given name in the current directory
    }
    
    /**
     * Create a new file
     * 
     * @param fileName The name of the file to create
     */
    private void createFile(String fileName) {
        // TODO: Implement creating a new file
        // Create a new empty file with the given name in the current directory
    }
    
    /**
     * Delete a file or directory
     * 
     * @param name The name of the file or directory to delete
     */
    private void delete(String name) {
        // TODO: Implement deleting a file or directory
        // If it's a directory, provide a warning and confirm deletion
    }
    
    /**
     * Rename a file or directory
     * 
     * @param oldName The current name of the file or directory
     * @param newName The new name for the file or directory
     */
    private void rename(String oldName, String newName) {
        // TODO: Implement renaming a file or directory
    }
    
    /**
     * Search for files matching a pattern
     * 
     * @param pattern The pattern to search for
     */
    private void findFiles(String pattern) {
        // TODO: Implement searching for files by name pattern
        // Use recursive method to search through directories
    }
    
    /**
     * Display detailed information about a file
     * 
     * @param fileName The name of the file to display information for
     */
    private void displayFileInfo(String fileName) {
        // TODO: Implement displaying file information
        // Show file size, last modified date, whether it's a directory, etc.
    }
    
    /**
     * Main method
     */
    public static void main(String[] args) {
        FileSystemManager manager = new FileSystemManager();
        manager.start();
    }
}
