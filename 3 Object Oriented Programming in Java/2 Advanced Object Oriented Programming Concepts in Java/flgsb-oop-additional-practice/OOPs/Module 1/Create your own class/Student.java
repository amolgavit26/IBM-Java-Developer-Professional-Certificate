/*

// Student class to store and manage student information
public class Student {
    // Step 1: Declare private variables for studentId, name, grade, and isActive
    // Hint: Use appropriate data types (String, String, double, boolean)
    
    
    // Step 2: Create getter methods for each variable
    // Hint: Use the format: public returnType getVariableName()
    
    
    // Step 3: Create setter methods for each variable
    // Hint: Use the format: public void setVariableName(parameter)
    // Add simple validation:
    // - For grade: Ensure it is between 0 and 100
    // - For studentId: No special validation needed
    // - For name: No special validation needed
    // - For isActive: No special validation needed
    
    
    // Step 4: Create a method to display student details
    // Hint: Use System.out.println() to print all student information
    // Format should include ID, name, grade, and status (Active/Inactive)
    
    
    // Step 5: Create a method that returns a letter grade based on the numeric grade
    // Hint: A: 90-100, B: 80-89, C: 70-79, D: 60-69, F: below 60
    
    
    // Step 6: Create a method to check if the student is passing (grade >= 60)
    // Hint: Return a boolean value
}


*/





// Student class to store and manage student information
public class Student {
    // Step 1: Private variables for student data
    private String studentId;
    private String name;
    private double grade;
    private boolean isActive;
    
    // Step 2: Getter methods to access private variables
    public String getStudentId() {
        return this.studentId;
    }
    
    public String getName() {
        return this.name;
    }
    
    public double getGrade() {
        return this.grade;
    }
    
    public boolean getIsActive() {
        return this.isActive;
    }
    
    // Step 3: Setter methods with simple validation
    public void setStudentId(String studentId) {
        this.studentId = studentId;
    }
    
    public void setName(String name) {
        this.name = name;
    }
    
    public void setGrade(double grade) {
        // Validate grade is between 0 and 100
        if (grade >= 0 && grade <= 100) {
            this.grade = grade;
        } else {
            System.out.println("Error: Grade must be between 0 and 100");
        }
    }
    
    public void setIsActive(boolean isActive) {
        this.isActive = isActive;
    }
    
    // Step 4: Method to stringify student details
    public String displayStudentDetails() {
        return "ID: " + this.studentId +
                "\nName: " + this.name + 
                " Grade: " + this.grade +
                "\nStatus: " + (this.isActive ? "Active" : "Inactive");
    }
    
    // Step 5: Method to calculate letter grade
    public String getLetterGrade() {
        if (this.grade >= 90) {
            return "A";
        } else if (this.grade >= 80) {
            return "B";
        } else if (this.grade >= 70) {
            return "C";
        } else if (this.grade >= 60) {
            return "D";
        } else {
            return "F";
        }
    }
    
    // Step 6: Method to check if student is passing
    public boolean isPassing() {
        return this.grade >= 60;
    }
}