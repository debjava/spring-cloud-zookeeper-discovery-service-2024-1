package com.ddlab.rnd.client;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;


// Below is the name of the application to be invoked by Feign Client
@FeignClient(name = "spring-cloud-zookeeper-discovery-service")
public interface AnotherFeignClient {

    @GetMapping("/hello")
    String getHelloFromService();
}
