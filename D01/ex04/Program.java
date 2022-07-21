public class Program {

    public static void main(String[] args) {

        TransactionsService transactionsService = new TransactionsService();

        System.out.println("------------------");
        transactionsService.addUser("Bill", 250);
        transactionsService.addUser("Tom", 0);

        System.out.println("Bill: " + transactionsService.getUserBalance(1));
        System.out.println("Tom: " + transactionsService.getUserBalance(2));

        System.out.println("------------------");
        transactionsService.makeTransaction(2, 1, 100);
        transactionsService.makeTransaction(1, 2, 100);

        System.out.println("Bill: " + transactionsService.getUserBalance(1));
        System.out.println("Tom: " + transactionsService.getUserBalance(2));

        System.out.println("------------------");

        Transaction[] userTransactions = transactionsService.getUserTransactions(1);
        System.out.println("Bill: ");

        for (Transaction t : userTransactions) {
            System.out.println(t.toStringHistory());
        }

        userTransactions = transactionsService.getUserTransactions(2);
        System.out.println("Tom: ");

        for (Transaction t : userTransactions) {
            System.out.println(t.toStringHistory());
        }

        System.out.println("------------------");

        System.out.println("Deleted first transaction");

        transactionsService.removeTransaction(transactionsService
                .getUserTransactions(1)[0].getIdentifier(), 1);

        userTransactions = transactionsService.getUserTransactions(1);
        System.out.println("Bill: ");

        for (Transaction t : userTransactions) {
            System.out.println(t.toStringHistory());
        }

        userTransactions = transactionsService.getUserTransactions(2);
        System.out.println("Tom: ");

        for (Transaction t : userTransactions) {
            System.out.println(t.toStringHistory());
        }

        System.out.println("------------------");

        System.out.println("Unpaired:");
        Transaction[] unpaired = transactionsService.getUnpaired();
        for (Transaction t : unpaired) {
            System.out.println(t.toStringUnpaired());
        }

    }
}
