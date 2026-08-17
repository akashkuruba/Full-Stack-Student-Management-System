import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.List;
import java.util.Set;

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

    public boolean addStudent(Student student) {

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

    public Student searchStudent(String usn) {

        if (studentMap.containsKey(usn)) {
            return studentMap.get(usn);
        }

        return null;
    }

    public boolean removeStudent(String usn) {

        Student student = studentMap.remove(usn);

        if (student != null) {

            students.remove(student);
            usns.remove(usn);
            recentStudents.remove(student);

            return true;
        }

        return false;
    }

    public boolean updateStudent(String oldUsn, Student updatedStudent) {

        if (!studentMap.containsKey(oldUsn)) {
            return false;
        }

        if (!oldUsn.equals(updatedStudent.getUsn())
                && usns.contains(updatedStudent.getUsn())) {
            return false;
        }

        Student oldStudent = studentMap.get(oldUsn);

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
}