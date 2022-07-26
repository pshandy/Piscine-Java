package edu.school21.chat.app;

import com.zaxxer.hikari.HikariDataSource;
import edu.school21.chat.repositories.MessagesRepository;
import edu.school21.chat.repositories.MessagesRepositoryJdbcImpl;

import java.sql.SQLException;
import java.util.Scanner;

public class Program {

    public static void main(String[] args) throws SQLException {

        Scanner in = new Scanner(System.in);

        HikariDataSource dataSource = new HikariDataSource();
        dataSource.setJdbcUrl("jdbc:postgresql://localhost:5432/mydb");
        dataSource.setUsername("pshandy");
        dataSource.setPassword("pshandy");

        System.out.println("Enter a message ID");
        Integer id = in.nextInt();

        MessagesRepository repositoryJdbc = new MessagesRepositoryJdbcImpl(dataSource);
        System.out.println(repositoryJdbc.findById(id.longValue()).get().toString());
        dataSource.close();

    }

}
