import java.util.LinkedList;
import java.util.List;
import java.util.Scanner;

public class Main {

    public static void main(String[] args) {

        StudentManager manager = new StudentManager();

        // LinkedList demo
        LinkedList<String> names = new LinkedList<>();

        names.add("Akash");
        names.add("Ranjith");

        System.out.println("LinkedList: " + names);

        names.addFirst("Rahul");
        System.out.println("After addFirst: " + names);

        names.addLast("Kiran");
        System.out.println("After addLast: " + names);

        names.removeFirst();
        System.out.println("After removeFirst: " + names);

        names.removeLast();
        System.out.println("After removeLast: " + names);


        // Create Student 1
        Student s1 = new Student(
                "Akash Kuruba",
                21,
                "CSE",
                "24BBTIT004"
        );


        // Create Student 2
        Student s2 = new Student("Ranjith");

        s2.setAge(21);
        s2.setBranch("CSE");
        s2.setUsn("24BBTIT005");


        // Add students
        try {

            boolean added1 = manager.addStudent(s1);
            System.out.println("Akash added: " + added1);

            boolean added2 = manager.addStudent(s2);
            System.out.println("Ranjith added: " + added2);

        } catch (InvalidAgeException e) {

            System.out.println(
                    "Error: " + e.getMessage()
            );
        }


        // Try duplicate student
        try {

            Student duplicateStudent = new Student(
                    "Another Student",
                    20,
                    "CSE",
                    "24BBTIT005"
            );

            boolean duplicateAdded =
                    manager.addStudent(duplicateStudent);

            System.out.println(
                    "Duplicate student added: "
                            + duplicateAdded
            );

        } catch (InvalidAgeException e) {

            System.out.println(
                    "Error: " + e.getMessage()
            );
        }


        // Display all students
        manager.displayStudents();


        // Search by USN
        try {

            Student foundStudent =
                    manager.searchStudent("24BBTIT004");

            System.out.println();
            System.out.println("Student Found:");
            System.out.println(
                    "Name: " + foundStudent.getName()
            );
            System.out.println(
                    "USN: " + foundStudent.getUsn()
            );

        } catch (StudentNotFoundException e) {

            System.out.println();
            System.out.println(
                    "Error: " + e.getMessage()
            );
        }


        // Search by Name
        try {

            Student foundByName =
                    manager.searchByName("Akash Kuruba");

            System.out.println();
            System.out.println(
                    "Student Found By Name:"
            );
            System.out.println(
                    "Name: " + foundByName.getName()
            );
            System.out.println(
                    "USN: " + foundByName.getUsn()
            );

        } catch (StudentNotFoundException e) {

            System.out.println();
            System.out.println(
                    "Error: " + e.getMessage()
            );
        }


        // Search by Branch
        List<Student> cseStudents =
                manager.searchByBranch("CSE");

        System.out.println();
        System.out.println("CSE Students:");

        for (Student student : cseStudents) {
            System.out.println(student.getName());
        }


        // Search by Age
        List<Student> ageStudents =
                manager.searchByAge(21);

        System.out.println();
        System.out.println(
                "Students with age 21:"
        );

        for (Student student : ageStudents) {
            System.out.println(student.getName());
        }


        // Search by Branch and Age
        List<Student> filteredStudents =
                manager.searchByBranchAndAge(
                        "CSE",
                        21
                );

        System.out.println();
        System.out.println(
                "CSE students aged 21:"
        );

        for (Student student : filteredStudents) {
            System.out.println(student.getName());
        }


        // Delete student with confirmation
        Scanner scanner = new Scanner(System.in);

        System.out.print(
                "\nAre you sure you want to delete "
                        + "24BBTIT004? (yes/no): "
        );

        String choice = scanner.nextLine();

        if (choice.equalsIgnoreCase("yes")) {

            try {

                boolean removed =
                        manager.removeStudent(
                                "24BBTIT004"
                        );

                System.out.println();

                if (removed) {

                    System.out.println(
                            "Student removed successfully."
                    );
                }

            } catch (StudentNotFoundException e) {

                System.out.println();
                System.out.println(
                        "Error: " + e.getMessage()
                );
            }

        } else {

            System.out.println();
            System.out.println(
                    "Delete operation cancelled."
            );
        }


        // Test deleting non-existent student
        try {

            manager.removeStudent("999999");

        } catch (StudentNotFoundException e) {

            System.out.println();
            System.out.println(
                    "Delete Error: "
                            + e.getMessage()
            );
        }


        // Verify deleted student
        try {

            manager.searchStudent("24BBTIT004");

        } catch (StudentNotFoundException e) {

            System.out.println();
            System.out.println(
                    "Search after delete: "
                            + e.getMessage()
            );
        }


        // Display students after deletion
        System.out.println();
        manager.displayStudents();

        System.out.println();

        manager.displayRecentStudents();


        // Create updated student
        Student s3 = new Student(
                "Rahul",
                22,
                "IT",
                "24BBTIT006"
        );


        // Update student
        try {

            boolean updated =
                    manager.updateStudent(
                            "24BBTIT005",
                            s3
                    );

            System.out.println();

            if (updated) {

                System.out.println(
                        "Student updated successfully."
                );
            }

        } catch (StudentNotFoundException e) {

            System.out.println();
            System.out.println(
                    "Error: " + e.getMessage()
            );

        } catch (DuplicateUsnException e) {

            System.out.println();
            System.out.println(
                    "Error: " + e.getMessage()
            );
        }


        // Display students after update
        System.out.println();
        System.out.println("Students after update:");

        manager.displayStudents();


        scanner.close();
    }
}