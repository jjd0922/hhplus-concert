package com.hanghe;

import org.springframework.boot.test.context.TestConfiguration;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;
import org.springframework.jdbc.datasource.DriverManagerDataSource;
import org.testcontainers.containers.MySQLContainer;

import javax.sql.DataSource;

@TestConfiguration
public class TestContainerConfiguration {

    @Bean
    public MySQLContainer<?> mySQLContainer() {
        MySQLContainer<?> container = new MySQLContainer<>("mysql:8.0.33")
                .withDatabaseName("hanghe")
                .withUsername("hanghe")
                .withPassword("hanghe1212");
        container.start();
        return container;
    }

    @Primary
    @Bean(name = "dataSource")
    public DataSource dataSource(MySQLContainer<?> mySQLContainer) {
        DriverManagerDataSource dataSource = new DriverManagerDataSource();
        dataSource.setDriverClassName("com.mysql.cj.jdbc.Driver");
        dataSource.setUrl("hanghe-database.cla4iewkenyo.ap-northeast-2.rds.amazonaws.com");
        dataSource.setUsername("hanghe");
        dataSource.setPassword("hanghe1212");
        return dataSource;
    }
}
