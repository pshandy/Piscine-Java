
package edu.school21.chat.repositories;

import edu.school21.chat.exceptions.NotSavedSubEntityException;
import edu.school21.chat.models.Chatroom;
import edu.school21.chat.models.Message;
import edu.school21.chat.models.User;

import javax.sql.DataSource;
import java.sql.*;
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

    @Override
    public void save(Message message) throws NotSavedSubEntityException {

        if (message.getAuthor() == null || message.getMessageRoom() == null
                || !userExists(message.getAuthor().getID()) || !roomExist(message.getMessageRoom().getID()))
            throw new NotSavedSubEntityException("Author and/or room do not exist!");

        String query = "INSERT INTO chat.messages (author, room, text, time) VALUES ("
                + message.getAuthor().getID() + ", "
                + message.getMessageRoom().getID() + ", "
                + "'" + message.getMessageText() + "', "
                + "'" + message.getMessageTime() + "');";

        try (Connection connection = dataSource.getConnection()) {

            PreparedStatement statement = connection.prepareStatement(query, Statement.RETURN_GENERATED_KEYS);
            statement.execute();

            ResultSet resultSet = statement.getGeneratedKeys();
            resultSet.next();

            message.setID(resultSet.getLong("id"));

        } catch (SQLException e) {
            e.printStackTrace();
            throw new NotSavedSubEntityException("Entity not saved.");
        }

    }

    @Override
    public void update(Message message) throws NotSavedSubEntityException {

        if (message.getAuthor() == null || message.getMessageRoom() == null
                || !userExists(message.getAuthor().getID()) || !roomExist(message.getMessageRoom().getID()))
            throw new NotSavedSubEntityException("Author and/or room do not exist!");

        String query = "UPDATE chat.messages"
                + " SET author = " + message.getAuthor().getID()
                + ", room = " + message.getMessageRoom().getID()
                + ", text = '" + message.getMessageText() + "'"
                + ", time = " + (message.getMessageTime() == null ? "NULL" : "'" + message.getMessageTime() + "'")
                + " WHERE id = " + message.getID() + ";";

        try (Connection connection = dataSource.getConnection();
            PreparedStatement statement = connection.prepareStatement(query)) {
            statement.execute();
        } catch (SQLException e) {
            e.printStackTrace();
            throw new NotSavedSubEntityException("Entity not updated.");
        }

    }

    private boolean userExists(Long id) {

        try (Connection connection = dataSource.getConnection()) {

            Statement stm = connection.createStatement();
            ResultSet rs = stm.executeQuery("SELECT COUNT(*) FROM chat.users WHERE id = " + id);
            rs.next();
            return (rs.getInt(1) != 0);

        } catch (SQLException e) {
            e.printStackTrace();
        }

        return (true);

    }

    private boolean roomExist(Long id) {

        try (Connection connection = dataSource.getConnection()) {

            Statement stm = connection.createStatement();
            ResultSet rs = stm.executeQuery("SELECT COUNT(*) FROM chat.rooms WHERE id = " + id);
            rs.next();
            return (rs.getInt(1) != 0);

        } catch (SQLException e) {
            e.printStackTrace();
        }

        return (true);

    }

}
