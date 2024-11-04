package com.kzahrevskyi.testtask.testtask.common.strategy;

import com.kzahrevskyi.testtask.testtask.configuration.properties.DataSourceConfigProperties;
import com.kzahrevskyi.testtask.testtask.dto.UserDto;
import com.kzahrevskyi.testtask.testtask.dto.UserRequestParam;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface QueryDatabaseStrategy {
  Page<UserDto> getUsers(
      DataSourceConfigProperties.FieldsMapping fieldsMapping,
      String tableName,
      UserRequestParam userRequestParam,
      Pageable pageable);
}