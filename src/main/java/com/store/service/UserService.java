package com.store.service;

import com.store.security.dtos.UserAuthDto;

public interface UserService {

    UserAuthDto getUserByEmail(String email);
}
