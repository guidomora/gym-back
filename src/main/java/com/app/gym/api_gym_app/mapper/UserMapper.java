package com.app.gym.api_gym_app.mapper;

import com.app.gym.api_gym_app.dto.UserResponse;
import com.app.gym.api_gym_app.model.User;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.ReportingPolicy;

import java.util.List;

@Mapper(componentModel = "spring", unmappedTargetPolicy = ReportingPolicy.IGNORE)
public interface UserMapper {

    @Mapping(source = "gym.nombre", target = "gymName")
    UserResponse toUserDTO(User user);

    @Mapping(source = "gymName", target = "gym.nombre")
    User toUser(UserResponse userDTO);

    List<UserResponse> toUserDTOs(List<User> users);

    default Long mapRoutineToId(com.app.gym.api_gym_app.model.Routine routine) {
        return routine != null ? routine.getId() : null;
    }
}