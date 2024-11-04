package com.kzahrevskyi.testtask.testtask.service;

import com.kzahrevskyi.testtask.testtask.dto.UserDto;
import com.kzahrevskyi.testtask.testtask.dto.UserRequestParam;
import com.kzahrevskyi.testtask.testtask.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class UserService {

  private final UserRepository userRepository;

  public Page<UserDto> getAllUsers(UserRequestParam userRequestParam, Pageable pageable) {
    return userRepository.findAllUsers(userRequestParam, pageable);
  }
}