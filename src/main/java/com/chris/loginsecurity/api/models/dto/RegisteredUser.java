package com.chris.loginsecurity.api.models.dto;


import lombok.Getter;
import lombok.Setter;

@Getter @Setter
public class RegisteredUser {
    private String username;
    private String email;
    private String password;
    private String repeatedPassword;
}
