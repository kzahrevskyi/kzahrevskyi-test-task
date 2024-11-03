package com.kzahrevskyi.testtask.testtask.controller;

import java.util.List;

import com.kzahrevskyi.testtask.testtask.model.User;
import com.kzahrevskyi.testtask.testtask.service.UserService;
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
  public List<User> getUsers() {
    return userService.getAllUsers();
  }
}