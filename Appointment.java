public class Appointment {
    private final String patientId;
    private final String patientName;
    private final String patientDiagnosis;
    private final Specialization requiredSpecialization;
    private final String doctorId;
    private final String doctorName;
    private final Specialization doctorSpecialization;
    private final String date;      // YYYY-MM-DD
    private final int priority;     // 1-5

    public Appointment(String patientId, String patientName, String patientDiagnosis,
                       Specialization requiredSpecialization,
                       String doctorId, String doctorName, Specialization doctorSpecialization,
                       String date, int priority) {
        this.patientId = patientId;
        this.patientName = patientName;
        this.patientDiagnosis = patientDiagnosis;
        this.requiredSpecialization = requiredSpecialization;
        this.doctorId = doctorId;
        this.doctorName = doctorName;
        this.doctorSpecialization = doctorSpecialization;
        this.date = date;
        this.priority = priority;
    }

    public String getPatientId() {
        return patientId;
    }

    public String getPatientName() {
        return patientName;
    }

    public String getPatientDiagnosis() {
        return patientDiagnosis;
    }

    public Specialization getRequiredSpecialization() {
        return requiredSpecialization;
    }

    public String getDoctorId() {
        return doctorId;
    }

    public String getDoctorName() {
        return doctorName;
    }

    public Specialization getDoctorSpecialization() {
        return doctorSpecialization;
    }

    public String getDate() {
        return date;
    }

    public int getPriority() {
        return priority;
    }

    @Override
    public String toString() {
        return "Appointment Details:\n"
                + "  Patient ID: " + patientId + "\n"
                + "  Patient Name: " + patientName + "\n"
                + "  Diagnosis: " + patientDiagnosis + "\n"
                + "  Required Specialization: " + requiredSpecialization + "\n"
                + "  Doctor ID: " + doctorId + "\n"
                + "  Doctor Name: " + doctorName + "\n"
                + "  Doctor Specialization: " + doctorSpecialization + "\n"
                + "  Date: " + date + "\n"
                + "  Priority: " + priority;
    }
}