package edu.school21.chat.models;

import java.sql.Timestamp;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Objects;

public class Message {

    private Long ID;
    private User author;
    private Chatroom messageRoom;
    private String messageText;
    private Timestamp messageTime;

    public Message(Long ID, User author, Chatroom messageRoom, String messageText, Timestamp messageTime) {

        this.author = author;
        this.messageRoom = messageRoom;
        this.messageText = messageText;
        this.messageTime = messageTime;
        this.ID = ID;

    }

    public Long getID() {
        return ID;
    }

    public void setID(Long ID) {
        this.ID = ID;
    }

    public User getAuthor() {
        return author;
    }

    public void setAuthor(User author) {
        this.author = author;
    }

    public Chatroom getMessageRoom() {
        return messageRoom;
    }

    public void setMessageRoom(Chatroom messageRoom) {
        this.messageRoom = messageRoom;
    }

    public String getMessageText() {
        return messageText;
    }

    public void setMessageText(String messageText) {
        this.messageText = messageText;
    }

    public void setMessageTime(Timestamp messageTime) {
        this.messageTime = messageTime;
    }

    public Timestamp getMessageTime() {
        return messageTime;
    }

    @Override
    public String toString() {
        return "Message {\n" +
                "\tid = " + ID + ",\n" +
                "\tauthor = " + author + ",\n" +
                "\tchatroom = " + messageRoom + ",\n" +
                "\ttext = \"" + messageText + '\"' + ",\n" +
                "\tmessageDateTime = " + messageTime +
                "\n}";
    }

    @Override
    public int hashCode() {
        return Objects.hash(ID, author, messageRoom, messageText, messageTime);
    }

    @Override
    public boolean equals(Object obj) {

        if (this == obj)
            return (true);

        if (!(obj instanceof Message))
            return (false);

        Message msg = (Message) obj;

        return (Objects.equals(ID, msg.ID)
                && author.equals(msg.author)
                && messageRoom.equals(msg.messageRoom)
                && messageText.equals(msg.messageText)
                && messageTime.equals(msg.messageTime));

    }

}