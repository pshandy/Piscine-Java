import java.util.UUID;

public class TransactionsService {

    private final UserList userList = new UserArrayList();

    public Integer addUser(String name, Integer balance) {
        User user = new User(name, balance);
        userList.addUser(user);
        return (user.getIdentifier());
    }

    public String getUserBalance(Integer userId) {
        return (userList.getUserById(userId).getName() + " - " + userList.getUserById(userId).getBalance());
    }

    public void makeTransaction(Integer recipientId, Integer senderId, Integer amount) {

        if (amount > userList.getUserById(senderId).getBalance()) {
            throw new IllegalTransactionException("Not enough money on sender's balance");
        }

        User recipient = userList.getUserById(recipientId);
        User sender = userList.getUserById(senderId);

        String uuid = UUID.randomUUID().toString();
        recipient.getTransactionsList().addTransaction(new Transaction(recipient, sender, amount, uuid));
        sender.getTransactionsList().addTransaction(new Transaction(recipient, sender, -amount, uuid));

        sender.setBalance(sender.getBalance() - amount);
        recipient.setBalance(recipient.getBalance() + amount);

    }

    public Transaction[] getUserTransactions(Integer userId) {
        return (userList.getUserById(userId).getTransactionsList().toArray());
    }

    public String removeTransaction(String transactionId, Integer userId) {
        Transaction deleted = userList.getUserById(userId).getTransactionsList().removeById(transactionId);
        if (deleted.getCategory() == Transaction.Category.CREDIT) {
            return ("Transfer To " + deleted.getRecipient().toString() + " " + deleted.getTransferAmount() + " removed");
        } else {
            return ("Transfer From " + deleted.getSender().toString() + " " + deleted.getTransferAmount() + " removed");
        }
    }

    public Transaction[] getUnpaired() {
        TransactionsList unpaired = new TransactionsLinkedList();
        for (Integer i = 0; i < userList.size(); i++) {
            User user = userList.getUserByIndex(i);
            for (Transaction t : user.getTransactionsList().toArray()) {
                if (t.getCategory() == Transaction.Category.DEBIT) {
                    if (!t.getSender().getTransactionsList().contains(t.getIdentifier())) {
                        unpaired.addTransaction(t);
                    }
                } else {
                    if (!(t.getRecipient().getTransactionsList().contains(t.getIdentifier()))) {
                        unpaired.addTransaction(t);
                    }
                }
            }
        }
        return (unpaired.toArray());
    }

    public User getUser(Integer userId) {
        return (userList.getUserById(userId));
    }

}
