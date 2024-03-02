package com.chris.loginsecurity.api.controllers;

import com.chris.loginsecurity.api.models.dto.AuthResponse;
import com.chris.loginsecurity.api.models.dto.UserDTO;
import com.chris.loginsecurity.api.models.dto.UserRequest;
import com.chris.loginsecurity.api.services.AuthenticationService;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@AllArgsConstructor
@RestController
@RequestMapping("/api/v1")
public class AuthController {

    private AuthenticationService authenticationService;

    @PostMapping("/login")
    public ResponseEntity<AuthResponse> login(@RequestBody UserRequest userRequest){
        AuthResponse response = authenticationService.login(userRequest);
        return new ResponseEntity<>(response, HttpStatus.OK);
    }

    @PostMapping("profile")
    public ResponseEntity<UserDTO> findLoggerUser(){
        UserDTO userDTO = authenticationService.findLoggerUser();
        return new ResponseEntity<>(userDTO,HttpStatus.OK);
    }

}
