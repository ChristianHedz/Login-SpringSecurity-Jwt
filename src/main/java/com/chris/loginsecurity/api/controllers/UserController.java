package com.chris.loginsecurity.api.controllers;

import com.chris.loginsecurity.api.models.dto.RegisteredUser;
import com.chris.loginsecurity.api.models.dto.RegisteredUserDTO;
import com.chris.loginsecurity.api.services.AuthenticationService;
import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@AllArgsConstructor
@RestController
@RequestMapping("api/v1")
public class UserController {
    private AuthenticationService authenticationService;
    @PostMapping("/user")
    public ResponseEntity<RegisteredUserDTO> registerUser(@RequestBody @Valid RegisteredUser registeredUser){
        RegisteredUserDTO response = authenticationService.registerUser(registeredUser);
        return new ResponseEntity<>(response, HttpStatus.CREATED);
    }
}
