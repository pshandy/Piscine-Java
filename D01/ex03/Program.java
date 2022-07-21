public class Program {

    public static void main(String[] args) {

        User Bob = new User("Bob", 0);
        User Tim = new User("Tim", 100);

        TransactionsList list = new TransactionsLinkedList();

        System.out.println("---------------------");
        System.out.println("Initial balance:");

        System.out.println(Tim.getBalance());
        System.out.println(Bob.getBalance());

        System.out.println("---------------------");

        list.addTransaction(new Transaction(Bob, Tim, 100));
        list.addTransaction(new Transaction(Tim, Bob, 100));
        list.addTransaction(new Transaction(Bob, Tim, 100));
        list.addTransaction(new Transaction(Bob, Tim, 100));

        System.out.println("Transaction statuses:");

        for (Transaction t : list.toArray()) {
            System.out.println(t.getStatus());
        }

        System.out.println("---------------------");
        System.out.println("After transactions balance:");

        System.out.println(Tim.getBalance());
        System.out.println(Bob.getBalance());

        System.out.println("---------------------");

        TransactionsList list2 = new TransactionsLinkedList();
        list2.addTransaction(new Transaction(Bob, Tim, 100));
        list2.addTransaction(new Transaction(Tim, Bob, 100));

        System.out.println("Transaction ids before removal in new array:");

        System.out.println(Tim.getBalance());
        for (Transaction t : list2.toArray()) {
            System.out.println(">" + t.getIdentifier());
        }

        System.out.println("---------------------");

        for (Transaction t : list2.toArray()) {
            list2.removeById(t.getIdentifier());
            break;
        }

        System.out.println("Transaction ids after removal in new array:");

        for (Transaction t : list2.toArray()) {
            System.out.println(">" + t.getIdentifier());
        }

    }
    
}
