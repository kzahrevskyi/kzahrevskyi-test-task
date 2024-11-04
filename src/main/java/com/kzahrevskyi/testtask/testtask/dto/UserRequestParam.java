package com.kzahrevskyi.testtask.testtask.dto;

import jakarta.validation.constraints.Pattern;
import lombok.Data;

@Data
public class UserRequestParam {
  @Pattern(regexp = "^[\\p{L} ]+$", message = "Invalid name: must contain only letters and spaces")
  private String name;
}