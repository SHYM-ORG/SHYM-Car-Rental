package com.shym.backend.service;

import com.shym.backend.model.User;
import com.shym.backend.repository.UserRepository;
import org.springframework.stereotype.Service;

@Service
public class UserService<T extends User>{

    UserRepository userRepository;

    public UserService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    public User getUserWithEmail(String email) throws Throwable {
        return (User) userRepository.findByEmail(email).orElseThrow(() ->new RuntimeException("jkdfj"));
    }
}
