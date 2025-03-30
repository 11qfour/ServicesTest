package com.example.service_test_task;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.openfeign.EnableFeignClients;
import org.springframework.context.annotation.ComponentScan;


@SpringBootApplication
@EnableFeignClients(basePackages = "com.example.service_test_task.Client")
public class ServiceTestTaskApplication {
	public static void main(String[] args) {
		SpringApplication.run(ServiceTestTaskApplication.class, args);
	}

}
