package com.teachmeskills.market.services;

import com.teachmeskills.market.annotation.LeadTimed;
import com.teachmeskills.market.model.Role;
import com.teachmeskills.market.model.Security;
import com.teachmeskills.market.model.User;
import com.teachmeskills.market.repository.SecurityRepository;
import com.teachmeskills.market.repository.UserRepository;
import org.springframework.stereotype.Service;

import java.sql.Timestamp;
@Service
public class RegistrationSecurityService {

    private final UserRepository userRepository;
    private final SecurityRepository securityRepository;

    public RegistrationSecurityService(UserRepository userRepository, SecurityRepository securityRepository) {
        this.userRepository = userRepository;
        this.securityRepository = securityRepository;
    }

    @LeadTimed("-> Worked out method registrationNewUser")
    public Boolean registrationNewUser(String firstname, String secondName, Integer age, String email, String sex, String telephoneNumber, String login, String password) {
        User user = new User();
        user.setFirstname(firstname);
        user.setSecondName(secondName);
        user.setAge(age);
        user.setEmail(email);
        user.setSex(sex);
        user.setTelephoneNumber(telephoneNumber);
        user.setCreated(new Timestamp(System.currentTimeMillis()));
        user.setUpdated(new Timestamp(System.currentTimeMillis()));
        user.setIsDeleted(false);

        Boolean isUserSaved = userRepository.isSaveUser (user);
        if (!isUserSaved) {
            return false;
        }

        Long userId = user.getId();

        Security security = new Security();
        security.setLogin(login);
        security.setPassword(password);
        security.setRole(Role.USER);
        security.setCreated(new Timestamp(System.currentTimeMillis()));
        security.setUpdated(new Timestamp(System.currentTimeMillis()));
        security.setUserId(userId);

        return securityRepository.isSaveSecurityUser (security);
    }
}