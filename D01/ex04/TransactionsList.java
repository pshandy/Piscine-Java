public interface TransactionsList {

    void addTransaction(Transaction transaction);

    void removeById(String uuid);

    boolean contains(String transactionId);

    Transaction[] toArray();

}
