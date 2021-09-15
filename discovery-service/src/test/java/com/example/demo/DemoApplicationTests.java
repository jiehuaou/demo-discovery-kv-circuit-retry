package com.example.demo;

import static org.junit.jupiter.api.Assertions.assertTrue;

import com.example.demo.svc.Counter;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

//@TestPropertySource(properties = {"spring.cloud.consul.config.enabled=false"})
@SpringBootTest(webEnvironment= SpringBootTest.WebEnvironment.RANDOM_PORT)
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
