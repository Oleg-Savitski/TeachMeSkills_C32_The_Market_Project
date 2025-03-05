package com.teachmeskills.market.services;

import com.teachmeskills.market.annotation.LeadTimed;
import com.teachmeskills.market.model.Role;
import com.teachmeskills.market.model.Security;
import com.teachmeskills.market.model.User;
import com.teachmeskills.market.repository.SecurityRepository;
import com.teachmeskills.market.repository.UserRepository;
import com.teachmeskills.market.utils.config.security.PasswordUtil;
import com.teachmeskills.market.utils.config.security.SaltGenerator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.sql.Timestamp;

@Service
public class RegistrationSecurityService {

    private final UserRepository userRepository;
    private final SecurityRepository securityRepository;

    @Autowired
    public RegistrationSecurityService(UserRepository userRepository, SecurityRepository securityRepository) {
        this.userRepository = userRepository;
        this.securityRepository = securityRepository;
    }

    @LeadTimed("-> Worked out method registrationNewUser  ")
    public Boolean registrationNewUser  (String firstname, String secondName, Integer age, String email, String sex, String fullTelephoneNumber, String login, String password) {
        User user = new User();
        user.setFirstname(firstname);
        user.setSecondName(secondName);
        user.setAge(age);
        user.setEmail(email);
        user.setSex(sex);
        user.setTelephoneNumber(fullTelephoneNumber);
        user.setCreated(new Timestamp(System.currentTimeMillis()));
        user.setUpdated(new Timestamp(System.currentTimeMillis()));
        user.setIsDeleted(false);

        Boolean isUserSaved = userRepository.isSaveUser  (user);
        if (!isUserSaved) {
            return false;
        }

        Long userId = user.getId();

        Security security = new Security();
        security.setLogin(login);

        String salt = SaltGenerator.generateSalt();
        security.setPassword(PasswordUtil.hashPassword(password, salt));
        security.setSalt(salt);
        security.setRole(Role.USER);
        security.setCreated(new Timestamp(System.currentTimeMillis()));
        security.setUpdated(new Timestamp(System.currentTimeMillis()));
        security.setUserId(userId);

        return securityRepository.isSaveSecurityUser  (security);
    }
}