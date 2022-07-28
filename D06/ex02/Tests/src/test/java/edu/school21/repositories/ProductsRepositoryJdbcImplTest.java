package edu.school21.repositories;

import edu.school21.models.Product;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;
import org.springframework.jdbc.datasource.embedded.EmbeddedDatabaseBuilder;
import org.springframework.jdbc.datasource.embedded.EmbeddedDatabaseType;

import javax.sql.DataSource;
import java.sql.SQLException;
import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

public class ProductsRepositoryJdbcImplTest {

    private DataSource dataSource;

    @BeforeEach
    void init() {
        dataSource = new EmbeddedDatabaseBuilder()
                .setType(EmbeddedDatabaseType.HSQL)
                .addScript("schema.sql")
                .addScript("data.sql")
                .build();
    }

    @ParameterizedTest
    @ValueSource(longs = {0, 1, 2, 3, 4})
    public void testUpdate(Long id) throws SQLException {

        ProductsRepository productsRepository = new ProductsRepositoryJdbcImpl(dataSource);

        Product EXPECTED_UPDATE_PRODUCT = productsRepository.findById(id).orElse(null);
        EXPECTED_UPDATE_PRODUCT.setPrice(123);

        productsRepository.update(EXPECTED_UPDATE_PRODUCT);

        Product RESULT_UPDATE_PRODUCT = productsRepository.findById(id).orElse(null);

        Assertions.assertNotNull(RESULT_UPDATE_PRODUCT);
        assertEquals(RESULT_UPDATE_PRODUCT, EXPECTED_UPDATE_PRODUCT);

    }

    @Test
    public void saveTest() {

        Product EXPECTED_SAVE_PRODUCT = new Product(1L, "ITEM", 777);

        ProductsRepository productsRepository = new ProductsRepositoryJdbcImpl(dataSource);

        productsRepository.save(EXPECTED_SAVE_PRODUCT);

        assertEquals(new Product(EXPECTED_SAVE_PRODUCT.getId(), "ITEM", 777), productsRepository.findById(EXPECTED_SAVE_PRODUCT.getId()).get());
    }

    @Test
    public void findAllTest() throws Exception {

        ProductsRepository productsRepository = new ProductsRepositoryJdbcImpl(dataSource);

        long EXPECTED_SIZE = 5;

        final List<Product> EXPECTED_FINDAll_PRODUCT  = Arrays.asList(
                new Product(0l, "Item 1", 50),
                new Product(1l, "Item 2", 100),
                new Product(2l, "Item 3", 120),
                new Product(3l, "Item 4", 150),
                new Product(4l, "Item 5", 300)
        );

        Assertions.assertEquals(EXPECTED_SIZE, productsRepository.findAll().size());

        List<Product> RESULT_FINDAll_PRODUCT = productsRepository.findAll();

        Assertions.assertEquals(RESULT_FINDAll_PRODUCT, EXPECTED_FINDAll_PRODUCT);
    }

    @ParameterizedTest
    @ValueSource(longs = {0, 1, 2, 3, 4})
    public void testDelete(long id) {

        ProductsRepository productsRepository = new ProductsRepositoryJdbcImpl(dataSource);
        productsRepository.delete(id);
        Assertions.assertThrows(ProductsRepositoryJdbcImpl.UserNotFoundException.class, () -> productsRepository.findById(id));

    }

    @ParameterizedTest
    @ValueSource(longs = {0, 1, 2, 3, 4})
    public void testFindByIdTrue(long id) {
        ProductsRepositoryJdbcImpl productsRepository = new ProductsRepositoryJdbcImpl(dataSource);
        Assertions.assertTrue(productsRepository.findById(id).isPresent());
    }

    @ParameterizedTest
    @ValueSource(longs = {5, 6, 7, 8})
    public void testFindByIdFalse(long id) throws SQLException {
        ProductsRepositoryJdbcImpl productsRepository = new ProductsRepositoryJdbcImpl(dataSource);
        Assertions.assertThrows(ProductsRepositoryJdbcImpl.UserNotFoundException.class, () -> productsRepository.findById(id));
    }

}
