import java.sql.*;
import java.util.Scanner;

public class EmployeeApp {
    private static final String URL = "jdbc:mysql://localhost:3306/employee_db";
    private static final String USER = "root";
    private static final String PASS = ""; // Your MySQL password

    private static Connection getConnection() throws SQLException {
        return DriverManager.getConnection(URL, USER, PASS);
    }

    public static void addEmployee(String name, String email, double salary) throws SQLException {
        String sql = "INSERT INTO employee (name, email, salary) VALUES (?, ?, ?)";
        try (Connection conn = getConnection(); PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setString(1, name);
            stmt.setString(2, email);
            stmt.setDouble(3, salary);
            stmt.executeUpdate();
            System.out.println("Employee added.");
        }
    }

    public static void viewEmployees() throws SQLException {
        String sql = "SELECT * FROM employee";
        try (Connection conn = getConnection();
                PreparedStatement stmt = conn.prepareStatement(sql);
                ResultSet rs = stmt.executeQuery()) {
            while (rs.next()) {
                System.out.printf("ID: %d, Name: %s, Email: %s, Salary: %.2f%n",
                        rs.getInt("id"), rs.getString("name"),
                        rs.getString("email"), rs.getDouble("salary"));
            }
        }
    }

    public static void updateEmployee(int id, String name, String email, double salary) throws SQLException {
        String sql = "UPDATE employee SET name=?, email=?, salary=? WHERE id=?";
        try (Connection conn = getConnection(); PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setString(1, name);
            stmt.setString(2, email);
            stmt.setDouble(3, salary);
            stmt.setInt(4, id);
            int updated = stmt.executeUpdate();
            System.out.println(updated > 0 ? "Employee updated." : "Employee not found.");
        }
    }

    public static void deleteEmployee(int id) throws SQLException {
        String sql = "DELETE FROM employee WHERE id=?";
        try (Connection conn = getConnection(); PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setInt(1, id);
            int deleted = stmt.executeUpdate();
            System.out.println(deleted > 0 ? "Employee deleted." : "Employee not found.");
        }
    }

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        while (true) {
            System.out.println("\n1. Add  2. View  3. Update  4. Delete  5. Exit");
            int choice = sc.nextInt();
            try {
                switch (choice) {
                    case 1:
                        System.out.print("Name: ");
                        String name = sc.next();
                        System.out.print("Email: ");
                        String email = sc.next();
                        System.out.print("Salary: ");
                        double salary = sc.nextDouble();
                        addEmployee(name, email, salary);
                        break;
                    case 2:
                        viewEmployees();
                        break;
                    case 3:
                        System.out.print("ID: ");
                        int id = sc.nextInt();
                        System.out.print("New Name: ");
                        name = sc.next();
                        System.out.print("New Email: ");
                        email = sc.next();
                        System.out.print("New Salary: ");
                        salary = sc.nextDouble();
                        updateEmployee(id, name, email, salary);
                        break;
                    case 4:
                        System.out.print("ID: ");
                        id = sc.nextInt();
                        deleteEmployee(id);
                        break;
                    case 5:
                        sc.close();
                        System.exit(0);
                }
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }
}
