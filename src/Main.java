public class Main {

    public static void main (String[] args) {

        Student s1 = new Student("Akash",21,"IT","24BBTIT004");
        s1.setName("Akash Kuruba");
        s1.setAge(21);
        s1.setBranch("CSE");
        s1.setUsn("24BBTIT004");

        Student s2 = new Student("Ranjith");

        System.out.println(s1.getName());
        System.out.println(s1.getAge());
        System.out.println(s1.getBranch());
        System.out.println(s1.getUsn());

        System.out.println(s2.getName());
    }
}