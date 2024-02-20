package com.chris.loginsecurity.api.services;

import com.chris.loginsecurity.api.models.dto.RegisteredUser;
import com.chris.loginsecurity.api.models.entity.User;

import java.util.Optional;

public interface UserService {
    Optional<User> findByUsername(String name);
    User registerUser(RegisteredUser registeredUser);
}
