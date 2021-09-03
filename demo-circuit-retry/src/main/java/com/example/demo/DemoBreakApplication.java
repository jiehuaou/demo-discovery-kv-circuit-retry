package com.example.demo;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.cloud.context.config.annotation.RefreshScope;
import org.springframework.context.annotation.Configuration;


@RefreshScope
@Configuration
// Discovery Client
@EnableDiscoveryClient
@SpringBootApplication
public class DemoBreakApplication {

	public static void main(String[] args) {
		SpringApplication.run(DemoBreakApplication.class, args);
	}

}
