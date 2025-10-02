import java.util.*;
import java.io.*;
import java.time.*;

public class MoodTracker {
    private static ArrayList<Mood> moods = new ArrayList<>();
    private static Scanner scanner = new Scanner(System.in);

    public static void main(String[] args) {
        while (true) {
            System.out.println("\n=== Mood Tracker Menu ===");
            System.out.println("1. Add a Mood");
            System.out.println("2. Delete Moods");
            System.out.println("3. Edit a Mood");
            System.out.println("4. Search Moods");
            System.out.println("5. Show All Moods");
            System.out.println("6. Save to File");
            System.out.println("7. Exit");
            System.out.print("Choose an option: ");

            String choice = scanner.nextLine();

            switch (choice) {
                case "1": addMood(); break;
                case "2": deleteMood(); break;
                case "3": editMood(); break;
                case "4": searchMood(); break;
                case "5": showAllMoods(); break;
                case "6": saveToFile(); break;
                case "7": System.out.println("Goodbye!"); return;
                default: System.out.println("Invalid choice. Try again.");
            }
        }
    }

    private static void addMood() {
        try {
            System.out.print("Enter mood name: ");
            String name = scanner.nextLine();

            System.out.print("Enter date (yyyy-mm-dd) or press Enter for today: ");
            String dateInput = scanner.nextLine();
            LocalDate date = dateInput.isEmpty() ? LocalDate.now() : LocalDate.parse(dateInput);

            System.out.print("Enter time (HH:mm) or press Enter for midnight: ");
            String timeInput = scanner.nextLine();
            LocalTime time = timeInput.isEmpty() ? LocalTime.MIDNIGHT : LocalTime.parse(timeInput);

            System.out.print("Enter notes (optional): ");
            String notes = scanner.nextLine();

            Mood newMood = new Mood(name, date, time, notes);

            if (moods.contains(newMood)) {
                throw new InvalidMoodException("Mood already exists for this date and time!");
            }

            moods.add(newMood);
            System.out.println("Mood added successfully!");

        } catch (InvalidMoodException e) {
            System.out.println("Error: " + e.getMessage());
        } catch (Exception e) {
            System.out.println("Invalid input. Please try again.");
        }
    }

    private static void deleteMood() {
        System.out.println("Delete by: 1) Date  2) Name+Date+Time");
        String option = scanner.nextLine();

        if ("1".equals(option)) {
            System.out.print("Enter date (yyyy-mm-dd): ");
            LocalDate date = LocalDate.parse(scanner.nextLine());
            moods.removeIf(m -> m.getDate().equals(date));
            System.out.println("Deleted moods on " + date);
        } else if ("2".equals(option)) {
            System.out.print("Enter mood name: ");
            String name = scanner.nextLine();
            System.out.print("Enter date (yyyy-mm-dd): ");
            LocalDate date = LocalDate.parse(scanner.nextLine());
            System.out.print("Enter time (HH:mm): ");
            LocalTime time = LocalTime.parse(scanner.nextLine());

            Mood target = new Mood(name, date, time, "");
            if (moods.remove(target)) {
                System.out.println("Mood deleted successfully.");
            } else {
                System.out.println("Mood not found.");
            }
        }
    }

    private static void editMood() {
        System.out.print("Enter mood name: ");
        String name = scanner.nextLine();
        System.out.print("Enter date (yyyy-mm-dd): ");
        LocalDate date = LocalDate.parse(scanner.nextLine());
        System.out.print("Enter time (HH:mm): ");
        LocalTime time = LocalTime.parse(scanner.nextLine());

        for (Mood m : moods) {
            if (m.getName().equals(name) && m.getDate().equals(date) && m.getTime().equals(time)) {
                System.out.print("Enter new notes: ");
                m.setNotes(scanner.nextLine());
                System.out.println("Notes updated!");
                return;
            }
        }
        System.out.println("Mood not found.");
    }

    private static void searchMood() {
        System.out.println("Search by: 1) Date  2) Name+Date+Time");
        String option = scanner.nextLine();

        if ("1".equals(option)) {
            System.out.print("Enter date (yyyy-mm-dd): ");
            LocalDate date = LocalDate.parse(scanner.nextLine());
            for (Mood m : moods) {
                if (m.getDate().equals(date)) {
                    System.out.println(m);
                }
            }
        } else if ("2".equals(option)) {
            System.out.print("Enter mood name: ");
            String name = scanner.nextLine();
            System.out.print("Enter date (yyyy-mm-dd): ");
            LocalDate date = LocalDate.parse(scanner.nextLine());
            System.out.print("Enter time (HH:mm): ");
            LocalTime time = LocalTime.parse(scanner.nextLine());

            Mood target = new Mood(name, date, time, "");
            int index = moods.indexOf(target);
            if (index != -1) {
                System.out.println(moods.get(index));
            } else {
                System.out.println("Mood not found.");
            }
        }
    }

    private static void showAllMoods() {
        if (moods.isEmpty()) {
            System.out.println("No moods tracked yet.");
        } else {
            for (Mood m : moods) {
                System.out.println(m);
            }
        }
    }

    private static void saveToFile() {
        try (PrintWriter writer = new PrintWriter(new FileWriter("moodtracker.txt"))) {
            for (Mood m : moods) {
                writer.println(m);
            }
            System.out.println("Moods saved to moodtracker.txt");
        } catch (IOException e) {
            System.out.println("Error saving file: " + e.getMessage());
        }
    }
}
