public class TransactionsLinkedList implements TransactionsList {

    private final Node head = new Node(null, null, null);
    private Integer size = 0;

    {
        head.next = head;
        head.prev = head;
    }

    @Override
    public void addTransaction(Transaction transaction) {
        Node tmp = new Node(head.prev, head, transaction);
        head.prev.next = tmp;
        head.prev = tmp;
        size++;
    }

    @Override
    public void removeById(String uuid) {
        Node tmp = head.next;
        while (tmp != head) {
            if (tmp.transaction.getIdentifier().equals(uuid)) {
                tmp.prev.next = tmp.next;
                tmp.next.prev = tmp.prev;
                size--;
                return;
            }
            tmp = tmp.next;
        }
        throw new TransactionNotFoundException("Transaction is not found!");
    }

    @Override
    public Transaction[] toArray() {
        Transaction[] array = new Transaction[size];
        Node tmp = head.next;
        for (int i = 0; i < size; i++) {
            array[i] = tmp.transaction;
            tmp = tmp.next;
        }
        return (array);
    }

    class Node {

        Node next;
        Node prev;
        Transaction transaction;

        Node(Node prev, Node next, Transaction transaction) {
            this.prev = prev;
            this.next = next;
            this.transaction = transaction;
        }

    }
    
}