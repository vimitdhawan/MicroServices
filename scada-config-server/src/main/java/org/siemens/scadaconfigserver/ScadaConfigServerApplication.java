package org.siemens.scadaconfigserver;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.config.server.EnableConfigServer;

@EnableConfigServer
@SpringBootApplication
public class ScadaConfigServerApplication {

	public static void main(String[] args) {
		SpringApplication.run(ScadaConfigServerApplication.class, args);
	}
}
