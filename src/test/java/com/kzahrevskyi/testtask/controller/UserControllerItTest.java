package com.kzahrevskyi.testtask.controller;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;

import com.kzahrevskyi.testtask.testtask.KzahrevskyiTestTaskApplication;
import lombok.SneakyThrows;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.DynamicPropertyRegistry;
import org.springframework.test.context.DynamicPropertySource;
import org.springframework.test.web.servlet.MockMvc;
import org.testcontainers.containers.PostgreSQLContainer;
import org.testcontainers.junit.jupiter.Container;

@SpringBootTest(classes = KzahrevskyiTestTaskApplication.class)
@ActiveProfiles("test")
@AutoConfigureMockMvc
class UserControllerItTest {

  @Autowired
  private MockMvc mockMvc;

  @Container
  private static final PostgreSQLContainer<?> postgresContainer = new PostgreSQLContainer<>("postgres:14")
      .withDatabaseName("testdb")
      .withUsername("testuser")
      .withPassword("testpass");

  @Container
  private static final PostgreSQLContainer<?> secondPostgresContainer = new PostgreSQLContainer<>("postgres:14")
      .withDatabaseName("testdb2")
      .withUsername("testuser")
      .withPassword("testpass");

  @DynamicPropertySource
  static void setup(DynamicPropertyRegistry registry) {
    postgresContainer.start();
    secondPostgresContainer.start();
    registry.add("DB1_URL", postgresContainer::getJdbcUrl);
    registry.add("DB1_USERNAME", postgresContainer::getUsername);
    registry.add("DB1_PASSWORD", postgresContainer::getPassword);

    registry.add("DB2_URL", secondPostgresContainer::getJdbcUrl);
    registry.add("DB2_USERNAME", secondPostgresContainer::getUsername);
    registry.add("DB2_PASSWORD", secondPostgresContainer::getPassword);
  }

  @BeforeEach
  @SneakyThrows
  void cleanDb() {
    cleanDb(postgresContainer);
    cleanDb(secondPostgresContainer);
  }

  @Test
  @SneakyThrows
  void shouldReturnAllUsersFromDB() {
    //given
    fillDb(postgresContainer);
    fillDb(secondPostgresContainer);

    //when-then
    mockMvc.perform(get("/api/users"))
        .andExpect(status().isOk())
        .andExpect(jsonPath("$").isArray())
        .andExpect(jsonPath("$").isNotEmpty())
        .andExpect(jsonPath("$.length()").value(4))
        .andExpect(jsonPath("$[0].username").value("user1"))
        .andExpect(jsonPath("$[-1].username").value("user2"));
  }

  @Test
  @SneakyThrows
  void shouldReturnAllUsersFilteredByName() {
    //given
    fillDb(postgresContainer);
    fillDb(secondPostgresContainer);

    //when-then
    mockMvc.perform(get("/api/users?name=Daniel"))
        .andExpect(status().isOk())
        .andExpect(jsonPath("$").isArray())
        .andExpect(jsonPath("$").isNotEmpty())
        .andExpect(jsonPath("$.length()").value(2))
        .andExpect(jsonPath("$[0].name").value("Daniel"));
  }

  @Test
  @SneakyThrows
  void shouldReturnBadRequestWhenFilterParamIsInvalid() {
    //given
    fillDb(postgresContainer);
    fillDb(secondPostgresContainer);

    //when-then
    mockMvc.perform(get("/api/users?name=<Daniel>"))
        .andExpect(status().isBadRequest());
  }

  @Test
  @SneakyThrows
  void shouldReturnEmptyListWhenTheraAreNoUsersInDB() {
    //when-then
    mockMvc.perform(get("/api/users"))
        .andExpect(status().isOk())
        .andExpect(jsonPath("$").isArray())
        .andExpect(jsonPath("$").isEmpty());
  }

  @SneakyThrows
  private void fillDb(PostgreSQLContainer postgresContainer) {
    Connection postgresConnection = initConnection(postgresContainer);
    Statement statement = postgresConnection.createStatement();
    statement.execute(
        "CREATE TABLE users (user_id SERIAL PRIMARY KEY, login VARCHAR(255), first_name VARCHAR(255), last_name VARCHAR(255))");
    statement.execute("INSERT INTO users (login, first_name, last_name) VALUES ('user1', 'Katie', 'Brown')");
    statement.execute("INSERT INTO users (login, first_name, last_name) VALUES ('user2', 'Daniel', 'Taylor')");
    postgresConnection.close();
  }

  @SneakyThrows
  private void cleanDb(PostgreSQLContainer postgresContainer) {
    Connection postgresConnection = initConnection(postgresContainer);
    Statement statement = postgresConnection.createStatement();
    statement.execute("DROP TABLE IF EXISTS users");
    postgresConnection.close();
  }

  private Connection initConnection(PostgreSQLContainer postgresContainer) throws SQLException {
    Connection postgresConnection = DriverManager.getConnection(postgresContainer.getJdbcUrl(),
        postgresContainer.getUsername(),
        postgresContainer.getPassword());
    return postgresConnection;
  }
}
