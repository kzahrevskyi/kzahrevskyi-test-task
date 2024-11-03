package com.kzahrevskyi.testtask.testtask.repository;

import java.util.List;

import com.kzahrevskyi.testtask.testtask.model.User;

public interface UserRepository {
  List<User> findAllUsers();
}