package com.kzahrevskyi.testtask.testtask.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;

@Data
@Builder
@AllArgsConstructor
public class UserDto {
  private Long id;
  private String username;
  private String name;
  private String surname;
}