package edu.school21.repositories;

import edu.school21.models.Product;

import javax.sql.DataSource;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class ProductsRepositoryJdbcImpl implements ProductsRepository {

    DataSource dataSource;

    public ProductsRepositoryJdbcImpl(DataSource dataSource) {
        this.dataSource = dataSource;
    }

    @Override
    public List<Product> findAll() {

        final String SQL = "SELECT * FROM products";
        List<Product> products = new ArrayList<>();

        try (Connection connection = dataSource.getConnection()) {

            Statement statement = connection.createStatement();
            ResultSet resultSet = statement.executeQuery(SQL);

            while(resultSet.next()) {
                Product product = new Product(resultSet.getLong("id"),
                        resultSet.getString("name"),
                        resultSet.getInt("price"));
                products.add(product);
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }

        return (products);

    }

    @Override
    public Optional<Product> findById(Long id) {

        Product product = null;

        String query = "SELECT * FROM products WHERE id = " + id;

        try (Connection connection = dataSource.getConnection()) {

            PreparedStatement preparedStatement = connection.prepareStatement(query);

            ResultSet resultSet = preparedStatement.executeQuery();
            resultSet.next();

            product = new Product(resultSet.getLong("id"),
                    resultSet.getString("name"),
                    resultSet.getInt("price"));

        } catch (SQLException e) {
            throw new UserNotFoundException("User not found!");
        }

        return (product == null ? Optional.empty() : Optional.of(product));

    }

    @Override
    public void update(Product product) {

        String query = "UPDATE products SET name = '" + product.getName()
                + "', price = " + product.getPrice()
                + " WHERE id = " + product.getId();

        try (Connection connection = dataSource.getConnection()) {

            PreparedStatement preparedStatement = connection.prepareStatement(query);
            preparedStatement.executeUpdate();

        } catch (SQLException e) {
            e.printStackTrace();
        }

    }

    @Override
    public void save(Product product) {

        String query = "INSERT INTO products (name, price) VALUES ('" + product.getName() + "', " + product.getPrice() + ")";

        try (Connection connection = dataSource.getConnection()) {

            PreparedStatement preparedStatement = connection.prepareStatement(query);
            preparedStatement.executeUpdate();

            PreparedStatement statement = connection.prepareStatement(query, Statement.RETURN_GENERATED_KEYS);
            statement.execute();

            ResultSet resultSet = statement.getGeneratedKeys();
            resultSet.next();

            product.setId(resultSet.getLong("id"));

        } catch (SQLException e) {
            e.printStackTrace();
        }

    }

    @Override
    public void delete(Long id) {

        String query = "DELETE FROM products WHERE id = " + id;

        try (Connection connection = dataSource.getConnection()) {

            PreparedStatement statement = connection.prepareStatement(query);
            statement.execute();

        } catch (SQLException e) {
            e.printStackTrace();
        }

    }

    class UserNotFoundException extends RuntimeException {
        public UserNotFoundException(String msg) {
            super(msg);
        }
    }
}
