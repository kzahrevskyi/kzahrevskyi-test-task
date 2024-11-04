package com.kzahrevskyi.testtask.testtask.common.strategy.impl;

import java.util.Objects;
import javax.sql.DataSource;

import com.kzahrevskyi.testtask.testtask.common.strategy.QueryDatabaseStrategy;
import com.kzahrevskyi.testtask.testtask.configuration.properties.DataSourceConfigProperties;
import com.kzahrevskyi.testtask.testtask.constants.DatabaseConstants;
import com.kzahrevskyi.testtask.testtask.dto.UserDto;
import com.kzahrevskyi.testtask.testtask.dto.UserRequestParam;
import org.jooq.DSLContext;
import org.jooq.SQLDialect;
import org.jooq.impl.DSL;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;

public class MysqlStrategyQuery implements QueryDatabaseStrategy {

  private final DSLContext dslContext;

  public MysqlStrategyQuery(DataSource dataSource) {
    this.dslContext = DSL.using(dataSource, SQLDialect.MYSQL);
  }

  @Override
  public Page<UserDto> getUsers(
      DataSourceConfigProperties.FieldsMapping fieldsMapping,
      String tableName,
      UserRequestParam userRequestParam,
      Pageable pageable) {
    var query = dslContext.select(
            DSL.field(fieldsMapping.getId()).as(DatabaseConstants.QueryConstants.FIELD_ID),
            DSL.field(fieldsMapping.getUsername()).as(DatabaseConstants.QueryConstants.FIELD_USERNAME),
            DSL.field(fieldsMapping.getName()).as(DatabaseConstants.QueryConstants.FIELD_NAME),
            DSL.field(fieldsMapping.getSurname()).as(DatabaseConstants.QueryConstants.FIELD_SURNAME))
        .from(DSL.table(tableName));

    if (Objects.nonNull(userRequestParam.getName())) {
      query.where(DSL.field(fieldsMapping.getName()).eq(userRequestParam.getName()));
    }

    long total = dslContext.fetchCount(query);

    var users = query
        .limit(pageable.getPageSize())
        .offset(pageable.getOffset())
        .fetch().map(record ->
            UserDto.builder()
                .id(record.get(DatabaseConstants.QueryConstants.FIELD_ID, Long.class))
                .name(record.get(DatabaseConstants.QueryConstants.FIELD_NAME, String.class))
                .surname(record.get(DatabaseConstants.QueryConstants.FIELD_SURNAME, String.class))
                .username(record.get(DatabaseConstants.QueryConstants.FIELD_USERNAME, String.class))
                .build());
    return new PageImpl<>(users, pageable, total);
  }
}