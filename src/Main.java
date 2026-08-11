public class Main {

    public static void main(String[] args) {

        Student s1 = new Student("Akash Kuruba",21,"CSE","24BBTIT004");

        Student s2 = new Student("Ranjith");

        System.out.println(s1.getName());
        System.out.println(s1.getAge());
        System.out.println(s1.getBranch());
        System.out.println(s1.getUsn());

        System.out.println(s2.getName());

        s1.display();
    }
}
