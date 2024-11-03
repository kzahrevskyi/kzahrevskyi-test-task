package com.kzahrevskyi.testtask.testtask.common.strategy;

import java.util.List;

import com.kzahrevskyi.testtask.testtask.configuration.properties.DataSourceConfigProperties;
import com.kzahrevskyi.testtask.testtask.model.User;

public interface QueryDatabaseStrategy {
  List<User> getUsers(DataSourceConfigProperties.FieldsMapping fieldsMapping, String tableName);
}