package com.kzahrevskyi.testtask.testtask.repository;

import java.util.List;

import com.kzahrevskyi.testtask.testtask.dto.UserDto;

public interface UserRepository {
  List<UserDto> findAllUsers();
}