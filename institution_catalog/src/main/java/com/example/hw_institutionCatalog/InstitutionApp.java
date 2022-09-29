package com.example.hw_institutionCatalog;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.amqp.core.Queue;
import org.springframework.amqp.support.converter.Jackson2JsonMessageConverter;
import org.springframework.amqp.support.converter.MessageConverter;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.openfeign.EnableFeignClients;
import org.springframework.context.annotation.Bean;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

@SpringBootApplication
@EnableJpaRepositories(basePackages = "com.example.hw_institutionCatalog.repository")
@EnableFeignClients
public class InstitutionApp {
	@Bean
	ObjectMapper objectMapper() {
		ObjectMapper objectMapper = new ObjectMapper();
		objectMapper.findAndRegisterModules();
		return objectMapper;
	}

	public static void main(String[] args) {
		SpringApplication.run(InstitutionApp.class, args);
	}
	@Bean("myQueue")
	public Queue myQueue() {
		return new Queue("myQueue", false);
	}
	@Bean("changeOwnerQueue")
	public Queue changeOwnerQueue() {
		return new Queue("changeOwnerQueue", false);
	}
	@Bean("deleteOwnerQueue")
	public Queue deleteOwnerQueue() {
		return new Queue("deleteOwnerQueue", false);
	}

	@Bean
	public MessageConverter jsonMessageConverter() {
		return new Jackson2JsonMessageConverter();
	}
}
