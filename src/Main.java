import java.util.LinkedList;
import java.util.List;

public class Main {

    public static void main(String[] args) {

        StudentManager manager = new StudentManager();

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
            boolean added2 = manager.addStudent(s2);

            System.out.println("Akash added: " + added1);
            System.out.println("Ranjith added: " + added2);


            // Duplicate student
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


        // Display students
        manager.displayStudents();


        // Search student
        try {

            Student foundStudent =
                    manager.searchStudent("24BBTIT004");

            System.out.println("Student Found:");
            System.out.println(
                    "Name: " + foundStudent.getName()
            );
            System.out.println(
                    "USN: " + foundStudent.getUsn()
            );

        } catch (StudentNotFoundException e) {

            System.out.println(
                    "Error: " + e.getMessage()
            );
        }

        // Search student by name
        try {

            Student foundStudent =
                    manager.searchByName("Akash Kuruba");

            System.out.println();
            System.out.println("Student Found By Name:");
            System.out.println(
                    "Name: " + foundStudent.getName()
            );
            System.out.println(
                    "USN: " + foundStudent.getUsn()
            );

        } catch (StudentNotFoundException e) {

            System.out.println(
                    "Error: " + e.getMessage()
            );
        }

        // Search students by branch
        List<Student> cseStudents =
                manager.searchByBranch("CSE");

        System.out.println();
        System.out.println("CSE Students:");

        for (Student student : cseStudents) {

            System.out.println(
                    student.getName()
            );
        }

        // Search students by age
        List<Student> studentsWithAge21 =
                manager.searchByAge(21);

        System.out.println();
        System.out.println("Students with age 21:");

        for (Student student : studentsWithAge21) {

            System.out.println(
                    student.getName()
            );
        }

        // Search students by branch and age
        List<Student> filteredStudents =
                manager.searchByBranchAndAge("CSE", 21);

        System.out.println();

        System.out.println("CSE students aged 21:");

        if (filteredStudents.isEmpty()) {

            System.out.println("No students found.");

        } else {

            for (Student student : filteredStudents) {

                System.out.println(
                        student.getName()
                );
            }
        }

        // Remove Akash
        try {

            boolean removed =
                    manager.removeStudent("24BBTIT004");

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


        manager.displayStudents();

        System.out.println();

        manager.displayRecentStudents();


        Student s3 = new Student(
                "Rahul",
                22,
                "IT",
                "24BBTIT006"
        );

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

        System.out.println();
        System.out.println("Students after update:");

        manager.displayStudents();
    }
}

