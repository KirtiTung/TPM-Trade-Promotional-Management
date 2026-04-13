package com.kirti.tpm;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.openfeign.EnableFeignClients;

@SpringBootApplication
@EnableFeignClients
public class TpmBackendApplication {

	public static void main(String[] args) {
		SpringApplication.run(TpmBackendApplication.class, args);
	}

}
