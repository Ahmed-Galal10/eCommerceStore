package com.store.service;

import com.store.dtos.UserAuthDto;

public interface UserService {

    UserAuthDto getUserByEmail(String email);
}
