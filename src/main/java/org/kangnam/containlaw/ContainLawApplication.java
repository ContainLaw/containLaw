package org.kangnam.containlaw;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.jdbc.DataSourceAutoConfiguration;
import org.springframework.scheduling.annotation.EnableScheduling;

@SpringBootApplication(exclude = {DataSourceAutoConfiguration.class}) // DB 비활성화
//@SpringBootApplication
@EnableScheduling
public class ContainLawApplication {

	public static void main(String[] args) {
		SpringApplication.run(ContainLawApplication.class, args);
	}
}
