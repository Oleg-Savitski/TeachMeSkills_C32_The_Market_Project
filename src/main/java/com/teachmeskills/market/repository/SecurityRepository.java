package com.teachmeskills.market.repository;

import com.teachmeskills.market.model.Security;
import com.teachmeskills.market.utils.config.database.DatabaseConfig;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.sql.Timestamp;

import static com.teachmeskills.market.utils.constant.SQLQuery.INSERT_INTO_IS_SAVE_SECURITY;

@Repository
public class SecurityRepository {

    private final DatabaseConfig databaseConfig;
    private static final Logger logger = LoggerFactory.getLogger(SecurityRepository.class);

    @Autowired
    public SecurityRepository(DatabaseConfig databaseConfig) {
        this.databaseConfig = databaseConfig;
    }

    public Boolean isSaveSecurityUser(Security security) {
        if (security == null) {
            logger.error("Security object is null");
            return false;
        }
        if (security.getLogin() == null || security.getPassword() == null || security.getRole() == null) {
            logger.error("Security fields cannot be null -> login={}, password={}, role={}",
                    security.getLogin(), security.getPassword(), security.getRole());
            return false;
        }

        try (Connection connection = databaseConfig.getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(INSERT_INTO_IS_SAVE_SECURITY)) {

            preparedStatement.setString(1, security.getLogin());
            preparedStatement.setString(2, security.getPassword());
            preparedStatement.setString(3, security.getRole().toString());
            preparedStatement.setTimestamp(4, Timestamp.valueOf(security.getCreated().toLocalDateTime()));
            preparedStatement.setTimestamp(5, Timestamp.valueOf(security.getUpdated().toLocalDateTime()));
            preparedStatement.setLong(6, security.getUserId());

            boolean isSaved = preparedStatement.executeUpdate() > 0;
            if (isSaved) {
                logger.info("Security saved successfully -> {}", security);
            } else {
                logger.warn("Security not saved -> {}", security);
            }
            return isSaved;
        } catch (SQLException e) {
            logger.error("Error saving security -> {}: {}", security, e.getMessage());
            return false;
        }
    }
}