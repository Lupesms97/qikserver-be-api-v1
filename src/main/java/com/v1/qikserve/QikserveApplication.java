package com.v1.qikserve;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.openfeign.EnableFeignClients;

@SpringBootApplication
@EnableFeignClients
public class QikserveApplication {

	public static void main(String[] args) {
		SpringApplication.run(QikserveApplication.class, args);
	}

}
