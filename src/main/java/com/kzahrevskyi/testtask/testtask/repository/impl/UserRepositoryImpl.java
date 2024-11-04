package com.kzahrevskyi.testtask.testtask.repository.impl;

import java.util.ArrayList;
import java.util.List;

import com.kzahrevskyi.testtask.testtask.common.factory.DatabaseStrategyFactory;
import com.kzahrevskyi.testtask.testtask.common.strategy.QueryDatabaseStrategy;
import com.kzahrevskyi.testtask.testtask.configuration.properties.DataSourceConfigProperties;
import com.kzahrevskyi.testtask.testtask.dto.UserDto;
import com.kzahrevskyi.testtask.testtask.dto.UserRequestParam;
import com.kzahrevskyi.testtask.testtask.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Repository;

@Slf4j
@Repository
@RequiredArgsConstructor
public class UserRepositoryImpl implements UserRepository {
  private final DatabaseStrategyFactory databaseStrategyFactory;

  @Override
  public Page<UserDto> findAllUsers(UserRequestParam userRequestParam, Pageable pageable) {
    List<UserDto> users = new ArrayList<>();
    long totalUsers = 0;
    for (String strategyKey : databaseStrategyFactory.getStrategyKeys()) {
      DataSourceConfigProperties.FieldsMapping fieldsMapping = databaseStrategyFactory.getFieldsMapping(strategyKey);
      String tableName = databaseStrategyFactory.getTableName(strategyKey);

      try {
        QueryDatabaseStrategy queryDatabaseStrategy = databaseStrategyFactory.chooseStrategy(strategyKey);
        var pagedUser = queryDatabaseStrategy.getUsers(fieldsMapping, tableName, userRequestParam, pageable);
        users.addAll(pagedUser.getContent());
        totalUsers += pagedUser.getTotalElements();
      } catch (Exception e) {
        log.error("Error during request to the table {}: {}", tableName, e.getMessage());
      }
    }
    return new PageImpl<>(users, pageable, totalUsers);
  }
}