public class User {

    private final Integer identifier;
    private final TransactionsList transactionsList = new TransactionsLinkedList();
    private String name;
    private Integer balance;

    public User(String name, Integer balance) {
        setBalance(balance);
        setName(name);
        this.identifier = UserIdsGenerator.getInstance().generateId();
    }

    @Override
    public boolean equals(Object obj) {
        return super.equals(obj);
    }

    public TransactionsList getTransactionsList() {
        return transactionsList;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name == null ? "Default User" : name;
    }

    public Integer getBalance() {
        return balance;
    }

    public void setBalance(Integer balance) {
        this.balance = balance == null || balance < 0 ? 0 : balance;
    }

    public Integer getIdentifier() {
        return identifier;
    }

    @Override
    public String toString() {
        return (name + "(id = " + identifier + ")");
    }

}
