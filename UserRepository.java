package com.Vishnu.BlogApp.repositary;

import org.springframework.data.jpa.repository.JpaRepository;
import com.Vishnu.BlogApp.model.User;
import java.util.Optional;

public interface UserRepository extends JpaRepository<User, Long> {
    Optional<User> findByUsername(String username);
}
