import java.util.ArrayList;
import java.util.Scanner;

class Student {

    int id;
    String name;
    int totalClasses;
    int attendedClasses;

    // Constructor
    Student(int id, String name) {
        this.id = id;
        this.name = name;
        this.totalClasses = 0;
        this.attendedClasses = 0;
    }

    // Mark attendance
    void markAttendance(boolean present) {
        totalClasses++;

        if (present) {
            attendedClasses++;
        }
    }

    // Calculate attendance percentage
    double getAttendancePercentage() {
        if (totalClasses == 0) {
            return 0.0;
        }

        return (attendedClasses * 100.0) / totalClasses;
    }

    // Check eligibility
    boolean isEligible() {
        return getAttendancePercentage() >= 75.0;
    }

    // Display student details
    void display() {
        System.out.println("\n-----------------------------");
        System.out.println("Student ID: " + id);
        System.out.println("Student Name: " + name);
        System.out.println(
            "Attendance: " + attendedClasses + "/" + totalClasses
        );

        System.out.printf(
            "Attendance Percentage: %.2f%%%n",
            getAttendancePercentage()
        );

        System.out.println(
            "Eligible: " + (isEligible() ? "Yes" : "No")
        );
        System.out.println("-----------------------------");
    }
}

public class AttendanceSystem {

    static Scanner sc = new Scanner(System.in);
    static ArrayList<Student> students = new ArrayList<>();

    public static void main(String[] args) {

        int choice = 0;

        do {
            System.out.println("\n================================");
            System.out.println("   ATTENDANCE MONITORING SYSTEM");
            System.out.println("================================");
            System.out.println("1. Add Student");
            System.out.println("2. Mark Attendance");
            System.out.println("3. View Attendance Report");
            System.out.println("4. Exit");
            System.out.println("================================");
            System.out.print("Enter your choice: ");

            if (!sc.hasNextInt()) {
                System.out.println("Invalid input! Enter a number.");
                sc.nextLine();
                continue;
            }

            choice = sc.nextInt();
            sc.nextLine();

            switch (choice) {

                case 1:
                    addStudent();
                    break;

                case 2:
                    markAttendance();
                    break;

                case 3:
                    viewReport();
                    break;

                case 4:
                    System.out.println(
                        "Thank you for using the Attendance Monitoring System!"
                    );
                    break;

                default:
                    System.out.println(
                        "Invalid choice! Please select 1 to 4."
                    );
            }

        } while (choice != 4);

        sc.close();
    }

    // Add a new student
    static void addStudent() {

        System.out.print("Enter Student ID: ");

        if (!sc.hasNextInt()) {
            System.out.println("Invalid ID! Enter a number.");
            sc.nextLine();
            return;
        }

        int id = sc.nextInt();
        sc.nextLine();

        // Prevent duplicate student IDs
        if (findStudent(id) != null) {
            System.out.println("Student ID already exists!");
            return;
        }

        System.out.print("Enter Student Name: ");
        String name = sc.nextLine().trim();

        if (name.isEmpty()) {
            System.out.println("Student name cannot be empty!");
            return;
        }

        Student student = new Student(id, name);
        students.add(student);

        System.out.println("Student added successfully!");
    }

    // Find student using ID
    static Student findStudent(int id) {

        for (Student student : students) {
            if (student.id == id) {
                return student;
            }
        }

        return null;
    }

    // Mark attendance
    static void markAttendance() {

        if (students.isEmpty()) {
            System.out.println("No students available! Add a student first.");
            return;
        }

        System.out.print("Enter Student ID: ");

        if (!sc.hasNextInt()) {
            System.out.println("Invalid ID! Enter a number.");
            sc.nextLine();
            return;
        }

        int id = sc.nextInt();
        sc.nextLine();

        Student student = findStudent(id);

        if (student == null) {
            System.out.println("Student not found!");
            return;
        }

        System.out.println("Student Name: " + student.name);
        System.out.print("Is the student present? (yes/no): ");

        String answer = sc.nextLine().trim();

        if (answer.equalsIgnoreCase("yes")) {
            student.markAttendance(true);
            System.out.println("Attendance marked as PRESENT.");

        } else if (answer.equalsIgnoreCase("no")) {
            student.markAttendance(false);
            System.out.println("Attendance marked as ABSENT.");

        } else {
            System.out.println("Invalid input! Enter yes or no.");
        }
    }

    // Display attendance report
    static void viewReport() {

        if (students.isEmpty()) {
            System.out.println("No students available!");
            return;
        }

        System.out.println("\n===== ATTENDANCE REPORT =====");

        for (Student student : students) {
            student.display();
        }
    }
}