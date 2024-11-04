package com.kzahrevskyi.testtask.testtask.common.strategy;

import java.util.List;

import com.kzahrevskyi.testtask.testtask.configuration.properties.DataSourceConfigProperties;
import com.kzahrevskyi.testtask.testtask.dto.UserDto;
import com.kzahrevskyi.testtask.testtask.dto.UserRequestParam;

public interface QueryDatabaseStrategy {
  List<UserDto> getUsers(DataSourceConfigProperties.FieldsMapping fieldsMapping, String tableName,  UserRequestParam userRequestParam);
}