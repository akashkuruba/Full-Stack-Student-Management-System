import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.List;
import java.util.Set;
import java.io.FileWriter;
import java.io.IOException;
import java.io.BufferedReader;
import java.io.FileReader;

public class StudentManager {

    private List<Student> students;
    private HashMap<String, Student> studentMap;
    private Set<String> usns;
    private LinkedList<Student> recentStudents;

    public StudentManager() {
        students = new ArrayList<>();
        studentMap = new HashMap<>();
        usns = new HashSet<>();
        recentStudents = new LinkedList<>();
    }

    public boolean addStudent(Student student) throws InvalidAgeException {

        if (student.getAge() < 18) {
            throw new InvalidAgeException("Age must be 18 or above");
        }

        if (usns.contains(student.getUsn())) {
            return false;
        }

        students.add(student);
        studentMap.put(student.getUsn(), student);
        usns.add(student.getUsn());

        recentStudents.addFirst(student);

        return true;
    }

    public void displayStudents() {

        System.out.println("Total Students: " + students.size());

        for (Student student : students) {

            System.out.println("Name: " + student.getName());
            System.out.println("Age: " + student.getAge());
            System.out.println("Branch: " + student.getBranch());
            System.out.println("USN: " + student.getUsn());

            System.out.println();
        }
    }

    public void displayRecentStudents() {

        System.out.println("Recently Added Students:");

        for (Student student : recentStudents) {
            System.out.println(student.getName());
        }
    }

    public Student searchStudent(String usn)
            throws StudentNotFoundException {

        if (studentMap.containsKey(usn)) {
            return studentMap.get(usn);
        }

        throw new StudentNotFoundException(
                "Student with USN " + usn + " not found."
        );
    }

    public Student searchByName(String name)
            throws StudentNotFoundException {

        for (Student student : students) {

            if (student.getName().equalsIgnoreCase(name)) {
                return student;
            }
        }

        throw new StudentNotFoundException(
                "Student with name " + name + " not found."
        );
    }

    public List<Student> searchByBranch(String branch) {

        List<Student> result = new ArrayList<>();

        for (Student student : students) {

            if (student.getBranch().equalsIgnoreCase(branch)) {
                result.add(student);
            }
        }

        return result;
    }

    public List<Student> searchByAge(int age) {

        List<Student> result = new ArrayList<>();

        for (Student student : students) {

            if (student.getAge() == age) {
                result.add(student);
            }
        }

        return result;
    }

    public List<Student> searchByBranchAndAge(String branch, int age) {

        List<Student> result = new ArrayList<>();

        for (Student student : students) {

            if (student.getBranch().equalsIgnoreCase(branch)
                    && student.getAge() == age) {

                result.add(student);
            }
        }

        return result;
    }

    public boolean removeStudent(String usn)
            throws StudentNotFoundException {

        Student student = studentMap.remove(usn);

        if (student != null) {

            students.remove(student);
            usns.remove(usn);
            recentStudents.remove(student);

            Student.decrementStudentCount();

            return true;
        }

        throw new StudentNotFoundException(
                "Student with USN " + usn + " not found."
        );
    }

    public boolean updateStudent(
            String oldUsn,
            Student updatedStudent
    ) throws StudentNotFoundException, DuplicateUsnException {

        if (!studentMap.containsKey(oldUsn)) {

            throw new StudentNotFoundException(
                    "Student with USN " + oldUsn + " not found."
            );
        }

        if (!oldUsn.equals(updatedStudent.getUsn())
                && usns.contains(updatedStudent.getUsn())) {

            throw new DuplicateUsnException(
                    "USN " + updatedStudent.getUsn()
                            + " already belongs to another student."
            );
        }

        Student oldStudent = studentMap.get(oldUsn);

        Student.decrementStudentCount();

        students.remove(oldStudent);
        students.add(updatedStudent);

        studentMap.remove(oldUsn);
        studentMap.put(updatedStudent.getUsn(), updatedStudent);

        usns.remove(oldUsn);
        usns.add(updatedStudent.getUsn());

        recentStudents.remove(oldStudent);
        recentStudents.addFirst(updatedStudent);

        return true;
    }

    public void saveStudentsToFile() {

        try {

            FileWriter writer =
                    new FileWriter("students.txt");

            for (Student student : students) {

                writer.write(
                        student.getName() + ","
                                + student.getAge() + ","
                                + student.getBranch() + ","
                                + student.getUsn() + "\n"
                );
            }

            writer.close();

            System.out.println(
                    "Students saved to file successfully."
            );

        } catch (IOException e) {

            System.out.println(
                    "File Error: " + e.getMessage()
            );
        }
    }
    public void loadStudentsFromFile() {

        students.clear();
        studentMap.clear();
        usns.clear();
        recentStudents.clear();

        Student.resetStudentCount();

        try {

            BufferedReader reader =
                    new BufferedReader(
                            new FileReader("students.txt")
                    );

            String line;

            while ((line = reader.readLine()) != null) {

                String[] data = line.split(",");

                String name = data[0];
                int age = Integer.parseInt(data[1]);
                String branch = data[2];
                String usn = data[3];

                Student student = new Student(
                        name,
                        age,
                        branch,
                        usn
                );

                students.add(student);
                studentMap.put(usn, student);
                usns.add(usn);
                recentStudents.addFirst(student);

                System.out.println("Name: " + name);
                System.out.println("Age: " + age);
                System.out.println("Branch: " + branch);
                System.out.println("USN: " + usn);
                System.out.println();
            }

            reader.close();

        } catch (IOException e) {

            System.out.println(
                    "File Error: " + e.getMessage()
            );
        }
    }
}