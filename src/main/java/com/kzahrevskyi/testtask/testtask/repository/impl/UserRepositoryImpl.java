package com.kzahrevskyi.testtask.testtask.repository.impl;

import java.util.ArrayList;
import java.util.List;

import com.kzahrevskyi.testtask.testtask.common.strategy.QueryDatabaseStrategy;
import com.kzahrevskyi.testtask.testtask.configuration.properties.DataSourceConfigProperties;
import com.kzahrevskyi.testtask.testtask.dto.UserDto;
import com.kzahrevskyi.testtask.testtask.repository.UserRepository;
import com.kzahrevskyi.testtask.testtask.common.factory.DatabaseStrategyFactory;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Repository;

@Slf4j
@Repository
@RequiredArgsConstructor
public class UserRepositoryImpl implements UserRepository {
  private final DatabaseStrategyFactory databaseStrategyFactory;

  @Override
  public List<UserDto> findAllUsers() {
    List<UserDto> users = new ArrayList<>();
    for (String strategyKey : databaseStrategyFactory.getStrategyKeys()) {
      DataSourceConfigProperties.FieldsMapping fieldsMapping = databaseStrategyFactory.getFieldsMapping(strategyKey);
      String tableName = databaseStrategyFactory.getTableName(strategyKey);

      try {
        QueryDatabaseStrategy queryDatabaseStrategy = databaseStrategyFactory.chooseStrategy(strategyKey);
        users.addAll(queryDatabaseStrategy.getUsers(fieldsMapping, tableName));
      } catch (Exception e) {
        log.error("Error during request to the table {}: {}", tableName, e.getMessage());
      }
    }
    return users;
  }
}