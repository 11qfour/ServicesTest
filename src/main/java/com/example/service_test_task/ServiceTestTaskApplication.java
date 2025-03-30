package com.example.service_test_task;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.openfeign.EnableFeignClients;

@EnableFeignClients
@SpringBootApplication
public class ServiceTestTaskApplication {

	public static void main(String[] args) {
		SpringApplication.run(ServiceTestTaskApplication.class, args);
	}

}
