package com.teachmeskills.market.services;

import com.teachmeskills.market.annotation.LeadTimed;
import com.teachmeskills.market.exception.UserNotFoundException;
import com.teachmeskills.market.model.Security;
import com.teachmeskills.market.model.User;
import com.teachmeskills.market.repository.SecurityRepository;
import com.teachmeskills.market.repository.UserRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.sql.Timestamp;
import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class UserService {

    private static final Logger logger = LoggerFactory.getLogger(UserService.class);

    private final UserRepository userRepository;
    private final SecurityRepository securityRepository;

    @Autowired
    public UserService(UserRepository userRepository, SecurityRepository securityRepository) {
        this.userRepository = userRepository;
        this.securityRepository = securityRepository;
    }

    @LeadTimed("-> Worked out method getAllUsers")
    public List<User> getAllUsers(String login) {
        logger.info("Fetching users for login -> {}", login);
        Security security = securityRepository.findByLoginValidate(login);

        if (security != null) {
            logger.info("User found with role -> {}", security.getRole());
            if ("MODERATOR".equals(security.getRole().toString())) {
                List<User> users = userRepository.getAllUsers().stream()
                        .sorted(Comparator.comparingLong(User::getId))
                        .collect(Collectors.toList());
                logger.info("Number of users fetched -> {}", users.size());
                return users;
            } else {
                logger.warn("User does not have MODERATOR role-> {}", login);
                throw new UserNotFoundException("User does not have MODERATOR role -> " + login);
            }
        } else {
            logger.warn("No security information found for login -> {}", login);
            throw new UserNotFoundException("No security information found for login -> " + login);
        }
    }

    @LeadTimed("-> Worked out method createUser")
    public boolean createUser (User user, String login) {
        if (user == null) {
            logger.error("User object is null");
            throw new IllegalArgumentException("User object cannot be null");
        }
        if (user.getFirstname() == null || user.getSecondName() == null || user.getEmail() == null || user.getTelephoneNumber() == null) {
            logger.error("User fields cannot be null -> firstname={}, secondName={}, email={}, telephoneNumber={}",
                    user.getFirstname(), user.getSecondName(), user.getEmail(), user.getTelephoneNumber());
            throw new IllegalArgumentException("User fields cannot be null");
        }

        Security security = securityRepository.findByLoginValidate(login);
        if (security == null || !"MODERATOR".equals(security.getRole().toString())) {
            logger.warn("User does not have MODERATOR role -> {}", login);
            throw new SecurityException("User does not have MODERATOR role -> " + login);
        }

        user.setCreated(new Timestamp(System.currentTimeMillis()));
        user.setUpdated(new Timestamp(System.currentTimeMillis()));
        user.setIsDeleted(false);
        boolean isCreated = userRepository.createUser (user);
        logger.info("User created successfully -> {}", user);
        return isCreated;
    }

    @LeadTimed("-> Worked out method updateUser")
    public boolean updateUser (User user, String login) {
        if (user == null || user.getId() == null) {
            logger.error("User object or ID is null");
            throw new IllegalArgumentException("User object and ID cannot be null");
        }

        Security security = securityRepository.findByLoginValidate(login);
        if (security == null || !"MODERATOR".equals(security.getRole().toString())) {
            logger.warn("User does not have MODERATOR role  -> {}", login);
            throw new SecurityException("User does not have MODERATOR role -> " + login);
        }

        user.setUpdated(new Timestamp(System.currentTimeMillis()));
        boolean isUpdated = userRepository.updateUser (user);
        if (isUpdated) {
            logger.info("User updated successfully -> {}", user);
        } else {
            logger.warn("User not found for update -> {}", user);
            throw new UserNotFoundException("User not found for update -> " + user.getId());
        }
        return true;
    }

    @LeadTimed("-> Worked out method deleteUser")
    public boolean deleteUser (Long id, String login) {
        if (id == null) {
            logger.error("User ID is  null");
            throw new IllegalArgumentException("User ID cannot be null");
        }

        Security security = securityRepository.findByLoginValidate(login);
        if (security == null || !"MODERATOR".equals(security.getRole().toString())) {
            logger.warn("User does not  have MODERATOR role -> {}", login);
            throw new SecurityException("User does not have MODERATOR role -> " + login);
        }

        boolean isDeleted = userRepository.deleteUser (id);
        if (isDeleted) {
            logger.info("User deleted successfully with id -> {}", id);
        } else {
            logger.warn("User not found for deletion with id -> {}", id);
            throw new UserNotFoundException("User not found for deletion with id -> " + id);
        }
        return true;
    }

    @LeadTimed("-> Worked out method getUserById")
    public User getUserById(Long id) {
        if (id == null) {
            logger.error("User ID is null");
            throw new IllegalArgumentException("User ID cannot be null");
        }

        User user = userRepository.getUserById(id);
        if (user == null) {
            logger.warn("User not found with id -> {}", id);
            throw new UserNotFoundException("User not found with id -> " + id);
        }
        return user;
    }
}