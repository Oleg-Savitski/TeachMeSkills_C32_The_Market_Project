package com.teachmeskills.market.repository;

import com.teachmeskills.market.annotation.LeadTimed;
import com.teachmeskills.market.model.User;
import com.teachmeskills.market.utils.config.database.DatabaseConfig;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.sql.Timestamp;

import static com.teachmeskills.market.utils.constant.SQLQuery.INSERT_INTO_IS_SAVE_USER;

@Repository
public class UserRepository {

    private final DatabaseConfig databaseConfig;
    private static final Logger logger = LoggerFactory.getLogger(UserRepository.class);

    @Autowired
    public UserRepository(DatabaseConfig databaseConfig) {
        this.databaseConfig = databaseConfig;
    }

    @LeadTimed("-> Worked out method isSaveUser")
    public Boolean isSaveUser (User user) {

        if (user == null) {
            logger.error("User object is null");
            return false;
        }
        if (user.getFirstname() == null || user.getSecondName() == null || user.getEmail() == null || user.getTelephoneNumber() == null) {
            logger.error("User  fields cannot be null -> firstname={}, secondName={}, email={}, telephoneNumber={}",
                    user.getFirstname(), user.getSecondName(), user.getEmail(), user.getTelephoneNumber());
            return false;
        }

        try (Connection connection = databaseConfig.getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(INSERT_INTO_IS_SAVE_USER)) {

            preparedStatement.setString(1, user.getFirstname());
            preparedStatement.setString(2, user.getSecondName());
            preparedStatement.setInt(3, user.getAge());
            preparedStatement.setString(4, user.getEmail());
            preparedStatement.setString(5, user.getSex());
            preparedStatement.setString(6, user.getTelephoneNumber());
            preparedStatement.setTimestamp(7, Timestamp.valueOf(user.getCreated().toLocalDateTime()));
            preparedStatement.setTimestamp(8, Timestamp.valueOf(user.getUpdated().toLocalDateTime()));
            preparedStatement.setBoolean(9, user.getIsDeleted());

            boolean isSaved = preparedStatement.executeUpdate() > 0;
            if (isSaved) {
                logger.info("User  saved successfully -> {}", user);
            } else {
                logger.warn("User  not saved -> {}", user);
            }
            return isSaved;
        } catch (SQLException e) {
            logger.error("Error saving user -> {}: {}", user, e.getMessage());
            return false;
        }
    }
}