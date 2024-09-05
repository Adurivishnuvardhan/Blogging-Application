package com.Vishnu.BlogApp.service;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.Vishnu.BlogApp.model.User;
import com.Vishnu.BlogApp.repositary.UserRepository;

@Service
public class UserService {

    @Autowired
    private UserRepository userRepository;

    /**
     * Save a new user.
     * @param user The user object to be saved.
     * @return The saved user.
     */
    public User saveUser(User user) {
        return userRepository.save(user);
    }

    /**
     * Validate a user's credentials.
     * @param username The username of the user.
     * @param password The password of the user.
     * @return The user if valid, or null if invalid.
     */
    public User validateUser(String username, String password) {
        // Find user by username
        Optional<User> optionalUser = userRepository.findByUsername(username);
        if (optionalUser.isPresent()) {
            User user = optionalUser.get();
            // Check if the password matches
            if (user.getPassword().equals(password)) {
                return user;
            }
        }
        return null;
    }
}
