package com.chris.loginsecurity.api.repositories;

import com.chris.loginsecurity.api.models.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface UserRepository extends JpaRepository<User,Long> {
    Optional<User> findByUsername(String name);
}
