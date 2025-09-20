/*


// StudentRegistry class to test the Student class
public class StudentRegistry {
    public static void main(String[] args) {
        // Step 1: Create two instances of the Student class
        
        
        // Step 2: Use setter methods to set values for all attributes of first student
        // Example values: ID "S001", name "John Doe", grade 85.5, active true
        
        
        // Step 3: Set values for second student
        // Example values: ID "S002", name "Jane Smith", grade 92.0, active true
        
        
        // Step 4: Display details of both students
        
        
        // Step 5: Compare the grades of the two students and print who has the higher grade
        // Hint: Create a separate method for this comparison
        
        
        // Step 6: Test the letter grade method for both students
        
        
        // Step 7: Test the passing status method for both students
        
        
        // Step 8: Change one student to inactive and display the updated information
    }
    
    // Step 9: Create a static method to compare two students' grades and return the student with the higher grade
    // Hint: Take two Student objects as parameters
}



*/


// StudentRegistry class to test the Student class
public class StudentRegistry {
    public static void main(String[] args) {
        // Step 1: Create two Student instances
        Student student1 = new Student();
        Student student2 = new Student();
        
        // Step 2: Set values for first student
        student1.setStudentId("S001");
        student1.setName("John Doe");
        student1.setGrade(85.5);
        student1.setIsActive(true);
        
        // Step 3: Set values for second student
        student2.setStudentId("S002");
        student2.setName("Jane Smith");
        student2.setGrade(92.0);
        student2.setIsActive(true);
        
        // Step 4: Display details of both students
        System.out.println("Displaying information for both students:");
        System.out.println(student1);
        System.out.println(student2);
        
        // Step 5: Compare grades and print who has the higher grade
        Student higherGradeStudent = compareGrades(student1, student2);
        System.out.println("\nComparing grades:");
        System.out.println(higherGradeStudent.getName() + " has the higher grade with " + 
                          higherGradeStudent.getGrade());
        
        // Step 6: Test letter grades
        System.out.println("\nLetter Grades:");
        System.out.println(student1.getName() + "'s letter grade: " + student1.getLetterGrade());
        System.out.println(student2.getName() + "'s letter grade: " + student2.getLetterGrade());
        
        // Step 7: Test passing status
        System.out.println("\nPassing Status:");
        System.out.println(student1.getName() + " is " + 
                          (student1.isPassing() ? "passing" : "not passing"));
        System.out.println(student2.getName() + " is " + 
                          (student2.isPassing() ? "passing" : "not passing"));
        
        // Step 8: Change one student to inactive
        System.out.println("\nChanging " + student1.getName() + "'s status to inactive");
        student1.setIsActive(false);
        System.out.println(student1);
        
        // Extra: Test invalid grade
        System.out.println("\nTesting grade validation:");
        student1.setGrade(105); // Should print error message
    }
    
    // Step 9: Static method to compare grades
    public static Student compareGrades(Student student1, Student student2) {
        if (student1.getGrade() > student2.getGrade()) {
            return student1;
        } else {
            return student2;
        }
    }
}