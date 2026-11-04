import javax.swing.*;
import java.awt.*;
import java.util.List;

public class HMSSystemGUI {
    private static final DoctorDirectory doctorDirectory = new DoctorDirectory();
    private static final PatientDirectory patientDirectory = new PatientDirectory();
    private static final SchedulingSystem schedulingSystem = new SchedulingSystem();

    private final JFrame frame;

    public HMSSystemGUI() {
        frame = new JFrame("Hospital Management System");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(800, 600);

        JTabbedPane tabbedPane = new JTabbedPane();
        tabbedPane.addTab("Dashboard", createDashboardPanel());
        tabbedPane.addTab("View Lists", createViewListsPanel());
        tabbedPane.addTab("Schedule Appointment", createSchedulingPanel());
        tabbedPane.addTab("Manage Records", createManageRecordsPanel());
        tabbedPane.addTab("Doctor Appointments", createDoctorAppointmentsPanel());

        frame.add(tabbedPane);
        frame.setVisible(true);
    }

    // =====================================================
    // Dashboard
    // =====================================================
    private JPanel createDashboardPanel() {
        JPanel panel = new JPanel(new BorderLayout());
        JLabel label = new JLabel("Welcome to the HMS System", JLabel.CENTER);
        label.setFont(new Font("Serif", Font.BOLD, 30));
        panel.add(label, BorderLayout.CENTER);
        return panel;
    }

    // =====================================================
    // View Lists
    // =====================================================
    private JPanel createViewListsPanel() {
        JPanel panel = new JPanel(new BorderLayout());

        JButton viewDoctorsButton = new JButton("View Doctors");
        JButton viewPatientsButton = new JButton("View Patients");
        JButton viewAppointmentsButton = new JButton("View Appointments");

        JTextArea displayArea = new JTextArea(15, 50);
        displayArea.setEditable(false);

        JPanel buttonPanel = new JPanel();
        buttonPanel.add(viewDoctorsButton);
        buttonPanel.add(viewPatientsButton);
        buttonPanel.add(viewAppointmentsButton);

        panel.add(buttonPanel, BorderLayout.NORTH);
        panel.add(new JScrollPane(displayArea), BorderLayout.CENTER);

        viewDoctorsButton.addActionListener(e -> {
            displayArea.setText("");
            for (Doctor doctor : doctorDirectory.getAllDoctors().values()) {
                displayArea.append(doctor + "\n");
            }
        });

        viewPatientsButton.addActionListener(e -> {
            displayArea.setText("");
            for (Patient patient : patientDirectory.getAllPatients().values()) {
                displayArea.append(patient + "\n");
            }
        });

        viewAppointmentsButton.addActionListener(e -> {
            displayArea.setText("");
            for (Appointment appointment : schedulingSystem.getAppointmentsByPriority()) {
                displayArea.append(appointment + "\n\n");
            }
        });

        return panel;
    }

    // =====================================================
    // Schedule Appointment
    // =====================================================
    private JPanel createSchedulingPanel() {
        JPanel panel = new JPanel(new GridLayout(0, 1));

        JTextField patientIdField = new JTextField(20);
        JTextField dateField = new JTextField(20);
        JTextField priorityField = new JTextField(20);
        JButton scheduleButton = new JButton("Schedule Appointment");

        JTextField deletePatientIdField = new JTextField(20);
        JTextField deleteDateField = new JTextField(20);
        JButton deleteAppointmentButton = new JButton("Delete Appointment");

        panel.add(new JLabel("Patient ID:"));
        panel.add(patientIdField);
        panel.add(new JLabel("Date (YYYY-MM-DD):"));
        panel.add(dateField);
        panel.add(new JLabel("Priority (1 - 5):"));
        panel.add(priorityField);
        panel.add(scheduleButton);

        panel.add(new JLabel("Patient ID for Appointment Deletion:"));
        panel.add(deletePatientIdField);
        panel.add(new JLabel("Appointment Date to Delete (YYYY-MM-DD):"));
        panel.add(deleteDateField);
        panel.add(deleteAppointmentButton);

        scheduleButton.addActionListener(e -> {
            String patientId = patientIdField.getText().trim();
            String date = dateField.getText().trim();
            String priorityText = priorityField.getText().trim();

            if (patientId.isEmpty() || date.isEmpty() || priorityText.isEmpty()) {
                JOptionPane.showMessageDialog(frame,
                        "Please fill in Patient ID, Date, and Priority.",
                        "Invalid Input",
                        JOptionPane.WARNING_MESSAGE);
                return;
            }

            int priority;
            try {
                priority = Integer.parseInt(priorityText);
            } catch (NumberFormatException ex) {
                JOptionPane.showMessageDialog(frame,
                        "Priority must be an integer between 1 and 5.",
                        "Invalid Input",
                        JOptionPane.WARNING_MESSAGE);
                return;
            }

            if (priority < 1 || priority > 5) {
                JOptionPane.showMessageDialog(frame,
                        "Priority must be between 1 and 5.",
                        "Invalid Input",
                        JOptionPane.WARNING_MESSAGE);
                return;
            }

            boolean scheduled = schedulingSystem.scheduleAppointment(
                    patientDirectory, doctorDirectory, patientId, date, priority);

            if (scheduled) {
                JOptionPane.showMessageDialog(frame,
                        "Appointment scheduled for Patient ID: " + patientId,
                        "Success",
                        JOptionPane.INFORMATION_MESSAGE);
                patientIdField.setText("");
                dateField.setText("");
                priorityField.setText("");
            } else {
                JOptionPane.showMessageDialog(frame,
                        "Could not schedule. Check patient ID, date format, "
                                + "or whether a matching doctor exists.",
                        "Schedule Failed",
                        JOptionPane.ERROR_MESSAGE);
            }
        });

        deleteAppointmentButton.addActionListener(e -> {
            String patientId = deletePatientIdField.getText().trim();
            String date = deleteDateField.getText().trim();

            if (patientId.isEmpty() || date.isEmpty()) {
                JOptionPane.showMessageDialog(frame,
                        "Please enter both Patient ID and Date.",
                        "Invalid Input",
                        JOptionPane.WARNING_MESSAGE);
                return;
            }

            boolean deleted = schedulingSystem.deleteAppointment(patientId, date);
            if (deleted) {
                JOptionPane.showMessageDialog(frame,
                        "Appointment deleted.",
                        "Success",
                        JOptionPane.INFORMATION_MESSAGE);
                deletePatientIdField.setText("");
                deleteDateField.setText("");
            } else {
                JOptionPane.showMessageDialog(frame,
                        "Appointment not found.",
                        "Delete Failed",
                        JOptionPane.ERROR_MESSAGE);
            }
        });

        return panel;
    }

    // =====================================================
    // Manage Records
    // =====================================================
    private JPanel createManageRecordsPanel() {
        JPanel panel = new JPanel(new GridLayout(0, 1));

        // Doctor fields
        JTextField doctorIdField = new JTextField(20);
        JTextField doctorNameField = new JTextField(20);
        JComboBox<Specialization> doctorSpecField =
                new JComboBox<>(Specialization.values());

        // Patient fields
        JTextField patientIdField = new JTextField(20);
        JTextField patientNameField = new JTextField(20);
        JTextField diagnosisField = new JTextField(20);
        JComboBox<Specialization> requiredSpecField =
                new JComboBox<>(Specialization.values());

        // Buttons
        JButton addDoctorButton = new JButton("Add Doctor");
        JButton addPatientButton = new JButton("Add Patient");

        // Doctor UI
        panel.add(new JLabel("Doctor ID:"));
        panel.add(doctorIdField);
        panel.add(new JLabel("Doctor Name:"));
        panel.add(doctorNameField);
        panel.add(new JLabel("Specialization:"));
        panel.add(doctorSpecField);
        panel.add(addDoctorButton);

        // Patient UI
        panel.add(new JLabel("Patient ID:"));
        panel.add(patientIdField);
        panel.add(new JLabel("Patient Name:"));
        panel.add(patientNameField);
        panel.add(new JLabel("Diagnosis:"));
        panel.add(diagnosisField);
        panel.add(new JLabel("Required Specialization:"));
        panel.add(requiredSpecField);
        panel.add(addPatientButton);

        // ---- US-01: Add Doctor ----
        addDoctorButton.addActionListener(e -> {
            String id = doctorIdField.getText().trim();
            String name = doctorNameField.getText().trim();
            Specialization spec = (Specialization) doctorSpecField.getSelectedItem();

            if (id.isEmpty() || name.isEmpty() || spec == null) {
                JOptionPane.showMessageDialog(frame,
                        "Please enter both a Doctor ID and a Name, and select a Specialization.",
                        "Invalid Input",
                        JOptionPane.WARNING_MESSAGE);
                return;
            }

            boolean added = doctorDirectory.addDoctor(id, name, spec);

            if (added) {
                JOptionPane.showMessageDialog(frame,
                        "Doctor \"" + name + "\" (" + id + ") added successfully.",
                        "Success",
                        JOptionPane.INFORMATION_MESSAGE);
                doctorIdField.setText("");
                doctorNameField.setText("");
                doctorSpecField.setSelectedIndex(0);
            } else {
                JOptionPane.showMessageDialog(frame,
                        "Could not add doctor. ID \"" + id + "\" may already exist.",
                        "Add Failed",
                        JOptionPane.ERROR_MESSAGE);
            }
        });

        // ---- US-02: Add Patient (basic) ----
        addPatientButton.addActionListener(e -> {
            String id = patientIdField.getText().trim();
            String name = patientNameField.getText().trim();
            String diagnosis = diagnosisField.getText().trim();
            Specialization spec = (Specialization) requiredSpecField.getSelectedItem();

            if (id.isEmpty() || name.isEmpty() || spec == null) {
                JOptionPane.showMessageDialog(frame,
                        "Please enter Patient ID, Name, and select a Specialization.",
                        "Invalid Input",
                        JOptionPane.WARNING_MESSAGE);
                return;
            }

            boolean added = patientDirectory.addPatient(id, name, diagnosis, spec);

            if (added) {
                JOptionPane.showMessageDialog(frame,
                        "Patient \"" + name + "\" (" + id + ") added successfully.",
                        "Success",
                        JOptionPane.INFORMATION_MESSAGE);
                patientIdField.setText("");
                patientNameField.setText("");
                diagnosisField.setText("");
                requiredSpecField.setSelectedIndex(0);
            } else {
                JOptionPane.showMessageDialog(frame,
                        "Could not add patient. ID \"" + id + "\" may already exist.",
                        "Add Failed",
                        JOptionPane.ERROR_MESSAGE);
            }
        });

        return panel;
    }

    // =====================================================
    // Doctor Appointments
    // =====================================================
    private JPanel createDoctorAppointmentsPanel() {
        JPanel panel = new JPanel(new BorderLayout());

        JTextField doctorIdField = new JTextField(20);
        JButton viewButton = new JButton("View Doctor's Appointments");
        JTextArea displayArea = new JTextArea(15, 50);
        displayArea.setEditable(false);

        JPanel top = new JPanel();
        top.add(new JLabel("Doctor ID:"));
        top.add(doctorIdField);
        top.add(viewButton);

        panel.add(top, BorderLayout.NORTH);
        panel.add(new JScrollPane(displayArea), BorderLayout.CENTER);

        viewButton.addActionListener(e -> {
            String doctorId = doctorIdField.getText().trim();
            displayArea.setText("");

            if (doctorId.isEmpty()) {
                displayArea.append("Please enter a Doctor ID.\n");
                return;
            }

            List<Appointment> appointments =
                    schedulingSystem.getAppointmentsByDoctor(doctorId);

            if (appointments.isEmpty()) {
                displayArea.append("No appointments found for Doctor ID: " + doctorId);
            } else {
                for (Appointment appt : appointments) {
                    displayArea.append(appt + "\n\n");
                }
            }
        });

        return panel;
    }

    // =====================================================
    // Main
    // =====================================================
    public static void main(String[] args) {
        SwingUtilities.invokeLater(HMSSystemGUI::new);
    }
}