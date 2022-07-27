

package edu.school21.chat.repositories;

import edu.school21.chat.models.Chatroom;
import edu.school21.chat.models.Message;
import edu.school21.chat.models.User;

import javax.sql.DataSource;
import java.sql.*;
import java.time.LocalDateTime;
import java.util.Optional;

public class MessagesRepositoryJdbcImpl implements MessagesRepository {

    private final DataSource dataSource;

    public MessagesRepositoryJdbcImpl(DataSource dataSource) {
        this.dataSource = dataSource;
    }

    @Override
    public Optional<Message> findById(Long id) {

        Message message = null;

        try (Connection connection = dataSource.getConnection()) {

            Statement stm = connection.createStatement();
            ResultSet rs = stm.executeQuery("SELECT * FROM chat.messages WHERE id = " + id);

            if (!rs.next())
                return (Optional.empty());

            String text = rs.getString("text");
            Long authorId = rs.getLong("author");
            Long roomId = rs.getLong("room");
            Timestamp timestamp = rs.getTimestamp("time");

            rs = stm.executeQuery("SELECT * FROM Chat.users WHERE id = " + authorId);
            rs.next();
            User author = new User(authorId, rs.getString("login"),
                    rs.getString("password"));

            rs = stm.executeQuery("SELECT * FROM chat.rooms WHERE id = " + roomId);
            rs.next();
            Chatroom room = new Chatroom(roomId, rs.getString("name"), author);

            message = new Message(id, author, room, text, timestamp);

        } catch (SQLException e) {
            e.printStackTrace();
        }

        return Optional.of(message);
    }
}
