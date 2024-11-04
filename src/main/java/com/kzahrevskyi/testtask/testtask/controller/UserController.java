package com.kzahrevskyi.testtask.testtask.controller;

import java.util.List;

import com.kzahrevskyi.testtask.testtask.dto.UserDto;
import com.kzahrevskyi.testtask.testtask.dto.UserRequestParam;
import com.kzahrevskyi.testtask.testtask.service.UserService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("api/users")
@RequiredArgsConstructor
public class UserController {
  private final UserService userService;

  @GetMapping
  public List<UserDto> getUsers(
      @Valid UserRequestParam userRequestParam) {
    return userService.getAllUsers(userRequestParam);
  }
}