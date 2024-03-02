package com.chris.loginsecurity.api.mapper;

import com.chris.loginsecurity.api.models.dto.RegisteredUser;
import com.chris.loginsecurity.api.models.dto.RegisteredUserDTO;
import com.chris.loginsecurity.api.models.dto.UserDTO;
import com.chris.loginsecurity.api.models.entity.User;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring")
public interface UserMapper{

    @Mapping(target = "id", ignore = true)
    @Mapping(target = "roles",ignore = true)
    @Mapping(target = "password",ignore = true)
    User registeredUserToUser(RegisteredUser registeredUser);


    @Mapping(target = "token", ignore = true)
    RegisteredUserDTO userToRegisteredUserDTO(User user);


    UserDTO userToUserDTO(User user);


}
