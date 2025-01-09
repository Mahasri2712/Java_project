package Workshpo_project;
import java.util.ArrayList;
import java.util.Scanner;

class Student {
    private String name;
    private int rollNumber;
    private String grade;
    private int attendance;

    public Student(String name, int rollNumber, String grade, int attendance) {
        this.name = name;
        this.rollNumber = rollNumber;
        this.grade = grade;
        this.attendance = attendance;
    }

    public String getName() {
        return name;
    }

    public int getRollNumber() {
        return rollNumber;
    }

    public String getGrade() {
        return grade;
    }

    public int getAttendance() {
        return attendance;
    }

    public void setGrade(String grade) {
        this.grade = grade;
    }

    public void setAttendance(int attendance) {
        this.attendance = attendance;
    }

    @Override
    public String toString() {
    	System.out.println("  Name\tRollNumber\tGrade\tAttendance");
        return " "+ name+"\t   " + rollNumber + "\t\t  " + grade + "\t    " + attendance + "%";
    }
}

class StudentManagementSystem {
    private ArrayList<Student> studentList;

    public StudentManagementSystem() {
        studentList = new ArrayList<>();
    }

    public void addStudent(String name, int rollNumber, String grade, int attendance) {
        Student student = new Student(name, rollNumber, grade, attendance);
        studentList.add(student);
        System.out.println("Student added successfully!");
    }

    public void displayStudents() {
        if (studentList.isEmpty()) {
            System.out.println("No students in the record.");
            return;
        }
        for (Student student : studentList) {
            System.out.println(student);
        }
    }

    public void updateStudent(int rollNumber, String grade, int attendance) {
        for (Student student : studentList) {
            if (student.getRollNumber() == rollNumber) {
                student.setGrade(grade);
                student.setAttendance(attendance);
                System.out.println("Student details updated successfully!");
                return;
            }
        }
        System.out.println("Student not found!");
    }

    public void deleteStudent(int rollNumber) {
        for (Student student : studentList) {
            if (student.getRollNumber() == rollNumber) {
                studentList.remove(student);
                System.out.println("Student deleted successfully!");
                return;
            }
        }
        System.out.println("Student not found!");
    }
}

public class Student_Management
{
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        StudentManagementSystem sms = new StudentManagementSystem();

        while (true) {
            System.out.println("\n--- Student Management System ---");
            System.out.println("1. Add Student");
            System.out.println("2. Display Students");
            System.out.println("3. Update Student");
            System.out.println("4. Delete Student");
            System.out.println("5. Exit");
            System.out.print("Enter your choice: ");
            int choice = scanner.nextInt();
            scanner.nextLine();  // Consume newline character

            switch (choice) {
                case 1: // Add Student
                    System.out.print("Enter Student Name: ");
                    String name = scanner.nextLine();
                    System.out.print("Enter Roll Number: ");
                    int rollNumber = scanner.nextInt();
                    scanner.nextLine();  // Consume newline
                    System.out.print("Enter Grade: ");
                    String grade = scanner.nextLine();
                    System.out.print("Enter Attendance Percentage: ");
                    int attendance = scanner.nextInt();
                    sms.addStudent(name, rollNumber, grade, attendance);
                    break;

                case 2: // Display Students
                    sms.displayStudents();
                    break;

                case 3: // Update Student
                    System.out.print("Enter Roll Number to update: ");
                    int updateRollNumber = scanner.nextInt();
                    scanner.nextLine();  // Consume newline
                    System.out.print("Enter new Grade: ");
                    String newGrade = scanner.nextLine();
                    System.out.print("Enter new Attendance Percentage: ");
                    int newAttendance = scanner.nextInt();
                    sms.updateStudent(updateRollNumber, newGrade, newAttendance);
                    break;

                case 4: // Delete Student
                    System.out.print("Enter Roll Number to delete: ");
                    int deleteRollNumber = scanner.nextInt();
                    sms.deleteStudent(deleteRollNumber);
                    break;

                case 5: // Exit
                    System.out.println("Exiting system...");
                    System.exit(0);
                    break;

                default:
                    System.out.println("Invalid choice. Please try again.");
            }
        }
    }
}

