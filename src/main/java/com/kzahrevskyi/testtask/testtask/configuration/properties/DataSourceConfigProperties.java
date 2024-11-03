package com.kzahrevskyi.testtask.testtask.configuration.properties;

import java.util.List;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

@Data
@Component
@ConfigurationProperties(prefix = "database-config")
public class DataSourceConfigProperties {
  private List<DataSource> dataSources;

  @Data
  public static class DataSource {
    private String name;
    private String strategy;
    private String url;
    private String user;
    private String password;
    private String table;
    private FieldsMapping mapping;
  }

  @Data
  public static class FieldsMapping {
    private String id;
    private String username;
    private String name;
    private String surname;
  }
}