package com.kzahrevskyi.testtask.testtask.repository;

import java.util.List;

import com.kzahrevskyi.testtask.testtask.dto.UserDto;
import com.kzahrevskyi.testtask.testtask.dto.UserRequestParam;

public interface UserRepository {
  List<UserDto> findAllUsers(UserRequestParam userRequestParam);
}