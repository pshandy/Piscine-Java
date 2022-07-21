import java.util.UUID;

public class TransactionsService {

    private final UserList userList = new UserArrayList();

    public void addUser(String name, Integer balance) {
        userList.addUser(new User(name, balance));
    }

    public Integer getUserBalance(Integer userId) {
        return (userList.getUserById(userId).getBalance());
    }

    public void makeTransaction(Integer recipientId, Integer senderId, Integer amount) {

        if (amount > getUserBalance(senderId)) {
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

    public void removeTransaction(String transactionId, Integer userId) {
        userList.getUserById(userId).getTransactionsList().removeById(transactionId);
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

}
