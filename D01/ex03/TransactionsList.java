public interface TransactionsList {

    void addTransaction(Transaction transaction);

    void removeById(String uuid);

    Transaction[] toArray();

}
