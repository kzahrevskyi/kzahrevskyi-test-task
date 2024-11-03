package com.kzahrevskyi.testtask.testtask.service;

import java.util.List;

import com.kzahrevskyi.testtask.testtask.dto.UserDto;
import com.kzahrevskyi.testtask.testtask.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class UserService {

  private final UserRepository userRepository;

  public List<UserDto> getAllUsers() {
    return userRepository.findAllUsers();
  }
}