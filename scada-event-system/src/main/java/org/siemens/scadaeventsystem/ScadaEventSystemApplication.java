package org.siemens.scadaeventsystem;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.cloud.openfeign.EnableFeignClients;

@SpringBootApplication
@EnableFeignClients("org.siemens.scadaeventsystem")
@EnableDiscoveryClient
public class ScadaEventSystemApplication {

	public static void main(String[] args) {
		SpringApplication.run(ScadaEventSystemApplication.class, args);
	}
}
