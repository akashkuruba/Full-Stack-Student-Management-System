public class Student {

    private String name;
    private int age;
    private String branch;
    private String usn;

    Student() {

    }

    Student(String name, int age, String branch, String usn) {

        this.name = name;
        this.age = age;
        this.branch = branch;
        this.usn = usn;
    }

    Student(String name) {

        this.name = name;
    }

    public String getName() {
        return name;
    }
    public int getAge() {
        return age;
    }
    public String getBranch() {
        return branch;
    }
    public String getUsn() {
        return usn;
    }
    public void setName(String name) {
        this.name = name;
    }
    public void setAge(int age) {
        if (age >= 0) {
            this.age = age;
        }
    }
    public void setBranch(String branch) {
        this.branch = branch;
    }
    public void setUsn(String usn) {
        this.usn = usn;
    }
}
