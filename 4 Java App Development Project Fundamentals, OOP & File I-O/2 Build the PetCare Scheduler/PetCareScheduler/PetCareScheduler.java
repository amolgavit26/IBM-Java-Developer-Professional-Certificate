import java.io.*;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.time.Duration;
import java.util.*;

/**
 * Console application to manage pets and appointments.
 */
public class PetCareScheduler {
    private static final String DATA_FILE = "pets.ser";
    private static final DateTimeFormatter INPUT_DT_FORMAT = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm");
    private static final Scanner scanner = new Scanner(System.in);
    private static Map<String, Pet> pets = new HashMap<>();

    // Allowed appointment types (case-insensitive check)
    private static final Set<String> ALLOWED_TYPES = new HashSet<>(Arrays.asList(
            "vet visit", "vaccination", "grooming", "checkup", "other"
    ));

    public static void main(String[] args) {
        loadPetsFromFile();
        boolean running = true;

        while (running) {
            printMenu();
            String choice = scanner.nextLine().trim();
            switch (choice) {
                case "1": registerPet(); break;
                case "2": scheduleAppointment(); break;
                case "3": displayAllPets(); break;
                case "4": displayAppointmentsForPet(); break;
                case "5": displayUpcomingAppointments(); break;
                case "6": displayPastAppointmentsForPet(); break;
                case "7": generateReports(); break;
                case "8":
                    savePetsToFile();
                    System.out.println("Data saved. Exiting.");
                    running = false;
                    break;
                default:
                    System.out.println("Invalid selection. Please choose 1-8.");
            }
        }
        scanner.close();
    }

    private static void printMenu() {
        System.out.println("\n=== PetCare Scheduler ===");
        System.out.println("1. Register Pet");
        System.out.println("2. Schedule Appointment");
        System.out.println("3. Display All Registered Pets");
        System.out.println("4. Display Appointments for a Pet");
        System.out.println("5. Display Upcoming Appointments (All Pets)");
        System.out.println("6. Display Past Appointments for a Pet");
        System.out.println("7. Generate Reports");
        System.out.println("8. Save & Exit");
        System.out.print("Choose an option: ");
    }

    private static void registerPet() {
        System.out.print("Enter unique Pet ID: ");
        String id = scanner.nextLine().trim();
        if (id.isEmpty()) {
            System.out.println("Pet ID cannot be empty.");
            return;
        }
        if (pets.containsKey(id)) {
            System.out.println("A pet with that ID already exists.");
            return;
        }

        System.out.print("Enter pet name: ");
        String name = scanner.nextLine().trim();
        System.out.print("Enter species/breed: ");
        String species = scanner.nextLine().trim();
        int age = readPositiveInt("Enter pet age (years): ");
        System.out.print("Enter owner name: ");
        String owner = scanner.nextLine().trim();
        System.out.print("Enter contact info: ");
        String contact = scanner.nextLine().trim();

        Pet pet = new Pet(id, name, species, age, owner, contact);
        pets.put(id, pet);
        System.out.println("Pet registered successfully on " + pet.getRegistrationDate());
    }

    private static int readPositiveInt(String prompt) {
        while (true) {
            System.out.print(prompt);
            String s = scanner.nextLine().trim();
            try {
                int val = Integer.parseInt(s);
                if (val < 0) {
                    System.out.println("Value must be zero or positive.");
                } else {
                    return val;
                }
            } catch (NumberFormatException e) {
                System.out.println("Please enter a valid integer.");
            }
        }
    }

    private static void scheduleAppointment() {
        System.out.print("Enter Pet ID to schedule appointment for: ");
        String id = scanner.nextLine().trim();
        Pet pet = pets.get(id);
        if (pet == null) {
            System.out.println("Pet ID not found.");
            return;
        }

        System.out.println("Allowed appointment types: " + ALLOWED_TYPES);
        System.out.print("Enter appointment type: ");
        String type = scanner.nextLine().trim();
        if (!isValidType(type)) {
            System.out.println("Invalid appointment type. Please choose one of: " + ALLOWED_TYPES);
            return;
        }

        LocalDateTime dt = null;
        while (true) {
            System.out.print("Enter date & time (format: yyyy-MM-dd HH:mm) e.g. 2025-09-30 14:30: ");
            String dtInput = scanner.nextLine().trim();
            try {
                dt = LocalDateTime.parse(dtInput, INPUT_DT_FORMAT);
                if (!dt.isAfter(LocalDateTime.now())) {
                    System.out.println("Appointment must be scheduled for a future date/time.");
                    continue;
                }
                break;
            } catch (DateTimeParseException ex) {
                System.out.println("Invalid date/time format. Please use yyyy-MM-dd HH:mm");
            }
        }

        System.out.print("Notes (optional, press Enter to skip): ");
        String notes = scanner.nextLine().trim();

        Appointment appt = new Appointment(type, dt, notes);
        pet.addAppointment(appt);
        System.out.println("Appointment scheduled for " + pet.getName() + " at " + dt.format(INPUT_DT_FORMAT));
    }

    private static boolean isValidType(String type) {
        if (type == null || type.trim().isEmpty()) return false;
        String normalized = type.trim().toLowerCase();
        for (String allowed : ALLOWED_TYPES) {
            if (allowed.equalsIgnoreCase(normalized)) return true;
        }
        return false;
    }

    private static void displayAllPets() {
        if (pets.isEmpty()) {
            System.out.println("No pets registered.");
            return;
        }
        System.out.println("\nRegistered Pets:");
        for (Pet p : pets.values()) {
            System.out.println(p);
        }
    }

    private static void displayAppointmentsForPet() {
        System.out.print("Enter Pet ID: ");
        String id = scanner.nextLine().trim();
        Pet pet = pets.get(id);
        if (pet == null) {
            System.out.println("Pet not found.");
            return;
        }
        List<Appointment> list = pet.getAppointments();
        if (list.isEmpty()) {
            System.out.println("No appointments for this pet.");
            return;
        }
        System.out.println("\nAppointments for " + pet.getName() + ":");
        list.sort(Comparator.comparing(Appointment::getDateTime));
        for (Appointment a : list) {
            System.out.println(a);
            System.out.println("-----");
        }
    }

    private static void displayUpcomingAppointments() {
        LocalDateTime now = LocalDateTime.now();
        List<AppointmentEntry> upcoming = new ArrayList<>();
        for (Pet p : pets.values()) {
            for (Appointment a : p.getAppointments()) {
                if (a.getDateTime().isAfter(now)) {
                    upcoming.add(new AppointmentEntry(p, a));
                }
            }
        }
        if (upcoming.isEmpty()) {
            System.out.println("No upcoming appointments.");
            return;
        }
        upcoming.sort(Comparator.comparing(e -> e.appointment.getDateTime()));
        System.out.println("\nUpcoming Appointments:");
        for (AppointmentEntry e : upcoming) {
            System.out.println("Pet ID: " + e.pet.getId() + ", Name: " + e.pet.getName());
            System.out.println(e.appointment);
            System.out.println("-----");
        }
    }

    private static void displayPastAppointmentsForPet() {
        System.out.print("Enter Pet ID: ");
        String id = scanner.nextLine().trim();
        Pet pet = pets.get(id);
        if (pet == null) {
            System.out.println("Pet not found.");
            return;
        }
        LocalDateTime now = LocalDateTime.now();
        List<Appointment> past = new ArrayList<>();
        for (Appointment a : pet.getAppointments()) {
            if (!a.getDateTime().isAfter(now)) {
                past.add(a);
            }
        }
        if (past.isEmpty()) {
            System.out.println("No past appointments for this pet.");
            return;
        }
        past.sort(Comparator.comparing(Appointment::getDateTime).reversed());
        System.out.println("\nPast Appointments for " + pet.getName() + ":");
        for (Appointment a : past) {
            System.out.println(a);
            System.out.println("-----");
        }
    }

    private static void generateReports() {
        System.out.println("\n=== Reports ===");
        petsWithAppointmentsNextWeek();
        petsOverdueForVetVisit();
    }

    /**
     * Pets with upcoming appointments in the next 7 days.
     */
    private static void petsWithAppointmentsNextWeek() {
        LocalDateTime now = LocalDateTime.now();
        LocalDateTime weekLater = now.plusDays(7);
        Set<String> petIds = new HashSet<>();

        for (Pet p : pets.values()) {
            for (Appointment a : p.getAppointments()) {
                if (!a.getDateTime().isBefore(now) && !a.getDateTime().isAfter(weekLater)) {
                    petIds.add(p.getId());
                    break;
                }
            }
        }

        System.out.println("\nPets with appointments in the next 7 days:");
        if (petIds.isEmpty()) {
            System.out.println("None.");
        } else {
            for (String id : petIds) {
                Pet p = pets.get(id);
                System.out.println(p);
            }
        }
    }

    /**
     * Pets overdue for a vet visit: no vet visit in last 6 months.
     */
    private static void petsOverdueForVetVisit() {
        LocalDateTime sixMonthsAgo = LocalDateTime.now().minusMonths(6);
        List<Pet> overdue = new ArrayList<>();

        for (Pet p : pets.values()) {
            Appointment lastVet = p.getLastVetVisit(); // checks type contains "vet"
            if (lastVet == null || lastVet.getDateTime().isBefore(sixMonthsAgo)) {
                overdue.add(p);
            }
        }

        System.out.println("\nPets overdue for a vet visit (no 'vet' appointment in last 6 months):");
        if (overdue.isEmpty()) {
            System.out.println("None.");
        } else {
            for (Pet p : overdue) {
                System.out.println(p);
            }
        }
    }

    private static void savePetsToFile() {
        try (ObjectOutputStream out = new ObjectOutputStream(new FileOutputStream(DATA_FILE))) {
            out.writeObject(pets);
            System.out.println("Saved " + pets.size() + " pets to file.");
        } catch (IOException e) {
            System.out.println("Error saving data: " + e.getMessage());
        }
    }

    @SuppressWarnings("unchecked")
    private static void loadPetsFromFile() {
        File f = new File(DATA_FILE);
        if (!f.exists()) {
            System.out.println("No saved data found. Starting fresh.");
            return;
        }
        try (ObjectInputStream in = new ObjectInputStream(new FileInputStream(f))) {
            Object obj = in.readObject();
            if (obj instanceof Map) {
                pets = (Map<String, Pet>) obj;
                System.out.println("Loaded " + pets.size() + " pets from file.");
            } else {
                System.out.println("Saved data has unexpected format. Starting fresh.");
            }
        } catch (IOException | ClassNotFoundException e) {
            System.out.println("Error loading data: " + e.getMessage());
        }
    }

    // Helper class for listing appointments with pet reference
    private static class AppointmentEntry {
        Pet pet;
        Appointment appointment;
        AppointmentEntry(Pet pet, Appointment appointment) {
            this.pet = pet;
            this.appointment = appointment;
        }
    }
}
