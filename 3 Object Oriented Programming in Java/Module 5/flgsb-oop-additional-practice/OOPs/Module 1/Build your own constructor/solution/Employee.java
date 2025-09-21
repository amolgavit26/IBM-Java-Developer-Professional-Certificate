// Employee class to demonstrate encapsulation
public class Employee implements Cloneable {
    // Step 1: Declare private variables for name, age, and salary
    private String name;
    private int age;
    private double salary;
    
    // Step 2: Create constructors
    // 2.1: Default constructor
    public Employee() {
        this.name = "Unknown";
        this.age = 18;
        this.salary = 0.0;
    }
    
    // 2.2: Overloaded constructor that initializes all three variables
    public Employee(String name, int age, double salary) {
        // Use setters to ensure validation rules are applied
        setName(name);
        setAge(age);
        setSalary(salary);
    }
    
    // Step 3: Create public getter methods for each variable
    public String getName() {
        return this.name;
    }
    
    public int getAge() {
        return this.age;
    }
    
    public double getSalary() {
        return this.salary;
    }
    
    // Step 4: Create public setter methods with validation
    public void setName(String name) {
        // Validation: name cannot be null or empty
        if (name != null && !name.trim().isEmpty()) {
            this.name = name;
        } else {
            System.out.println("Error: Name cannot be null or empty. Setting to 'Unknown'");
            this.name = "Unknown";
        }
    }
    
    public void setAge(int age) {
        // Validation: age must be between 18 and 65 (inclusive)
        if (age >= 18 && age <= 65) {
            this.age = age;
        } else {
            System.out.println("Error: Age must be between 18 and 65. Setting to default (18)");
            this.age = 18;
        }
    }
    
    public void setSalary(double salary) {
        // Validation: salary must be non-negative
        if (salary >= 0) {
            this.salary = salary;
        } else {
            System.out.println("Error: Salary cannot be negative. Setting to 0");
            this.salary = 0;
        }
    }
    
    // Step 5: Create a method to calculate annual salary
    public double calculateAnnualSalary() {
        return this.salary * 12;
    }
    
    // Step 6: Create a method to give a raise
    public void giveRaise(double percentage) {
        if (percentage > 0) {
            double raiseAmount = this.salary * (percentage / 100);
            this.salary += raiseAmount;
            System.out.println(this.name + " received a raise of " + percentage + "%");
        } else {
            System.out.println("Raise percentage must be positive");
        }
    }
    
    // Step 7: Create a method to display employee details
    public void displayDetails() {
        System.out.println("Employee Details:");
        System.out.println("Name: " + this.name);
        System.out.println("Age: " + this.age);
        System.out.println("Monthly Salary: $" + String.format("%.2f", this.salary));
        System.out.println("Annual Salary: $" + String.format("%.2f", calculateAnnualSalary()));
        System.out.println("---------------------------");
    }
    
    // Step 8: Override the clone method to make Employee objects cloneable
    @Override
    protected Object clone() throws CloneNotSupportedException {
        // Call the superclass clone method to create a shallow copy
        return super.clone();
    }
}
