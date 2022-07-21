public class Program {

    public static void main(String[] args) {
        User Bob = new User(null, null);
        User Bill = new User("Bill", 0);
        User Max = new User("Max", null);
        User Tim = new User("Max", 200);

        System.out.println(Bob.toString());
        System.out.println(Bill.toString());
        System.out.println(Max.toString());
        System.out.println(Tim.toString());

        System.out.println("----------------------");

        Transaction t = new Transaction(Bob, Tim, 200);
        System.out.println(Bob.toString());
        System.out.println(Tim.toString());
        System.out.println(t.toString());

    }
}
