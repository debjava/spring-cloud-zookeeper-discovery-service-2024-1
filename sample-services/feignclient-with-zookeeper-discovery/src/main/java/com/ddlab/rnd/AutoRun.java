package com.ddlab.rnd;

//import com.ddlab.rnd.client.AnotherFeignClient;
import com.ddlab.rnd.client.AnotherFeignClient;
//import com.ddlab.rnd.client.HelloWorldClient;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.context.event.ApplicationReadyEvent;
import org.springframework.cloud.client.ServiceInstance;
import org.springframework.cloud.client.discovery.DiscoveryClient;
import org.springframework.context.event.EventListener;
import org.springframework.stereotype.Component;

import com.ddlab.rnd.client.SampleFeignClient;

import java.net.URI;
import java.util.List;

@Component
public class AutoRun {
	
	@Autowired
	private SampleFeignClient client;

	@Autowired
	private DiscoveryClient discoveryClient;

	@Autowired
	private AnotherFeignClient anotherClient;

	public String getServiceUrl() {
		List<ServiceInstance> list = discoveryClient.getInstances("spring-cloud-zookeeper-discovery-service");
		return !list.isEmpty() ? list.get(0).getUri().toString() : null;
	}

	public void invokeServiceUsingBaseURI() {
		URI baseURI = URI.create(getServiceUrl());
		String response = client.getHello(baseURI);
		System.out.println("Response: "+response);
	}

	public void invokeDirectService() {
		String response = anotherClient.getHelloFromService();
		System.out.println("Another client Response: "+response);
	}

	@EventListener(ApplicationReadyEvent.class)
	public void run() {
		System.out.println("App started running ...");
//		showServiceUrl();
		invokeServiceUsingBaseURI();
		invokeDirectService();
	}

}
