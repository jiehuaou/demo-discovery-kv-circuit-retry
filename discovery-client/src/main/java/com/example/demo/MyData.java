package com.example.demo;

import javax.annotation.PostConstruct;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.cloud.context.config.annotation.RefreshScope;
import org.springframework.context.annotation.Configuration;


/**
 * consul config :
 * 
 * config/data-service-1/aws
 *       user : xxx
 *       password : yyyy
 *       sample : zzz
 */

@RefreshScope
@Configuration
@ConfigurationProperties("aws")
public class MyData {

	private String user = "hello-abc";
    private String password = "undefined";
    private String sample ;
    
    @PostConstruct
    public void postConstruct() {
        // to validate if properties are loaded
        System.out.println("** value user : " + user);
        System.out.println("** value password : " + password);
        System.out.println("** sample : " + sample);
    }

    public String getUser() {
        return user;
    }

    public void setUser(String user) {
        this.user = user;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getSample() {
        return sample;
    }

    public void setSample(String sample) {
        this.sample = sample;
    }
}