import java.util.Scanner;

public class Main {

    public static void main(String[] args) {

        StudentManager manager = new StudentManager();

        manager.loadStudentsFromFile();

        System.out.println(
                "Total students created: "
                        + Student.getStudentCount()
        );

        System.out.println(
                "College: " + Student.getCollegeName()
        );

        Scanner scanner = new Scanner(System.in);

        int choice = 0;

        while (choice != 7) {

            System.out.println();
            System.out.println(
                    "===== Student Management System ====="
            );
            System.out.println("1. Add Student");
            System.out.println("2. Display Students");
            System.out.println("3. Search Student");
            System.out.println("4. Update Student");
            System.out.println("5. Delete Student");
            System.out.println("6. Save Students");
            System.out.println("7. Exit");

            System.out.print("Enter your choice: ");

            choice = scanner.nextInt();

            System.out.println();

            switch (choice) {

                case 1:

                    scanner.nextLine(); // clear leftover Enter

                    System.out.print("Enter student name: ");
                    String name = scanner.nextLine();

                    System.out.print("Enter student age: ");
                    int age = scanner.nextInt();

                    scanner.nextLine(); // clear leftover Enter

                    System.out.print("Enter student branch: ");
                    String branch = scanner.nextLine();

                    System.out.print("Enter student USN: ");
                    String usn = scanner.nextLine();

                    Student newStudent = new Student(
                            name,
                            age,
                            branch,
                            usn
                    );

                    try {

                        boolean added =
                                manager.addStudent(newStudent);

                        if (added) {
                            System.out.println(
                                    "Student added successfully."
                            );
                        } else {
                            System.out.println(
                                    "Student with this USN already exists."
                            );
                        }

                    } catch (InvalidAgeException e) {

                        System.out.println(
                                "Error: " + e.getMessage()
                        );
                    }

                    break;

                case 2:

                    manager.displayStudents();

                    break;

                case 3:

                    scanner.nextLine(); // clear leftover Enter

                    System.out.print("Enter USN to search: ");
                    String searchUsn = scanner.nextLine();

                    try {

                        Student foundStudent =
                                manager.searchStudent(searchUsn);

                        System.out.println();
                        System.out.println("Student Found:");
                        System.out.println("Name: " + foundStudent.getName());
                        System.out.println("Age: " + foundStudent.getAge());
                        System.out.println("Branch: " + foundStudent.getBranch());
                        System.out.println("USN: " + foundStudent.getUsn());

                    } catch (StudentNotFoundException e) {

                        System.out.println(
                                "Search Error: " + e.getMessage()
                        );
                    }

                    break;

                case 4:

                    scanner.nextLine(); // clear leftover Enter

                    System.out.print("Enter old USN: ");
                    String oldUsn = scanner.nextLine();

                    System.out.print("Enter new name: ");
                    String newName = scanner.nextLine();

                    System.out.print("Enter new age: ");
                    int newAge = scanner.nextInt();

                    scanner.nextLine(); // clear leftover Enter

                    System.out.print("Enter new branch: ");
                    String newBranch = scanner.nextLine();

                    System.out.print("Enter new USN: ");
                    String newUsn = scanner.nextLine();

                    Student updatedStudent = new Student(
                            newName,
                            newAge,
                            newBranch,
                            newUsn
                    );

                    try {

                        boolean updated =
                                manager.updateStudent(
                                        oldUsn,
                                        updatedStudent
                                );

                        if (updated) {
                            System.out.println(
                                    "Student updated successfully."
                            );
                        }

                    } catch (StudentNotFoundException e) {

                        System.out.println(
                                "Update Error: " + e.getMessage()
                        );

                    } catch (DuplicateUsnException e) {

                        System.out.println(
                                "Update Error: " + e.getMessage()
                        );
                    }

                    break;

                case 5:

                    scanner.nextLine(); // clear leftover Enter

                    System.out.print("Enter USN to delete: ");
                    String deleteUsn = scanner.nextLine();

                    try {

                        boolean deleted =
                                manager.removeStudent(deleteUsn);

                        if (deleted) {
                            System.out.println(
                                    "Student removed successfully."
                            );
                        }

                    } catch (StudentNotFoundException e) {

                        System.out.println(
                                "Delete Error: " + e.getMessage()
                        );
                    }

                    break;

                case 6:
                    manager.saveStudentsToFile();

                    System.out.println(
                            "Students saved successfully."
                    );
                    break;

                case 7:
                    System.out.println(
                            "Exiting Student Management System..."
                    );
                    break;

                default:
                    System.out.println(
                            "Invalid choice. Please try again."
                    );
            }
        }

        scanner.close();
    }
}