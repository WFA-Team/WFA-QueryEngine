package com.wfa.query;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

/*
 * This is just a simple application that will let you execute both
 * real-time and snapshot queries.
 * @author -> tortoiseDev
 */

@SpringBootApplication(scanBasePackages = {"com.wfa"})
public class QueryEngineApplication {
	public static void main(String[] args) {
		System.out.println("Starting Query Engine");
		SpringApplication.run(QueryEngineApplication.class, args);
	}
}
