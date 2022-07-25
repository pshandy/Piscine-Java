package edu.school21.chat;

import java.util.List;
import java.util.Objects;

public class Chatroom {

    private Long ID;
    private String name;
    private User owner;
    private List<Message> messageList;

    public Chatroom(Long ID, String name, User owner) {
        this.name = name;
        this.owner = owner;
        this.ID = ID;
    }

    public Chatroom(Long ID, String name, User owner, List<Message> messageList) {
        this(ID, name, owner);
        this.messageList = messageList;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public User getOwner() {
        return owner;
    }

    public void setOwner(User owner) {
        this.owner = owner;
    }

    @Override
    public String toString() {
        return ("Name: " + name
                + ". ID: " + ID
                + ". Owner: " + owner + ".");
    }

    @Override
    public int hashCode() {
        return Objects.hash(ID, name, owner, messageList);
    }

    @Override
    public boolean equals(Object obj) {

        if (this == obj)
            return (true);

        if (!(obj instanceof  Chatroom))
            return (false);

        Chatroom room = (Chatroom) obj;

        return (Objects.equals(ID, room.ID)
                && name.equals(room.name)
                && owner.equals(room.owner)
                && messageList.equals(room.messageList));

    }

}