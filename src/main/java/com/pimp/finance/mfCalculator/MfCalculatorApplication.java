package com.pimp.finance.mfCalculator;

import com.pimp.finance.mfCalculator.resource.MFResource;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

@SpringBootApplication
public class MfCalculatorApplication {

	public static void main(String[] args) {
		SpringApplication.run(MfCalculatorApplication.class, args);
	}

	@Bean
	public CommandLineRunner demo(MFResource resource){
		return (args -> System.out.println(resource.retrieveSummary("Rajiv Sawhney")));
	}

}
