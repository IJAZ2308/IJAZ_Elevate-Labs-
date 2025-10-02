import java.sql.*;
import java.util.Scanner;

public class EmployeeDBApp {
    // Database connection details
    private static final String URL = "jdbc:mysql://localhost:3306/employee_db";
    private static final String USER = "root";   // change as per your DB
    private static final String PASSWORD = "yourpassword"; // change as per your DB

    private Connection conn;
    private Scanner scanner;

    public EmployeeDBApp() {
        try {
            conn = DriverManager.getConnection(URL, USER, PASSWORD);
            System.out.println("✅ Database Connected Successfully!");
        } catch (SQLException e) {
            System.out.println("❌ Connection Failed: " + e.getMessage());
        }
        scanner = new Scanner(System.in);
    }

    // Add Employee
    public void addEmployee() {
        System.out.print("Enter Name: ");
        String name = scanner.nextLine();
        System.out.print("Enter Department: ");
        String dept = scanner.nextLine();
        System.out.print("Enter Salary: ");
        double salary = scanner.nextDouble();
        scanner.nextLine(); // consume newline

        String sql = "INSERT INTO employees (name, department, salary) VALUES (?, ?, ?)";
        try (PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setString(1, name);
            stmt.setString(2, dept);
            stmt.setDouble(3, salary);
            stmt.executeUpdate();
            System.out.println("✅ Employee Added Successfully!");
        } catch (SQLException e) {
            System.out.println("❌ Error: " + e.getMessage());
        }
    }

    // View Employees
    public void viewEmployees() {
        String sql = "SELECT * FROM employees";
        try (Statement stmt = conn.createStatement(); ResultSet rs = stmt.executeQuery(sql)) {
            System.out.println("\n---- Employee List ----");
            while (rs.next()) {
                System.out.println(rs.getInt("id") + " | " + rs.getString("name") + " | "
                        + rs.getString("department") + " | " + rs.getDouble("salary"));
            }
            System.out.println("-----------------------\n");
        } catch (SQLException e) {
            System.out.println("❌ Error: " + e.getMessage());
        }
    }

    // Update Employee
    public void updateEmployee() {
        System.out.print("Enter Employee ID to update: ");
        int id = scanner.nextInt();
        scanner.nextLine();

        System.out.print("Enter New Department: ");
        String dept = scanner.nextLine();
        System.out.print("Enter New Salary: ");
        double salary = scanner.nextDouble();
        scanner.nextLine();

        String sql = "UPDATE employees SET department = ?, salary = ? WHERE id = ?";
        try (PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setString(1, dept);
            stmt.setDouble(2, salary);
            stmt.setInt(3, id);
            int rows = stmt.executeUpdate();
            if (rows > 0) {
                System.out.println("✅ Employee Updated Successfully!");
            } else {
                System.out.println("⚠ No Employee Found with ID: " + id);
            }
        } catch (SQLException e) {
            System.out.println("❌ Error: " + e.getMessage());
        }
    }

    // Delete Employee
    public void deleteEmployee() {
        System.out.print("Enter Employee ID to delete: ");
        int id = scanner.nextInt();
        scanner.nextLine();

        String sql = "DELETE FROM employees WHERE id = ?";
        try (PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setInt(1, id);
            int rows = stmt.executeUpdate();
            if (rows > 0) {
                System.out.println("✅ Employee Deleted Successfully!");
            } else {
                System.out.println("⚠ No Employee Found with ID: " + id);
            }
        } catch (SQLException e) {
            System.out.println("❌ Error: " + e.getMessage());
        }
    }

    // Menu
    public void menu() {
        while (true) {
            System.out.println("\n==== Employee Database Menu ====");
            System.out.println("1. Add Employee");
            System.out.println("2. View Employees");
            System.out.println("3. Update Employee");
            System.out.println("4. Delete Employee");
            System.out.println("5. Exit");
            System.out.print("Choose option: ");

            int choice = scanner.nextInt();
            scanner.nextLine();

            switch (choice) {
                case 1: addEmployee(); break;
                case 2: viewEmployees(); break;
                case 3: updateEmployee(); break;
                case 4: deleteEmployee(); break;
                case 5: System.out.println("👋 Exiting..."); return;
                default: System.out.println("⚠ Invalid Choice!");
            }
        }
    }

    public static void main(String[] args) {
        EmployeeDBApp app = new EmployeeDBApp();
        app.menu();
    }
}
