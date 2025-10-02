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
        // Initialize the current directory to the user's current directory
        currentDirectory = new File(System.getProperty("user.dir"));
        
        // Initialize the scanner for reading user input
        scanner = new Scanner(System.in);
        
        // Initialize the date formatter for displaying timestamps
        dateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
    }
    
    /**
     * Start the file system manager
     */
    public void start() {
        System.out.println("Welcome to the File System Manager!");
        System.out.println("Type 'help' to see available commands.");
        
        boolean running = true;
        while (running) {
            // Display the current directory path as a prompt
            System.out.print(currentDirectory.getAbsolutePath() + "> ");
            
            // Read user command
            String command = scanner.nextLine().trim();
            
            // Process the command
            if (command.equalsIgnoreCase("exit")) {
                running = false;
            } else {
                running = processCommand(command);
            }
        }
        
        // Close the scanner before exiting
        scanner.close();
        System.out.println("Thank you for using File System Manager. Goodbye!");
    }
    
    /**
     * Process a user command
     * 
     * @param command The command entered by the user
     * @return true to continue, false to exit
     */
    private boolean processCommand(String command) {
        // Skip empty commands
        if (command.isEmpty()) {
            return true;
        }
        
        // Split the command into parts (command name and arguments)
        String[] parts = command.trim().split("\\s+", 2);
        String commandName = parts[0].toLowerCase();
        String args = (parts.length > 1) ? parts[1] : "";
        
        switch (commandName) {
            case "help":
                displayHelp();
                break;
            case "ls":
                listFiles();
                break;
            case "cd":
                changeDirectory(args);
                break;
            case "pwd":
                System.out.println(currentDirectory.getAbsolutePath());
                break;
            case "mkdir":
                if (args.isEmpty()) {
                    System.out.println("Error: Directory name is required");
                } else {
                    createDirectory(args);
                }
                break;
            case "touch":
                if (args.isEmpty()) {
                    System.out.println("Error: File name is required");
                } else {
                    createFile(args);
                }
                break;
            case "rm":
                if (args.isEmpty()) {
                    System.out.println("Error: File or directory name is required");
                } else {
                    delete(args);
                }
                break;
            case "rename":
                // Split args into old name and new name
                String[] nameParts = args.split("\\s+", 2);
                if (nameParts.length < 2) {
                    System.out.println("Error: Both old and new names are required");
                } else {
                    rename(nameParts[0], nameParts[1]);
                }
                break;
            case "find":
                if (args.isEmpty()) {
                    System.out.println("Error: Search pattern is required");
                } else {
                    findFiles(args);
                }
                break;
            case "info":
                if (args.isEmpty()) {
                    System.out.println("Error: File name is required");
                } else {
                    displayFileInfo(args);
                }
                break;
            case "exit":
                return false;
            default:
                System.out.println("Unknown command: " + commandName);
                System.out.println("Type 'help' for available commands.");
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
        // Get the list of files and directories in the current directory
        File[] files = currentDirectory.listFiles();
        
        // Check if the directory is empty or cannot be accessed
        if (files == null || files.length == 0) {
            System.out.println("Directory is empty or cannot be accessed.");
            return;
        }
        
        // Display the list of files and directories
        System.out.println("Contents of " + currentDirectory.getAbsolutePath() + ":");
        System.out.println("Type | Size (bytes) | Last Modified       | Name");
        System.out.println("-------------------------------------------------");
        
        for (File file : files) {
            // Show 'd' if it's a directory or '-' if it's a file
            char type = file.isDirectory() ? 'd' : '-';
            
            // Format the last modified date
            String lastModified = dateFormat.format(new Date(file.lastModified()));
            
            // Display file information
            System.out.printf(" %c   | %11d | %s | %s%n", 
                    type, file.length(), lastModified, file.getName());
        }
    }
    
    /**
     * Change to a different directory
     * 
     * @param dirName The name of the directory to change to
     */
    private void changeDirectory(String dirName) {
        if (dirName.isEmpty()) {
            // If no directory is specified, don't change directory
            System.out.println("Current directory: " + currentDirectory.getAbsolutePath());
            return;
        }
        
        if (dirName.equals("..")) {
            // Go to parent directory
            File parent = currentDirectory.getParentFile();
            if (parent != null) {
                currentDirectory = parent;
            } else {
                System.out.println("Already at root directory.");
            }
        } else {
            // Change to the specified directory
            File newDir = new File(currentDirectory, dirName);
            
            if (newDir.exists() && newDir.isDirectory()) {
                currentDirectory = newDir;
            } else {
                System.out.println("Error: Directory does not exist: " + dirName);
            }
        }
    }
    
    /**
     * Create a new directory
     * 
     * @param dirName The name of the directory to create
     */
    private void createDirectory(String dirName) {
        File newDir = new File(currentDirectory, dirName);
        
        if (newDir.exists()) {
            System.out.println("Error: A file or directory with that name already exists.");
            return;
        }
        
        boolean created = newDir.mkdir();
        if (created) {
            System.out.println("Directory created: " + dirName);
        } else {
            System.out.println("Error: Failed to create directory: " + dirName);
        }
    }
    
    /**
     * Create a new file
     * 
     * @param fileName The name of the file to create
     */
    private void createFile(String fileName) {
        File newFile = new File(currentDirectory, fileName);
        
        if (newFile.exists()) {
            System.out.println("Error: A file or directory with that name already exists.");
            return;
        }
        
        try {
            boolean created = newFile.createNewFile();
            if (created) {
                System.out.println("File created: " + fileName);
            } else {
                System.out.println("Error: Failed to create file: " + fileName);
            }
        } catch (IOException e) {
            System.out.println("Error creating file: " + e.getMessage());
        }
    }
    
    /**
     * Delete a file or directory
     * 
     * @param name The name of the file or directory to delete
     */
    private void delete(String name) {
        File fileToDelete = new File(currentDirectory, name);
        
        if (!fileToDelete.exists()) {
            System.out.println("Error: File or directory does not exist: " + name);
            return;
        }
        
        if (fileToDelete.isDirectory()) {
            // Check if directory is empty
            File[] contents = fileToDelete.listFiles();
            if (contents != null && contents.length > 0) {
                System.out.println("Warning: Directory is not empty. Delete anyway? (y/n)");
                String response = scanner.nextLine().trim().toLowerCase();
                
                if (!response.equals("y")) {
                    System.out.println("Deletion cancelled.");
                    return;
                }
                
                // Recursively delete directory contents
                boolean allDeleted = deleteRecursively(fileToDelete);
                if (allDeleted) {
                    System.out.println("Directory and its contents deleted: " + name);
                } else {
                    System.out.println("Error: Failed to delete some contents of directory: " + name);
                }
                return;
            }
        }
        
        boolean deleted = fileToDelete.delete();
        if (deleted) {
            System.out.println((fileToDelete.isDirectory() ? "Directory" : "File") + " deleted: " + name);
        } else {
            System.out.println("Error: Failed to delete " + 
                    (fileToDelete.isDirectory() ? "directory" : "file") + ": " + name);
        }
    }
    
    /**
     * Recursively delete a directory and its contents
     * 
     * @param directory The directory to delete
     * @return true if successful, false otherwise
     */
    private boolean deleteRecursively(File directory) {
        File[] contents = directory.listFiles();
        if (contents != null) {
            for (File file : contents) {
                if (file.isDirectory()) {
                    deleteRecursively(file);
                } else {
                    file.delete();
                }
            }
        }
        return directory.delete();
    }
    
    /**
     * Rename a file or directory
     * 
     * @param oldName The current name of the file or directory
     * @param newName The new name for the file or directory
     */
    private void rename(String oldName, String newName) {
        File oldFile = new File(currentDirectory, oldName);
        File newFile = new File(currentDirectory, newName);
        
        if (!oldFile.exists()) {
            System.out.println("Error: File or directory does not exist: " + oldName);
            return;
        }
        
        if (newFile.exists()) {
            System.out.println("Error: A file or directory with the new name already exists: " + newName);
            return;
        }
        
        boolean renamed = oldFile.renameTo(newFile);
        if (renamed) {
            System.out.println((oldFile.isDirectory() ? "Directory" : "File") + 
                    " renamed from " + oldName + " to " + newName);
        } else {
            System.out.println("Error: Failed to rename " + 
                    (oldFile.isDirectory() ? "directory" : "file") + ".");
        }
    }
    
    /**
     * Search for files matching a pattern
     * 
     * @param pattern The pattern to search for
     */
    private void findFiles(String pattern) {
        System.out.println("Searching for files matching pattern: " + pattern);
        int count = findFilesRecursively(currentDirectory, pattern, 0);
        System.out.println("Found " + count + " matching files/directories.");
    }
    
    /**
     * Recursively search for files matching a pattern
     * 
     * @param directory The directory to search in
     * @param pattern The pattern to search for
     * @param currentDepth The current recursion depth
     * @return The number of matching files found
     */
    private int findFilesRecursively(File directory, String pattern, int currentDepth) {
        int count = 0;
        File[] files = directory.listFiles();
        
        if (files == null) {
            return 0;
        }
        
        for (File file : files) {
            // Check if the file name matches the pattern
            if (file.getName().contains(pattern)) {
                // Print indentation based on depth
                for (int i = 0; i < currentDepth; i++) {
                    System.out.print("  ");
                }
                
                // Print file information
                System.out.println(file.isDirectory() ? "[Dir] " : "[File] " + file.getName());
                count++;
            }
            
            // Recursively search in subdirectories
            if (file.isDirectory()) {
                count += findFilesRecursively(file, pattern, currentDepth + 1);
            }
        }
        
        return count;
    }
    
    /**
     * Display detailed information about a file
     * 
     * @param fileName The name of the file to display information for
     */
    private void displayFileInfo(String fileName) {
        File file = new File(currentDirectory, fileName);
        
        if (!file.exists()) {
            System.out.println("Error: File or directory does not exist: " + fileName);
            return;
        }
        
        System.out.println("Information for: " + file.getName());
        System.out.println("----------------------------------");
        System.out.println("Type:          " + (file.isDirectory() ? "Directory" : "File"));
        System.out.println("Path:          " + file.getAbsolutePath());
        System.out.println("Size:          " + file.length() + " bytes");
        System.out.println("Last Modified: " + dateFormat.format(new Date(file.lastModified())));
        System.out.println("Readable:      " + file.canRead());
        System.out.println("Writable:      " + file.canWrite());
        System.out.println("Executable:    " + file.canExecute());
        System.out.println("Hidden:        " + file.isHidden());
        
        if (file.isDirectory()) {
            File[] contents = file.listFiles();
            int fileCount = 0;
            int dirCount = 0;
            
            if (contents != null) {
                for (File child : contents) {
                    if (child.isDirectory()) {
                        dirCount++;
                    } else {
                        fileCount++;
                    }
                }
            }
            
            System.out.println("Content:       " + fileCount + " files, " + dirCount + " directories");
        }
    }
    
    /**
     * Main method
     */
    public static void main(String[] args) {
        FileSystemManager manager = new FileSystemManager();
        manager.start();
    }
}
