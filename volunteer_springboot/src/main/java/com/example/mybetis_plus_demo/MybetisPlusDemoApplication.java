package com.example.mybetis_plus_demo;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
@MapperScan("com.example.mybetis_plus_demo.mapper")
public class MybetisPlusDemoApplication {

	public static void main(String[] args) {
		SpringApplication.run(MybetisPlusDemoApplication.class, args);
	}

}
