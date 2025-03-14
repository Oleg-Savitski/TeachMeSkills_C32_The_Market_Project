package com.teachmeskills.market.services;

import com.teachmeskills.market.dto.AuthenticationResult;
import com.teachmeskills.market.exception.AuthenticationException;
import com.teachmeskills.market.model.Security;
import com.teachmeskills.market.repository.SecurityRepository;
import com.teachmeskills.market.utils.config.security.PasswordUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class AuthenticationService {

    private final SecurityRepository securityRepository;
    private static final Logger logger = LoggerFactory.getLogger(AuthenticationService.class);

    @Autowired
    public AuthenticationService(SecurityRepository securityRepository) {
        this.securityRepository = securityRepository;
    }

    public AuthenticationResult isAuthenticateUser (String login, String password) {
        logger.info("Attempting to authenticate user with login -> {}", login);

        Security security = securityRepository.findByLoginValidate(login);

        if (security == null) {
            logger.warn("Authentication failed -> User with login '{}' not found.", login);
            throw new AuthenticationException("User  not found");
        }

        boolean isAuthenticated = PasswordUtil.checkPassword(password, security.getSalt(), security.getPassword());

        if (isAuthenticated) {
            logger.info("User  '{}' authenticated successfully.", login);
            return new AuthenticationResult(true, "Authentication successful");
        } else {
            logger.warn("Authentication failed -> Incorrect password for user '{}'.", login);
            throw new AuthenticationException("Invalid password");
        }
    }
}