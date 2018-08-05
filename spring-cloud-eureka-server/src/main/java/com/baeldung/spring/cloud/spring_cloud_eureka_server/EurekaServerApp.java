package com.baeldung.spring.cloud.spring_cloud_eureka_server;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.server.EnableEurekaServer;

import com.netflix.appinfo.InstanceInfo;
import com.netflix.discovery.EurekaClient;
import com.netflix.discovery.shared.Application;

/**
 * Hello world!
 *
 */
@SpringBootApplication
@EnableEurekaServer
public class EurekaServerApp {
	
    @Autowired
    private static EurekaClient eurekaClient;
    
    public static void doRequest() {
    	
    	if(eurekaClient==null) {
    		System.out.println("null");
    		return;
    	}
    	
        Application application = eurekaClient.getApplication("student-service");
        InstanceInfo instanceInfo = application.getInstances().get(0);
        String hostname = instanceInfo.getHostName();
        int port = instanceInfo.getPort();
        System.out.println(hostname);
        System.out.println(port);
        
    }
    
    
	public static void main(String[] args) {
		SpringApplication.run(EurekaServerApp.class, args);
		doRequest();
	}
}
