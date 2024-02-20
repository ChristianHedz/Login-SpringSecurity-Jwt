package com.chris.loginsecurity.api.services;

import com.chris.loginsecurity.api.exceptions.InvalidPasswordException;
import com.chris.loginsecurity.api.exceptions.ResourceNotFoundException;
import com.chris.loginsecurity.api.mapper.UserMapper;
import com.chris.loginsecurity.api.models.dto.RegisteredUser;
import com.chris.loginsecurity.api.models.entity.Role;
import com.chris.loginsecurity.api.models.entity.User;
import com.chris.loginsecurity.api.repositories.RoleRepository;
import com.chris.loginsecurity.api.repositories.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Collections;
import java.util.Optional;

@RequiredArgsConstructor
@Service
public class UserServiceImpl implements UserService{

    private final UserRepository userRepository;
    private final UserMapper userMapper;
    private final RoleRepository roleRepository;
    private final PasswordEncoder passwordEncoder;
    @Value("${default.role}")
    private String defaultRole;

    @Override
    public Optional<User> findByUsername(String name) {
        return userRepository.findByUsername(name);
    }

    @Transactional
    public User registerUser(RegisteredUser registeredUser){
        validatePassword(registeredUser);
        User user = userMapper.registeredUserToUser(registeredUser);
        user.setPassword(passwordEncoder.encode(registeredUser.getPassword()));
        Role role = roleRepository.findByName(defaultRole)
                .orElseThrow(() -> new ResourceNotFoundException("role","customer",defaultRole));
        user.setRoles(Collections.singleton(role));
        return userRepository.save(user);
    }

    private void validatePassword(RegisteredUser registeredUser) {
        if(!registeredUser.getPassword().equals(registeredUser.getRepeatedPassword())){
            throw  new InvalidPasswordException("Las contrasenas no coinciden,Intentalo de nuevo");
        }
    }


}
