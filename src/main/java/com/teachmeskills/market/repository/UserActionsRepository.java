package com.teachmeskills.market.repository;

import com.teachmeskills.market.annotation.LeadTimed;
import com.teachmeskills.market.model.UserActions;
import com.teachmeskills.market.utils.config.database.DatabaseConfig;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.sql.Timestamp;

import static com.teachmeskills.market.utils.constant.SQLQuery.INSERT_INTO_IS_SAVE_USER_ACTION;

@Repository
public class UserActionsRepository {

    private final DatabaseConfig databaseConfig;
    private static final Logger logger = LoggerFactory.getLogger(UserActionsRepository.class);

    @Autowired
    public UserActionsRepository(DatabaseConfig databaseConfig) {
        this.databaseConfig = databaseConfig;
    }

    @LeadTimed("-> Worked out method isSaveUserAction")
    public Boolean isSaveUserAction(UserActions userActions) {
        if (userActions == null) {
            logger.error("User Actions object is null");
            return false;
        }
        if (userActions.getUserId() == null || userActions.getProductId() == null || userActions.getAction() == null) {
            logger.error("User Actions fields cannot be null -> userId={}, productId={}, action={}",
                    userActions.getUserId(), userActions.getProductId(), userActions.getAction());
            return false;
        }

        try (Connection connection = databaseConfig.getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(INSERT_INTO_IS_SAVE_USER_ACTION)) {

            preparedStatement.setLong(1, userActions.getUserId());
            preparedStatement.setLong(2, userActions.getProductId());
            preparedStatement.setString(3, userActions.getAction());
            preparedStatement.setTimestamp(4, Timestamp.valueOf(userActions.getActionTime().toLocalDateTime()));

            boolean isSaved = preparedStatement.executeUpdate() > 0;
            if (isSaved) {
                logger.info("User Actions saved successfully -> {}", userActions);
            } else {
                logger.warn("User Actions not saved -> {}", userActions);
            }
            return isSaved;
        } catch (SQLException e) {
            logger.error("Error saving UserActions -> {}: {}", userActions, e.getMessage());
            return false;
        }
    }
}