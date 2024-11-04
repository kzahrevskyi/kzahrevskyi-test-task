package com.kzahrevskyi.testtask.testtask.common.strategy.impl;

import java.util.List;
import java.util.Objects;
import javax.sql.DataSource;

import com.kzahrevskyi.testtask.testtask.common.strategy.QueryDatabaseStrategy;
import com.kzahrevskyi.testtask.testtask.configuration.properties.DataSourceConfigProperties;
import com.kzahrevskyi.testtask.testtask.dto.UserDto;
import com.kzahrevskyi.testtask.testtask.dto.UserRequestParam;
import org.jooq.DSLContext;
import org.jooq.SQLDialect;
import org.jooq.impl.DSL;

public class PostgresStrategyQuery implements QueryDatabaseStrategy {
  private final DSLContext dslContext;

  public PostgresStrategyQuery(DataSource dataSource) {
    this.dslContext = DSL.using(dataSource, SQLDialect.POSTGRES);
  }

  @Override
  public List<UserDto> getUsers(
      DataSourceConfigProperties.FieldsMapping fieldsMapping,
      String tableName,
      UserRequestParam userRequestParam) {
    var query = dslContext.select(
            DSL.field(fieldsMapping.getId()).as("id"),
            DSL.field(fieldsMapping.getUsername()).as("username"),
            DSL.field(fieldsMapping.getName()).as("name"),
            DSL.field(fieldsMapping.getSurname()).as("surname"))
        .from(DSL.table(tableName));

    if (Objects.nonNull(userRequestParam.getName())) {
      query.where(DSL.field(fieldsMapping.getName()).eq(userRequestParam.getName()));
    }

    return query.fetch().map(record ->
        UserDto.builder()
            .id(record.get("id", Long.class))
            .name(record.get("name", String.class))
            .surname(record.get("surname", String.class))
            .username(record.get("username", String.class))
            .build());
  }
}