import java.time.LocalDate;
import java.time.LocalTime;
import java.util.Objects;

public class Mood {
    private String name;
    private LocalDate date;
    private LocalTime time;
    private String notes;

    // Default values: today’s date, midnight time
    public Mood(String name) {
        this(name, LocalDate.now(), LocalTime.MIDNIGHT, "");
    }

    public Mood(String name, LocalDate date) {
        this(name, date, LocalTime.MIDNIGHT, "");
    }

    public Mood(String name, LocalDate date, LocalTime time) {
        this(name, date, time, "");
    }

    public Mood(String name, String notes) {
        this(name, LocalDate.now(), LocalTime.MIDNIGHT, notes);
    }

    public Mood(String name, LocalDate date, String notes) {
        this(name, date, LocalTime.MIDNIGHT, notes);
    }

    public Mood(String name, LocalDate date, LocalTime time, String notes) {
        this.name = name;
        this.date = date;
        this.time = time;
        this.notes = notes;
    }

    // Getters & Setters
    public String getName() { return name; }
    public void setName(String name) { this.name = name; }

    public LocalDate getDate() { return date; }
    public void setDate(LocalDate date) { this.date = date; }

    public LocalTime getTime() { return time; }
    public void setTime(LocalTime time) { this.time = time; }

    public String getNotes() { return notes; }
    public void setNotes(String notes) { this.notes = notes; }

    @Override
    public String toString() {
        return "Mood{name='" + name + "', date=" + date +
               ", time=" + time + ", notes='" + notes + "'}";
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Mood)) return false;
        Mood mood = (Mood) o;
        return Objects.equals(name, mood.name) &&
               Objects.equals(date, mood.date) &&
               Objects.equals(time, mood.time);
    }

    @Override
    public int hashCode() {
        return Objects.hash(name, date, time);
    }
}
