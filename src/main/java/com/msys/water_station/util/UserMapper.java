package com.msys.water_station.util;

import java.util.List;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

import com.msys.water_station.Model.User;
import com.msys.water_station.dto.user.Register;
import com.msys.water_station.dto.user.UserResponse;

@Mapper(componentModel = "spring")
public interface UserMapper {
    @Mapping(target = "roleName", source = "role.name")
    UserResponse toDTO(User user);

    @Mapping(target = "roleName", source = "role.name")
    List<UserResponse> toDTOContains(List<User> users);

    @Mapping(target = "role", ignore = true) // handled in service
    @Mapping(target = "id", ignore = true) // auto-generated
    @Mapping(target = "fullname", constant = "true") // default active
    User toEntity(Register register);
}
