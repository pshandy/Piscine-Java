package edu.school21.chat.app;

import com.zaxxer.hikari.HikariDataSource;
import edu.school21.chat.models.Message;
import edu.school21.chat.models.User;
import edu.school21.chat.repositories.MessagesRepository;
import edu.school21.chat.repositories.MessagesRepositoryJdbcImpl;

import java.sql.Timestamp;
import java.time.LocalDateTime;
import java.util.Optional;
import java.util.Scanner;

public class Program {

    public static void main(String[] args) {

        Scanner in = new Scanner(System.in);

        HikariDataSource dataSource = new HikariDataSource();
        dataSource.setJdbcUrl("jdbc:postgresql://localhost:1111/pshandy");
        dataSource.setUsername("pshandy");
        dataSource.setPassword("pshandy");

        MessagesRepository messagesRepository = new MessagesRepositoryJdbcImpl(dataSource);
        Optional<Message> messageOptional = messagesRepository.findById(1L);
        if (messageOptional.isPresent()) {
            User us = new User(2L, "user", "user");
            Message message = messageOptional.get();
            message.setMessageText("HI");
            message.setAuthor(us);
            message.setMessageTime(Timestamp.valueOf(LocalDateTime.now()));
            messagesRepository.update(message);
        } else {
            System.out.println("Not present!");
        }
        messageOptional = messagesRepository.findById(1L);
        System.out.println(messageOptional.get().toString());
    }

}