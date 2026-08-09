public class Main {

    public static void main (String[] args) {

        Student s1 = new Student("Akash",21,"IT","24BBTIT004");

        Student s2 = new Student("Ranjith");

        System.out.println(s1.name);
        System.out.println(s1.age);
        System.out.println(s1.branch);
        System.out.println(s1.usn);

        System.out.println(s2.name);
    }
}