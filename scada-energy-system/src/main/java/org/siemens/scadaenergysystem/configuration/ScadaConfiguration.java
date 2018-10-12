package org.siemens.scadaenergysystem.configuration;

import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;

import brave.sampler.Sampler;

@Configuration
@EnableAutoConfiguration
@ComponentScan({"org.siemens.scadaenergysystem"})
public class ScadaConfiguration {
	
	@Bean
	public Sampler defaultSampler(){
		return Sampler.ALWAYS_SAMPLE;
	}

}
