public class Patient {
    private final String id;
    private final String name;
    private final String diagnosis;
    private final Specialization requiredSpecialization;

    public Patient(String id, String name, String diagnosis, Specialization requiredSpecialization) {
        this.id = id;
        this.name = name;
        this.diagnosis = diagnosis;
        this.requiredSpecialization = requiredSpecialization;
    }

    public String getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public String getDiagnosis() {
        return diagnosis;
    }

    public Specialization getRequiredSpecialization() {
        return requiredSpecialization;
    }

    @Override
    public String toString() {
        return "Patient ID: " + id
                + ", Name: " + name
                + ", Diagnosis: " + diagnosis
                + ", Required Specialization: " + requiredSpecialization;
    }
}