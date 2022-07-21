public class Transaction {

    private String identifier;
    private User recipient;
    private User sender;
    private Category category;
    private Integer transferAmount;

    public Transaction(User recipient, User sender, Integer amount, String uuid) {

        setRecipient(recipient);
        setSender(sender);
        setTransferAmount(amount);
        setIdentifier(uuid);

        if (amount < 0)
            setCategory(Category.CREDIT);
        else
            setCategory(Category.DEBIT);

    }

    public String getIdentifier() {
        return identifier;
    }

    public void setIdentifier(String identifier) {
        this.identifier = identifier;
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

    @Override
    public String toString() {
        return (identifier);
    }

    public String toStringHistory() {
        if (category == Category.CREDIT) {
            return ("To " + recipient.toString() + " " + transferAmount + " with id = " + identifier);
        } else {
            return ("From " + sender.toString() + " " + transferAmount + " with id = " + identifier);
        }
    }

    public String toStringUnpaired() {
        if (category == Category.DEBIT) {
            return (recipient.toString() + " has an unacknowledged transfer id = "
                    + identifier + " from " + sender.toString() + " for " + transferAmount);
        } else {
            return (sender.toString() + " has an unacknowledged transfer id = "
                    + identifier + " to " + recipient.toString() + " for " + transferAmount);
        }
    }

    enum Category {
        DEBIT,
        CREDIT
    }

}
