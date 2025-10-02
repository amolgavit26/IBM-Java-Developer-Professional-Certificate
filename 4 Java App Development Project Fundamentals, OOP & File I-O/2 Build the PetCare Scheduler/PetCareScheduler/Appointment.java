import java.io.Serializable;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Objects;

/**
 * Represents an appointment for a pet (vet visit, vaccination, grooming, etc.).
 */
public class Appointment implements Serializable {
    private static final long serialVersionUID = 1L;

    private String type;
    private LocalDateTime dateTime;
    private String notes;

    public Appointment(String type, LocalDateTime dateTime, String notes) {
        this.type = type;
        this.dateTime = dateTime;
        this.notes = (notes == null) ? "" : notes;
    }

    public String getType() {
        return type;
    }

    public LocalDateTime getDateTime() {
        return dateTime;
    }

    public String getNotes() {
        return notes;
    }

    public void setType(String type) {
        this.type = type;
    }

    public void setDateTime(LocalDateTime dateTime) {
        this.dateTime = dateTime;
    }

    public void setNotes(String notes) {
        this.notes = (notes == null) ? "" : notes;
    }

    @Override
    public String toString() {
        DateTimeFormatter fmt = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm");
        return "Type: " + type +
               "\nDate & Time: " + dateTime.format(fmt) +
               (notes.isEmpty() ? "" : ("\nNotes: " + notes));
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Appointment)) return false;
        Appointment other = (Appointment) o;
        return Objects.equals(type, other.type) &&
               Objects.equals(dateTime, other.dateTime) &&
               Objects.equals(notes, other.notes);
    }

    @Override
    public int hashCode() {
        return Objects.hash(type, dateTime, notes);
    }
}
