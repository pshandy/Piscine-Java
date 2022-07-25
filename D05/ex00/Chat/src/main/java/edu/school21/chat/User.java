package edu.school21.chat;

import java.util.List;
import java.util.Objects;

public class User {

    private Long ID;
    private String login;
    private String password;
    private List<Chatroom> createdRooms;
    private List<Chatroom> invRooms;

    public User(Long ID, String login, String password) {
        this.ID = ID;
        this.login = login;
        this.password = password;
    }

    public User(Long ID, String login, String password, List<Chatroom> createdRooms) {
        this(ID, login, password);
        this.createdRooms = createdRooms;
    }

    public User(Long ID, String login, String password,  List<Chatroom> createdRooms, List<Chatroom> invRooms) {
        this(ID, login, password, createdRooms);
        this.invRooms = invRooms;
    }

    public String getLogin() {
        return login;
    }

    public void setLogin(String login) {
        this.login = login;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    @Override
    public String toString() {
        return ("Login: " + login
                + ". ID: " + ID + ".");
    }

    @Override
    public int hashCode() {
        return Objects.hash(ID, login, password, createdRooms, invRooms);
    }

    @Override
    public boolean equals(Object obj) {

        if (this == obj)
            return (true);

        if (!(obj instanceof  User))
            return (false);

        User user = (User) obj;

        return (ID.equals(user.ID)
                && login.equals(user.login)
                && password.equals(user.password)
                && createdRooms.equals(user.createdRooms)
                && invRooms.equals(user.invRooms));

    }


}
