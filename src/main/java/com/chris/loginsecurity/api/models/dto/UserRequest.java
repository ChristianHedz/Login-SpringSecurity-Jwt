package com.chris.loginsecurity.api.models.dto;

import lombok.Getter;
import lombok.Setter;

@Getter @Setter
public class UserRequest {
    private String username;
    private String password;
}
