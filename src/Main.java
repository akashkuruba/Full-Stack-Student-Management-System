import java.util.LinkedList;

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


        // Update Ranjith
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

            } else {

                System.out.println(
                        "Updated USN already exists."
                );
            }

        } catch (StudentNotFoundException e) {

            System.out.println();
            System.out.println(
                    "Error: " + e.getMessage()
            );
        }


        manager.displayStudents();
    }
}