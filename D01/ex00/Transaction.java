import java.util.UUID;

public class Transaction {

    private enum Category {
        DEBIT,
        CREDIT
    }

    ;

    private enum Status {
        FAIL,
        SUCCESS
    }

    ;

    private String identifier;
    private User recipient;
    private User sender;
    private Category category;
    private Integer transferAmount;

    private Status status;

    public Transaction(User recipient, User sender, Integer amount) {

        setRecipient(recipient);
        setSender(sender);
        setTransferAmount(amount);

        if (amount < 0)
            setCategory(Category.CREDIT);
        else
            setCategory(Category.DEBIT);

        if (sender.getBalance() < 0 || sender.getBalance() < amount) {
            setStatus(Status.FAIL);
        } else {
            sender.setBalance(sender.getBalance() - amount);
            recipient.setBalance(recipient.getBalance() + amount);
            setStatus(Status.SUCCESS);
        }

        this.identifier = UUID.randomUUID().toString();

    }

    public String getIdentifier() {
        return identifier;
    }

    public User getRecipient() {
        return recipient;
    }

    public void setRecipient(User recipient) {
        this.recipient = recipient;
    }

    public User getSender() {
        return sender;
    }

    public void setSender(User sender) {
        this.sender = sender;
    }

    public Category getCategory() {
        return category;
    }

    public void setCategory(Category category) {
        this.category = category;
    }

    public Integer getTransferAmount() {
        return transferAmount;
    }

    public void setTransferAmount(Integer transferAmount) {
        this.transferAmount = transferAmount;
    }

    public Status getStatus() {
        return status;
    }

    public void setStatus(Status status) {
        this.status = status;
    }

    @Override
    public String toString() {
        return "Transaction {" +
                "identifier = " + identifier +
                ", recipient = " + recipient +
                ", sender = " + sender +
                ", amount = " + transferAmount +
                ", category = " + category +
                ", status = " + status +
                '}';
    }

}
