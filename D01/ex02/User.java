public class User {

    private String name;
    private Integer balance;
    private final Integer identifier;

    public User(String name, Integer balance) {
        setBalance(balance);
        setName(name);
        this.identifier = UserIdsGenerator.getInstance().generateId();
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
        return "User {" +
                "identifier = " + identifier +
                ", name = '" + name + '\'' +
                ", balance = " + balance +
                '}';
    }

}
