import java.util.ArrayList;
import java.util.Comparator;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class SchedulingSystem {
    private final Map<String, Appointment> appointmentMap;

    public SchedulingSystem() {
        this.appointmentMap = new HashMap<>();
    }

    public List<Appointment> getAppointmentsByDoctor(String doctorId) {
        List<Appointment> result = new ArrayList<>();
        for (Appointment a : appointmentMap.values()) {
            if (a.getDoctorId().equals(doctorId)) {
                result.add(a);
            }
        }
        return result;
    }

    public List<Appointment> getAppointmentsByPriority() {
        List<Appointment> sorted = new ArrayList<>(appointmentMap.values());
        sorted.sort(Comparator.comparingInt(Appointment::getPriority));
        return sorted;
    }

    public boolean scheduleAppointment(PatientDirectory patientDirectory,
                                       DoctorDirectory doctorDirectory,
                                       String patientId, String date, int priority) {
        if (priority < 1 || priority > 5) {
            return false;
        }

        Patient patient = patientDirectory.getPatientById(patientId);
        if (patient == null) {
            return false;
        }

        Doctor doctor = getDoctorForSpecialization(
                doctorDirectory, patient.getRequiredSpecialization());
        if (doctor == null) {
            return false;
        }

        String key = patientId + "-" + date;
        if (appointmentMap.containsKey(key)) {
            return false;
        }

        Appointment appt = new Appointment(
                patient.getId(), patient.getName(), patient.getDiagnosis(),
                patient.getRequiredSpecialization(),
                doctor.getId(), doctor.getName(), doctor.getSpecialization(),
                date, priority);

        appointmentMap.put(key, appt);
        return true;
    }

    public boolean deleteAppointment(String patientId, String date) {
        return appointmentMap.remove(patientId + "-" + date) != null;
    }

    private Doctor getDoctorForSpecialization(DoctorDirectory dir, Specialization spec) {
        for (Doctor d : dir.getAllDoctors().values()) {
            if (d.getSpecialization() == spec) {
                return d;
            }
        }
        return null;
    }
}