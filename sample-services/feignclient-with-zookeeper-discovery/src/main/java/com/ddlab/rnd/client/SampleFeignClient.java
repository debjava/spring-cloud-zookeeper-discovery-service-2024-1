package com.ddlab.rnd.client;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import java.net.URI;

@FeignClient(name = "SomeFeignClientName", url = "http://localhost:8080") // <== Dummy URL
public interface SampleFeignClient {

	@GetMapping("/hello")
	String getHello(URI baseUri);
}
