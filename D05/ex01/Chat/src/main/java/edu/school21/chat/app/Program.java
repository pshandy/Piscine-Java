package edu.school21.chat.app;

import com.zaxxer.hikari.HikariDataSource;
import edu.school21.chat.models.Message;
import edu.school21.chat.repositories.MessagesRepository;
import edu.school21.chat.repositories.MessagesRepositoryJdbcImpl;

import java.sql.SQLException;
import java.util.Optional;
import java.util.Scanner;

public class Program {

    public static void main(String[] args) {

        Scanner in = new Scanner(System.in);

        HikariDataSource dataSource = new HikariDataSource();
        dataSource.setJdbcUrl("jdbc:postgresql://localhost:1111/pshandy");
        dataSource.setUsername("pshandy");
        dataSource.setPassword("pshandy");

        MessagesRepository repositoryJdbc = null;
        repositoryJdbc = new MessagesRepositoryJdbcImpl(dataSource);

        System.out.println("Enter a message ID");
        Integer id = in.nextInt();

        while (id != -1) {

            Optional<Message> t = repositoryJdbc.findById(id.longValue());

            if (t.isPresent()) {
                System.out.println(t.get());
            } else {
                System.out.println("Not present.");
            }

            System.out.println("Enter a message ID");
            id = in.nextInt();

        }
        dataSource.close();

    }

}