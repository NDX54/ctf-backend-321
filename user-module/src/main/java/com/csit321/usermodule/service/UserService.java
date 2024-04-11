package com.csit321.usermodule.service;

import com.csit321.usermodule.controller.dto.UserDto;
import com.csit321.usermodule.model.entities.User;

public interface UserService {
    User findByUsername(String username);

    User save(UserDto userDto);
}
