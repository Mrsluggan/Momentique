package com.Momentique.Momentique.Resource;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import com.Momentique.Momentique.Models.User;
import com.Momentique.Momentique.Repositories.UserRepository;

@RestController
public class UserRest {

    private final UserRepository userRepository;

    public UserRest(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @GetMapping("users")
    public Iterable<User> findAllUsers() {
        return userRepository.findAll();
    }

}
