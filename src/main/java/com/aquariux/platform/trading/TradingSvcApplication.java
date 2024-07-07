package com.aquariux.platform.trading;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.openfeign.EnableFeignClients;
import org.springframework.scheduling.annotation.EnableScheduling;

@SpringBootApplication
@EnableScheduling
@EnableFeignClients
public class TradingSvcApplication {

	public static void main(String[] args) {
		SpringApplication.run(TradingSvcApplication.class, args);
	}

}
