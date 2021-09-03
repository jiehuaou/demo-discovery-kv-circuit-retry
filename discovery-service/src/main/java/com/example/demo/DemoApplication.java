package com.example.demo;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.cloud.context.config.annotation.RefreshScope;
import org.springframework.context.annotation.Configuration;
import org.springframework.stereotype.Controller;

import java.util.concurrent.atomic.AtomicInteger;

@RefreshScope
@Configuration
@EnableAutoConfiguration
@EnableDiscoveryClient
@RestController
@SpringBootApplication
@Controller
public class DemoApplication {

	@Autowired
	Counter counter;

	@Autowired
	MyData data ;

	@Value("${label}")
	String label = "undefine";

	//
	String value = "undefined";
	
	public static void main(String[] args) {
		SpringApplication.run(DemoApplication.class, args);
	}

	@RequestMapping("/hello")
	public String hello() {
	  return "Hello "+ value;
	}

	@RequestMapping("/id")
	public ResponseEntity<String> myId() {
		String result = String.format("/id: %s : %s (%d)" , data.getUser() , label , counter.getCounter()) ;
		counter.countAndSuccess();

		if(!label.startsWith("error") && !label.startsWith("bad")){
			System.out.println("query /id --------> " + result + " .. ok");
			return ResponseEntity.ok().body(result);
		}

		if(counter.isSuccessState()){
			System.out.println("query /id --------> " + result + " .. ok");
			return ResponseEntity.ok().body(result);
		}

		System.out.println("query /id --------> " + result + " .. failed");
		return ResponseEntity.status(500).body("something wrong");
	}

	@RequestMapping("/404")
	public ResponseEntity<String> svc404() {
		String psw = data.getPassword();
		return ResponseEntity.status(404).body("this is bad request");
	}

	@RequestMapping("/psw")
	public String myPassword() {
		String psw = data.getPassword();
	  	return "psw is "+ psw;
	}

	@RequestMapping("/custom-health-check")
	public String healthCheck() {
		// System.out.println("health-check " + System.currentTimeMillis());
		return "OK";
	}
}
