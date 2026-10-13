import java.util.Collections;
import java.util.HashMap;
import java.util.Map;

public class DoctorDirectory {
    private final Map<String, Doctor> doctorMap;

    public DoctorDirectory() {
        this.doctorMap = new HashMap<>();
    }

    /**
     * Adds a doctor to the directory.
     *
     * @return true if added successfully, false if the ID already exists
     *         or any required field is blank/null.
     */
    public boolean addDoctor(String id, String name, Specialization specialization) {
        if (id == null || id.isBlank()) {
            return false;
        }
        if (name == null || name.isBlank()) {
            return false;
        }
        if (specialization == null) {
            return false;
        }
        if (doctorMap.containsKey(id)) {
            return false;
        }
        doctorMap.put(id, new Doctor(id, name, specialization));
        return true;
    }

    public boolean containsDoctor(String id) {
        return doctorMap.containsKey(id);
    }

    public Map<String, Doctor> getAllDoctors() {
        return Collections.unmodifiableMap(doctorMap);
    }

    public Doctor getDoctorById(String doctorId) {
        return doctorMap.get(doctorId);
    }

    public boolean removeDoctor(String id) {
        return doctorMap.remove(id) != null;
    }
}