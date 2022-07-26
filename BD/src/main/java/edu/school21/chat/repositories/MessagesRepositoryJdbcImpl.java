package edu.school21.chat.repositories;

import edu.school21.chat.models.Chatroom;
import edu.school21.chat.models.Message;
import edu.school21.chat.models.User;

import javax.sql.DataSource;
import java.sql.*;
import java.util.Optional;

public class MessagesRepositoryJdbcImpl implements MessagesRepository {

    private final Connection connection;

    public MessagesRepositoryJdbcImpl(DataSource dataSource) throws SQLException {
        this.connection = dataSource.getConnection();
    }

    @Override
    public Optional<Message> findById(Long id) {

        Message message = null;

        try (Statement stm = connection.createStatement()) {

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
                    rs.getString("password"), null, null);

            rs = stm.executeQuery("SELECT * FROM chat.rooms WHERE id = " + roomId);
            rs.next();
            Chatroom room = new Chatroom(roomId, rs.getString("name"),
                    null, null);

            message = new Message(id, author, room, text, timestamp.toLocalDateTime());

        } catch (SQLException e) {
            e.printStackTrace();
        }

        return Optional.of(message);
    }



}
