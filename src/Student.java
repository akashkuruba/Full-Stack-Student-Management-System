public class Student extends Person {

    private String branch;
    private String usn;

    Student() {
        super("",0);
    }

    Student(String name, int age, String branch, String usn) {

        super(name, age);

        this.branch = branch;
        this.usn = usn;
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
}
