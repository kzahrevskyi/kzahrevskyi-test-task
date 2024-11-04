package com.kzahrevskyi.testtask.testtask.controller;

import com.kzahrevskyi.testtask.testtask.dto.UserDto;
import com.kzahrevskyi.testtask.testtask.dto.UserRequestParam;
import com.kzahrevskyi.testtask.testtask.service.UserService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springdoc.core.annotations.ParameterObject;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("api/users")
@RequiredArgsConstructor
public class UserController {
  private final UserService userService;

  @Operation(summary = "Get all users with pagination and filtering",
      responses = {@ApiResponse(description = "Users retrieved successfully")})
  @GetMapping
  public Page<UserDto> getUsers(
      @Valid @ParameterObject UserRequestParam userRequestParam,
      @RequestParam(value = "size", required = false, defaultValue = "10") int size,
      @RequestParam(value = "page", required = false, defaultValue = "0") int page) {
    return userService.getAllUsers(userRequestParam, PageRequest.of(page, size));
  }
}