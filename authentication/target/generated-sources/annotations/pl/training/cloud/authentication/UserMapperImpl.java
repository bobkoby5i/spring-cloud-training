package pl.training.cloud.authentication;

import javax.annotation.processing.Generated;
import org.springframework.stereotype.Component;

@Generated(
    value = "org.mapstruct.ap.MappingProcessor",
    date = "2019-12-13T14:21:23+0100",
    comments = "version: 1.3.0.Final, compiler: javac, environment: Java 11.0.3 (Oracle Corporation)"
)
@Component
public class UserMapperImpl implements UserMapper {

    @Override
    public User toUser(UserDto userDto) {
        if ( userDto == null ) {
            return null;
        }

        User user = new User();

        user.setFirstName( userDto.getFirstName() );
        user.setLastName( userDto.getLastName() );
        user.setUsername( userDto.getUsername() );
        user.setPassword( userDto.getPassword() );

        return user;
    }

    @Override
    public UserDto toUserDto(User user) {
        if ( user == null ) {
            return null;
        }

        UserDto userDto = new UserDto();

        userDto.setFirstName( user.getFirstName() );
        userDto.setLastName( user.getLastName() );
        userDto.setUsername( user.getUsername() );
        userDto.setPassword( user.getPassword() );

        return userDto;
    }
}
