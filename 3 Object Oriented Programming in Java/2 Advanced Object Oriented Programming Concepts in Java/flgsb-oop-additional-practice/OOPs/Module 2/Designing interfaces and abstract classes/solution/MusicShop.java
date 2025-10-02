// Step 1: Create an abstract class Instrument
abstract class Instrument {
    // Private and protected fields
    private String name;
    protected int year;
    
    // Constructor
    public Instrument(String name, int year) {
        this.name = name;
        this.year = year;
    }
    
    // Abstract method that child classes must implement
    public abstract String play();
    
    // Concrete method that all child classes inherit
    public String getInstrumentDetails() {
        return "Instrument: " + name + ", Year: " + year;
    }
    
    // Getter for name
    public String getName() {
        return name;
    }
}

// Step 2: Create an interface Tunable
interface Tunable {
    // Abstract methods - no implementation
    String tune();
    String adjustPitch(boolean up);
}

// Step 3: Create an interface Maintainable
interface Maintainable {
    // Abstract methods - no implementation
    String clean();
    String inspect();
}

// Step 4: Create a concrete class StringedInstrument that extends Instrument
class StringedInstrument extends Instrument {
    // Additional field for this specific type
    private int numberOfStrings;
    
    // Constructor
    public StringedInstrument(String name, int year, int numberOfStrings) {
        super(name, year); // Call parent constructor
        this.numberOfStrings = numberOfStrings;
    }
    
    // Implementation of abstract method from parent
    @Override
    public String play() {
        return "Playing the stringed instrument";
    }
    
    // Override parent method to add additional information
    @Override
    public String getInstrumentDetails() {
        return super.getInstrumentDetails() + ", Strings: " + numberOfStrings;
    }
    
    // Getter for number of strings
    public int getNumberOfStrings() {
        return numberOfStrings;
    }
}

// Step 5: Create a concrete class Guitar that extends StringedInstrument and implements interfaces
class Guitar extends StringedInstrument implements Tunable, Maintainable {
    // Additional field specific to Guitar
    private String guitarType;
    
    // Constructor
    public Guitar(String name, int year, int numberOfStrings, String guitarType) {
        super(name, year, numberOfStrings); // Call parent constructor
        this.guitarType = guitarType;
    }
    
    // Override play method to be more specific
    @Override
    public String play() {
        return "Playing the " + guitarType + " guitar with " + getNumberOfStrings() + " strings";
    }
    
    // Override parent method to add more details
    @Override
    public String getInstrumentDetails() {
        return super.getInstrumentDetails() + ", Type: " + guitarType;
    }
    
    // Implement methods from Tunable interface
    @Override
    public String tune() {
        return "Tuning the " + guitarType + " guitar";
    }
    
    @Override
    public String adjustPitch(boolean up) {
        return up ? "Increasing pitch of the guitar" : "Decreasing pitch of the guitar";
    }
    
    // Implement methods from Maintainable interface
    @Override
    public String clean() {
        return "Cleaning the " + guitarType + " guitar";
    }
    
    @Override
    public String inspect() {
        return "Inspecting the " + guitarType + " guitar from " + year;
    }
}

// Step 6: Create a concrete class Piano that extends Instrument and implements interfaces
class Piano extends Instrument implements Tunable, Maintainable {
    // Additional field specific to Piano
    private boolean isGrand;
    
    // Constructor
    public Piano(String name, int year, boolean isGrand) {
        super(name, year);
        this.isGrand = isGrand;
    }
    
    // Implementation of abstract method from parent
    @Override
    public String play() {
        return "Playing the " + (isGrand ? "grand" : "upright") + " piano";
    }
    
    // Override parent method to add more details
    @Override
    public String getInstrumentDetails() {
        return super.getInstrumentDetails() + ", Type: " + (isGrand ? "Grand" : "Upright");
    }
    
    // Implement methods from Tunable interface
    @Override
    public String tune() {
        return "Tuning the piano";
    }
    
    @Override
    public String adjustPitch(boolean up) {
        return up ? "Increasing pitch of the piano" : "Decreasing pitch of the piano";
    }
    
    // Implement methods from Maintainable interface
    @Override
    public String clean() {
        return "Cleaning the piano keys and interior";
    }
    
    @Override
    public String inspect() {
        return "Inspecting the " + (isGrand ? "grand" : "upright") + " piano from " + year;
    }
}

// Step 7: Create a class to test everything
public class MusicShop {
    public static void main(String[] args) {
        // Create an array of Instrument objects
        Instrument[] instruments = new Instrument[3];
        instruments[0] = new Guitar("Fender Stratocaster", 2020, 6, "electric");
        instruments[1] = new Piano("Steinway", 2015, true);
        instruments[2] = new Guitar("Martin", 2018, 12, "acoustic");
        
        // Demonstrate polymorphism by calling play() for each instrument
        System.out.println("===== PLAYING INSTRUMENTS =====");
        for (Instrument instrument : instruments) {
            System.out.println(instrument.play());
            System.out.println(instrument.getInstrumentDetails());
        }
        
        // Demonstrate interface type checking and method calling
        System.out.println("\n===== MAINTAINING INSTRUMENTS =====");
        for (Instrument instrument : instruments) {
            System.out.println("Working with: " + instrument.getName());
            
            // Check if this instrument is Tunable
            if (instrument instanceof Tunable) {
                Tunable tunableInstrument = (Tunable) instrument;
                System.out.println(tunableInstrument.tune());
                System.out.println(tunableInstrument.adjustPitch(true));
            }
            
            // Check if this instrument is Maintainable
            if (instrument instanceof Maintainable) {
                Maintainable maintainableInstrument = (Maintainable) instrument;
                System.out.println(maintainableInstrument.clean());
                System.out.println(maintainableInstrument.inspect());
            }
            
            System.out.println("-----");
        }
    }
}
