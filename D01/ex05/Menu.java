import java.util.Scanner;

public class Menu {

    private final TransactionsService tr = new TransactionsService();

    private Integer mode = 0;

    public Menu(String mode) {
        if (mode.equals("dev")) {
            this.mode = 1;
        }
    }

    private Integer getNumber(Scanner in) {

        Integer number = 0;

        do {
            while (!in.hasNextInt()) {
                System.err.println("Number must be Integer!");
                in.next();
            }
            number = in.nextInt();
            if (number < 0) {
                System.err.println("Number must be positive!");
            }

        } while (number < 0);

        return (number);

    }

    public void showMenu() {

        System.out.println("" +
                "1. Add a user\n" +
                "2. View user balances\n" +
                "3. Perform a transfer\n" +
                "4. View all transactions for a specific user\n" +
                "5. DEV - remove a transfer by ID\n" +
                "6. DEV - check transfer validity\n" +
                "7. Finish execution");

    }

    public void showFooter() {
        System.out.println("---------------------------------------------------------");
    }

    public void addUser(Scanner in) {

        System.out.println("Enter a user name and a balance");
        String name = in.next();
        Integer balance = getNumber(in);

        Integer userId = tr.addUser(name, balance);
        System.out.println("User with id = " + userId + " is added");

    }

    public void viewBalance(Scanner in) {

        System.out.println("Enter a user ID");
        Integer userId = getNumber(in);

        try {
            System.out.println(tr.getUserBalance(userId));
        } catch (UserNotFoundException e) {
            System.err.println("User not found!");
        }

    }

    public void addTransfer(Scanner in) {

        System.out.println("Enter a sender ID, a recipient ID, and a transfer amount");

        Integer sender = getNumber(in);

        Integer recipient = getNumber(in);

        while (recipient.equals(sender)) {
            System.err.println("Ids must be different!");
            recipient = getNumber(in);
        }

        Integer amount = getNumber(in);

        try {
            tr.makeTransaction(recipient, sender, amount);
        } catch (UserNotFoundException e) {
            System.err.println("User not found!");
            return;
        } catch (IllegalTransactionException e) {
            System.err.println("Not enough money on sender's balance!");
            return;
        }

        System.out.println("The transfer is completed");

    }

    public void viewHistory(Scanner in) {

        System.out.println("Enter a user ID");
        Integer userId = getNumber(in);

        try {
            for (Transaction t : tr.getUserTransactions(userId)) {
                System.out.println(t.toStringHistory());
            }
        } catch (UserNotFoundException e) {
            System.err.println("User not found!");
        }

    }

    public void removeTransfer(Scanner in) {

        if (mode != 1) {
            System.err.println("No rights!");
            return;
        }

        System.out.println("Enter a user ID and a transfer ID");

        Integer userId = getNumber(in);
        String transactionId = in.nextLine().trim();

        if (transactionId.equals("")) {
            transactionId = in.nextLine().trim();
        }

        try {
            System.out.println(tr.removeTransaction(transactionId, userId));
        } catch (TransactionNotFoundException e) {
            System.err.println("Transaction not found!");
        } catch (UserNotFoundException e) {
            System.err.println("User not found!");
        }

    }

    public void checkTransfer() {

        if (mode != 1) {
            System.err.println("No rights!");
            return;
        }

        Transaction[] unpaired = tr.getUnpaired();
        for (Transaction t : unpaired) {
            System.out.println(t.toStringUnpaired());
        }

    }

}
