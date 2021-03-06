package com.shym.backend.repository;

import com.shym.backend.model.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface UserRepository<T extends User> extends JpaRepository<T, String> {
    Optional<T> findByEmail(String email);
    boolean existsByEmail(String email);
}
