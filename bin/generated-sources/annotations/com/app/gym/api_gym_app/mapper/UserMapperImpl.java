package com.app.gym.api_gym_app.mapper;

import com.app.gym.api_gym_app.dto.UserResponse;
import com.app.gym.api_gym_app.model.Gym;
import com.app.gym.api_gym_app.model.User;
import java.util.ArrayList;
import java.util.List;
import javax.annotation.processing.Generated;
import org.springframework.stereotype.Component;

@Generated(
    value = "org.mapstruct.ap.MappingProcessor",
    date = "2025-11-18T01:02:20-0300",
    comments = "version: 1.5.3.Final, compiler: Eclipse JDT (IDE) 3.44.0.v20251023-0518, environment: Java 21.0.8 (Eclipse Adoptium)"
)
@Component
public class UserMapperImpl implements UserMapper {

    @Override
    public UserResponse toUserDTO(User user) {
        if ( user == null ) {
            return null;
        }

        UserResponse.UserResponseBuilder userResponse = UserResponse.builder();

        userResponse.gymName( userGymNombre( user ) );
        userResponse.birthdate( user.getBirthdate() );
        userResponse.email( user.getEmail() );
        userResponse.id( user.getId() );
        userResponse.name( user.getName() );
        userResponse.phoneNumber( user.getPhoneNumber() );
        userResponse.role( user.getRole() );

        return userResponse.build();
    }

    @Override
    public User toUser(UserResponse userDTO) {
        if ( userDTO == null ) {
            return null;
        }

        User.UserBuilder user = User.builder();

        user.gym( userResponseToGym( userDTO ) );
        user.birthdate( userDTO.getBirthdate() );
        user.email( userDTO.getEmail() );
        user.id( userDTO.getId() );
        user.name( userDTO.getName() );
        user.phoneNumber( userDTO.getPhoneNumber() );
        user.role( userDTO.getRole() );

        return user.build();
    }

    @Override
    public List<UserResponse> toUserDTOs(List<User> users) {
        if ( users == null ) {
            return null;
        }

        List<UserResponse> list = new ArrayList<UserResponse>( users.size() );
        for ( User user : users ) {
            list.add( toUserDTO( user ) );
        }

        return list;
    }

    private String userGymNombre(User user) {
        if ( user == null ) {
            return null;
        }
        Gym gym = user.getGym();
        if ( gym == null ) {
            return null;
        }
        String nombre = gym.getNombre();
        if ( nombre == null ) {
            return null;
        }
        return nombre;
    }

    protected Gym userResponseToGym(UserResponse userResponse) {
        if ( userResponse == null ) {
            return null;
        }

        Gym.GymBuilder gym = Gym.builder();

        gym.nombre( userResponse.getGymName() );

        return gym.build();
    }
}
