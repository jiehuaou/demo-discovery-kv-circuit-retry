package com.example.demo;

import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.TestPropertySource;
import org.springframework.test.context.junit4.SpringRunner;

//@TestPropertySource(properties = {"spring.cloud.consul.config.enabled=false"})
@SpringBootTest
//@RunWith(SpringRunner.class)
public class DemoApplicationTests {
	@Autowired
	Counter c ;

	@Test
	void testRandom() {
		for (int i = 0; i < 22; i++) {
			System.out.printf("%d = %s \n" , i, c.countAndSuccess());
		}

	}

}
