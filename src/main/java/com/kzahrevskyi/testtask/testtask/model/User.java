package com.kzahrevskyi.testtask.testtask.model;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class User {
  private Long id;
  private String username;
  private String name;
  private String surname;
}