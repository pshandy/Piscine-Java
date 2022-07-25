package edu.school21.chat;

import java.time.LocalDateTime;
import java.util.Objects;

public class Message {

    private Long ID;
    private User author;
    private Chatroom messageRoom;
    private String messageText;
    private LocalDateTime messageTime;

    public Message(Long ID, User author, Chatroom messageRoom, String messageText) {

        this.author = author;
        this.messageRoom = messageRoom;
        this.messageText = messageText;
        this.messageTime =  LocalDateTime.now();
        this.ID = ID;

    }

    public Long getID() {
        return ID;
    }

    public User getAuthor() {
        return author;
    }

    public Chatroom getMessageRoom() {
        return messageRoom;
    }

    public String getMessageText() {
        return messageText;
    }

    public LocalDateTime getMessageTime() {
        return messageTime;
    }

    @Override
    public String toString() {
        return ("Text: " + messageText
                + ". ID: " + ID
                + ". Author: " + author
                + ". Chat Room: " + messageRoom
                + ". Time: " + messageTime);
    }

    @Override
    public int hashCode() {
        return Objects.hash(ID, author, messageRoom, messageText, messageTime);
    }

    @Override
    public boolean equals(Object obj) {

        if (this == obj)
            return (true);

        if (!(obj instanceof  Message))
            return (false);

        Message msg = (Message) obj;

        return (Objects.equals(ID, msg.ID)
                && author.equals(msg.author)
                && messageRoom.equals(msg.messageRoom)
                && messageText.equals(msg.messageText)
                && messageTime.equals(msg.messageTime));

    }

}
