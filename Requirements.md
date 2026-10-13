Hospital Management System (HMS) - Project Scope and Specification
1. Customer Statement of Requirements
Vision Statement
The Hospital Management System (HMS) is a comprehensive Java-based desktop application
designed to streamline and automate the administrative and clinical operations of healthcare
facilities. The core purpose of this system is to provide an intuitive, centralized platform that
enables healthcare administrators, medical staff, and support personnel to efficiently manage
patient records, doctor information, and appointment scheduling.
Scope
The system will facilitate three primary functions:
Record Management: Registration and maintenance of detailed profiles for both patients and
doctors, including patient diagnoses and doctor specializations.
Intelligent Scheduling: Scheduling appointments based on a matching algorithm that pairs
patients with appropriate specialists based on their medical condition. The system will assign
priority levels (1-5) to appointments to facilitate triage.
Data Visualization and Management: Viewing appointments sorted by priority, searching and
filtering appointments by doctor ID, and performing full CRUD (Create, Read, Update, Delete)
operations on all records.
The application is intended for use by Hospital Administrators, Medical Receptionists, and
Healthcare Providers. It is currently a single-user, desktop-based system with plans for future
web-based expansion.
2. Requirements Specification
Functional Requirements (FR)
FR1 Patient Management: The system must allow users to add, view, and delete patient
records. Each record must include a unique ID, name, diagnosis, and required specialization.
FR2 Doctor Management: The system must allow users to add, view, and delete doctor records.
Each record must include a unique ID, name, and medical specialization.
FR3 Appointment Scheduling: The system must allow users to schedule appointments by
inputting a Patient ID, Date (YYYY-MM-DD), and Priority Level (1-5).
FR4 Intelligent Matching: The system must automatically match a patient to a doctor based on
the patient's Required Specialization field.
FR5 Appointment Deletion: The system must allow users to delete appointments by providing
the Patient ID and Date.
FR6 Data Visualization (Lists): The system must provide a View Lists panel to display all
registered doctors, all registered patients, and all appointments sorted by priority.
FR7 Doctor-Specific View: The system must provide a panel to view all scheduled appointments
filtered by a specific Doctor ID.
FR8 Input Validation: The system must validate that Priority Levels are integers between 1 and
5 and prevent duplicate appointments using a unique patientId-date key.
Non-Functional Requirements (NFR)
NFR1 Technology Stack: The application must be built using Java SE 23 and Java Swing for the
GUI (MVC architecture).
NFR2 Performance: Data retrieval and storage operations must use Java's HashMap and
ArrayList for fast, in-memory access without network latency.
NFR3 Usability: The interface must utilize tabbed panels for intuitive navigation (Dashboard,
View Lists, Schedule Appointment, Manage Records, Doctor Appointments).
NFR4 Platform Independence: The use of Java Swing ensures the application is
platform-independent.
NFR5 Reliability: The system must handle invalid inputs gracefully using exception handling and
display user-friendly error messages via JOptionPane.
NFR6 Scalability: The architecture must be designed to seamlessly transition from in-memory
storage to a JDBC relational database in future iterations.
3. Data and Storage Blueprint
Data Input
The primary mechanism for data input is Manual Entry via the Java Swing GUI.
Manage Records Panel: Users manually type in Doctor ID, Doctor Name, Patient ID, Patient
Name, and Diagnosis. They select Specialization from a dropdown (JComboBox).
Schedule Appointment Panel: Users manually type in Patient ID, Date (YYYY-MM-DD), and
Priority Level.
Delete Operations: Users manually type in the ID and Date to confirm deletions.
Database or Storage
For this first iteration, the storage solution is an In-Memory Database (HashMap-based).
Implementation: The system uses java.util.HashMap collections to store objects.
DoctorDirectory uses a Map of String to Doctor.
PatientDirectory uses a Map of String to Patient.
SchedulingSystem uses a Map of String to Appointment where the key is patientId + "-" + date.
Rationale: This approach simplifies initial deployment and testing, provides fast data access,
and allows easy manipulation through Java's native interfaces.
Future Roadmap: The architecture is explicitly designed to integrate with a Relational Database
(e.g., MySQL, Postgres) using JDBC in future sprints to enable persistent data storage,
concurrent access, and transaction support.
4. Agile Product Backlog
The following user stories translate the functional requirements into standard Agile format.
US-01 Add New Doctor
User Story: As a Medical Receptionist, I want to add a new doctor to the system so that they
can be assigned to patients.
Priority: High
Acceptance Criteria: Doctor ID, Name, and Specialization are saved to DoctorDirectory. A
confirmation message is displayed.
US-02 Add New Patient
User Story: As a Medical Receptionist, I want to add a new patient with their diagnosis and
required specialization so that the system can match them with the right doctor.
Priority: High
Acceptance Criteria: Patient ID, Name, Diagnosis, and Required Specialization are saved to
PatientDirectory.
US-03 Schedule Appointment
User Story: As a Medical Receptionist, I want to schedule an appointment by entering a Patient
ID, Date, and Priority so that the patient is seen by a specialist.
Priority: High
Acceptance Criteria: The system automatically finds a doctor matching the patient's required
specialization and stores the appointment. Priority must be 1-5.
US-04 View Appointments by Priority
User Story: As a Hospital Administrator, I want to view a list of all appointments sorted by priority
so that I can triage urgent cases.
Priority: Medium
Acceptance Criteria: The View Lists panel displays appointments in ascending order of priority
(1 = highest).
US-05 View Doctor-Specific Appointments
User Story: As a Healthcare Provider, I want to view only my own scheduled appointments so
that I can manage my daily schedule.
Priority: Medium
Acceptance Criteria: The Doctor Appointments panel allows input of a Doctor ID and displays
only appointments matching that ID.
US-06 Delete Appointment
User Story: As a Medical Receptionist, I want to delete an existing appointment so that
cancelled slots are freed up.
Priority: Medium
Acceptance Criteria: Inputting a Patient ID and Date removes the record from the system. A
confirmation dialog is shown before deletion.
US-07 Delete Doctor or Patient Record
User Story: As a Hospital IT Staff, I want to remove a doctor or patient record so that the
database does not contain outdated information.
Priority: Low
Acceptance Criteria: Users can delete records by ID. A confirmation dialog
(JOptionPane.YES_OPTION) prevents accidental deletion.
US-08 User-Friendly Interface and Error Handling
User Story: As a User, I want to see a user-friendly dashboard and error messages so that I can
navigate the system easily and correct my input mistakes.
Priority: Medium
Acceptance Criteria: The GUI uses a JTabbedPane. Invalid inputs (e.g., Priority 6) trigger a
JOptionPane error message instead of crashing.
