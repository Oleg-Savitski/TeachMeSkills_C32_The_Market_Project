package com.teachmeskills.market.repository;

import com.teachmeskills.market.annotation.LeadTimed;
import com.teachmeskills.market.model.Product;
import com.teachmeskills.market.utils.config.database.DatabaseConfig;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

import static com.teachmeskills.market.utils.constant.SQLQuery.*;

@Repository
public class ProductRepository {

    private final DatabaseConfig databaseConfig;
    private static final Logger logger = LoggerFactory.getLogger(ProductRepository.class);

    @Autowired
    public ProductRepository(DatabaseConfig databaseConfig) {
        this.databaseConfig = databaseConfig;
    }

    @LeadTimed("-> Worked out method createProduct")
    public Boolean createProduct(Product product) {
        if (product == null) {
            logger.error("Product object is null");
            return false;
        }
        if (product.getName() == null || product.getPrice() == null) {
            logger.error("Product fields cannot be null -> name={}, price={}",
                    product.getName(), product.getPrice());
            return false;
        }

        try (Connection connection = databaseConfig.getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(INSERT_INTO_IS_SAVE_PRODUCT)) {

            preparedStatement.setString(1, product.getName());
            preparedStatement.setDouble(2, product.getPrice());
            preparedStatement.setTimestamp(3, Timestamp.valueOf(product.getCreated().toLocalDateTime()));
            preparedStatement.setTimestamp(4, Timestamp.valueOf(product.getUpdated().toLocalDateTime()));

            boolean isSaved = preparedStatement.executeUpdate() > 0;
            if (isSaved) {
                logger.info("Product saved successfully -> {}", product);
            } else {
                logger.warn("Product not saved -> {}", product);
            }
            return isSaved;
        } catch (SQLException e) {
            logger.error("Error saving product -> {}: {}", product, e.getMessage());
            return false;
        }
    }

    @LeadTimed("-> Worked out method getProductById")
    public Product getProductById(Long id) {
        Product product = null;
        try (Connection connection = databaseConfig.getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(SELECT_PRODUCT_BY_ID)) {

            preparedStatement.setLong(1, id);
            ResultSet resultSet = preparedStatement.executeQuery();

            if (resultSet.next()) {
                product = new Product();
                product.setId(resultSet.getLong("id"));
                product.setName(resultSet.getString("name"));
                product.setPrice(resultSet.getDouble("price"));
                product.setCreated(Timestamp.valueOf(resultSet.getTimestamp("created").toLocalDateTime()));
                product.setUpdated(Timestamp.valueOf(resultSet.getTimestamp("updated").toLocalDateTime()));
                logger.info("Product retrieved successfully -> {}", product);
            } else {
                logger.warn("Product not found with id -> {}", id);
            }
        } catch (SQLException e) {
            logger.error("Error retrieving product with id {}: {}", id, e.getMessage());
        }
        return product;
    }

    @LeadTimed("-> Worked out method updateProduct")
    public Boolean updateProduct(Product product) {
        if (product == null || product.getId() == null) {
            logger.error("Product object or ID is null");
            return false;
        }

        try (Connection connection = databaseConfig.getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(UPDATE_PRODUCT)) {

            preparedStatement.setString(1, product.getName());
            preparedStatement.setDouble(2, product.getPrice());
            preparedStatement.setTimestamp(3, Timestamp.valueOf(product.getUpdated().toLocalDateTime()));
            preparedStatement.setLong(4, product.getId());

            boolean isUpdated = preparedStatement.executeUpdate() > 0;
            if (isUpdated) {
                logger.info("Product updated successfully -> {}", product);
            } else {
                logger.warn("Product not updated -> {}", product);
            }
            return isUpdated;
        } catch (SQLException e) {
            logger.error("Error updating product -> {}: {}", product, e.getMessage());
            return false;
        }
    }

    @LeadTimed("-> Worked out method deleteProduct")
    public Boolean deleteProduct(Long id) {
        if (id == null) {
            logger.error("Product ID is null");
            return false;
        }

        try (Connection connection = databaseConfig.getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(DELETE_PRODUCT)) {

            preparedStatement.setLong(1, id);
            boolean isDeleted = preparedStatement.executeUpdate() > 0;
            if (isDeleted) {
                logger.info("Product deleted successfully with id -> {}", id);
            } else {
                logger.warn("Product not deleted with id -> {}", id);
            }
            return isDeleted;
        } catch (SQLException e) {
            logger.error("Error deleting product with id {}: {}", id, e.getMessage());
            return false;
        }
    }

    @LeadTimed("-> Worked out method getAllProducts")
    public List<Product> getAllProducts() {
        List<Product> products = new ArrayList<>();
        try (Connection connection = databaseConfig.getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(SELECT_ALL_PRODUCTS);
             ResultSet resultSet = preparedStatement.executeQuery()) {

            while (resultSet.next()) {
                Product product = new Product();
                product.setId(resultSet.getLong("id"));
                product.setName(resultSet.getString("name"));
                product.setPrice(resultSet.getDouble("price"));
                product.setCreated(Timestamp.valueOf(resultSet.getTimestamp("created").toLocalDateTime()));
                product.setUpdated(Timestamp.valueOf(resultSet.getTimestamp("updated").toLocalDateTime()));
                products.add(product);
            }
            logger.info("Retrieved all products successfully");
        } catch (SQLException e) {
            logger.error("Error retrieving all products: {}", e.getMessage());
        }
        return products;
    }
}