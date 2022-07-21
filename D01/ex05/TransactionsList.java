public interface TransactionsList {

    void addTransaction(Transaction transaction);

    Transaction removeById(String uuid);

    boolean contains(String transactionId);

    Transaction[] toArray();

}
