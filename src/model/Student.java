package model;

public class Student extends Person {

    private static int studentCount = 0;

    private static final String COLLEGE_NAME = "CMR University";

    public static String getCollegeName() {
        return COLLEGE_NAME;
    }

    public final void displayCollegeRule() {
        System.out.println("Students must follow college rules.");
    }

    private String branch;
    private String usn;

    Student() {
        super("",0);
    }

    public Student(String name, int age, String branch, String usn) {

        super(name, age);

        this.branch = branch;
        this.usn = usn;

        studentCount++;
    }

    public static void decrementStudentCount() {
        studentCount--;
    }

    public static void resetStudentCount() {
        studentCount = 0;
    }

    public static int getStudentCount() {
        return studentCount;
    }

    Student(String name) {
        this(name,0, "", "");
    }

    public String getBranch() {
        return branch;
    }
    public String getUsn() {
        return usn;
    }

    public void setBranch(String branch) {
        this.branch = branch;
    }
    public void setUsn(String usn) {
        this.usn = usn;
    }

    @Override
    public void display(){
        System.out.println("I am a student");
    }

    @Override
    public String toString() {
        return "Name: " + getName()
                + "\nAge: " + getAge()
                + "\nBranch: " + branch
                + "\nUSN: " + usn;
    }
}
