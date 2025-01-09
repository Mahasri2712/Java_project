package Workshpo_project;
import java.io.*;
import java.util.*;
class Employee {
    private int id;
    private String name;
    private String department;
    public Employee(int id, String name, String department) {
        this.id = id;
        this.name = name;
        this.department = department;
    }
    public int getId() {
        return id;
    }
    public void setId(int id) {
        this.id = id;
    }
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
    public String getDepartment() {
        return department;
    }
    public void setDepartment(String department) {
        this.department = department;
    }
    public void display() {
        System.out.println("ID: " + id + "\nName: " + name + "\nDepartment: " + department);
    }
    public void saveToFile() {
        try (FileWriter fw = new FileWriter("employees.txt", true);
             BufferedWriter bw = new BufferedWriter(fw)) {
            bw.write(id + "," + name + "," + department);
            bw.newLine();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    public static List<Employee> loadFromFile() {
        List<Employee> employees = new ArrayList<>();
        try (BufferedReader br = new BufferedReader(new FileReader("employees.txt"))) {
            String line;
            while ((line = br.readLine()) != null) {
                String[] data = line.split(",");
                Employee employee = new Employee(Integer.parseInt(data[0]), data[1], data[2]);
                employees.add(employee);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return employees;
    }
}
public class EmployeeManagementSystem {
    private static Scanner scanner = new Scanner(System.in);
    public static void main(String[] args) {
        List<Employee> employees = Employee.loadFromFile();
        while (true) {
            System.out.println("Employee Management System");
            System.out.println("1. Add Employee");
            System.out.println("2. Update Employee");
            System.out.println("3. Search Employee");
            System.out.println("4. Display All Employees");
            System.out.println("5. Exit");
            System.out.print("Enter your choice: ");
            int choice = scanner.nextInt();
            scanner.nextLine(); // Consume newline character
            switch (choice) {
                case 1:
                    addEmployee(employees);
                    break;
                case 2:
                    updateEmployee(employees);
                    break;
                case 3:
                    searchEmployee(employees);
                    break;
                case 4:
                    displayAllEmployees(employees);
                    break;
                case 5:
                    System.out.println("Exiting...");
                    System.exit(0);
                default:
                    System.out.println("Invalid choice, please try again.");
            }
        }
    }
    private static void addEmployee(List<Employee> employees) {
        System.out.print("Enter Employee ID: ");
        int id = scanner.nextInt();
        scanner.nextLine(); // Consume newline character
        System.out.print("Enter Employee Name: ");
        String name = scanner.nextLine();
        System.out.print("Enter Employee Department: ");
        String department = scanner.nextLine();
        Employee newEmployee = new Employee(id, name, department);
        newEmployee.saveToFile();
        employees.add(newEmployee);
        System.out.println("Employee added successfully!");
    }
    private static void updateEmployee(List<Employee> employees) {
        System.out.print("Enter Employee ID to update: ");
        int id = scanner.nextInt();
        scanner.nextLine(); // Consume newline character
        Employee employeeToUpdate = null;
        for (Employee employee : employees) {
            if (employee.getId() == id) {
                employeeToUpdate = employee;
                break;
            }
        }
        if (employeeToUpdate != null) {
            System.out.print("Enter new Name: ");
            String newName = scanner.nextLine();
            System.out.print("Enter new Department: ");
            String newDepartment = scanner.nextLine();
            employeeToUpdate.setName(newName);
            employeeToUpdate.setDepartment(newDepartment);
            System.out.println("Employee updated successfully!");
        } else {
            System.out.println("Employee not found!");
        }
    }
    private static void searchEmployee(List<Employee> employees) {
        System.out.print("Enter Employee ID or Department to search: ");
        String query = scanner.nextLine();
        boolean found = false;
        for (Employee employee : employees) {
            if (String.valueOf(employee.getId()).equals(query) || employee.getDepartment().equalsIgnoreCase(query)) {
                employee.display();
                found = true;
            }
        }
        if (!found) {
            System.out.println("No employee found with the given ID or Department.");
        }
    }
    private static void displayAllEmployees(List<Employee> employees) {
        if (employees.isEmpty()) {
            System.out.println("No employees to display.");
        } else {
            for (Employee employee : employees) {
                employee.display();
            }
        }
    }
}

