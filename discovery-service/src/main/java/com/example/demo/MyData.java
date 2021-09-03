package com.example.demo;

import javax.annotation.PostConstruct;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.cloud.context.config.annotation.RefreshScope;
import org.springframework.context.annotation.Configuration;


/**
 * load property from consul kV folder :
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

    private int successCount = 10;
    private int failCount = 10;
    
    @PostConstruct
    public void postConstruct() {
        // to validate if properties are loaded
        System.out.println("** user : " + user);
        System.out.println("** password : " + password);
        System.out.println("** sample : " + sample);
        System.out.println("** successCount : " + successCount);
        System.out.println("** failCount : " + failCount);
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

    public int getSuccessCount() {
        return successCount;
    }

    public void setSuccessCount(int successCount) {
        this.successCount = successCount;
    }

    public int getFailCount() {
        return failCount;
    }

    public void setFailCount(int failCount) {
        this.failCount = failCount;
    }
}