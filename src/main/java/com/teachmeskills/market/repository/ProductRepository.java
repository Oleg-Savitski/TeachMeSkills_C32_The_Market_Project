package com.teachmeskills.market.repository;

import com.teachmeskills.market.model.Product;
import com.teachmeskills.market.utils.config.database.DatabaseConfig;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.sql.Timestamp;

import static com.teachmeskills.market.utils.constant.SQLQuery.INSERT_INTO_IS_SAVE_PRODUCT;

@Repository
public class ProductRepository {

    private final DatabaseConfig databaseConfig;
    private static final Logger logger = LoggerFactory.getLogger(ProductRepository.class);

    @Autowired
    public ProductRepository(DatabaseConfig databaseConfig) {
        this.databaseConfig = databaseConfig;
    }

    public Boolean isSaveProduct(Product product) {
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
}