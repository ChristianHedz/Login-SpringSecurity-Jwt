package com.chris.loginsecurity.api.models.dto;

import com.chris.loginsecurity.api.models.entity.Role;
import lombok.Data;
import java.util.Set;

@Data
public class UserDTO {
    private Long id;
    private String username;
    private String email;
    private Set<Role> roles;
}
