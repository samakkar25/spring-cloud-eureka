package com.baeldung.spring.cloud.spring_cloud_eureka_client;

import java.net.URI;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.ServiceInstance;
import org.springframework.cloud.client.discovery.DiscoveryClient;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.cloud.client.loadbalancer.LoadBalanced;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;
import org.springframework.context.annotation.Bean;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpMethod;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

//-----------We can use both @EnableEurekaClient OR @EnableDiscoveryClient---------

@SpringBootApplication
@EnableDiscoveryClient
@RestController
public class EurekaClient1 {

	
	 @Autowired
	 private DiscoveryClient discoveryClient;

	// @Autowired
	// private EurekaClient eurekaClient;

//	@Autowired
	private RestTemplate restTemplate = new RestTemplate();

	public static void main(String[] args) throws InterruptedException {
		SpringApplication.run(EurekaClient1.class, args);
	}

	@RequestMapping("/xyz")
	public String greeting1() {
		
		//-----calling service using rest template new keyword
		/*
		 To run this below url with local host, we need to initialize rest template
		 with new keyword
		 not with autowired
		 reason don't know
		*/
//		String uri1 = "http://localhost:8098/allstudentxml";
//		String response1 = restTemplate.exchange(uri1, HttpMethod.GET, 
//				null, new ParameterizedTypeReference<String>() {]}).getBody();
//		System.out.println(response1);
		
		//-----calling service using rest tamplete autowired
		/*
		 To run this below url with registered service name, we need to initialize
		 rest template with
		 autowired and not with new keyword
		 This is because rest template will gets initiliazed in the spring context and
		 from there other
		 objects will pick it. With new keu=yword it will not be identified
		 */
//		String uri2 = "http://student-service/studentjson";
//		String response2 = restTemplate.exchange(uri2, HttpMethod.GET, 
//				null, new ParameterizedTypeReference<String>() {}).getBody();
//		System.out.println(response2);
		
		
		//-----calling service using DiscoveryClient autowired object
		/*
		 * 
		 * Here I have one open question, the below is only working when 
		 * I am creating resttemplate object with new keyword
		 * Its not working with autowired
		 * Q: Why resttemplate can't work with autowired 
		 * 
		 */
		System.out.println(discoveryClient.getServices());
		ServiceInstance instance =  discoveryClient.getInstances("student-service").get(0);
		String uri3 = "http://" + instance.getHost() + ":" + instance.getPort() + "/studentjson";
		System.out.println(uri3);
		String response2 = restTemplate.exchange(uri3, HttpMethod.GET, 
				null, new ParameterizedTypeReference<String>() {}).getBody();
		System.out.println(response2);
		uri3 = instance.getUri() + "/studentjson";
		System.out.println(uri3);
		response2 = restTemplate.exchange(uri3, HttpMethod.GET, 
				null, new ParameterizedTypeReference<String>() {}).getBody();
		System.out.println(response2);
		
		return "Hello from EurekaClient!";
		
		
		
		
	}
	
	
	
	@Bean
	@LoadBalanced
	public RestTemplate getTemplate() {
		return new RestTemplate();
	}
}