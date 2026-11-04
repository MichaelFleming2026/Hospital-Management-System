import java.util.Collections;
import java.util.HashMap;
import java.util.Map;

public class PatientDirectory {
    private final Map<String, Patient> patients;

    public PatientDirectory() {
        this.patients = new HashMap<>();
    }

    public boolean addPatient(String id, String name, String diagnosis,
                              Specialization requiredSpecialization) {
        if (id == null || id.isBlank()) {
            return false;
        }
        if (name == null || name.isBlank()) {
            return false;
        }
        if (patients.containsKey(id)) {
            return false;
        }
        patients.put(id, new Patient(id, name, diagnosis, requiredSpecialization));
        return true;
    }

    public Map<String, Patient> getAllPatients() {
        return Collections.unmodifiableMap(patients);
    }

    public Patient getPatientById(String id) {
        return patients.get(id);
    }

    public boolean removePatient(String id) {
        return patients.remove(id) != null;
    }
}