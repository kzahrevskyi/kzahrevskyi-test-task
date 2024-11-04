package com.kzahrevskyi.testtask.testtask.repository;

import com.kzahrevskyi.testtask.testtask.dto.UserDto;
import com.kzahrevskyi.testtask.testtask.dto.UserRequestParam;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface UserRepository {
  Page<UserDto> findAllUsers(UserRequestParam userRequestParam, Pageable pageable);
}