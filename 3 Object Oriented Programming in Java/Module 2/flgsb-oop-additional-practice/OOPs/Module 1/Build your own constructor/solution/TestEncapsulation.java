public class TestEncapsulation {
    public static void main(String[] args) {
        // Step 1: Create two instances of the Employee class
        // First employee using the parameterized constructor
        System.out.println("Creating first employee using constructor...");
        Employee employee1 = new Employee("John Doe", 30, 5000.0);
        
        // Second employee using default constructor and setters
        System.out.println("\nCreating second employee using setters...");
        Employee employee2 = new Employee();
        employee2.setName("Jane Smith");
        employee2.setAge(25);
        employee2.setSalary(5500.0);
        
        // Step 2: Display details of both employees
        System.out.println("\nInitial Employee Details:");
        employee1.displayDetails();
        employee2.displayDetails();
        
        // Step 3: Try setting invalid values
        System.out.println("Testing validation with invalid values:");
        employee1.setName("");  // Invalid: empty name
        employee1.setAge(70);   // Invalid: age outside range
        employee2.setSalary(-100); // Invalid: negative salary
        
        System.out.println("\nEmployee Details After Invalid Values:");
        employee1.displayDetails();
        employee2.displayDetails();
        
        // Step 4: Give both employees a 10% raise
        System.out.println("Giving both employees a 10% raise:");
        employee1.giveRaise(10);
        employee2.giveRaise(10);
        
        System.out.println("\nEmployee Details After Raise:");
        employee1.displayDetails();
        employee2.displayDetails();
        
        // Step 5: Clone the first employee
        System.out.println("\nCloning the first employee...");
        try {
            Employee clonedEmployee = (Employee) employee1.clone();
            System.out.println("Cloned Employee Details:");
            clonedEmployee.displayDetails();
            
            // Step 14: Modify the original employee to show clones are separate objects
            System.out.println("Modifying the original employee's salary...");
            employee1.setSalary(6000.0);
            
            System.out.println("\nOriginal Employee After Modification:");
            employee1.displayDetails();
            
            System.out.println("Cloned Employee After Original was Modified:");
            clonedEmployee.displayDetails();
        } catch (CloneNotSupportedException e) {
            System.out.println("Cloning not supported: " + e.getMessage());
        }
        
        // Step 6: Compare salaries
        String result = compareSalaries(employee1, employee2);
        System.out.println("Salary Comparison Result: " + result);
    }
    
    // Step 7: Create a method that compares the salaries of two employees
    // and returns the name of the employee with the higher salary
    // If salaries are equal, return "Equal salaries"// Method to compare salaries
    public static String compareSalaries(Employee emp1, Employee emp2) {
        if (emp1.getSalary() > emp2.getSalary()) {
            return emp1.getName() + " has a higher salary";
        } else if (emp2.getSalary() > emp1.getSalary()) {
            return emp2.getName() + " has a higher salary";
        } else {
            return "Equal salaries";
        }
    }
}
