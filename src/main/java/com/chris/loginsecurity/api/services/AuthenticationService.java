package com.chris.loginsecurity.api.services;

import com.chris.loginsecurity.api.exceptions.ResourceNotFoundException;
import com.chris.loginsecurity.api.mapper.UserMapper;
import com.chris.loginsecurity.api.models.dto.AuthResponse;
import com.chris.loginsecurity.api.models.dto.RegisteredUser;
import com.chris.loginsecurity.api.models.dto.RegisteredUserDTO;
import com.chris.loginsecurity.api.models.dto.UserRequest;
import com.chris.loginsecurity.api.models.entity.JwtToken;
import com.chris.loginsecurity.api.models.entity.User;
import com.chris.loginsecurity.api.repositories.TokenRepository;
import lombok.AllArgsConstructor;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Map;

@AllArgsConstructor
@Service
public class AuthenticationService {
    private TokenRepository tokenRepository;
    private UserMapper userMapper;
    private UserService userService;
    private AuthenticationManager authManager;
    private JwtService jwtService;

    @Transactional
    public RegisteredUserDTO registerUser(RegisteredUser registeredUser){
        User user = userService.registerUser(registeredUser);
        String jwt = jwtService.generateToken(user,generateExtraClaims(user));
        saveToken(jwt,user);
        RegisteredUserDTO registeredUserDTO = userMapper.userToRegisteredUserDTO(user);
        registeredUserDTO.setToken(jwt);
        return registeredUserDTO;
    }

    private void saveToken(String jwt,User user) {
        JwtToken token = new JwtToken();
        token.setUser(user);
        token.setToken(jwt);
        token.setExpiration(jwtService.extractExpiration(jwt));
        token.setValid(true);
        tokenRepository.save(token);
    }

    private Map<String,Object> generateExtraClaims(User user) {
        return Map.of(
                "username",user.getUsername(),
                "authorities",user.getAuthorities()
        );
    }

    public AuthResponse login(UserRequest userRequest) {
        Authentication auth =  new UsernamePasswordAuthenticationToken(
                userRequest.getUsername(),userRequest.getPassword()
        );
        authManager.authenticate(auth);
        User user = userService.findByUsername(userRequest.getUsername())
                .orElseThrow(() -> new ResourceNotFoundException("user","username",userRequest.getUsername()));
        String jwt = jwtService.generateToken(user,generateExtraClaims(user));
        saveToken(jwt,user);

        return new AuthResponse(jwt);
    }
}
