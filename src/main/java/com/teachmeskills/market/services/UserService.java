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
            logger.info("User  found with role -> {}", security.getRole());
            if ("MODERATOR".equals(security.getRole().toString())) {
                List<User> users = userRepository.getAllUsers().stream()
                        .sorted(Comparator.comparingLong(User::getId))
                        .collect(Collectors.toList());
                logger.info("Number of users fetched -> {}", users.size());
                return users;
            } else {
                logger.warn("User  does not have MODERATOR role -> {}", login);
                throw new UserNotFoundException("User  does not have MODERATOR role -> " + login);
            }
        } else {
            logger.warn("No security information found for login -> {}", login);
            throw new UserNotFoundException("No security information found for login -> " + login);
        }
    }
}