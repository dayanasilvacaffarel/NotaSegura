package com.example.demo;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.jdbc.core.JdbcTemplate;

import javax.sql.DataSource;

@SpringBootApplication
public class NotaSeguraApplication {

	@Autowired
	private DataSource dataSource;
	public static void main(String[] args) {
		SpringApplication.run(NotaSeguraApplication.class, args);
	}

	@Bean
	public CommandLineRunner demo(JdbcTemplate jdbcTemplate) {
		return (args) -> {

			System.out.println("Conexión a la base de datos exitosa.");
		};
	}

}
