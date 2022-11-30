package it.cgmconsulting.sentence;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;

@EnableEurekaClient
@SpringBootApplication
public class SentenceApplication {

	public static void main(String[] args) {
		SpringApplication.run(SentenceApplication.class, args);
	}

}
