package com.kzahrevskyi.testtask.testtask.common.factory;

import static com.kzahrevskyi.testtask.testtask.constants.DatabaseConstants.MYSQL_STRATEGY_NAME;
import static com.kzahrevskyi.testtask.testtask.constants.DatabaseConstants.POSTGRES_STRATEGY_NAME;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import javax.sql.DataSource;

import com.kzahrevskyi.testtask.testtask.common.strategy.QueryDatabaseStrategy;
import com.kzahrevskyi.testtask.testtask.common.strategy.impl.MysqlStrategyQuery;
import com.kzahrevskyi.testtask.testtask.common.strategy.impl.PostgresStrategyQuery;
import com.kzahrevskyi.testtask.testtask.configuration.properties.DataSourceConfigProperties;
import lombok.Getter;
import lombok.Setter;
import lombok.extern.slf4j.Slf4j;
import org.springframework.jdbc.datasource.DriverManagerDataSource;
import org.springframework.stereotype.Component;

@Component
@Slf4j
@Getter
@Setter
public class DatabaseStrategyFactory {
  private final Map<String, QueryDatabaseStrategy> strategies = new HashMap<>();
  private final Map<String, DataSourceConfigProperties.FieldsMapping> fieldsMapping = new HashMap<>();
  private final Map<String, String> tableNames = new HashMap<>();

  public DatabaseStrategyFactory(DataSourceConfigProperties dataSourceConfigProperties) {
    for (DataSourceConfigProperties.DataSource dbSource : dataSourceConfigProperties.getDataSources()) {
      DataSource dataSource = configureDataSource(dbSource);
      QueryDatabaseStrategy strategy;
      if (POSTGRES_STRATEGY_NAME.equalsIgnoreCase(dbSource.getStrategy())) {
        strategy = new PostgresStrategyQuery(dataSource);
      } else if (MYSQL_STRATEGY_NAME.equalsIgnoreCase(dbSource.getStrategy())) {
        strategy = new MysqlStrategyQuery(dataSource);
      } else {
        log.warn("Unknown database strategy was requested: " + dbSource.getStrategy());
        continue;
      }
      strategies.put(dbSource.getName(), strategy);
      fieldsMapping.put(dbSource.getName(), dbSource.getMapping());
      tableNames.put(dbSource.getName(), dbSource.getTable());
    }
  }

  public QueryDatabaseStrategy chooseStrategy(String strategyKey) {
    return strategies.get(strategyKey);
  }

  public List<String> getStrategyKeys() {
    return new ArrayList<>(strategies.keySet());
  }

  public DataSourceConfigProperties.FieldsMapping getFieldsMapping(String strategyKey) {
    return fieldsMapping.get(strategyKey);
  }

  public String getTableName(String strategyKey) {
    return tableNames.get(strategyKey);
  }

  private DataSource configureDataSource(DataSourceConfigProperties.DataSource source) {
    DriverManagerDataSource dataSource = new DriverManagerDataSource();
    dataSource.setUrl(source.getUrl());
    dataSource.setUsername(source.getUser());
    dataSource.setPassword(source.getPassword());
    return dataSource;
  }
}