package com.store.service.impl;

import com.store.dtos.UserAuthDto;
import com.store.model.User;
import com.store.repository.UserRepo;
import com.store.service.UserService;
import com.store.util.mappers.UserAuthMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UserServiceImpl implements UserService {
    @Autowired
    private UserRepo userRepo;
    @Autowired
    private UserAuthMapper userAuthMapper;
    @Override
    public UserAuthDto getUserByEmail(String email) {
        User user = userRepo.findUserByEmailLike(email);
        return userAuthMapper.toDto(user);
    }
}
