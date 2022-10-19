package com.example.crudmobil;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.jdbc.DataSourceAutoConfiguration;

//@SpringBootApplication(exclude = {DataSourceAutoConfiguration.class})
@SpringBootApplication
public class CrudmobilApplication {

	public static void main(String[] args) {
		SpringApplication.run(CrudmobilApplication.class, args);
	}

}
