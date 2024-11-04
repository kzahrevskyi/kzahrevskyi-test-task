package com.kzahrevskyi.testtask.testtask.constants;

import lombok.experimental.UtilityClass;

@UtilityClass
public class DatabaseConstants {
  public static final String POSTGRES_STRATEGY_NAME = "postgres";
  public static final String MYSQL_STRATEGY_NAME = "mysql";

  public static class QueryConstants{
    public static final String FIELD_ID = "id";
    public static final String FIELD_USERNAME = "username";
    public static final String FIELD_NAME = "name";
    public static final String FIELD_SURNAME = "surname";
  }
}