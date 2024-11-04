package com.kzahrevskyi.testtask.testtask.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.Pattern;
import lombok.Data;

@Data
public class UserRequestParam {
  @Schema(description = "Filter by name", example = "John")
  @Pattern(regexp = "^[\\p{L} ]+$", message = "Invalid name: must contain only letters and spaces")
  private String name;
}