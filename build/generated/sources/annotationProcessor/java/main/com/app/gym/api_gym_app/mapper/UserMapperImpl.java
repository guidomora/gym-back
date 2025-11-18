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
    date = "2025-11-18T01:03:25-0300",
    comments = "version: 1.5.3.Final, compiler: IncrementalProcessingEnvironment from gradle-language-java-8.14.3.jar, environment: Java 21.0.3 (Eclipse Adoptium)"
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
        userResponse.id( user.getId() );
        userResponse.name( user.getName() );
        userResponse.email( user.getEmail() );
        userResponse.role( user.getRole() );
        userResponse.phoneNumber( user.getPhoneNumber() );
        userResponse.birthdate( user.getBirthdate() );

        return userResponse.build();
    }

    @Override
    public User toUser(UserResponse userDTO) {
        if ( userDTO == null ) {
            return null;
        }

        User.UserBuilder user = User.builder();

        user.gym( userResponseToGym( userDTO ) );
        user.id( userDTO.getId() );
        user.name( userDTO.getName() );
        user.email( userDTO.getEmail() );
        user.phoneNumber( userDTO.getPhoneNumber() );
        user.birthdate( userDTO.getBirthdate() );
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
