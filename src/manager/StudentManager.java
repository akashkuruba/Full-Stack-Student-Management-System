package manager;

import model.Student;
import exception.DuplicateUsnException;
import exception.InvalidAgeException;
import exception.StudentNotFoundException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.LinkedList;
import java.util.List;
import java.util.Set;
import java.io.FileWriter;
import java.io.IOException;
import java.io.BufferedReader;
import java.io.FileReader;
import java.sql.PreparedStatement;
import java.sql.Connection;
import db.DBConnection;


public class StudentManager {

    private List<Student> students;
    private Map<String, Student> studentMap;
    private Set<String> usns;
    private LinkedList<Student> recentStudents;

    public StudentManager() {
        students = new ArrayList<>();
        studentMap = new HashMap<>();
        usns = new HashSet<>();
        recentStudents = new LinkedList<>();
    }

    public boolean addStudent(Student student) throws InvalidAgeException, java.sql.SQLException {

        if (student.getAge() < 18) {
            throw new InvalidAgeException("Age must be 18 or above");
        }

        boolean added = usns.add(student.getUsn());

        if (!added) {
            return false;
        }

        students.add(student);
        studentMap.put(student.getUsn(), student);
        recentStudents.addFirst(student);

        Connection connection = DBConnection.getConnection();

        String sql = "INSERT INTO students (usn, name, age, department) VALUES (?, ?, ?, ?)";

        PreparedStatement statement = connection.prepareStatement(sql);

        statement.setString(1, student.getUsn());
        statement.setString(2, student.getName());
        statement.setInt(3, student.getAge());
        statement.setString(4, student.getBranch());

        statement.executeUpdate();

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

        Student student = studentMap.get(usn);

        if (student != null) {
            return student;
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
            throws StudentNotFoundException, java.sql.SQLException {

        Student student = studentMap.remove(usn);

        if (student != null) {

            students.remove(student);
            usns.remove(usn);
            recentStudents.remove(student);

            Student.decrementStudentCount();

            Connection connection = DBConnection.getConnection();

            String sql = "DELETE FROM students WHERE usn = ?";

            PreparedStatement statement = connection.prepareStatement(sql);

            statement.setString(1, usn);

            statement.executeUpdate();

            return true;
        }

        throw new StudentNotFoundException(
                "Student with USN " + usn + " not found."
        );
    }

    public boolean updateStudent(String oldUsn, Student updatedStudent)
            throws StudentNotFoundException, DuplicateUsnException, java.sql.SQLException {

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

        Connection connection = DBConnection.getConnection();

        String sql = "UPDATE students SET usn = ?, name = ?, age = ?, department = ? WHERE usn = ?";

        PreparedStatement statement = connection.prepareStatement(sql);

        statement.setString(1, updatedStudent.getUsn());
        statement.setString(2, updatedStudent.getName());
        statement.setInt(3, updatedStudent.getAge());
        statement.setString(4, updatedStudent.getBranch());
        statement.setString(5, oldUsn);

        statement.executeUpdate();

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

    public Student searchStudentFromDatabase(String usn)
            throws java.sql.SQLException, StudentNotFoundException {

        Connection connection = DBConnection.getConnection();

        String sql = "SELECT * FROM students WHERE usn = ?";

        PreparedStatement statement = connection.prepareStatement(sql);

        statement.setString(1, usn);

        java.sql.ResultSet result = statement.executeQuery();

        if (result.next()) {

            String name = result.getString("name");
            int age = result.getInt("age");
            String branch = result.getString("department");

            return new Student(name, age, branch, usn);
        }

        throw new StudentNotFoundException(
                "Student with USN " + usn + " not found in database."
        );
    }

        public void loadStudentsFromDatabase() throws java.sql.SQLException {

            Connection connection = DBConnection.getConnection();

            String sql = "SELECT * FROM students";

            PreparedStatement statement = connection.prepareStatement(sql);

            java.sql.ResultSet result = statement.executeQuery();

            while (result.next()) {

                String usn = result.getString("usn");
                String name = result.getString("name");
                int age = result.getInt("age");
                String branch = result.getString("department");

                Student student = new Student(name, age, branch, usn);

                students.add(student);
                studentMap.put(usn, student);
                usns.add(usn);
                recentStudents.addFirst(student);
            }
        }
    }
