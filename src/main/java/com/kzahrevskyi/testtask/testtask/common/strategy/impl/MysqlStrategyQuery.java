package com.kzahrevskyi.testtask.testtask.common.strategy.impl;

import java.util.List;
import javax.sql.DataSource;

import com.kzahrevskyi.testtask.testtask.common.strategy.QueryDatabaseStrategy;
import com.kzahrevskyi.testtask.testtask.configuration.properties.DataSourceConfigProperties;
import com.kzahrevskyi.testtask.testtask.dto.UserDto;
import org.springframework.jdbc.core.JdbcTemplate;

public class MysqlStrategyQuery implements QueryDatabaseStrategy {
  private final JdbcTemplate jdbcTemplate;

  public MysqlStrategyQuery(DataSource dataSource) {
    this.jdbcTemplate = new JdbcTemplate(dataSource);
  }

  @Override
  public List<UserDto> getUsers(DataSourceConfigProperties.FieldsMapping fieldsMapping, String tableName) {
    String sql = String.format("SELECT %s AS id, %s AS username, %s AS name, %s AS surname FROM %s",
        fieldsMapping.getId(),
        fieldsMapping.getUsername(),
        fieldsMapping.getName(),
        fieldsMapping.getSurname(),
        tableName);

    return jdbcTemplate.query(sql, (rs, rowNum) -> new UserDto(
        rs.getLong("id"),
        rs.getString("username"),
        rs.getString("name"),
        rs.getString("surname")
    ));
  }
}