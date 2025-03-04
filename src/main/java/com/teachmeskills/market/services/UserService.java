package com.teachmeskills.market.services;

import com.teachmeskills.market.model.User;
import com.teachmeskills.market.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserService {

    private final UserRepository userRepository;

    @Autowired
    public UserService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    public Boolean saveUser (User user) {
        return userRepository.isSaveUser (user);
    }

    public User getUserById(Long id) {
        return userRepository.getUserById(id);
    }

    public List<User> getAllUsers() {
        return userRepository.getAllUsers();
    }

    public Boolean updateUser (User user) {
        return userRepository.updateUser (user);
    }

    public Boolean deleteUser (Long id) {
        return userRepository.deleteUser (id);
    }
}