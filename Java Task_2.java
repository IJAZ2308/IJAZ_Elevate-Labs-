import java.util.ArrayList;
import java.util.Scanner;
import java.util.Collections;
import java.util.Comparator;

class Student {
    private int id;
    private String name;
    private double marks;

    // Constructor
    public Student(int id, String name, double marks) {
        this.id = id;
        this.name = name;
        this.marks = marks;
    }

    // Getters & Setters
    public int getId() { return id; }
    public void setId(int id) { this.id = id; }

    public String getName() { return name; }
    public void setName(String name) { this.name = name; }

    public double getMarks() { return marks; }
    public void setMarks(double marks) { this.marks = marks; }

    @Override
    public String toString() {
        return "ID: " + id + " | Name: " + name + " | Marks: " + marks;
    }
}

public class StudentManagementSystem {
    private static ArrayList<Student> students = new ArrayList<>();
    private static Scanner sc = new Scanner(System.in);

    public static void main(String[] args) {
        int choice;
        do {
            System.out.println("\n===== Student Record Management System =====");
            System.out.println("1. Add Student");
            System.out.println("2. View Students");
            System.out.println("3. Update Student");
            System.out.println("4. Delete Student");
            System.out.println("5. View Students (Sorted by Marks)");
            System.out.println("6. Interview Questions");
            System.out.println("7. Exit");
            System.out.print("Enter choice: ");
            choice = sc.nextInt();

            switch (choice) {
                case 1 -> addStudent();
                case 2 -> viewStudents();
                case 3 -> updateStudent();
                case 4 -> deleteStudent();
                case 5 -> sortByMarks();
                case 6 -> printInterviewQuestions();
                case 7 -> System.out.println("Exiting... Bye!");
                default -> System.out.println("Invalid choice! Try again.");
            }
        } while (choice != 7);
    }

    // Add Student
    private static void addStudent() {
        System.out.print("Enter ID: ");
        int id = sc.nextInt();
        sc.nextLine();
        System.out.print("Enter Name: ");
        String name = sc.nextLine();
        System.out.print("Enter Marks: ");
        double marks = sc.nextDouble();

        students.add(new Student(id, name, marks));
        System.out.println("✅ Student added successfully!");
    }

    // View Students
    private static void viewStudents() {
        if (students.isEmpty()) {
            System.out.println("No records found.");
        } else {
            System.out.println("=== Student Records ===");
            for (Student s : students) {
                System.out.println(s);
            }
        }
    }

    // Update Student
    private static void updateStudent() {
        System.out.print("Enter Student ID to update: ");
        int id = sc.nextInt();
        boolean found = false;

        for (Student s : students) {
            if (s.getId() == id) {
                found = true;
                sc.nextLine();
                System.out.print("Enter new name: ");
                s.setName(sc.nextLine());
                System.out.print("Enter new marks: ");
                s.setMarks(sc.nextDouble());
                System.out.println("✅ Record updated successfully!");
                break;
            }
        }
        if (!found) {
            System.out.println("❌ Student not found.");
        }
    }

    // Delete Student
    private static void deleteStudent() {
        System.out.print("Enter Student ID to delete: ");
        int id = sc.nextInt();
        boolean removed = students.removeIf(s -> s.getId() == id);

        if (removed) {
            System.out.println("✅ Record deleted successfully!");
        } else {
            System.out.println("❌ Student not found.");
        }
    }

    // Sort by Marks
    private static void sortByMarks() {
        if (students.isEmpty()) {
            System.out.println("No records to sort.");
            return;
        }
        students.sort(Comparator.comparingDouble(Student::getMarks).reversed());
        System.out.println("=== Students Sorted by Marks (High → Low) ===");
        for (Student s : students) {
            System.out.println(s);
        }
    }

   

}
