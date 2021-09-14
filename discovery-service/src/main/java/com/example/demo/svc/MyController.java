package com.example.demo.svc;

import java.net.URI;
import java.util.List;
import java.util.Random;

import javax.annotation.PostConstruct;
import javax.naming.ServiceUnavailableException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.cloud.client.ServiceInstance;
import org.springframework.cloud.client.discovery.DiscoveryClient;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.cloud.context.config.annotation.RefreshScope;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;


//@RefreshScope
//@Configuration
//@EnableAutoConfiguration
//@EnableDiscoveryClient

@RestController
public class MyController {
    @Autowired
    Counter counter;

    @Autowired
    MyData data ;

    @Value("${label}")
    String label = "undefine";

    @Value("${server.port}")
    String port  = "undefine";

    @PostConstruct
    public void postConstruct() {
        System.out.println("      ");
        System.out.println("***   label : " + label);
        System.out.println("***   port  : " + port);
        System.out.println("      ");
    }

    //
    String value = "undefined";
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