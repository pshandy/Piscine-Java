package edu.school21.chat.app;

import com.zaxxer.hikari.HikariDataSource;
import edu.school21.chat.models.Chatroom;
import edu.school21.chat.models.Message;
import edu.school21.chat.models.User;
import edu.school21.chat.repositories.MessagesRepository;
import edu.school21.chat.repositories.MessagesRepositoryJdbcImpl;

import java.sql.Timestamp;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Scanner;

public class Program {

    public static void main(String[] args) {

        Scanner in = new Scanner(System.in);

        HikariDataSource dataSource = new HikariDataSource();
        dataSource.setJdbcUrl("jdbc:postgresql://localhost:1111/pshandy");
        dataSource.setUsername("pshandy");
        dataSource.setPassword("pshandy");

        User creator = new User(3L, "user", "user", new ArrayList(), new ArrayList());
        User author = creator;
        Chatroom room = new Chatroom(22L, "room", creator, new ArrayList());
        Message message = new Message(null, author, room, "Hello!", Timestamp.valueOf(LocalDateTime.now()));
        MessagesRepository messagesRepository = new MessagesRepositoryJdbcImpl(dataSource);

        messagesRepository.save(message);
        System.out.println(message.getID());
        System.out.println(messagesRepository.findById(14L).get().toString());

    }

}
