import java.util.Arrays;
import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

public enum Specialization {
    PEDIATRICS(1, "Pediatrics"),
    CARDIOLOGY(2, "Cardiology"),
    GASTROENTEROLOGY(3, "Gastroenterology"),
    DERMATOLOGY(4, "Dermatology"),
    MEDICAL_GENETICS(5, "Medical Genetics"),
    PSYCHIATRY(6, "Psychiatry"),
    GYNECOLOGY(7, "Gynecology"),
    NEUROLOGY(8, "Neurology"),
    SURGEON(9, "Surgeon"),
    GENERAL_PRACTITIONER(10, "General Practitioner");

    private final int number;
    private final String name;

    Specialization(int number, String name) {
        this.number = number;
        this.name = name;
    }

    public int getNumber() {
        return number;
    }

    public String getName() {
        return name;
    }

    public static Specialization getByNumber(int number) {
        for (Specialization spec : Specialization.values()) {
            if (spec.getNumber() == number) {
                return spec;
            }
        }
        return null;
    }

    public static Specialization getByName(String name) {
        if (name == null || name.isEmpty()) {
            return null;
        }
        for (Specialization spec : Specialization.values()) {
            if (spec.getName().equalsIgnoreCase(name)) {
                return spec;
            }
        }
        return null;
    }

    @Override
    public String toString() {
        return name + " (Code: " + number + ")";
    }

    public static boolean isValidSpecialization(int number) {
        return getByNumber(number) != null;
    }

    public static boolean isValidSpecialization(String name) {
        return getByName(name) != null;
    }

    public static List<Specialization> getSortedByNumber() {
        return Arrays.stream(Specialization.values())
                .sorted(Comparator.comparingInt(Specialization::getNumber))
                .collect(Collectors.toList());
    }
}