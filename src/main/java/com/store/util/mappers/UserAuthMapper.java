package com.store.util.mappers;

import com.store.dtos.UserAuthDto;
import com.store.model.User;
import org.springframework.stereotype.Component;

@Component
public class UserAuthMapper extends EntityDtoMapper<User, UserAuthDto>{
    @Override
    public UserAuthDto toDto(User entity) {
        UserAuthDto userAuthDto = new UserAuthDto();
        userAuthDto.setEmail(entity.getEmail());
        userAuthDto.setPassword(entity.getPassword());
        userAuthDto.setRole(entity.getRole());
        return userAuthDto;
    }

    @Override
    public User toEntity(UserAuthDto dto) {
        throw new UnsupportedOperationException();
    }
}
