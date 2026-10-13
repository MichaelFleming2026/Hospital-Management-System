public class Doctor {
    private final String id;
    private final String name;
    private final Specialization specialization;

    public Doctor(String id, String name, Specialization specialization) {
        this.id = id;
        this.name = name;
        this.specialization = specialization;
    }

    public String getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public Specialization getSpecialization() {
        return specialization;
    }

    @Override
    public String toString() {
        return "Doctor ID: " + id
                + ", Name: " + name
                + ", Specialization: " + specialization;
    }
}