package com.kzahrevskyi.testtask.testtask;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.jdbc.DataSourceAutoConfiguration;

@SpringBootApplication(exclude = {DataSourceAutoConfiguration.class})
public class KzahrevskyiTestTaskApplication {

  public static void main(String[] args) {
    SpringApplication.run(KzahrevskyiTestTaskApplication.class, args);
  }
}