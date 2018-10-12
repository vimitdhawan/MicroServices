package org.siemens.scadanamingserver;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.server.EnableEurekaServer;

@SpringBootApplication
@EnableEurekaServer
public class ScadaNamingServerApplication {

	public static void main(String[] args) {
		SpringApplication.run(ScadaNamingServerApplication.class, args);
	}
}
