
##  spring boot 2.3.x can work with Spring cloud version Hoxton.SRX
``` bom.xml
<groupId>org.springframework.boot</groupId>
<artifactId>spring-boot-starter-parent</artifactId>
<version>2.3.3.RELEASE</version>
...
<java.version>1.8</java.version>
<spring-cloud.version>Hoxton.SR12</spring-cloud.version>
```

spring cloud load-balancer can fetch healthy service instance list.


##  spring boot 2.5.x can NOT work with Spring cloud version 2020.0.X
``` bom.xml
<groupId>org.springframework.boot</groupId>
<artifactId>spring-boot-starter-parent</artifactId>
<version>2.5.4</version>
...
<java.version>1.8</java.version>
<spring-cloud.version>2020.0.3</spring-cloud.version>
```

spring cloud load-balancer can not fetch service instance list 
or even fetch the unhealthy instance.

### fix : map your host.domain to 127.0.0.1 in System32/drivers/etc/hosts, such as
```
127.0.0.1  ESPNW0802.abchome.net (your host.domain name)
```
 consul try to register instance with host.domain format, 
and **ServiceInstanceListSupplier** try to resolve it back to IP, 
which can cause unknown IP.
 
 pom.xml
```xml 
	<dependency>
		<groupId>org.springframework.boot</groupId>
		<artifactId>spring-boot-starter-webflux</artifactId>
	</dependency>
	<dependency>
		<groupId>org.springframework.cloud</groupId>
		<artifactId>spring-cloud-starter-loadbalancer</artifactId>
	</dependency>
	<dependency>
		<groupId>com.github.ben-manes.caffeine</groupId>
		<artifactId>caffeine</artifactId>
		<version>2.9.2</version>
	</dependency>
```

Configure Load-Balance WebClient.Builder
```java
@LoadBalancerClient(value = "data-service-1", configuration = LoadBalancerConfiguration.class)
@Configuration
public class RestConfig {
    @LoadBalanced
    @Bean
    public RestTemplate loadbalancedRestTemplate() {
        return new RestTemplate();
    }
     // system health-Check
    @Primary
    @Bean
    WebClient.Builder webClientBuilder() {
        return WebClient.builder();
    }
     // business use
    @LoadBalanced
    @Bean("load-balance")
    WebClient.Builder webClientBuilder2() {
        return WebClient.builder();
    }
}
```

Instance List Supplier
```java
public class LoadBalancerConfiguration {
    @Bean
    public ServiceInstanceListSupplier supplier(ConfigurableApplicationContext context) {
        return ServiceInstanceListSupplier.builder()
                .withDiscoveryClient()   // use DiscoveryClient
                .withHealthChecks()      // do HealthCheck
                .withCaching()
                .build(context);
    }
}
```

application.yml

```yaml
    loadbalancer:
      health-check:
        refetch-instances: true
        refetch-instances-interval: 5s     # interval for refetch-instances
        repeat-health-check: true         # no need as instances will be refetched
        interval: 2s                      # Interval for HealthCheck
      ribbon:
        enabled: false
      cache:
        ttl: 20s
        enabled: true
```


