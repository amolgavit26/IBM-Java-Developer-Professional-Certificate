// Rectangle class - a subclass of Shape
public class Rectangle extends Shape {
    // Step 1: Declare private variables for length and width
    private double length;
    private double width;
    
    // Step 2: Create a constructor that takes name, color, length, and width as parameters
    public Rectangle(String name, String color, double length, double width) {
        // Call the parent constructor to initialize name and color
        super(name, color);
        this.length = length;
        this.width = width;
    }
    
    // Step 3: Create getter methods for length and width
    public double getLength() {
        return length;
    }
    
    public double getWidth() {
        return width;
    }
    
    // Step 4: Override the area() method to calculate the area of a rectangle
    @Override
    public double area() {
        // Area of a rectangle = length * width
        return length * width;
    }
    
    // Step 5: Override the perimeter() method to calculate the perimeter of a rectangle
    @Override
    public double perimeter() {
        // Perimeter of a rectangle = 2 * (length + width)
        return 2 * (length + width);
    }
    
    // Step 6: Override toString() method to include rectangle-specific information
    @Override
    public String toString() {
        return super.toString() + ", Shape Type: Rectangle, Length: " + length + ", Width: " + width;
    }
}
