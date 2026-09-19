package app;

import db.DBConnection;
import manager.StudentManager;
import model.Student;

import java.sql.Connection;
import java.util.Scanner;

public class Main {

    public static void main(String[] args) {

        Connection connection = DBConnection.getConnection();

        if (connection != null) {
            System.out.println("Database connected successfully!");
        }

        StudentManager manager = new StudentManager();
        Scanner scanner = new Scanner(System.in);

        try {
            manager.loadStudentsFromDatabase();
        } catch (java.sql.SQLException e) {
            System.out.println("Database Error: " + e.getMessage());
        }

        while (true) {

            System.out.println("\n===== Student Management System =====");
            System.out.println("1. Add Student");
            System.out.println("2. Display Students");
            System.out.println("3. Search Student");
            System.out.println("4. Update Student");
            System.out.println("5. Delete Student");
            System.out.println("6. Save Students");
            System.out.println("7. Exit");
            System.out.print("Enter your choice: ");

            int choice = scanner.nextInt();
            scanner.nextLine();

            try {

                switch (choice) {

                    case 1:
                        System.out.print("Enter name: ");
                        String name = scanner.nextLine();

                        System.out.print("Enter age: ");
                        int age = scanner.nextInt();
                        scanner.nextLine();

                        System.out.print("Enter department: ");
                        String department = scanner.nextLine();

                        System.out.print("Enter USN: ");
                        String usn = scanner.nextLine();

                        Student student =
                                new Student(name, age, department, usn);

                        if (manager.addStudent(student)) {
                            System.out.println("Student added successfully.");
                        } else {
                            System.out.println("USN already exists.");
                        }
                        break;

                    case 2:
                        manager.displayStudents();
                        break;

                    case 3:
                        System.out.print("Enter USN to search: ");
                        String searchUsn = scanner.nextLine();

                        System.out.println(manager.searchStudentFromDatabase(searchUsn));
                        break;

                    case 4:
                        System.out.print("Enter old USN: ");
                        String oldUsn = scanner.nextLine();

                        System.out.print("Enter new name: ");
                        String newName = scanner.nextLine();

                        System.out.print("Enter new age: ");
                        int newAge = scanner.nextInt();
                        scanner.nextLine();

                        System.out.print("Enter new department: ");
                        String newDepartment = scanner.nextLine();

                        System.out.print("Enter new USN: ");
                        String newUsn = scanner.nextLine();

                        Student updatedStudent =
                                new Student(newName, newAge,
                                        newDepartment, newUsn);

                        manager.updateStudent(oldUsn, updatedStudent);

                        System.out.println("Student updated successfully.");
                        break;

                    case 5:
                        System.out.print("Enter USN to delete: ");
                        String deleteUsn = scanner.nextLine();

                        manager.removeStudent(deleteUsn);

                        System.out.println("Student deleted successfully.");
                        break;

                    case 6:
                        manager.saveStudentsToFile();
                        System.out.println("Students saved successfully.");
                        break;

                    case 7:
                        System.out.println("Exiting...");
                        scanner.close();
                        return;

                    default:
                        System.out.println("Invalid choice.");

                }

            } catch (Exception e) {
                System.out.println(e.getMessage());
            }
        }
    }
}
