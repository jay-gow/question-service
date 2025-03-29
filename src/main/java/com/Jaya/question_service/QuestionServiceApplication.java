package com.Jaya.question_service;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication(scanBasePackages = "com.jaya.question_service")

public class QuestionServiceApplication 
{
	public static void main(String[] args) {
		SpringApplication.run(QuestionServiceApplication.class, args);
	}

}
