import java.io.Serializable;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

/**
 * Represents a Pet record with appointments.
 */
public class Pet implements Serializable {
    private static final long serialVersionUID = 1L;

    private String id;                // unique pet ID
    private String name;
    private String species;           // species / breed
    private int age;
    private String ownerName;
    private String contactInfo;
    private LocalDate registrationDate;
    private List<Appointment> appointments;

    public Pet(String id, String name, String species, int age, String ownerName, String contactInfo) {
        this.id = id;
        this.name = name;
        this.species = species;
        this.age = age;
        this.ownerName = ownerName;
        this.contactInfo = contactInfo;
        this.registrationDate = LocalDate.now();
        this.appointments = new ArrayList<>();
    }

    public String getId() { return id; }
    public String getName() { return name; }
    public String getSpecies() { return species; }
    public int getAge() { return age; }
    public String getOwnerName() { return ownerName; }
    public String getContactInfo() { return contactInfo; }
    public LocalDate getRegistrationDate() { return registrationDate; }
    public List<Appointment> getAppointments() { return appointments; }

    public void setName(String name) { this.name = name; }
    public void setSpecies(String species) { this.species = species; }
    public void setAge(int age) { this.age = age; }
    public void setOwnerName(String ownerName) { this.ownerName = ownerName; }
    public void setContactInfo(String contactInfo) { this.contactInfo = contactInfo; }

    public void addAppointment(Appointment appointment) {
        if (appointment != null) {
            appointments.add(appointment);
        }
    }

    /**
     * Returns the most recent appointment of the given type (case-insensitive substring match),
     * or null if none exist.
     */
    public Appointment getMostRecentAppointmentByType(String typeKeyword) {
        Appointment best = null;
        for (Appointment a : appointments) {
            if (a.getType().toLowerCase().contains(typeKeyword.toLowerCase())) {
                if (best == null || a.getDateTime().isAfter(best.getDateTime())) {
                    best = a;
                }
            }
        }
        return best;
    }

    /**
     * Convenience: returns last vet-visit appointment (type contains "vet").
     */
    public Appointment getLastVetVisit() {
        return getMostRecentAppointmentByType("vet");
    }

    @Override
    public String toString() {
        return "ID: " + id +
               ", Name: " + name +
               ", Species/Breed: " + species +
               ", Age: " + age +
               ", Owner: " + ownerName +
               ", Contact: " + contactInfo +
               ", Registered: " + registrationDate +
               ", Appointments: " + appointments.size();
    }
}
